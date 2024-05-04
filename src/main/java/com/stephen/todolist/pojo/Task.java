package com.stephen.todolist.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Task implements Serializable {
    @ApiModelProperty(readOnly = true)
    private Long id;
    private String name;
    private String description;
    private int status;
}
