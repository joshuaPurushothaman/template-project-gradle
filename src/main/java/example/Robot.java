package example;

import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ev3dev.sensors.ev3.EV3IRSensor;
import example.josh.subsystems.*;
import example.josh.util.sensors.*;
import lejos.hardware.port.SensorPort;

public class Robot extends TimerTask
{
    OdomDrivetrain dt = new OdomDrivetrain();
    Remote remote = new Remote(new EV3IRSensor(SensorPort.S2));
    CommandExecutor auto = new CommandExecutor(dt);

    public static Logger LOGGER = LoggerFactory.getLogger(Robot.class);

    /**
     * Gets executed once during initialization.
     */
    public void init()
    {
        auto.init(auto::selectSquarePath);
    }

    /**
     * Gets executed periodically.
     */
    public void run()
    {
        dt.updatePeriodic();
        remote.updatePeriodic();

        // double speedMult = 0.4;
        // dt.tankDrive
        // (
        //     (int) (dt.getMaxSpeedDegreesPerSecond() * remote.getLeft() * speedMult),
        //     (int) (dt.getMaxSpeedDegreesPerSecond() * remote.getRight() * speedMult)
        // );

        // dt.followLine();

        // dt.driveToDistance2(0.5);
        // dt.turnToAngle(90);
        auto.periodic();

        LOGGER.info(dt.toString());
    }
}
