package edu.hust.it4409.common.util;

public class EnumNameUtils {
    
    private EnumNameUtils() {
    }
    
    public static String formatName(String name) {
        return name.replace("_", " ");
    }
}
