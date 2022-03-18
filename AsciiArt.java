import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.lang.Math;

public class AsciiArt {
    
    public static final String[] ASCII_MAP = {"N","@","M","W","0","8","#","D","K","6","A","%","P","S","4","k","V","e","o","I","u","*","x","7","L","[","c","l","|","/","r",";","\"","!",":","_",",","-","'","`"};

    public static void main(String[] args) throws IOException {
        double[][] image = imageToArray("input.png"); // INPUT LINE
        PrintStream myOut = new PrintStream(new File("out.txt")); // OUTPUT LINE
        for(int i = 0; i < image.length; i++) {
            myOut.println(arrayToAscii(image[i]));
        }
    }

    public static double[][] imageToArray(String filename) throws IOException {
        BufferedImage image = ImageIO.read(new File(filename));
        int height = image.getHeight();
        int width = image.getWidth();
        double[][] arr = new double[height][width];
        Color pixelColor = null;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pixelColor = new Color(image.getRGB(j, i));
                arr[i][j] = (pixelColor.getRed() * 0.299) + (pixelColor.getGreen() * 0.114) + (pixelColor.getBlue() * 0.587);
            }
        }
        return arr;
    }

    public static String arrayToAscii(double[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(ASCII_MAP[(int)Math.round(arr[i]/(255.0/(ASCII_MAP.length - 1)))]);
        }
        return sb.toString();
    }

}
