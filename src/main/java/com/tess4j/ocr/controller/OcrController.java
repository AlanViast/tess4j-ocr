package com.tess4j.ocr.controller;

import com.tess4j.ocr.service.OcrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/**
 * 图片识别接口
 *
 * @author AlanViast
 */
@RestController
@RequestMapping("ocr")
public class OcrController {

    @Autowired
    private OcrService ocrService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Map<String, Object> ocrImage(@RequestParam("file") MultipartFile file) {

        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            String result = ocrService.ocr(image);
            return Collections.singletonMap("result", result.trim());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
