/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pixel;

/**
 *
 * @author Freddy
 */
import static javax.management.Query.lt;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


import java.io.*;
import javax.script.*;


public class Main
{

   static int width;
   static int height;
   static int alpha = 2;
   static double beta = 50;
   
   public static void main( String[] args ) throws ScriptException, IOException
   {
        try
        {
//       System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
         Mat source = Imgcodecs.imread("C:\\Users\\Freddy\\Documents\\Braille-OCR-master\\part0.jpg", Imgproc.THRESH_BINARY);
         Mat destination = new Mat(source.rows(),source.cols(),source.type());
         
         destination = source;

         int erosion_size = 5;
         int dilation_size = 5;
         
         Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new  Size(2*erosion_size + 1, 2*erosion_size+1));
         Imgproc.erode(source, destination, element);
         Imgcodecs.imwrite("erosion.jpg", destination);
//
//         source = Imgcodecs.imread("digital_image_processing.jpg",  Imgcodecs.CV_LOAD_IMAGE_COLOR);
//         
//         destination = source;
//         
//         Mat element1 = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new  Size(2*dilation_size + 1, 2*dilation_size+1));
//         Imgproc.dilate(source, destination, element1);
//         Imgcodecs.imwrite("dilation.jpg", destination);
            
            
        }catch (Exception e)
        {
            System.out.println("error: " + e.getMessage());
        }
        

    }
               
}
