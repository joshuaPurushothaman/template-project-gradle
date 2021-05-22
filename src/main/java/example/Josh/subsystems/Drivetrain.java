package example.Josh.subsystems;

import ev3dev.actuators.lego.motors.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class Drivetrain
{
    EV3LargeRegulatedMotor lMotor;
    EV3LargeRegulatedMotor rMotor;
    
    public Drivetrain()
    {
        lMotor = new EV3LargeRegulatedMotor(MotorPort.B);
        rMotor = new EV3LargeRegulatedMotor(MotorPort.C);

        //To Stop the motor in case of pkill java for example
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                System.out.println("Emergency Stop");
                lMotor.flt(true);
                rMotor.flt(true);
            }
        }));

        lMotor.suspendRegulation();
        rMotor.suspendRegulation();
    }

    /**
     * Tank Drive.
     * @param leftSpeed speed of left wheel in degrees per second
     * @param rightSpeed speed of right wheel in degrees per second
     */
    public void tankDrive(int leftSpeed, int rightSpeed)
    {
        lMotor.setSpeed(-leftSpeed);
        rMotor.setSpeed(-rightSpeed);
    }

    /**
     * Arcade Drive.
     * @param fdSpeed degrees per second
     * @param turnSpeed clockwise positive, degrees per second
     */
    public void arcadeDrive(int fdSpeed, int turnSpeed)
    {
        tankDrive(fdSpeed + turnSpeed, fdSpeed - turnSpeed);
    }

    public void brake()
    {
        lMotor.brake();
        rMotor.brake();
    }

    public double getMaxSpeedDegreesPerSecond()
    {
        return lMotor.getMaxSpeed();
    }
}
