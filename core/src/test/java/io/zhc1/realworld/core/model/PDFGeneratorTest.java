package io.zhc1.realworld.core.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class PDFGeneratorTest {

    private final String outputFilePath = "output_test.pdf";
    private final Path outputPath = Paths.get(outputFilePath);

    @Test
    void pdf_is_generated_successfully() {
        // Arrange
        PDFGenerator generator = new PDFGenerator();

        // Act
        generator.generatePdf("https://google.com", outputFilePath);

        // Assert
        File pdfFile = outputPath.toFile();
        assertThat(pdfFile).exists().as("PDF-filen ble ikke generert.");
        assertThat(pdfFile.length()).isGreaterThan(0).as("PDF-filen er tom.");
    }

    @AfterEach
    void cleanUp() {
        File pdfFile = outputPath.toFile();
        if (pdfFile.exists()) {
            boolean deleted = pdfFile.delete();
            assertThat(deleted).isTrue().as("PDF-filen kunne ikke slettes.");
        }
    }
}
