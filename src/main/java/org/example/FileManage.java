package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FileManage {

    public static void deleteFilesIfTooLarge(File folder) {
        if (getDirectorySize(folder) >= 100)
            for (List<File> files = sortListByDate(getListOfFiles(folder)); getDirectorySize(folder) > 50; deleteFile(files.get(0)));
    }

    public static List<File> sortListByDate(List<File> fileList) {
        fileList.sort(Comparator.comparingLong(File::lastModified));
        return fileList;
    }

    public static List<File> getListOfFiles(File folder) {
        List<File> files = new ArrayList<>();
        if (folder.exists() && folder.isDirectory()) {
            File[] files1 = folder.listFiles();
            for (File file : files1) {
                if (file.isDirectory())
                    files.addAll(getListOfFiles(file));
                else files.add(file);
            }
        }
        return files;
    }

    public static long getDirectorySize(File folder) {
        long size = 0;
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            for (File file : files) {
                if (file.isDirectory())
                    size += getDirectorySize(file);
                else size += file.length();
            }
        }
        return size / (1024 * 1024);
    }

    public static void deleteFile(File fileToDelete) {
        if (fileToDelete.isFile()) {
            fileToDelete.delete();
        }
    }

    public static void moveFiles() {
        try {
            for (File file : FileManage.getListOfFiles(new File("temp/"))) {
                Files.move(Paths.get(file.getPath()), Paths.get("out/"));
            }
        } catch (IOException e) {
            System.out.println("Cannot move files to \"out/\"");
        }
    }

    public static void deleteAllFiles() {
        for (File file : FileManage.getListOfFiles(new File("temp/"))) {
            FileManage.deleteFile(file);
        }
    }
}
