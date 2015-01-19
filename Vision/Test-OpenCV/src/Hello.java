import java.util.*;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import org.opencv.highgui.Highgui;


//
// Opens an image, does displays it in a simple viewer using LoadImage and writes it to
// to "imageCopy.png".
//
// This was derived from a sample to do face detection so I've comments those parts out
// since they may give us clues on how to manipulate images, but are not relevant for our
// FRC project.  Here's a link to the original sample description:
// http://docs.opencv.org/doc/tutorials/introduction/desktop_java/java_dev_intro.html
class myCopyImage {
	  public void run() {
	    System.out.println("\nRunning CopyImage");

	    // Create a face detector from the cascade file in the resources
	    // directory.
	    //CascadeClassifier faceDetector = new CascadeClassifier(getClass().getResource("/lbpcascade_frontalface.xml").getPath());

	    // For now, just read the image that is in the directory with our java project file
	    Mat image = Highgui.imread("C:lena.jpg", Highgui.CV_LOAD_IMAGE_COLOR);	    
	    //Mat image = Highgui.imread("yellow-tote-top.jpg", Highgui.CV_LOAD_IMAGE_COLOR);	    
	    int srcRows = image.rows();
	    int srcCols = image.cols();
	    long imgData = image.dataAddr();
	    System.out.println("Source rows = " + srcRows + " Src columns = " + srcCols);
	    new LoadImage("C:dst1.jpg",image);
	    int loadedRows = image.rows();
	    int loadedCols = image.cols();
	    System.out.println("Loaded rows = " + loadedRows + " Loaded columns = " + loadedCols);
	    	// Detect faces in the image.
	    	// MatOfRect is a special container class for Rect.
	    	// MatOfRect faceDetections = new MatOfRect();
	    	//faceDetector.detectMultiScale(image, faceDetections);

	    	//System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));

	    	// Draw a bounding box around each face.
	    	//for (Rect rect : faceDetections.toArray()) {
	    	//    Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
	    	//}

	    	// Save the visualized detection.
	    	//String filename = "faceDetection.png";
	    
	    	// For now, just save a copy of the original image.  
	    	// Note: that this is also done in the LoadImage function.
	    	String filename = "imageCopy.jpg";
	    	System.out.println(String.format("Writing %s", filename));
	    	Highgui.imwrite(filename, image);
	  }
}

public class Hello
{
   public static void main( String[] args )
   {
      System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
      
      // Basic test code to display the contents of a matrix
      Mat mat = Mat.eye( 3, 3, CvType.CV_8UC1 );
      System.out.println( "mat = " + mat.dump() );
      
      // Call the sample code to display an image
      //new myCopyImage.run();
      new myCopyImage().run();
      System.out.println("CopyImage complete");
  
   }
    
}


