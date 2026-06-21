package kup.moemoetun.shwegrammaroffline.utility;

public class FileUtils {
    public static String removeFileExtension(String fileName) {
        int extensionIndex = fileName.lastIndexOf(".");
        if (extensionIndex != -1) {
            return fileName.substring(0, extensionIndex);
        } else {
            return fileName;
        }
    }


}

