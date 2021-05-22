package example.Josh.util.geometry;

/**
 * Represents a two-dimensional translation and rotation.
 * Same conventions as Pose2d.
 */
public class Transform2d
{
    public double x, y, theta;

    public Transform2d(double x, double y, double theta)
    {
        this.x = x;
        this.y = y;
        this.theta = theta;
    }

    public Transform2d fromPoses(Pose2d start, Pose2d end)
    {
        return start.transformTo(end);
    }

    public double getMagnitude()
    {
        //  pythagorean!
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    /**
     * NOT THETA.
     * @return the angular component of the translation vector.
     */
    public double getTranslationAngle()
    {
        return Math.atan(x/y);
    }
}
