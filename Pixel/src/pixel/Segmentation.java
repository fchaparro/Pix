
package pixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import static pixel.Main2.MenorDistancia;
import static pixel.Main2.beta;


public class Segmentation {
	
    static int width = 0;
    static int height = 0;
    static int inicioGris = 0;
    static int finGris = 0;
    static int alturaNegra = 0;
    static int alturaGris = 0;
    static int negros=0;
    static int finanterior=0;
    static int inicioanterior=0;
    static int libres=0;
    static int bandera=0;
    static int vuelta=0;
    static int acento=0;
    static int Mayuscula=0;
    static int MayusculaTotal=0;
    static BufferedImage img = null;
    static BufferedImage img2=null;
    static String[][] ABC = new String[49][2];

    static Color myRed = new Color(0, 0, 0); // Color white
    static int rgbB = myRed.getRGB();
    
    static Color myGris = new Color(127,127,127); // Color white
    static int rgbG = myGris.getRGB();

    static Color myRed1 = new Color(255, 0, 0); // Color white
    static int rgbR = myRed1.getRGB();

    static Color myRed2 = new Color(255, 255, 255); // Color white
    static int rgbW = myRed2.getRGB();

    static String CADENA;

    public static void main(String[] args) {
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
        try {
            //System.loadLibrary( Core.NATIVE_LIBRARY_NAME );

            Mat source = Imgcodecs.imread("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\Pixel\\results\\parte10.jpg", Imgcodecs.CV_LOAD_IMAGE_ANYCOLOR);
            Mat destination = new Mat(source.rows(),source.cols(),source.type());
            
//            Imgproc.equalizeHist(source, source);
            Imgproc.threshold(source, source, 30, 255, Imgproc.THRESH_BINARY);
            
            final Size kernelSize = new Size(1,1);
            final Point  anchor = new Point(-1,-1);
            final int interations = 1;
            
            Imgproc.blur(source, source, kernelSize, anchor);
            
            Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, kernelSize);
//     
            Imgproc.erode(source, source, kernel, anchor, interations);
            Imgproc.threshold(source, source, 12, 255, Imgproc.THRESH_BINARY);
//            
            final Size kernelSize2 = new Size(1,1);
            Mat kernel2 = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, kernelSize2);
//     
            Imgproc.dilate(source, source, kernel2, anchor, interations);
//            
            
            Imgcodecs.imwrite("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\Pixel\\results\\test2.jpg",source);
            

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        CADENA="";

