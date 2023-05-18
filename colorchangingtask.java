package EECS1021;

import org.firmata4j.Pin;
import org.firmata4j.ssd1306.SSD1306;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimerTask;
import static EECS1021.Main.isSummer;

public class colorchangingtask extends TimerTask{
    private    final SSD1306 display;
    private    final Pin pin;
    private    final    Pin electrochromicglss;

    public colorchangingtask(SSD1306 display,Pin pin, Pin electrochromicglss){
        this.display = display;
        this.pin = pin;
        this.electrochromicglss = electrochromicglss;
    }
    @Override
    public void run(){
        String value = String.valueOf(pin.getValue());
        System.out.println("lightsensor value "+value);
        display.getCanvas().setTextsize(1);
        display.getCanvas().drawString(0,0,"lightsensor value " + value);

        String status;
        if (electrochromicglss.getValue()==1)
        {
            status = "dark";
        }
        else
        {
            status = "tranparent";
        }

        display.getCanvas().drawString(0,15,"glass is  " + status);
        display.display();
        display.getCanvas().clear();
        Calendar c = new GregorianCalendar();
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_YEAR);

  if ((pin.getValue() >= 500)&&(isSummer)){

            try {

                {
                    electrochromicglss.setValue(1);

                }



            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                electrochromicglss.setValue(0);

            } catch (Exception ex) {
                System.out.println("glass is broken");
            }
        }}};
