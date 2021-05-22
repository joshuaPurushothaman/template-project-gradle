package example;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ev3dev.sensors.ev3.EV3IRSensor;
import example.Josh.subsystems.*;
import example.Josh.util.sensors.*;
import example.programming.SimpleWebServer;
import lejos.hardware.port.SensorPort;

public class Robot
{
    OdomDrivetrain dt;
    Remote remote = new Remote(new EV3IRSensor(SensorPort.S2));
    SimpleWebServer webServer;
    
    public static Logger LOGGER = LoggerFactory.getLogger(Robot.class);

    /**
     * Gets executed once during initialization.
     */
    public void init()
    {
        dt = new OdomDrivetrain();  //  initialized here to not use static initialization and have Sysfs errors

        // try
        // {
        //     webServer = new SimpleWebServer();
        // }
        // catch (IOException e) 
        // { 
        //     LOGGER.error("Error creating webserver: " + e.toString());
        // }
    }

    /**
     * Gets executed periodically
     * @return whether to continue or not
     */
    public boolean periodic()
    {
        dt.updatePeriodic();
        remote.updatePeriodic();

        double speedMult = 0.25;

        // dt.tankDrive
        // (
        //     (int) (dt.getMaxSpeedDegreesPerSecond() * remote.getLeft() * speedMult),
        //     (int) (dt.getMaxSpeedDegreesPerSecond() * remote.getRight() * speedMult)
        // );
        // dt.followLine();

        dt.driveToDistance(0.7);
        
        LOGGER.info(dt.toString());

        // try {
        //     webServer.run();
        // } catch (IOException e) {
        //     LOGGER.error(e.toString());
        // }

        // webServer.execute("hello");

        return true;
    }
}
