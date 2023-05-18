
import org.firmata4j.I2CDevice;
import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;
import org.firmata4j.ssd1306.SSD1306;
import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;
import java.util.GregorianCalendar;


public class Main {
    static final int A1 = 15;//Moisture sensor
    static final byte I2cconnection = 0x3C;//    OLED    Display
    static  boolean isSummer;//the boolean variable which controls the actions in GUI
    private double lightreading;

    public Main() throws IOException, InterruptedException {
        createfirstTime();
    }
    Pin electrochromicglass;



    public void createfirstTime() throws InterruptedException, IOException {
        isSummer = false;
        var mygrboard = new FirmataDevice("COM3");
        mygrboard.start();
        mygrboard.ensureInitializationIsDone();


        var lightsens = mygrboard.getPin(15);
        this.lightreading = lightsens.getValue();
        //OLED
        I2CDevice i2cObject = mygrboard.getI2CDevice((byte) 0x3C);
        SSD1306 theOledObject = new SSD1306(i2cObject, SSD1306.Size.SSD1306_128_64);
        theOledObject.init();

        this.electrochromicglass = mygrboard.getPin(2);
        electrochromicglass.setMode(Pin.Mode.OUTPUT);
        var task = new colorchangingtask(theOledObject, lightsens, electrochromicglass);
        new Timer().schedule(task, 0, 1000);
        Calendar c = new GregorianCalendar();
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_YEAR);
    }
    public void checkWork() throws IOException {
        if (isSummer) {
            if ((lightreading >= 500)) {
                electrochromicglass.setValue(1);

            } else {
                electrochromicglass.setValue(0);
            }
        } else {
            electrochromicglass.setValue(0);
        }

    }

}



