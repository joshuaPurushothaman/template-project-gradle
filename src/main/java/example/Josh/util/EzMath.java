package example.josh.util;

public class EzMath
{
    /**
     * Returns a value clamped within the given range.
     * @param value
     * @param min
     * @param max
     * @return
     */
    public static double clamp(double value, double min, double max)
    {
        return Math.max(Math.min(value, max), min);
    }

    /**
     * Scales values linearly, like Arduino map() <p>
     * map(75, 50, 100, 0, 100)<br><br>
     *
     * inScale = 100 - 50 = 50<br>
     * outScale = 100 - 0 = 100<br><br>
     *
     * from0to1 = (75 - 50) / 50 = 25/50 = 1/2<br><br>
     *
     * return 1/2 * 100 = 50 //YUH<br>
     *    
     * @param value the value to be mapped
     * @param inMin
     * @param inMax
     * @param outMin
     * @param outMax
     * @return mapped value
     */
    public static double map(double value, double inMin, double inMax, double outMin, double outMax)
    {
        double inScale = inMax - inMin;
        double outScale = outMax - outMin;
            
        double from0to1 = (value - inMin) / inScale;

        return from0to1 * outScale;
    }
}
