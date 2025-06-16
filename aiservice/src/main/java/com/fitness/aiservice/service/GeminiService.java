package com.fitness.aiservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class GeminiService {

    private final WebClient webClient;
    @Value("${gemini.api.url}")
    private String GEMINI_API_URL;
    @Value("${gemini.api.key}")
    private String GEMINI_API_KEY;

    public GeminiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String getAnswer(String question) {
        // 1. Validate input
        if (question == null || question.trim().isEmpty()) {
            throw new IllegalArgumentException("Question cannot be null or empty");
        }

        // 2. Build proper Gemini request structure
        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of(
                                "parts", List.of(
                                        Map.of("text", question) // Use the actual question parameter
                                )
                        )
                ),
                "generationConfig", Map.of(  // Optional: Add generation parameters
                        "temperature", 0.9,
                        "maxOutputTokens", 2048
                )
        );

        try {
            // 3. Execute request with timeout and better error handling
            return webClient.post()
                    .uri(GEMINI_API_URL + GEMINI_API_KEY)
                    .header("Content-Type", "application/json")
                    .bodyValue(requestBody)
                    .retrieve()
                    .onStatus(
                            status -> status.isError(),
                            response -> response.bodyToMono(String.class)
                                    .flatMap(errorBody -> Mono.error(new RuntimeException(
                                            "Gemini API error: " + response.statusCode() + " - " + errorBody
                                    )))
                    )
                    .bodyToMono(String.class)
                    .block(Duration.ofSeconds(30)); // Add reasonable timeout

        } catch (WebClientResponseException e) {
            // 4. Handle API errors specifically
            String errorDetails = e.getResponseBodyAsString();
            log.error("Gemini API Error ({}): {}", e.getStatusCode(), errorDetails);
            throw new RuntimeException("Failed to get answer from Gemini: " + errorDetails, e);

        } catch (Exception e) {
            // 5. Handle other errors (timeout, network, etc)
            log.error("Unexpected error calling Gemini API", e);
            throw new RuntimeException("Service temporarily unavailable", e);
        }
    }
}
