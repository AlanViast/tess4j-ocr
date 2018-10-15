package com.tess4j.ocr.service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 验证码识别服务
 *
 * @author AlanViast
 */
@Service
public class OcrService {

    private Tesseract tessreact = null;

    @PostConstruct
    public void init() {
        tessreact = new Tesseract();
        try {
            String dataDir = new ClassPathResource("tessdata").getFile().getAbsolutePath();
            tessreact.setDatapath(dataDir);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 识别服务
     *
     * @param image 图片文件对象
     * @return 识别结果
     */
    public String ocr(BufferedImage image) {
        try {
            return tessreact.doOCR(image);
        } catch (TesseractException e) {
            throw new RuntimeException(e);
        }
    }

}
