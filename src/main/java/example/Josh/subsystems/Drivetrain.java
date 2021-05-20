package example.Josh.subsystems;

import ev3dev.actuators.lego.motors.EV3LargeRegulatedMotor;
import ev3dev.sensors.Battery;
import ev3dev.sensors.ev3.EV3GyroSensor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;

public class Drivetrain
{
    EV3LargeRegulatedMotor lMotor = new EV3LargeRegulatedMotor(MotorPort.B);
    EV3LargeRegulatedMotor rMotor = new EV3LargeRegulatedMotor(MotorPort.C);
    EV3GyroSensor gyro = new EV3GyroSensor(SensorPort.S3);
    
    public Drivetrain()
    {
        //To Stop the motor in case of pkill java for example
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                System.out.println("Emergency Stop");
                lMotor.stop();
                rMotor.stop();
            }
        }));
    }

    //#region basic driving
    /**
     * Tank Drive.
     * @param leftSpeed speed of left wheel in degrees per second
     * @param rightSpeed speed of right wheel in degrees per second
     */
    public void tankDrive(int leftSpeed, int rightSpeed)
    {
        lMotor.setSpeed(leftSpeed);
        rMotor.setSpeed(rightSpeed);
    }

    /**
     * Arcade Drive.
     * @param fdSpeed degrees per second
     * @param turnSpeed clockwise positive, degrees per second
     */
    public void arcadeDrive(int fdSpeed, int turnSpeed)
    {
        tankDrive(fdSpeed - turnSpeed, fdSpeed + turnSpeed);
    }

    public void brake()
    {
        lMotor.brake();
        rMotor.brake();
    }
    //#endregion

    public int getMaxSpeedDegreesPerSecond()
    {
        // The max sustainable speed is 100 * voltage
        return (int) (100 *  Battery.getInstance().getVoltage());
    }
}
