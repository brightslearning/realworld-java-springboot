package io.zhc1.realworld.core.model;

import com.microsoft.playwright.*;
import java.nio.file.Paths;

public class PDFGenerator {

    public void generatePdf(String url, String outputFilePath) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            Page page = browser.newPage();
            page.navigate(url);
            page.pdf(new Page.PdfOptions()
                    .setPath(Paths.get(outputFilePath))
                    .setFormat("A4"));
            browser.close();
            System.out.println("PDF generert: " + outputFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate PDF", e);
        }
    }

    public static void main(String[] args) {
        PDFGenerator generator = new PDFGenerator();
        generator.generatePdf("https://google.com", "output.pdf");
    }
}
