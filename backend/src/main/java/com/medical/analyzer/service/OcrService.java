package com.medical.analyzer.service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class OcrService {

    public String extractText(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (fileName != null && fileName.toLowerCase().endsWith(".pdf")) {
            return extractTextFromPdf(file);
        } else {
            return extractTextFromImage(file);
        }
    }

    private String extractTextFromPdf(MultipartFile file) {
        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        } catch (IOException e) {
            throw new RuntimeException("Failed to extract text from PDF", e);
        }
    }

    private String extractTextFromImage(MultipartFile file) {
        try {
            Path tempFile = Files.createTempFile("ocr-", file.getOriginalFilename());
            file.transferTo(tempFile.toFile());

            Tesseract tesseract = new Tesseract();
            
            // Check for TESSDATA_PREFIX environment variable first (used in Docker/production)
            String tessDataEnv = System.getenv("TESSDATA_PREFIX");
            Path tessDataPath;
            
            if (tessDataEnv != null && !tessDataEnv.isEmpty()) {
                tessDataPath = Path.of(tessDataEnv);
            } else {
                // Fallback for local development
                tessDataPath = Path.of("tessdata");
                if (!Files.exists(tessDataPath)) {
                    tessDataPath = Path.of(System.getProperty("user.dir"), "tessdata");
                    if (!Files.exists(tessDataPath)) {
                        tessDataPath = Path.of(System.getProperty("user.dir"), "backend", "tessdata");
                    }
                }
            }
            
            System.out.println("Using tessdata path: " + tessDataPath.toAbsolutePath());
            
            if (!Files.exists(tessDataPath)) {
                throw new RuntimeException("tessdata directory not found at: " + tessDataPath.toAbsolutePath());
            }

            tesseract.setDatapath(tessDataPath.toAbsolutePath().toString()); 
            tesseract.setLanguage("eng");

            String result = tesseract.doOCR(tempFile.toFile());
            
            Files.deleteIfExists(tempFile);
            return result;
        } catch (IOException | TesseractException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to extract text from Image: " + e.getMessage(), e);
        }
    }
}
