package pixel;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;


import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Core;
import org.opencv.core.Mat;


public class filas_negras { 
	
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

    static Color myRed = new Color(0, 0, 255); // Color white
    static int rgbB = myRed.getRGB();

    static Color myRedd = new Color(0, 0, 255); // Color white
    static int rgbR = myRedd.getRGB();

    public static void main(String[] args) {

        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );

        try {
            img = ImageIO.read(new File("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\Pixel\\images\\BlancoNegro.jpg"));
             width = img.getWidth();
             height = img.getHeight();
             System.out.println("ANCHO: "+width+"\nAlto: "+height);
        }
        catch (IOException e) {
        }

        ENCUENTRA_FILAS_VACIAS();

        ELIMINA_FILAS_INTERMEDIAS();

        //ENSANCHAMOS_FRANJAS();

        RECORTAMOS_FRANJAS();


        System.out.println("CANTIDAD DE FILAS LIBRES: "+libres);

        System.out.println("CANTIDAD DE FILAS CON PUNTOS: "+negros);

        try {	
            File outputfile2 = new File("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\Pixel\\results\\lienas_rojas.jpg");
            ImageIO.write(img, "png", outputfile2);
        }
        catch (IOException e) {
        }
    }

    public static void ENCUENTRA_FILAS_VACIAS(){
        vuelta=0;
        bandera=0;
        libres=0;
        int y_1=0;
        for (int y = 1; y <= height-2; y++) {
            for (int x = 0; x <= width-1; x++) {
                Color pixel2 = new Color(img.getRGB(x, y));
                if (pixel2.getBlue()==0 && pixel2.getGreen()==0 && pixel2.getRed()==0) {
                    //SI NO ENCUENTRA PIXEL BLANCO SUMA 1 
                    bandera+=1;
                    //System.out.println(pixel2.getBlue()+" "+pixel2.getGreen()+" "+pixel2.getRed());
                }
                if (vuelta==1) {
                    y_1=y-1;
                    img.setRGB(x, y_1, rgbR);
                }
            }
            if (bandera==width) {
                if(vuelta==0) {
                    libres+=1;
                    vuelta=1;
                }
            } else {
                vuelta=0;
            }
            bandera=0;
        }
    }   

    public static void ELIMINA_FILAS_INTERMEDIAS() {

        vuelta=0;
        bandera=0;

        //ELIMINA LAS FILAS INTERMEDIAS ENTRE PUNTOS
        for (int y = 0; y <= height-2; y++) {
            //Color pixel2 = new Color(img.getRGB(x, y));
            Color pixel2 = new Color(img.getRGB(10, y));
            if (!(pixel2.getBlue()==0 && pixel2.getGreen()==0 && pixel2.getRed()==0)) {
                if (vuelta == 0) {
                    inicioGris=y;
                    vuelta=1;
                }
            }
            else{
                if (vuelta == 1) {
                    finGris=y-1;
                    vuelta =0;
                    EliminarLineasIntermedias(inicioGris,finGris);
                }
            }
        }

    }
	
    public static void ENSANCHAMOS_FRANJAS() {

        vuelta=0;
        bandera=0;
       // BUSCAMOS LAS SUBIMAGENES PARA HALLAR LOS PATRONES BRAILLE
        for (int y = 1; y <= height-2; y++) {
            //pixel anterior
            Color pixel1 = new Color(img.getRGB(10, y-1));
            //pixel actual
            Color pixel2 = new Color(img.getRGB(10, y));

            if (!(pixel2.getBlue()==0 && pixel2.getGreen()==0 && pixel2.getRed()==0)) {

                if (pixel1.getBlue()==0 && pixel1.getGreen()==0 && pixel1.getRed()==0) {
                    finGris=y;
                    vuelta+=1;
                    //System.out.println("fin franja negra pixel: "+(y-1));
                    y+=EnsancharFranjaNegra(inicioGris,finGris,finGris);
                    //System.out.println(finGr);
                    //y+=bandera;
                    bandera=0;
                }
            } else if (pixel2.getBlue()==0 && pixel2.getGreen()==0 && pixel2.getRed()==0) {

                if (!(pixel1.getBlue()==0 && pixel1.getGreen()==0 && pixel1.getRed()==0)) {
                    inicioGris=y;
                    //System.out.println("inicio franja negra pixel: "+y);
                }
            }
        }   
    }

    public static void RECORTAMOS_FRANJAS() {
        vuelta=0;
        bandera=0;
        inicioGris=0;
        // BUSCAMOS LAS SUBIMAGENES PARA HALLAR LOS PATRONES BRAILLE
        for (int y = 3; y <= height-3; y++) {
            //pixel anterior
            Color pixel1 = new Color(img.getRGB(10, y-1));
            //pixel actual
            Color pixel2 = new Color(img.getRGB(10, y));
            if (!(pixel2.getBlue()==0 && pixel2.getGreen()==0 && pixel2.getRed()==0)) {
                if (pixel1.getBlue()==0 && pixel1.getGreen()==0 && pixel1.getRed()==0) {
                    finGris=y;
                    vuelta+=1;
//                                    System.out.println("fin franja negra pixel: "+(y-1));
//                                    System.out.println("Inicio Gris"+ (inicioGris));
//                                    System.out.println("Fin Gris"+ (finGris));
//                                    System.out.println("FinGris"+ (finGris-inicioGris)+"\n");
                    y+=2;
                    BufferedImage clipped = null;
                    if(!((finGris-inicioGris)<20)){
                        try
                        {   clipped = img.getSubimage(0, inicioGris, (width-1), (finGris-inicioGris));
                            try {	
                                File outputfile2 = new File("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\Pixel\\results\\parte"+vuelta+".jpg");
                                ImageIO.write(clipped, "png", outputfile2);
                            }
                            catch (IOException e) {
                            }
                        }
                        catch(RasterFormatException rfe)
                        {
                            //System.out.println("raster format error: " + rfe.getMessage());
                            return;
                        }
                    }
                }
            } else if (pixel2.getBlue()==0 && pixel2.getGreen()==0 && pixel2.getRed()==0) {
                if (!(pixel1.getBlue()==0 && pixel1.getGreen()==0 && pixel1.getRed()==0)) {
                    inicioGris=y;
                    //System.out.println("inicio franja negra pixel: "+y);
                }
            }
        }
    }

    public static void EliminarLineasIntermedias(int X, int Y) {
        if ((Y-X)<50) {
            negros+=1;
            for (int y = X; y <=Y; y++) {

            for (int x = 0; x <= width-1; x++) {
                            img.setRGB(x, y, rgbB);
            }
            }
        }
    }

    public static int EnsancharFranjaNegra(int X, int Y, int Z) {
        //fila superior
        //System.out.println("\n"+(Y-X)+"\n");
        int A=0;
        int B=0;
        if((Y-X)<=156) {
            A=156-(Y-X);
            B=A/2;
            if((A%2)==0) {
                //System.out.println("\n"+B+" "+A+" "+((Y+B+1)-(X-B)));
                sumar(Y,B);
                restar(X,B);
            }else {
                //System.out.println("\n"+B+" "+(B)+" "+(B+1)+" "+A+" "+((Y+B+1)-(X-B-1)));
                sumar(Y,B+1);
                restar(X,B);
            }
        }
        else if((Y-X)==155){
            System.out.println("\nCORRECTO");
        }else {
            System.out.println("\nMUY GRUESO");
        }
        //bandera=Y+B+1;
        return(B+1);
    }

    public static void restar(int X,int A) {
        for (int y = (X-A); y < X+1; y++) {	
            for (int x = 0; x < width; x++) {
                img.setRGB(x, y, rgbB);
            }
        }
    }

    public static void sumar(int Y,int A) {
        for (int y = Y-1; y < Y+A; y++) {	
            for (int x = 0; x < width; x++) {
                img.setRGB(x, y, rgbB);
            }
        }
    }

}
