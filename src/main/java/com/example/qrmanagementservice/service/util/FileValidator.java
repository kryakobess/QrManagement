package com.example.qrmanagementservice.service.util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public class FileValidator {

    public static final Set<String> ALLOWED_EXTENSIONS = Set.of("png", "jpg", "jpeg", "svg");

    public static void validateMultipart(FilePart file) {
        var filename = file.filename();
        var extension = FilenameUtils.getExtension(filename);
        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            throw new IllegalStateException(String.format("Invalid extension for file: %s. " +
                    "File can have only these extension: %s", filename, ALLOWED_EXTENSIONS));
        }
    }

}
