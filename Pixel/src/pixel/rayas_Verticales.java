package pixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class rayas_Verticales { 
	

	public static void main(String[] args) {

		Color myRed = new Color(255, 0, 0); // Color white
		int rgbR = myRed.getRGB();
		
		int width = 0;
		int height = 0;
		int blancos=0;
		int negros=0;
		int bandera=0;
		int vuelta=0;
		int y_1=0;
		//DECLARAR LOS LAS VARIABLES PARA LAS COORDENADAS DE LOS PUNTOS

		double promedio =0;
			    BufferedImage img = null;
			    try {
			        img = ImageIO.read(new File("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\Pixel\\results\\part2.jpg"));
			         width = img.getWidth();
			         height = img.getHeight();
			         System.out.println("ANCHO: "+width+"\nAlto: "+height);
			    }
			    catch (IOException e) {
			    }
			    //FILAS
			    for (int y = 0; y <= height-1; y++) {
			    	
			        for (int x = 0; x <= width-1; x++) {
			        	
			        	Color pixel2 = new Color(img.getRGB(x, y));
	    				if (pixel2.getBlue()<120 && pixel2.getGreen()<120 && pixel2.getRed()<120) {
	    					bandera+=1;
	    				}
	    				if (vuelta==1) {
	    					y_1=y-1;
	    					img.setRGB(x, y_1, rgbR);
	    				}
			        }
			        if (bandera==width) {
			        	if(vuelta==0) {
			        		blancos+=1;
			        		vuelta=1;
			        	}
			        }else {
			        	vuelta=0;
			        }
			        bandera=0;
			    }
			    vuelta=0;
				bandera=0;
				
			    //COLUMNAS
		        for (int x = 0; x <= width-1; x++) {	
			    	for (int y = 0; y <= height-1; y++) {
			    		
			        	Color pixel2 = new Color(img.getRGB(x, y));
	    				if (pixel2.getBlue()==0 && pixel2.getGreen()==0 && pixel2.getRed()==0) {
	    					bandera+=1;
	    				}
			        }
			        if (bandera==(height-1)) {

			        	if(vuelta==0) {
			        		negros+=1;
			        		vuelta=1;
			        	}
			        }else {
			        	vuelta=0;
			        }
			        bandera=0;
			    }
		System.out.println("CANTIDAD DE FILAS: "+blancos);

		System.out.println("CANTIDAD DE COLUMNAS: "+negros);
	try {	
			File outputfile2 = new File("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\Pixel\\results\\lienas_rojas.jpg");
		    ImageIO.write(img, "png", outputfile2);
		}
		catch (IOException e) {
		}
	}

}