        ABC[0][0]= "100000";  ABC[0][1]= "a";
        ABC[1][0]= "101000";  ABC[1][1]= "b";
        ABC[2][0]= "110000";  ABC[2][1]= "c";
        ABC[3][0]= "110100";  ABC[3][1]= "d";
        ABC[4][0]= "100100";  ABC[4][1]= "e";
        ABC[5][0]= "111000";  ABC[5][1]= "f";
        ABC[6][0]= "111100";  ABC[6][1]= "g";
        ABC[7][0]= "101100";  ABC[7][1]= "h";
        ABC[8][0]= "011000";  ABC[8][1]= "i";
        ABC[9][0]= "011100";  ABC[9][1]= "j";
        ABC[10][0]= "100010"; ABC[10][1]= "k";
        ABC[11][0]= "101010"; ABC[11][1]= "l";
        ABC[12][0]= "110010"; ABC[12][1]= "m";
        ABC[13][0]= "110110"; ABC[13][1]= "n";
        ABC[14][0]= "111101"; ABC[14][1]= "ñ";
        ABC[15][0]= "100110"; ABC[15][1]= "o";
        ABC[16][0]= "111010"; ABC[16][1]= "p";
        ABC[17][0]= "111110"; ABC[17][1]= "q";
        ABC[18][0]= "101110"; ABC[18][1]= "r";
        ABC[19][0]= "011010"; ABC[19][1]= "s";
        ABC[20][0]= "011110"; ABC[20][1]= "t";
        ABC[21][0]= "100011"; ABC[21][1]= "u";
        ABC[22][0]= "101011"; ABC[22][1]= "v";
        ABC[23][0]= "011101"; ABC[23][1]= "w";
        ABC[24][0]= "110011"; ABC[24][1]= "x";
        ABC[25][0]= "000000"; ABC[25][1]= " ";
        ABC[26][0]= "110111"; ABC[26][1]= "y";
        ABC[27][0]= "100111"; ABC[27][1]= "z";
        ABC[28][0]= "000010"; ABC[28][1]= "."; 
        ABC[29][0]= "001001"; ABC[29][1]= "?";
        ABC[30][0]= "101011"; ABC[30][1]= "NUMERADOR";
        ABC[31][0]= "001000"; ABC[31][1]= ","; 
        ABC[32][0]= "000000"; ABC[32][1]= " "; 
        ABC[33][0]= "001100"; ABC[33][1]= ":"; 
        ABC[34][0]= "010010"; ABC[34][1]= "/"; 
        ABC[35][0]= "001101"; ABC[35][1]= "!";
        ABC[36][0]= "011011"; ABC[36][1]= "=";
        ABC[37][0]= "011001"; ABC[37][1]= "\""; 
        ABC[38][0]= "001010"; ABC[38][1]= "*"; 
        ABC[39][0]= "000000"; ABC[39][1]= " ";
        ABC[40][0]= "000011"; ABC[40][1]= "-";
        ABC[41][0]= "010001"; ABC[41][1]= "MAYUS";
        ABC[42][0]= "101111"; ABC[42][1]= "á";
        ABC[43][0]= "011011"; ABC[43][1]= "é";
        ABC[44][0]= "010010"; ABC[44][1]= "í";
        ABC[45][0]= "010011"; ABC[45][1]= "ó";
        ABC[46][0]= "011111"; ABC[46][1]= "ú";
        ABC[47][0]= "101101"; ABC[47][1]= "ü";
        ABC[48][0]= "000011"; ABC[48][1]= "ac";
        ABC[48][0]= "101001"; ABC[48][1]= "(";
        ABC[48][0]= "010110"; ABC[48][1]= ")";

        try {
            img = ImageIO.read(new File("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\Pixel\\results\\test2.jpg"));
             width = img.getWidth();
             height = img.getHeight();
             System.out.println("ANCHO: "+width+"\nAlto: "+height);
        }
        catch (IOException e) {
        }

        ENCONTRAMOS_COLUMNAS_VACIAS();

        ELIMINAMOS_COLUMNAS_INTERMEDIAS();

        ENSANCHAMOS_FILAS();

        SEPARADOR_DE_PALABRAS();

        System.out.println("\n"+CADENA);

