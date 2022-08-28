import org.firmata4j.I2CDevice;
import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;
import org.firmata4j.ssd1306.SSD1306;
import java.io.IOException;
import java.util.Timer;

public class MinorProject {
    static final int A1 = 15;//Moisture sensor
    static final byte I2C0 = 0x3C;//    OLED    Display
    public static void main(String[] args)
            throws InterruptedException, IOException
    {
        var mygrvboard = new FirmataDevice("COM3");
        mygrvboard.start();
        mygrvboard.ensureInitializationIsDone();

        var Moistval = mygrvboard.getPin(15);
        double Moisval = Moistval.getValue();
        I2CDevice i2cObject = mygrvboard.getI2CDevice((byte) 0x3C);
        SSD1306 theOledObject = new SSD1306(i2cObject, SSD1306.Size.SSD1306_128_64);
        theOledObject.init();

        var watPump = mygrvboard.getPin(2);
        watPump.setMode(Pin.Mode.OUTPUT);
        var task = new WateringTask(theOledObject,Moistval,watPump);
        new Timer().schedule(task, 0, 1000);

        mygrvboard.start(); // start comms with board;

        // Starting pump
            try {
                if (Moisval >= 520){  // If the soil is dry turn on pump
                watPump.setValue(1);
                }
                else {
                    watPump.setValue(0);
                }
            } catch (Exception ex) {
                System.out.println("Pump error.");
            }
            watPump.setValue(0);
    }
}