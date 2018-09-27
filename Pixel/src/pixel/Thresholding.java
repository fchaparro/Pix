package pixel;


import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Thresholding {
	   public static void main( String[] args ){
		   
		      try{

		         System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
		         Mat source = Imgcodecs.imread("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\Pixel\\results\\gau.jpg",  Imgcodecs.CV_LOAD_IMAGE_COLOR);
		         Mat destination = new Mat(source.rows(),source.cols(),source.type());

		         destination = source;
		         Imgproc.threshold(source,destination,150,255,Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C);
		         Imgcodecs.imwrite("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\Pixel\\results\\ThreshZero.jpg", destination);
		         
		      }catch (Exception e) {
		         System.out.println("error: " + e.getMessage());
		      }
		   }
		}