        try {	
            File outputfile2 = new File("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\Pixel\\results\\columnas_rojas.jpg");
            ImageIO.write(img, "png", outputfile2);
        }
        catch (IOException e) {
        }
    }

 
    
    public static void ENCONTRAMOS_COLUMNAS_VACIAS(){
        bandera=0;
        vuelta=0;
        int x_1=0;
        //ENCUENTRA LAS COLUMNAS VACIAS

        for (int x = 0; x <= width-1; x++) {	
            for (int y = 0; y <= height-1; y++) {
                Color pixel2 = new Color(img.getRGB(x, y));
                if ((pixel2.getBlue()<255 && pixel2.getGreen()<255 && pixel2.getRed()<255)) {
                    //SI NO ENCUENTRA PIXEL BLANCO SUMA 1 
                    bandera+=1;
                }
                if (vuelta==1) {
                    x_1=x-1;
                    img.setRGB(x_1, y, rgbR);
                }
            }
            if (bandera==height) {
                if(vuelta==0) {
                    libres+=1;
                    vuelta=1;
                    //System.out.println("vuelta");
                }
            } else {
                vuelta=0;
            }
            bandera=0;
        }
    }
 
    public static void ELIMINAMOS_COLUMNAS_INTERMEDIAS(){

        bandera=0;
        vuelta=0;
        inicioanterior=0;
        finanterior=0;
        int negroanterior=0;
        //ELIMINA LAS COLUMNAS INTERMEDIAS ENTRE PUNTOS
        for (int x = 0; x <= width-1; x++) {
            //Color pixel2 = new Color(img.getRGB(x, y));
            Color pixel2 = new Color(img.getRGB(x, 3));
            //System.out.println(pixel2.getBlue()+"  "+pixel2.getGreen()+"  "+pixel2.getRed()+"  ");
            if ((pixel2.getBlue()==127 && pixel2.getGreen()==127 && pixel2.getRed()==127)) {
                if (vuelta == 0) {
                    inicioanterior=inicioGris;
                    inicioGris=x;
                    vuelta=1;
                }
            }
            else{
                if (vuelta == 1) {
                    finanterior=finGris;
                    finGris=x-1;
                    vuelta =0;
                    bandera++;
//                    System.out.println("Inicio "+(inicioGris));
//                    System.out.println("Fin "+(finGris));
//                    System.out.println("Grosor "+(finGris-inicioGris)+"\n");
//					System.out.println(bandera+"  resto: "+(bandera%2));

                    if ((finGris-inicioGris)>5 && (finGris-inicioGris)<10) {
                        EliminarLineasIntermedias2(inicioGris,finGris);
                        bandera+=1;
                    }else

                    //COMPROBAMOS SI NO ES LA SECUENCIA ESPACIO-FALTANTE-SEPARADOR, PARA NO SEGMENTAR ERRONEAMENTE
                    if((finGris-inicioGris)>90 && ( finanterior-inicioanterior)>90) {
                        bandera+=1;
                    }else {
                        if ((bandera%2)==0){
                            if (!((finGris-inicioGris)>36)) {
                                EliminarLineasIntermedias(inicioGris,finGris);
                                bandera+=1;
                            }else
                                bandera+=1;
                        }
                        if (((finGris-inicioGris)<40) && bandera!=2)
                            EliminarLineasIntermedias(inicioGris,finGris);
                        else 
                            bandera+=1;

                    }
                }
            }
        }
        negros=0;
        bandera=0;
        inicioanterior=0;
        finanterior=0;
        int B=0;
        int A=0;
        for (int x = 0; x <= width-1; x++) {
            //Color pixel2 = new Color(img.getRGB(x, y));
            Color pixel2 = new Color(img.getRGB(x, height-1));
                    if ((pixel2.getBlue()==127 && pixel2.getGreen()==127 && pixel2.getRed()==127)) {
                            if (vuelta == 0) {
                                    A=inicioanterior;
                                    inicioanterior=inicioGris;
                                    inicioGris=x;
                                    vuelta=1;
                            }
                    }
                    else if (vuelta == 1) {
                                    B=finanterior;
                                    finanterior=finGris;
                                    finGris=x-1;
                                    vuelta =0;
                                    bandera++;

                                    //System.out.println(bandera+"---"+(B-A)+"  "+(finanterior-inicioanterior)+" "+(finGris-inicioGris));

                                    if(negros<40 && negroanterior<40 && (finanterior-inicioanterior)<55 && (finGris-inicioGris)<60 && (B-A)<55) {
                                                    EliminarLineasIntermedias(inicioanterior,finanterior);
                                                    negroanterior+=(inicioGris-inicioanterior);
                                    }else if(negros<40 && negroanterior<40 && (finanterior-inicioanterior)<50 && (finGris-inicioGris)>60 && (B-A)>60) {
                                                    //EliminarLineasIntermedias(inicioanterior,finanterior);
                                                    negroanterior+=(inicioGris-inicioanterior);
                                    }else if(negros<40 && negroanterior<40 && (finanterior-inicioanterior)<50 && (finGris-inicioGris)<50 && (B-A)>120) {
                                            EliminarLineasIntermedias(inicioanterior,finanterior);
                                            negroanterior+=(inicioGris-inicioanterior);
                                    }else if(negros<40 && negroanterior<40 && (finanterior-inicioanterior)<50 && ((finGris-inicioGris)>60 && (finGris-inicioGris)<120)) {
                                                    EliminarLineasIntermedias(inicioanterior,finanterior);
                                                    negroanterior+=(inicioGris-inicioanterior);
                                    }else if(negros<40 && negroanterior<40 && (finanterior-inicioanterior)<50 && (finGris-inicioGris)>120) {
                                                    EliminarLineasIntermedias(inicioanterior,finanterior);
                                                    negroanterior+=(inicioGris-inicioanterior);
                                    }else if(negros<40 && negroanterior<40 && (finanterior-inicioanterior)<55 && (finGris-inicioGris)>90 && (B-A)>55) {
                                                    //EliminarLineasIntermedias(inicioanterior,finanterior);
                                                    //System.out.println("hola");
                                                    negroanterior+=(inicioGris-inicioanterior);
                                    }else
                                            negroanterior=negros;
                                    negros=0;
                    }
                    else {
                            negros++;
                            }
            }
    }

    public static void SEPARADOR_DE_PALABRAS() {
        vuelta=0;
        String frase="";
        int inicionegro=0;
        int finnegro=0;
        int inicioanterior=0;
        int finanterior=0;
        for (int x = 1; x <= width-1; x++) {
            Color pixel2 = new Color(img.getRGB(x, 3));
            //System.out.println(pixel2.getBlue()+" "+pixel2.getGreen()+" "+pixel2.getRed());
            if ((pixel2.getBlue()==127 && pixel2.getGreen()==127 && pixel2.getRed()==127)) {
                if (vuelta == 1) {
                    finnegro=x-1;
                    vuelta =0;
                    DIBUJA_MATRIS_3X2(inicionegro, finnegro);
                    frase+=PATRON_BRAILLE(inicionegro, finnegro);
                }
            }
            else{
                if (vuelta == 0) {
                    inicionegro=x;
                    vuelta = 1;
                }
            }
        }
        System.out.println(frase);
    }	

    public static void DIBUJA_MATRIS_3X2(int X, int Y){
        vuelta=0;
        bandera=0;
        libres=0;
        
        int medio=((Y-X)/2)+X;
        for (int y = 1; y < height; y++) {
            img.setRGB(medio, y,rgbG);
        }
       
        for (int y = 2; y < height-1; y++) {
            for (int x = X; x < Y; x++) {
                Color pixel2 = new Color(img.getRGB(x, y));
                //System.out.println(pixel2.getBlue()+" "+pixel2.getGreen()+" "+pixel2.getRed());
                if ((pixel2.getBlue()>200 && pixel2.getGreen()>200 && pixel2.getRed()>200)) {
                    libres=0;
                    break;
                }
                else{
                    libres+=1;
                }
            }
            if(libres==(Y-X)){
                //System.out.println("fila negra");
                if (!(y<(height/4))){
                    if((y+5)<height){
                        for (int x = X; x < Y+1; x++) {
                            img.setRGB(x, y+2,rgbG);
                        }   
                    }
                }
            }
        }
        libres=0;
        for (int y=height-1; y>2 ; y--) {
            Color pixel2 = new Color(img.getRGB(X+5, y));
            //System.out.println(pixel2.getBlue()+" "+pixel2.getGreen()+" "+pixel2.getRed());
            if ((pixel2.getBlue()==127 && pixel2.getGreen()==127 && pixel2.getRed()==127)) {
                
                if(y<(height/2) && libres>(height/3)){
                    for (int x = X; x < Y+1; x++) {
                            img.setRGB(x,y+15,rgbG);
                        }
                    break;
                }
                else
                    break;
            }
            else{
                if ((pixel2.getBlue()==0 && pixel2.getGreen()==0 && pixel2.getRed()==0)) {
                    libres+=1;
                }
            }
        }
       

    }
    
    public static String PATRON_BRAILLE(int X, int Y) {
        vuelta=0;
        bandera=0;
        libres=0;
        int techo_1=0;
        String cadena="";
        String frase="";
        
        int medio=((Y-X)/2)+X;
        
        for (int y = 2; y <= height-1; y++) {
            Color pixel2 = new Color(img.getRGB(X, y));
            if ((pixel2.getBlue()==127 && pixel2.getGreen()==127 && pixel2.getRed()==127)) {
                
                //SE ENVIA LA PRIMERA MITAD DESDE X HASTA EL PUNTO MEDIO
                cadena+=retroceder(techo_1,y,X,medio);
                
                //SE ENVIA LA SEGUNDA MITAD DESDE EL PUNTO MEDIO HASTA EL PUNTO Y
                cadena+=retroceder(techo_1,y,medio+1,Y);
                
                techo_1=y+1;
            }
            if(y==height-1){
                //SE ENVIA LA PRIMERA MITAD DESDE X HASTA EL PUNTO MEDIO
                cadena+=retroceder(techo_1,y,X,medio);
                
                //SE ENVIA LA SEGUNDA MITAD DESDE EL PUNTO MEDIO HASTA EL PUNTO Y
                cadena+=retroceder(techo_1,y,medio+1,Y);
            }
        }
        
        for(int a=0; a<49; a++){
            if(ABC[a][0].equals(cadena)){
                frase+=ABC[a][1];
            }
        }
        return(frase);
    }

    public static String retroceder(int techo_1,int y,int X,int medio){
            //System.out.println(techo_1+" "+y+" "+X+" "+medio+" ");
            String cadena="0";
             for (int x = X+1; x < medio; x++) {
                    for (int a=techo_1+1; a<y; a++){
                        Color pixel2 = new Color(img.getRGB(x, a));
                        //System.out.println(pixel2.getBlue()+" "+pixel2.getGreen()+" "+pixel2.getRed());
                        if ((pixel2.getBlue()==255 && pixel2.getGreen()==255 && pixel2.getRed()==255)) {
                                cadena="1";
                        }
                    }
                }
            return cadena;
    }
    
    public static int recorrer(int inicioY, int finY, int inicioX, int finX) {
        int z=0;
        for (int y = inicioY; y <= finY-1; y++) {			
            for (int x = inicioX; x <= finX-1; x++) {
                Color pixel2 = new Color(img.getRGB(x, y));
                if ((pixel2.getBlue()==0 && pixel2.getGreen()==0 && pixel2.getRed()==0)) {
                    z+=1;
                    //System.out.println(pixel2.getBlue());
                }else {
                }
            }
        }
        if((finY-inicioY)*(finX-inicioX)==z) {
            z=0;
        }else 
            z=1;
        return(z);
    }

    public static void ENSANCHAMOS_FILAS(){
        vuelta=0;
        bandera=0;
        inicioGris=0;
        finGris=0;
        int contador=0;
        int actual=0;
        int anterior=0;
        int negroanterior=0;
        String orden="";
        //ENSANCHAMOS LAS LINEAS FINAS	
        for (int x = 5; x <= width-1; x++) {
            //Color pixel2 = new Color(img.getRGB(x, y));
            Color pixel2 = new Color(img.getRGB(x, 2));
             if ((pixel2.getBlue()==127 && pixel2.getGreen()==127 && pixel2.getRed()==127)) {
                if (vuelta == 0) {
                    inicioanterior=inicioGris;
                    inicioGris=x;
                    vuelta=1;
                }
            }else if ((vuelta == 1)) {
                finanterior=finGris;
                finGris=x-1;
                vuelta =0;
//					System.out.println((finanterior-inicioanterior));
                actual=finGris-inicioGris;
                anterior=finanterior-inicioanterior;

                contador+=1;
//                System.out.println("Numero   "+(contador));
//                System.out.println("Anterior "+(anterior));
//                System.out.println("Actual   "+(actual)+"\n");
                if(negros>0 && negros<15) {
                    //System.out.println("Negros "+negros);
                    if((anterior)>50 && (actual)<20) {
                        orden="restar";
//                        System.out.println(orden+"\n");
                        Ensanchar(finanterior,orden);
                        negros=0;
                    }
                    if((anterior)<20 && (actual>50))  {
                        orden="sumar";
//                        System.out.println(orden+"\n");
                        Ensanchar(inicioGris,orden);
                        negros=0;
                    }
                    if((anterior)>50 && (actual)>20) {
                        orden="sumar";
//                        System.out.println(orden+"\n");
                        Ensanchar(inicioGris,orden);
                        negros=0;
                    }
                    if((anterior)<20 && (actual)<50) {
                        orden="sumar";
//                        System.out.println(orden+"\n");
                        Ensanchar(inicioGris,orden);
                        negros=0;
                    }
                    if(anterior>12 && actual>15 && actual<40) {
                        orden="sumar";
                        Ensanchar(inicioGris,orden);
                        negros=0;
                    }
                    if(anterior>90 && actual>90) {
                        orden="sumar";
//                        System.out.println(orden+"\n");
                        Ensanchar(inicioGris,orden);
                        negros=0;
                    }
                    if(anterior>50 && actual>50) {
                        orden="sumar";
                        Ensanchar(inicioGris,orden);
                        inicioGris+=60;
                        negros=0;
                    }
                }
                negros=0;
            }else {
                negros++;
                if (vuelta==1)
                    System.out.println("hola");
            }
        }
    }

    public static void Ensanchar(int A, String B) {
            if (B=="restar") {
                    for (int x=(A-15);x<=A;x++) {
                            for (int y = 0; y <= height-1; y++) {
                                    img.setRGB(x, y, rgbB);
                            }
                    }
                    //System.out.println(B);
            }else {
                    for (int x=A;x<=(A+15);x++) {
                            for (int y = 0; y <= height-1; y++) {
                                    img.setRGB(x, y, rgbB);
                            }
                    }
                    //System.out.println(B);
            }
    }

    static public boolean comprobar() {
        int X=0;
        int inicio=0;
        int fin=0;
        int vueltaa=0;
        int repollo=0;
        for (int x = 0; x <= width-1; x++) {
            Color pixel2 = new Color(img.getRGB(x, height-1));
            if (!(pixel2.getBlue()==0 && pixel2.getGreen()==0 && pixel2.getRed()==0)) {
                if (vueltaa == 0) {
                    X++;
                    inicio=x;
                    vueltaa=1;
                }
            }else if(vueltaa==1) {
                fin=x-1;
                vueltaa=0;
                switch(X) {
                case 1: if((fin-inicio)>150) {
                            repollo+=1;
                        }
                        break;
                case 2: if((fin-inicio)>90 && (fin-inicio)<130) {
                            repollo+=1;
                        }
                        break;
                case 3: if((fin-inicio)<60) {
                            repollo+=1;
                        }
                        break;
                }
            }
        }
        System.out.println(repollo);
        if (repollo==3)
            return true;
        else
            return false;
    }

    public static void EliminarLineasIntermedias(int X, int Y) {
            // X es el inicio de la franja e Y es el final de la franja
        if ((Y-X)<10) {
            for (int x = X; x <=Y; x++) {
                for (int y = 0; y <= height-1; y++) {
                    img.setRGB(x, y, rgbB);
                }
            }
        }
    }

    public static void EliminarLineasIntermedias2(int X, int Y) {
		// X es el inicio de la franja e Y es el final de la franja
        for (int x = X-1; x <=Y+1; x++) {

            for (int y = 0; y <= height-1; y++) {
                img.setRGB(x, y, rgbB);
            }
        }
    }
	
}
