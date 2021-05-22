package example.Josh.util.geometry;

/**
 * Represents a two-dimensional position with heading.
 * 
 * Conventions:
 *  forward: +y
 *  right: +x
 *  clockwise with 0 facing forward: +theta
 */
public class Pose2d
{
    public double x, y, theta;

    public Pose2d(double x, double y, double theta)
    {
        this.x = x;
        this.y = y;
        this.theta = theta;
    }

    public void reset()
    {
        x = 0;
        y = 0;
        theta = 0;
    }

    public Transform2d transformTo(Pose2d end)
    {
        return new Transform2d(end.x-x, end.y-y, end.theta-theta);
    }
}
