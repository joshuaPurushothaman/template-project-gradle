package example.josh.subsystems;

import ev3dev.actuators.lego.motors.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class Drivetrain
{
    EV3LargeRegulatedMotor lMotor;
    EV3LargeRegulatedMotor rMotor;
    
    double leftOutput, rightOutput;

    public Drivetrain()
    {
        boolean hasMotorCreationError = false;
        do {
            try {
                lMotor = new EV3LargeRegulatedMotor(MotorPort.B);
                rMotor = new EV3LargeRegulatedMotor(MotorPort.C);
                
                hasMotorCreationError = false;
            } catch (Exception e) {
                hasMotorCreationError = true;
            }
        } while (hasMotorCreationError);

        //To Stop the motor in case of pkill java for example
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                System.out.println("Emergency Stop");
                lMotor.flt(true);
                rMotor.flt(true);
            }
        }));
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
        lMotor.forward();
        rMotor.forward();
        
        leftOutput = leftSpeed;
        rightOutput = rightSpeed;
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
        lMotor.stop();
        lMotor.stop();
    }

    public double getMaxSpeedDegreesPerSecond()
    {
        return lMotor.getMaxSpeed();
    }

    @Override
    public String toString()
    {
        return String.format("Left: %3.2f, Right: %3.2f", leftOutput, rightOutput);
    }
}
