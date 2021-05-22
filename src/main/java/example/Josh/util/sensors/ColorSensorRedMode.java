package example.Josh.util.sensors;

import org.slf4j.LoggerFactory;

import ev3dev.sensors.ev3.EV3ColorSensor;
import example.Josh.util.IPeriodicSubsystem;
import lejos.robotics.SampleProvider;

public class ColorSensorRedMode implements IPeriodicSubsystem
{
    EV3ColorSensor colorSensor;
    SampleProvider sp;
    
    float[] sample;
    
    public ColorSensorRedMode(EV3ColorSensor colorSensor)
    {
        this.colorSensor = colorSensor;

        sp = colorSensor.getRedMode();
        
        sample = new float[sp.sampleSize()];
    }

    @Override
    public void updatePeriodic()
    {
        sp.fetchSample(sample, 0);
        // final var logger = LoggerFactory.getLogger(ColorSensorRedMode.class);
        // logger.info("Ambience: " + getAmbience());
    }

    public int getAmbience()
    {
        return (int) sample[0];
    }
}
