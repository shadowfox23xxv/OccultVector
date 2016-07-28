package dev.tilegame.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class ImageLoader {

    public static BufferedImage loadImage(String path){
        try{
            return ImageIO.read(ImageLoader.class.getResource(path)); //returns the image at the path passed to the method
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);//exit if image fails to load
        }
        return null;//required to avoid error as needed return statment is within the try/catch block
    }
}
