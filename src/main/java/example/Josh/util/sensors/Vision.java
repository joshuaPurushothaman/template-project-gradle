package example.josh.util.sensors;

import javax.swing.JFrame;

// import org.bytedeco.javacv.CanvasFrame;
// import org.bytedeco.javacv.Frame;
// import org.bytedeco.javacv.FrameGrabber;
// import org.bytedeco.javacv.Java2DFrameConverter;
// import org.bytedeco.javacv.OpenCVFrameConverter;
// import org.bytedeco.javacv.OpenCVFrameGrabber;
// import org.bytedeco.opencv.global.opencv_imgcodecs;
// import org.bytedeco.opencv.opencv_core.IplImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ev3dev.actuators.LCDStretch;

/**
 * tracks targets using JavaCV! :)
 */
public class Vision
{
    Logger LOGGER = LoggerFactory.getLogger(Vision.class);

    /**
     * must be called at init time
     */
    public void init()
    {
        // FrameGrabber grabber = new OpenCVFrameGrabber(0);

        // try {
        //     grabber.start();
        //     Frame frame = grabber.grab();
        //     grabber.close();

        //     var converter = new Java2DFrameConverter();
        //     var img = converter.convert(frame);
        //     converter.close();

        //     LCDStretch.getInstance().drawImage(img, 0, 0, 0);
        // } catch (Exception e) {
        //     LOGGER.error("oopsy doopsy", e.toString());
        // }

        // LOGGER.info(frame.toString());
    }

    /**
     * must be called periodically
     */
    public void updatePeriodic()
    {

    }

    /**
     * Checks if target is recognized
     * @return true if target is recognized
     */
    public boolean getV()
    {
        return false;
    }

    /**
     * @return x degrees deviation from center
     */
    public double getX()
    {
        return 0;
    }

    /**
     * @return y degrees deviation from center
     */
    public double getY()
    {
        return 0;
    }
}