package com.javarush.borisov.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Если в каталоге нет подкаталогов, то каталог не учитывается.
 * Это связано с тем что заявки могут находиться только в папках
 * с месяцами. (а ещё у меня не хватило мозгов на их добавление :) )
 */

public class FolderScanner {
    List<String> foundedFolders = new ArrayList<>();
    List<String> foundedSubFolders = new ArrayList<>();
    Path rootPath;
    boolean subFolders = false;

    public List<String> scan(String path, Boolean subFolders) {

        boolean stop;
        path = path.replaceAll("\\\\", "/");
        URL resource = FolderScanner.class.getClassLoader().getResource(path);
        if (resource == null) {
            return null;
        }
        try {
            rootPath = Path.of(resource.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(rootPath, Files::isDirectory)) {
            for (Path paths : stream) {
                if (subFolders) {
                    foundedSubFolders.add(paths.getFileName().toString());
                    this.scan(path + "/" + paths.getFileName().toString(), false);
                } else {
                    if (!foundedSubFolders.isEmpty()) {
                        foundedFolders.add(foundedSubFolders.getLast() + "/" + paths.getFileName().toString());
                    } else {
                        foundedFolders.add(paths.getFileName().toString());
                    }
                }
            }
            return foundedFolders;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}