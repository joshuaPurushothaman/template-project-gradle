package example;

import example.Josh.subsystems.Drivetrain;

public class Robot
{
    Drivetrain dt = new Drivetrain();

    /**
     * Gets executed once during initialization.
     */
    public void init()
    {

    }

    /**
     * Gets executed periodically
     * @return whether to break or not
     */
    public boolean periodic()
    {
        dt.arcadeDrive(dt.getMaxSpeedDegreesPerSecond(), 0);
        
        return false;
    }
}
