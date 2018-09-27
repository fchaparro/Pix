package pixel;



import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

public class GaussianBlur {

	   public static void main( String[] args ){

				System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
		      try {
		         //System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
		         
		         Mat source = Imgcodecs.imread("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\Pixel\\results\\equ.jpg", Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);

		         Mat destination = new Mat(source.rows(),source.cols(),source.type());
		         Mat destination2 = new Mat(source.rows(),source.cols(),source.type());
		         Imgproc.equalizeHist(source, destination2);
		         Imgproc.GaussianBlur(destination2, destination,new Size(5,5), 7, 9);
		         Imgproc.GaussianBlur(destination, destination2, new Size(5,5), 7, 9);
		         Core.addWeighted(destination2, 1.5, destination, -0.5, 0, destination);
		         Imgproc.threshold(destination,source,200,255,Imgproc.ADAPTIVE_THRESH_MEAN_C);
		         Imgcodecs.imwrite("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\Pixel\\results\\Gaussian1.jpg", source);
		         Imgproc.threshold(source,destination2,255,0,Imgproc.THRESH_TOZERO_INV);
		         Imgproc.GaussianBlur(destination2, destination, new Size(5,5), 7, 9);
		         Imgcodecs.imwrite("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\Pixel\\results\\Gaussian2.jpg", destination);
		         
		      
		      } catch (Exception e) {
		         System.out.println("Error: " + e.getMessage());
		      }
		   }

}
