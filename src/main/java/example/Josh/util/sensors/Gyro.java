package example.josh.util.sensors;

import ev3dev.sensors.ev3.EV3GyroSensor;
import example.josh.util.IPeriodicSubsystem;
import lejos.robotics.SampleProvider;

public class Gyro implements IPeriodicSubsystem
{
    SampleProvider sp;
    EV3GyroSensor gyro;

    double angleDegrees;

    public Gyro(EV3GyroSensor gyro)
    {
        this.gyro = gyro;
        
        gyro.reset();
        
        sp = gyro.getAngleMode();
    }

    @Override
    public void updatePeriodic()
    {
            float [] sample = new float[sp.sampleSize()];
            sp.fetchSample(sample, 0);
            angleDegrees = (int)sample[0];
    }
    
    public double getAngleDegrees()
    {
        return angleDegrees;
    }

    public void reset()
    {
        gyro.reset();
    }
}
