package example.josh.subsystems;

import ev3dev.sensors.ev3.*;
import example.josh.util.IPeriodicSubsystem;
import example.josh.util.alg.PIDFController;
import example.josh.util.sensors.*;
import lejos.hardware.port.SensorPort;

public class SensorDrivetrain extends Drivetrain implements IPeriodicSubsystem
{
    ColorSensorRedMode leftColorSensor = new ColorSensorRedMode(new EV3ColorSensor(SensorPort.S1));
    ColorSensorRedMode rightColorSensor = new ColorSensorRedMode(new EV3ColorSensor(SensorPort.S4));
    Gyro gyro = new Gyro(new EV3GyroSensor(SensorPort.S3));
    // ir is busy being used by remote control
    
    public SensorDrivetrain()
    {
        super();
    }

    @Override
    public void updatePeriodic()
    {
        leftColorSensor.updatePeriodic();
        rightColorSensor.updatePeriodic();
        gyro.updatePeriodic();
    }

    PIDFController lineFollower = new PIDFController(3, 0, 0.5, 0);
    public void followLine()
    {
        int output = (int) -lineFollower.calculate(leftColorSensor.getAmbience() - rightColorSensor.getAmbience(), 0);

        arcadeDrive((int) (getMaxSpeedDegreesPerSecond() * 0.15), output);
    }
}
