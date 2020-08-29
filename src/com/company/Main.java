package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {

        List<String> objectsList = new ArrayList<>();
        GameProgress gameProgress1 = new GameProgress(10, 9, 8, 5.5);
        GameProgress gameProgress2 = new GameProgress(9, 7, 6, 20.5);
        GameProgress gameProgress3 = new GameProgress(15, 3, 12, 17.5);


        saveGame("D://JavaGames/savegames/save1.dat", gameProgress1);
        saveGame("D://JavaGames/savegames/save2.dat", gameProgress2);
        saveGame("D://JavaGames/savegames/save3.dat", gameProgress3);

        objectsList.add("D://JavaGames/savegames/save1");
        objectsList.add("D://JavaGames/savegames/save2");
        objectsList.add("D://JavaGames/savegames/save3");

        zipFiles("D://JavaGames/savegames/zip.zip", objectsList);


    }

    private static void saveGame(String filename, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }


    private static void zipFiles(String zipFileName, List<String> objectsList) {
        try (ZipOutputStream zout = new ZipOutputStream(new
                FileOutputStream(zipFileName))) {

            for (String note : objectsList) {

                try (FileInputStream fis = new FileInputStream(note + ".dat")) {
                    ZipEntry entry = new ZipEntry(note + "_packed.dat");
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
