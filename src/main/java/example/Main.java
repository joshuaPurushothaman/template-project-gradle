package example;

import java.util.Timer;

public class Main
{
    public static void main(final String[] args)
    {
        Robot robot = new Robot();
        // VisionTestRobot robot = new VisionTestRobot();

        robot.init();

        Timer timer = new Timer();
        
        final long periodMs = 10;
        
        timer.scheduleAtFixedRate(robot, 0, periodMs);
    }
}