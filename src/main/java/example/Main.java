package example;

import lejos.utility.Delay;

public class Main
{
    public static void main(final String[] args)
    {
        Robot robot = new Robot();

        robot.init();

        while (robot.periodic())
            Delay.msDelay(50);
        
        System.exit(0);
    }
}