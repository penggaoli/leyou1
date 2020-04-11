package com.bes.image.service;
// 上传图片到本地
import com.bes.common.enums.ExceptionEnum;
import com.bes.common.exception.LeyouException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Service
@Slf4j
public class ImageUploadService {

    public String imageUpload(MultipartFile file) {
        try {
        // 保存文件到本地
            File fileAddress = new File("F://",file.getOriginalFilename());
            file.transferTo(fileAddress);
            return fileAddress.getPath();
        } catch (IOException e) {
            log.error("上传文件失败！", e);
            throw new LeyouException(ExceptionEnum.IMAGE_UPLOAD_FIELD);
        }
    }
}
