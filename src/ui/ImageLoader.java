package ui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	private static final String workingDir = System.getProperty("user.dir");
	private static final String pathSeparator = File.separator;
	private static BufferedImage blank;
	
	public ImageLoader() {
		blank = loadImage("src/img/blank.jpg");
	}
	
	public static BufferedImage loadImage(String fileSource) {
    	BufferedImage img = null;
		try {
			formatFileName(fileSource);
			img = ImageIO.read(new File(fileSource));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return img;
    }
	
	public static BufferedImage loadImage(String fileSource, double scale) {
		BufferedImage img = null;
		try {
			formatFileName(fileSource);
			img = ImageIO.read(new File(fileSource));
			//width = (int) Math.round(img.getWidth() * scale);
	        //height = (int) Math.round(img.getHeight() * scale);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return img;
    }
	
	private static String formatFileName(String fileName) {
		fileName = workingDir + pathSeparator + fileName;
    	if(!pathSeparator.equals("/")) {
    		fileName = fileName.replace("/", pathSeparator);
    	}
    	else if(!pathSeparator.equals("\\")) {
    		fileName = fileName.replace("\\", pathSeparator);
    	}
    	return fileName;
    }
	
	public static BufferedImage getBlankImage() {
		return blank;
	}
}
