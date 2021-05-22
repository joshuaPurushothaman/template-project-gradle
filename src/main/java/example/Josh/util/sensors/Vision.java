package example.Josh.util.sensors;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;

public class Vision
{
    static {
        System.getProperties().setProperty("java.library.path=", "C:/Users/joshu/Downloads/opencv/build/x64/vc15/lib");
        if (!OpenCVLoader.initDebug())
            Log.e("OpenCv", "Unable to load OpenCV");
        else
            Log.d("OpenCv", "OpenCV loaded");
    }
    

	static final int READ_IMAGE_CNT = 1000;
	static final int ENCODE_IMAGE_CNT = 1000;
	VideoCapture vid;
	Mat camImage = new Mat();

	void init()
	{
		vid = new VideoCapture(0);
		//vid.set(Highgui.CV_CAP_PROP_FRAME_WIDTH, 160);
		//vid.set(Highgui.CV_CAP_PROP_FRAME_HEIGHT, 120);
		//vid.open(0);
		vid.read(camImage);
		System.out.println("Image size is: " + camImage.cols() + "x" + camImage.rows());
	}

	void captureTest()
	{
		System.out.println("capture test read " + READ_IMAGE_CNT + " images");
		long start = System.currentTimeMillis();
		for(int i = 0; i < READ_IMAGE_CNT; i++)
			vid.read(camImage);
		double time = (double)(System.currentTimeMillis() - start)/1000;
		System.out.println("Time is " + time + " = " + (READ_IMAGE_CNT/time) + "fps");
	}

	void encodeTest()
	{
		System.out.println("Encoding image as jpeg " + ENCODE_IMAGE_CNT + " images");
		MatOfByte buf = new MatOfByte();
		long start = System.currentTimeMillis();
		for(int i = 0; i < READ_IMAGE_CNT; i++)
			Highgui.imencode(".jpg", camImage, buf);
		double time = (double)(System.currentTimeMillis() - start)/1000;
		System.out.println("Time is " + time + " = " + (READ_IMAGE_CNT/time) + "fps");
	}


	public static void main(String[] args) {
        // Load the native library.
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Vision test = new Vision();
        test.init();
        test.captureTest();
        test.encodeTest();
    }
}