package co.edu.uptc.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.security.SecureRandom;

public class Utils {
    public static void sleepThread(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getRedMessage() {
        return "\033[31m";
    }

    public static String getGreenMessage() {
        return "\033[32m";
    }

    public static String getYellowMessage() {
        return "\033[33m";
    }

    public static String getBlueMessage() {
        return "\033[34m";
    }

    public static String getPurpleMessage() {
        return "\033[35m";
    }

    public static String getCyanMessage() {
        return "\033[36m";
    }

    public static String getWhiteMessage() {
        return "\033[37m";
    }

    public static String getResetMessage() {
        return "\033[0m";
    }

    public static String getPinkMessage() {
        return "\033[95m";
    }

    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.drawImage(image, 0, 0, null);
        graphics2D.dispose();
        return bufferedImage;
    }

    public static SecureRandom getSecureRandom(String algorithmName) {
        try {
            return SecureRandom.getInstance(algorithmName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
