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
import org.opencv.core.Core;
import static pixel.filas_negras.img;
import static pixel.filas_negras.vuelta;

/**
 *
 * @author Freddy
 */
public class BlancoNegro {
    static int width = 0;
	static int height = 0;
	static int inicioGris = 0;
	static int finGris = 0;
	static int alturaNegra = 0;
	static int alturaGris = 0;
	static int negros=0;
	static int vuelta=0;
	static int libres=0;
	static int bandera=0;
    
        
        static BufferedImage img = null;

	static Color Black = new Color(0, 0, 0); // Color white
	
	static Color White = new Color(255, 255, 255); // Color white
        
    public static void main(String[] args) {

		System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
		
	    try {
	        img = ImageIO.read(new File("C:\\Users\\Freddy\\Documents\\Braille-OCR-master\\mask.jpg"));
	         width = img.getWidth();
	         height = img.getHeight();
	         System.out.println("ANCHO: "+width+"\nAlto: "+height);
	    }
	    catch (IOException e) {
	    }
		vuelta=0;
		bandera=0;
		libres=0;
		int y_1=0;
		
		
	    for (int y = 0; y <= height-1; y++) {
	        for (int x = 0; x <= width-1; x++) {
                    Color pixel2 = new Color(img.getRGB(x, y));
                    if (pixel2.getBlue()<255 && pixel2.getGreen()<255 && pixel2.getRed()<255) {
                        //SI NO ENCUENTRA PIXEL BLANCO SUMA 1 
                        img.setRGB(x, y, Black.getRGB());
                    }
                    else{
                        img.setRGB(x, y, White.getRGB());
                    }
	        }
	    }
            
            try {	
                File outputfile2 = new File("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\Pixel\\results\\BlancoNegro.jpg");
                ImageIO.write(img, "png", outputfile2);
            }
            catch (IOException e) {
            }
            
	}
}
