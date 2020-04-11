package com.bes.image.web;

import com.bes.common.enums.ExceptionEnum;
import com.bes.common.exception.LeyouException;
import com.bes.image.service.ImageUploadService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("upload")
public class UploadImageController {
    @Autowired
    ImageUploadService imageUploadService;

    private static List<String> ALLOW_IMAGE_TYPES = Arrays.asList("image/jpg", "image/png", "image/bmp");

    @PostMapping("image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        String contextType = file.getContentType();
        if (!ALLOW_IMAGE_TYPES.contains(contextType)) {
            throw new LeyouException(ExceptionEnum.IMAGE_TYPE_NOT_ALLOW);
        }
        return ResponseEntity.ok(imageUploadService.imageUpload(file));
    }

}
