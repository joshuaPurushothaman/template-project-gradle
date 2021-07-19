package example.josh.subsystems;

import example.josh.util.alg.PIDFController;
import lejos.robotics.navigation.Pose;

public class OdomDrivetrain extends SensorDrivetrain
{
    public static final double WHEEL_DIAMETER_METERS = 0.102;
    public static final double WHEEL_CIRCUMFERENCE_METERS = WHEEL_DIAMETER_METERS * Math.PI;
    public static final double TRACK_WIDTH_METERS = 0.125;

    Pose pose = new Pose();

    public OdomDrivetrain()
    {
        super();
        resetPosition();
    }

    @Override
    public void updatePeriodic()
    {
        super.updatePeriodic();
        updatePosition();
    }

    private void updatePosition()
    {
        // # calculate how far each side has gone
        double lRotMeters = ((double) -lMotor.getTachoCount()/360.0) * WHEEL_CIRCUMFERENCE_METERS;
        double rRotMeters = ((double) -rMotor.getTachoCount()/360.0) * WHEEL_CIRCUMFERENCE_METERS;

        double distance = (lRotMeters + rRotMeters) / 2.0;
        // pose.theta = gyro.getAngleDegrees();
        double thetaRadians = (lRotMeters - rRotMeters) / TRACK_WIDTH_METERS;

        // pose.x = distance * Math.sin(thetaRadians); //  y is right
        // pose.y = distance * Math.cos(thetaRadians); //  x is forward
        // pose.theta = Math.toDegrees(thetaRadians) % 360;

        pose.setLocation((float) (distance * Math.cos(thetaRadians)),
            (float) (distance * Math.sin(thetaRadians)));
        
        pose.setHeading((float) Math.toDegrees(thetaRadians));
    }

    public Pose getPosition()
    {
        return pose;
    }

    public void resetPosition()
    {
        pose = new Pose();
        resetSensors();
    }

    public void resetSensors()
    {
        gyro.reset();
        lMotor.resetTachoCount();
        rMotor.resetTachoCount();
    }

    @Override
    public String toString()
    {
        return super.toString() + String.format("\t%3.2f   %3.2f   %3.2f", pose.getX(), pose.getY(), pose.getHeading());
    }



    PIDFController distController = new PIDFController(1000, 10, 0, 0,
        -getMaxSpeedDegreesPerSecond(), getMaxSpeedDegreesPerSecond(), 0.03, 0.01);
    public boolean driveToDistance(double distanceMeters)
    {
        int output = (int) distController.calculate(distanceMeters, pose.getX());
        boolean atSetpoint = distController.atSetpoint();
        
        if (!atSetpoint) arcadeDrive(output, 0);
        else brake();
        
        return atSetpoint;
    }

    PIDFController angleController = new PIDFController(2, 0.02, 0, 0,
        -getMaxSpeedDegreesPerSecond(), getMaxSpeedDegreesPerSecond(), 2, 1);   //  TODO: tune
    public boolean turnToAngle(double angleDegrees)
    {
        // double pv = gyro.getAngleDegrees();
        double pv = pose.getHeading();
        int output = (int) angleController.calculate(angleDegrees, pv);
        boolean atSetpoint = angleController.atSetpoint();

        if (!atSetpoint) arcadeDrive(0, output);
        else brake();
        
        return atSetpoint;
    }
}
