package com.javarush.khmelov.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

public class ImageService {

    private static final String IMAGES_FOLDER = "images";
    private static final String PART_NAME = "image";
    private static final String NO_IMAGE_PNG = "no-image.png";
    private static final List<String> EXTENSIONS = List.of(
            ".jpg", ".jpeg", ".png", ".bmp", ".gif", ".webp"
    );

    public final Path WEB_INF = Paths.get(URI.create(
                    Objects.requireNonNull(
                            ImageService.class.getResource("/")
                    ).toString()))
            .getParent();

    private final Path imagesFolder = WEB_INF.resolve(IMAGES_FOLDER);

    @SneakyThrows
    public ImageService() {
        Files.createDirectories(imagesFolder);
    }


    @SneakyThrows
    public Path getImagePath(String filename) {
        return EXTENSIONS.stream()
                .map(ext -> imagesFolder.resolve(filename + ext))
                .filter(Files::exists)
                .findAny()
                .orElse(imagesFolder.resolve(NO_IMAGE_PNG));
    }

    public void uploadImage(HttpServletRequest req, String imageId) throws IOException, ServletException {
        Part data = req.getPart(PART_NAME);
        if (Objects.nonNull(data) && data.getInputStream().available() > 0) {
            String filename = data.getSubmittedFileName();
            String ext = filename.substring(filename.lastIndexOf("."));
            deleteOldFiles(imageId);
            filename = imageId + ext;
            uploadImageInternal(filename, data.getInputStream());
        }
    }

    private void deleteOldFiles(String filename) {
        EXTENSIONS.stream()
                .map(ext -> imagesFolder.resolve(filename + ext))
                .filter(Files::exists)
                .forEach(p -> {
                    try {
                        Files.deleteIfExists(p);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @SneakyThrows
    private void uploadImageInternal(String name, InputStream data) {
        try (data) {
            if (data.available() > 0) {
                Files.copy(data, imagesFolder.resolve(name), StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

}
