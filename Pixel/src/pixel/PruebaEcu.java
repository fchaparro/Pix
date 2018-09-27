/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pixel;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Point;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgcodecs.Imgcodecs;


import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import org.opencv.core.CvType;




/**
 *
 * @author Freddy
 */
public class PruebaEcu {
    public static void main (String[] args){
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME);
         try {
            Mat img = Imgcodecs.imread("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\Pixel\\images\\1405.jpg");
            Mat equ = new Mat();
            Mat img1 = new Mat();
            Mat img2 = new Mat();
            Mat img3 = new Mat();
            Mat img4 = new Mat();
            Mat gau = new Mat();
            int erosion_size=1;
            int dilation_size=1;
            
            Mat kernel = Mat.ones(5,5,CvType.CV_8UC1);
            Point anchor = new Point(1,1);
           
            Imgproc.threshold(img, img1, 122, 255, Imgproc.THRESH_BINARY);
            Imgproc.blur(img1, img2, new Size(5, 5));
            
            Imgproc.erode(img2, img3, kernel, anchor, dilation_size);
            Imgproc.threshold(img3, img4, 12, 255, Imgproc.THRESH_BINARY);
           
            kernel = Mat.ones(3,2,CvType.CV_32F);
            
            Imgproc.dilate(img4, equ, kernel, anchor, dilation_size);
            
           // Imgproc.cvtColor(equ,gau, Imgproc.THRESH_BINARY_INV);
  

            Imgcodecs.imwrite("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\Pixel\\results\\ima.jpg",img);
            Imgcodecs.imwrite("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\Pixel\\results\\gau.jpg",equ);
//            Imgcodecs.imwrite("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\Pixel\\results\\gray.jpg",gray);
//            Imgcodecs.imwrite("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\Pixel\\results\\grayOrig.jpg",grayOrig);
//            Imgcodecs.imwrite("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\Pixel\\results\\gau.jpg",gau);
        } catch (Exception e) {
		         System.out.println("Error: " + e.getMessage());
		      }
    }
}
