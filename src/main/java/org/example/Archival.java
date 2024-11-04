package org.example;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Archival {
    private static final String out = "out/";



    public static void archiveFiles(List<File> files, String password){
        try (ZipFile zipFile = new ZipFile(out + System.currentTimeMillis() + ".zip")){
            ZipParameters zipParameters = new ZipParameters();
            zipParameters.setCompressionMethod(CompressionMethod.DEFLATE);
            zipParameters.setEncryptionMethod(EncryptionMethod.AES);
            zipParameters.setEncryptFiles(true);
            zipFile.setPassword(password.toCharArray());
            zipFile.addFiles(files, zipParameters);
        } catch (IOException e) {
            System.out.println("Cannot create an archive: " + e.getMessage());
        }
    }

    public static void archiveFiles(List<File> files){
        try (ZipFile zipFile = new ZipFile(out + System.currentTimeMillis() + ".zip")){
            ZipParameters zipParameters = new ZipParameters();
            zipParameters.setCompressionMethod(CompressionMethod.DEFLATE);
            zipFile.addFiles(files, zipParameters);
        } catch (IOException e) {
            System.out.println("Cannot create an archive: " + e.getMessage());
        }
    }

    public static void archiveDirectory(File folder){
        try (ZipFile zipFile = new ZipFile(out + System.currentTimeMillis() + ".zip")){
            ZipParameters zipParameters = new ZipParameters();
            zipParameters.setCompressionMethod(CompressionMethod.DEFLATE);
            zipFile.addFolder(folder, zipParameters);
        } catch (IOException e) {
            System.out.println("Cannot create an archive: " + e.getMessage());
        }
    }

    public static void archiveDirectory(File folder, String password){
        try (ZipFile zipFile = new ZipFile(out + System.currentTimeMillis() + ".zip")){
            ZipParameters zipParameters = new ZipParameters();
            zipParameters.setCompressionMethod(CompressionMethod.DEFLATE);
            zipParameters.setEncryptionMethod(EncryptionMethod.AES);
            zipParameters.setEncryptFiles(true);
            zipFile.setPassword(password.toCharArray());
            zipFile.addFolder(folder, zipParameters);
        } catch (IOException e) {
            System.out.println("Cannot create an archive: " + e.getMessage());
        }
    }




}
