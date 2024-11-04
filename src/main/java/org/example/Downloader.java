package org.example;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Downloader {
    private static final String temp = System.getProperty("user.dir") + "/temp/";

    public static void downloadFiles(List<String> urls) {
        for (String url : urls) {
            downloadFile(url);
        }
    }

    public static void downloadFile(String fileURL) {
        try {
            URL url = new URL(fileURL);
            String path = url.getPath();
            path = path.substring(path.lastIndexOf('/') + 1);
            try (InputStream in = new BufferedInputStream(url.openStream());
                 FileOutputStream fos = new FileOutputStream(temp + path)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer, 0, buffer.length)) != -1) {
                    fos.write(buffer, 0, length);
                }
                System.out.println("File downloaded");
            } catch (FileNotFoundException e) {
                System.out.println("Temp directory has not been found. Unable to save file: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Unknown problem saving file: " + e.getMessage());
            }
        } catch (MalformedURLException e) {
            System.out.println("Invalid file url: " + e.getMessage());
        }
    }
}
