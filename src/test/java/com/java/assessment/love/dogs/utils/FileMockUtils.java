package com.java.assessment.love.dogs.utils;

import com.java.assessment.love.dogs.controller.DogImageController;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileMockUtils {

    public static MockMultipartFile getMockMultipartFile(String name, String originalFilename) throws URISyntaxException {
        Path path = Paths.get(DogImageController.class.getResource(name).toURI());
        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
        }
        MockMultipartFile file = new MockMultipartFile("file", originalFilename,
                MediaType.APPLICATION_OCTET_STREAM_VALUE, content);
        return file;
    }
}
