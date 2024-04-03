package com.dardeus.demos.ai.tti.ctrl;

import com.dardeus.demos.ai.tti.ctrl.dto.ImageProposal;
import com.dardeus.demos.ai.tti.ctrl.dto.ImageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.image.ImageClient;
import org.springframework.ai.image.ImageMessage;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.stabilityai.StyleEnum;
import org.springframework.ai.stabilityai.api.StabilityAiImageOptions;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * TextToImageCtrl summary here...
 *
 * @author rbresler
 **/
@RestController
@CrossOrigin(origins = "*")
public class TextToImageCtrl {

    private static final Logger log = LoggerFactory.getLogger(TextToImageCtrl.class);

    private final ImageClient imageClient;

    public TextToImageCtrl(ImageClient imageClient) {
        this.imageClient = imageClient;
    }

    @PostMapping("/generate-image")
    public ImageProposal generateImage(@RequestBody ImageRequest request) {
        log.info("Received text-to-image request: {}", request);

        int width = request.width() % 64 == 0 ? request.width() : request.width() + 64 - (request.width() % 64);
        int height = request.height() % 64 == 0 ? request.height() : request.height() + 64 - (request.height() % 64);
        ImagePrompt prompt = new ImagePrompt(new ImageMessage(request.prompt()),
                StabilityAiImageOptions.builder()
                        .withStylePreset(StyleEnum.PHOTOGRAPHIC)
                        .withN(request.samples())
                        .withHeight(height)
                        .withWidth(width)
                        .build());
        ImageResponse response = imageClient.call(prompt);
        log.trace("Received image response metadata: {}", response.getMetadata());
        return new ImageProposal(response.getResults().stream()
                .map(imageGeneration -> imageGeneration.getOutput().getB64Json())
                .toList());
    }

    /**
     * Save image to file
     *
     * @param filename    image filename
     * @param base64Image base64 image content
     */
    private void saveImageToFile(String filename, String base64Image) {
        try {
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);
            Path filePath = Paths.get(filename);
            Files.write(filePath, imageBytes);
            log.trace("Image saved to file: {}", filename);
        } catch (IOException e) {
            log.error("Failed to save image to file: {}", e.getMessage());
        }
    }
}
