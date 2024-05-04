package com.stephen.todolist.service.impl;

import com.stephen.todolist.service.KeepieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KeepieServiceImpl implements KeepieService {

    @Value("${keepie.url}")
    private String keepieUrl;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public String getCredential(String receiveUrl) {
        try {
            // send request to keepie to get the credential
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String requestBody = "{\"receive_url\": \"" + receiveUrl + "\"}";
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            String responseBody = restTemplate.postForObject(keepieUrl + "/sendSecretToMe",
                    requestEntity,
                    String.class);

            return responseBody;
        } catch (Exception e) {
            // todo
        }
        return null;

    }
}
