package pixel;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

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




public class Main2 {
	
	
	
	public static void MenorDistancia(double x) {
		double valoractual = x;
		if(MenorDistancia == 0.0) {
			MenorDistancia=valoractual;
		} else if (valoractual <= MenorDistancia) {
			MenorDistancia=valoractual;
		}
	}
	
	public static void MayorDistancia(double x) {
		double valoractual = x;
		if(MayorDistancia == 0.0) {
			MayorDistancia=valoractual;
		} else if (valoractual >= MayorDistancia) {
			MayorDistancia=valoractual;
		}
	}
	
	static double MenorDistancia =0.0;
	static double MayorDistancia =0.0;
	static int width;
	static int height;
	static double alpha = 0;
	static double beta = 0;
	static double x = 0.0;
	static double y = 0.0;
	static Color myRed = new Color(0, 0, 0); // Color white
	static int rgbB = myRed.getRGB();
	
	
	public static void main(String[] args) {

				System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
			    // Consider the image for processing
			    //Mat image = Imgcodecs.imread("Gaussian45.jpg", Imgproc.COLOR_BGR2GRAY);
			    Mat image = Imgcodecs.imread("C:\\Users\\Freddy\\Documents\\Braille-OCR-master\\mask.jpg");
			    Mat imageHSV = new Mat(image.size(), CvType.CV_8UC4);
			    Mat imageBlurr = new Mat(image.size(), CvType.CV_8UC4);
			    Mat imageA = new Mat(image.size(), CvType.CV_32F);
			    Imgproc.cvtColor(image, imageHSV, Imgproc.COLOR_BGR2GRAY);
			    Imgproc.GaussianBlur(imageHSV, imageBlurr, new Size(7,7), 1);
			    Imgcodecs.imwrite("Gau.jpg",imageHSV);
			    Imgproc.adaptiveThreshold(imageBlurr, imageA, 100,Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY,7, 5);
			    Imgcodecs.imwrite("Tresh.jpg",imageA);
			    
			    Imgcodecs.imwrite("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\Pixel\\results\\duplicado.jpg",imageHSV);
			    image = Imgcodecs.imread("Tresh.jpg");
			    
			    Imgproc.Canny(image, imageA, 100, 255);
                       
			    Imgcodecs.imwrite("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\Pixel\\results\\canny.jpg",image);
                            
                            
			    List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
			    Mat hierarchy = new Mat();
			    Imgproc.findContours(imageHSV, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE); 
			    
			    //Moments moments = Imgproc.moments(hierarchy);
			    //CargarDuplicado();
			    Mat imagenNegra = Imgcodecs.imread("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\Pixel\\results\\duplicado.jpg");
			   // Color myRed = new Color(255, 0, 0); // Color white
				//int rgbR = myRed.getRGB();
			    int a=0;
			    Point anterior = new Point();
		    	Point centroid = new Point();
			    for (Mat pm: contours) {
			    	if (Imgproc.contourArea(pm) > 10 ){
			    		
			    	a++;
			    	beta+=1;
			    	Moments moments = Imgproc.moments(pm);
					centroid.x = (moments.get_m10() / moments.get_m00());
					centroid.y = moments.get_m01() / moments.get_m00();
						if(a==0) {
							anterior.x = centroid.x;
							anterior.y = centroid.y;
			    		}
						else {
							//System.out.println("Punto actual: "+centroid+"   Punto anterior: "+anterior);
							euclideanDistance(centroid, anterior);
							anterior.x = centroid.x;
							anterior.y = centroid.y;
						}
					//x = Math.floor(centroid.x);
					//y = Math.floor(centroid.y);
					Imgproc.circle(imagenNegra, centroid, 2,new Scalar(255,0,0),1);
					Imgproc.circle(imagenNegra, centroid, 3,new Scalar(255,0,0),1);
					Imgproc.circle(imagenNegra, centroid, 4,new Scalar(255,0,0),1);
					Imgproc.circle(imagenNegra, centroid, 5,new Scalar(255,0,0),1);
					Imgproc.circle(imagenNegra, centroid, 6,new Scalar(255,0,0),1);
//					Imgproc.circle(imagenNegra, centroid, 7,new Scalar(255,0,0),2);
//					Imgproc.circle(imagenNegra, centroid, 8,new Scalar(255,0,0),2);
//					Imgproc.circle(imagenNegra, centroid, 9,new Scalar(255,0,0),2);
//					Imgproc.circle(imagenNegra, centroid, 10,new Scalar(255,0,0),2);
					Imgcodecs.imwrite("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\Pixel\\results\\test2.jpg",imagenNegra);
					//System.out.println(beta+"  En X "+ Math.floor(centroid.x)+"  En Y "+Math.floor(centroid.y));
			    	}
			    }
			    
			    System.out.println("La cantidad de puntos encontrados es: "+a);
			    //System.out.println("La menor distancia entre los puntos es de: "+MenorDistancia);
			    //System.out.println("La mayor distancia entre los puntos es de: "+MayorDistancia);
			    
	}

	public static double euclideanDistance(Point a, Point b){
        double distance = 0.0;
        try{
            if(a != null && b != null){
                double xDiff = a.x - b.x;
                double yDiff = a.y - b.y;
                distance = Math.sqrt(Math.pow(xDiff,2) + Math.pow(yDiff, 2));
                //System.out.println("Distancia "+distance);
                MenorDistancia(distance);
                MayorDistancia(distance);
            }
        }catch(Exception e){
            System.err.println("Something went wrong in euclideanDistance function in "+e.getMessage());
        }
        return distance;
    }
	
	public static void CargarDuplicado() {

	    BufferedImage img = null;
	    try {
	        img = ImageIO.read(new File("duplicado.jpg"));
	         width = img.getWidth();
	         height = img.getHeight();
	         System.out.println("Duplicado cargado satisfactoriamente");
	    }
	    catch (IOException e) {
	    }
	    for (int y = 0; y <= height-1; y++) {
	        for (int x = 0; x <= width-1; x++) {
					img.setRGB(x, y, rgbB);
	        }
	    }
	    
	    try {	
			File outputfile2 = new File("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\Pixel\\results\\duplicado2.jpg");
		    ImageIO.write(img, "png", outputfile2);
		}
		catch (IOException e) {
		}
		
	}

}
