import    org.firmata4j.Pin;
import    org.firmata4j.ssd1306.SSD1306;
import    java.util.TimerTask;
public class WateringTask extends TimerTask{
    private    final    SSD1306    display;
    private    final    Pin    pin;
    private    final    Pin wpump;

    public WateringTask(SSD1306 display,Pin pin, Pin wpump){
        this.display = display;
        this.pin = pin;
        this.wpump = wpump;
    }
    @Override
    public void run(){
        String value = String.valueOf(pin.getValue());
        System.out.println("Moisture value "+value);
        display.getCanvas().setTextsize(1);
        display.getCanvas().drawString(0,0,"Moisture value " + value);

        String Vale;
        if (wpump.getValue()==1)
        {
            Vale = "ON";
        }
        else
        {
            Vale = "OFF";
        }

        display.getCanvas().drawString(0,15,"Pump is: " + Vale);
        display.display();
        display.getCanvas().clear();

        if (pin.getValue() >= 520) {
            try {
                wpump.setValue(1);

            } catch (Exception ex) {
                System.out.println("not working");
            }
            System.out.println("Pump ON");
        }
        else{
            try {
                wpump.setValue(0);

            } catch (Exception ex) {
                System.out.println("not working");
            }
            System.out.println("Pump OFF");
        }
    }
}