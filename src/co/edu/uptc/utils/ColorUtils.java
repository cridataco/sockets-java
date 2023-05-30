package co.edu.uptc.utils;

public class ColorUtils {
    public static final String RED = "\033[31m";
    public static final String GREEN = "\033[32m";
    public static final String YELLOW = "\033[33m";
    public static final String BLUE = "\033[34m";
    public static final String PURPLE = "\033[35m";
    public static final String CYAN = "\033[36m";
    public static final String WHITE = "\033[37m";
    public static final String RESET = "\033[0m";
    public static final String PINK = "\033[95m";

    public static String getColorHex(int color) {
        return String.format("#%06X", (0xFFFFFF & color));
    }
}