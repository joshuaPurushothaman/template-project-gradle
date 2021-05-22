package example.Josh.subsystems;

import org.slf4j.LoggerFactory;

import example.Josh.util.alg.PIDController;
import example.Josh.util.geometry.Pose2d;

public class OdomDrivetrain extends SensorDrivetrain
{
    public static final double WHEEL_DIAMETER_METERS = 0.0811;
    public static final double WHEEL_CIRCUMFERENCE_METERS = WHEEL_DIAMETER_METERS * Math.PI;
    public static final double TRACK_WIDTH_METERS = 0.1445;

    Pose2d pose = new Pose2d(0, 0, 0);

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

        pose.x = distance * Math.sin(thetaRadians); //  y is right
        pose.y = distance * Math.cos(thetaRadians); //  x is forward
        pose.theta = Math.toDegrees(thetaRadians) % 360;
    }

    public Pose2d getPosition()
    {
        return pose;
    }

    public void resetPosition()
    {
        pose.reset();
        gyro.reset();
        lMotor.resetTachoCount();
        rMotor.resetTachoCount();
    }

    @Override
    public String toString()
    {
        return String.format("%3.2f   %3.2f   %3.2f", pose.x, pose.y, pose.theta);
    }



    


    PIDController distController = new PIDController(400, 4, 1000);  //  TODO: tune
    public void driveToDistance(double distanceMeters)
    {
        int output = (int) distController.calculate(pose.y, distanceMeters);

        final var logger = LoggerFactory.getLogger(OdomDrivetrain.class);
        logger.info("Output: ", output);

        arcadeDrive(output, 0);
    }

    PIDController angleController = new PIDController(1, 0, 0);   //  TODO: tune
    public void turnToAngle(double angleDegrees)
    {
        // double pv = gyro.getAngleDegrees();
        double pv = pose.theta;

        arcadeDrive(0, (int) angleController.calculate(angleDegrees, pv));
    }
}
