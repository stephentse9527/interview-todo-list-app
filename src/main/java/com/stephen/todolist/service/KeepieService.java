package com.stephen.todolist.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public interface KeepieService {

    String getCredential(String receiveUrl);
}
