package example.Josh.subsystems;

import ev3dev.sensors.ev3.*;
import example.Josh.util.IPeriodicSubsystem;
import example.Josh.util.alg.PIDController;
import example.Josh.util.sensors.*;
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

    PIDController lineFollower = new PIDController(3, 0, 0.5);
    public void followLine()
    {
        int output = (int) -lineFollower.calculate(leftColorSensor.getAmbience() - rightColorSensor.getAmbience(), 0);

        arcadeDrive((int) (getMaxSpeedDegreesPerSecond() * 0.15), output);
    }
}
