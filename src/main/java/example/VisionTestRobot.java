package example;

import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import example.josh.util.sensors.*;

public class VisionTestRobot extends TimerTask
{
    Vision viz = new Vision();

    public static Logger LOGGER = LoggerFactory.getLogger(Robot.class);

    /**
     * Gets executed once during initialization.
     */
    public void init()
    {
        viz.init();
    }

    /**
     * Gets executed periodically.
     */
    public void run()
    {
        viz.updatePeriodic();

        LOGGER.info(String.format("tv: %b tx: %2.2f ty: %2.2f", viz.getV(), viz.getX(), viz.getY()));
    }
}
