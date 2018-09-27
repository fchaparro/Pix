package pixel;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class EliminandoPuntosBlancos2 {
	
	public static void main(String[] args) {
		Color myBalck = new Color(0, 0, 0); // Color white
		int rgbN = myBalck.getRGB();
		Color myWhite = new Color(255, 255, 255); // Color white
		int rgbB = myWhite.getRGB();
		Color myRed = new Color(255, 0, 0); // Color white
		int rgbR = myRed.getRGB();
		int width = 0;
		int height = 0;
		int blancos =0;
		int negros =0;
		//DECLARAR LOS LAS VARIABLES PARA LAS COORDENADAS DE LOS PUNTOS

		
		double promedio =0;
		try {
			    BufferedImage img = null;
			    try {
			        img = ImageIO.read(new File("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\Pixel\\results\\binary_inv.jpg"));
			         width = img.getWidth();
			         height = img.getHeight();
			    }
			    catch (IOException e) {
			    }
			    
			    for (int y = 1; y <= height-2; y++) {
			        for (int x = 1; x <= width-2; x++) {
			        		//Pixel superior izquierda
			        	Color pixel2 = new Color(img.getRGB(x-1, y-1));
	    				if (pixel2.getBlue()<30 && pixel2.getGreen()<30 && pixel2.getRed()<30) {
	    					negros++;
	    				}
	    					//Pixel de la izquierda
			        	Color pixel3 = new Color(img.getRGB(x-1, y));
	    				if (pixel3.getBlue()<30 && pixel3.getGreen()<30 && pixel3.getRed()<30) {
	    					negros++;
	    				}
	    					//Pixel inferior izquierda
			        	Color pixel4 = new Color(img.getRGB(x-1, y+1));
	    				if (pixel4.getBlue()<30 && pixel4.getGreen()<30 && pixel4.getRed()<30) {
	    					negros++;
	    				}
	    					//Pixel Supeior
			        	Color pixel5 = new Color(img.getRGB(x, y-1));
	    				if (pixel5.getBlue()<30 && pixel5.getGreen()<30 && pixel5.getRed()<30) {
	    					negros++;
	    				}
	    					//Pixel infeior
			        	Color pixel6 = new Color(img.getRGB(x, y+1));
	    				if (pixel6.getBlue()<30 && pixel6.getGreen()<30 && pixel6.getRed()<30) {
	    					negros++;	
	    				}
	    					//Pixel superior derecha
			        	Color pixel7 = new Color(img.getRGB(x+1, y-1));
	    				if (pixel7.getBlue()<30 && pixel7.getGreen()<30 && pixel7.getRed()<30){
	    					negros++;
	    				}
	    					//Pixel de la derecha
			        	Color pixel9 = new Color(img.getRGB(x+1, y));
			        	if (pixel9.getBlue()<30 && pixel9.getGreen()<30 && pixel9.getRed()<30) {
	    					negros++;
			        	}
			        		//Pixel inferior derecha
			        	Color pixel8 = new Color(img.getRGB(x+1, y+1));
	    				if (pixel8.getBlue()<30 && pixel8.getGreen()<30 && pixel8.getRed()<30) {
	    					negros++;
	    				}
	    				//Se quita el promedio del color de pixeles de la regilla 3x3
			        	promedio = (negros*100)/8;
			        	
			        	//Si el promedio de negro es mayor se sustituye el pixel blanco por negro
			        	if (promedio>60) {
			        		img.setRGB(x, y, rgbN);
			        	}
			        	//Si el promedio es blanco, se sustituye el pixel por blanco
			        	if (promedio<20) {
			        		img.setRGB(x, y, rgbB);
			        	}
			        	negros=0;
			        }
			    }
			    
			    File outputfile = new File("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\Pixel\\results\\sinPuntos.jpg");
			    ImageIO.write(img, "png", outputfile);
			    System.out.println("****  	TERMINADO LA ELIMINACION DE PUNTOS BLANCOS***** ");
			   
		}
		catch (IOException e) {
			
		}

	}

}
