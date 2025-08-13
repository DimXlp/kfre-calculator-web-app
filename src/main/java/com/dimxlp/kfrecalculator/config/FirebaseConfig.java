package com.dimxlp.kfrecalculator.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {
    @PostConstruct
    public void init() throws Exception {
        if (FirebaseApp.getApps().isEmpty()) {
            String json = System.getenv("FIREBASE_CREDENTIALS_JSON");
            var builder = FirebaseOptions.builder();
            if (json != null && !json.isBlank()) {
                builder.setCredentials(
                    GoogleCredentials.fromStream(
                        new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8)))
                );
            } else {
                builder.setCredentials(GoogleCredentials.getApplicationDefault());
            }
            FirebaseApp.initializeApp(builder.build());
        }
    }
}
