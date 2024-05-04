package com.stephen.todolist.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Credential implements Serializable {

    private String username;
    private String password;
}
