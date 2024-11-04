package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        createTempDir();
        System.out.println("""
                watermark - add watermark to all downloaded pictures,\
                
                export - export all downloaded files as zip archive\
                
                help - show this message\
                
                exit - end program\
                
                entering ulr link will download try to download the file""");
        for (String input = new Scanner(System.in).nextLine(); !Objects.equals(input, "exit"); input = new Scanner(System.in).nextLine())
            processInput(input);
    }

    private static void createTempDir() {
        Path path = Paths.get("temp"), path2 = Paths.get("out");
        try {
            if (!Files.exists(path)) {
                Files.createDirectory(path);
                System.out.println("Created temp directory");
            } else System.out.println("temp directory already exists");
            if (!Files.exists(path2)) {
                Files.createDirectory(path2);
                System.out.println("Created out directory");
            } else System.out.println("out directory already exists");
        } catch (IOException e) {
            System.out.println("Failed to create a directory: " + e.getMessage());
        }
    }

    private static void processInput(String input) {
        switch (input) {
            case "watermark": {
                System.out.print("Enter text to watermark: ");
                String input2 = new Scanner(System.in).nextLine();
                for (File file : FileManage.getListOfFiles(new File("temp/"))) {
                    ManipImage.addWatermark(file.getPath(), input2);
                }
                System.out.println("Watermarks added");
            }
            break;
            case "export": {
                System.out.println("Exporting files...");
                if (FileManage.getListOfFiles(new File("temp/")).size() >= 3){
                    Archival.archiveDirectory(new File("temp/"));
                    FileManage.deleteAllFiles();
                    System.out.println("Files archived");
                } else if (FileManage.getListOfFiles(new File("temp/")).size() >= 10){
                    System.out.print("Enter encryption password: ");
                    String input2 = new Scanner(System.in).nextLine();
                    FileManage.deleteFilesIfTooLarge(new File("temp/"));
                    Archival.archiveDirectory(new File("temp/"), input2);
                    FileManage.deleteAllFiles();
                    System.out.println("Files archived");
                } else {
                    FileManage.moveFiles();
                }
            }
            break;
            case "help":
            {
                System.out.println("""
                        watermark - add watermark to all downloaded pictures,\
                        
                        export - export all downloaded files as zip archive\
                        
                        help - show this message\
                        
                        exit - end program\
                        
                        entering ulr link will download try to download the file""");
            }
            break;
            default:
                Downloader.downloadFile(input);
                break;
        }
    }
}