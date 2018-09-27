/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 *
 * @author Freddy
 */
public class franjaHorizontal {
    	
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
			        img = ImageIO.read(new File("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\Pixel\\images\\lineas.png"));
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
	    				if (pixel2.getBlue()==0 && pixel2.getGreen()==0 && pixel2.getRed()==255) {
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
                        int rojos=0;
                        int negromasfino=100;
                        int negromasgrueso=0;
		        for (int x = 0; x <= 1; x++) {	
			    	for (int y = 0; y <= height-1; y++) {
			    		Color pixel2 = new Color(img.getRGB(x, y));
                                        System.out.println(pixel2.getBlue()+" "+pixel2.getGreen()+" "+pixel2.getRed());
			        	
	    				if (pixel2.getBlue()==0 && pixel2.getGreen()==0 && pixel2.getRed()==255) {
	    					rojos=1;
                                                if (bandera>=negromasgrueso)
                                                    negromasgrueso=bandera;
                                                if (bandera<=negromasfino)
                                                    negromasfino=bandera;
                                                bandera=0;
                                                //System.out.println("Rojo");
	    				}
                                        else if (pixel2.getBlue()==0 && pixel2.getGreen()==0 && pixel2.getRed()==0) {
                                            rojos=0;
                                            bandera+=1;
                                        }
			        }
			        
			    }
		System.out.println("FILA MAS FINA: "+negromasfino);

		System.out.println("FILA MAS GRUESA: "+negromasgrueso);
//	try {	
//			File outputfile2 = new File("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\Pixel\\results\\lienas_rojas.jpg");
//		    ImageIO.write(img, "png", outputfile2);
//		}
//		catch (IOException e) {
//		}
	}

}

