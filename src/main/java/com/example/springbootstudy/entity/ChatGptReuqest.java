package com.example.springbootstudy.entity;

import lombok.Data;

/**
 * @author xhq
 * @version 1.0
 * @description Create by 2023/3/31 20:44
 */
@Data
public class ChatGptReuqest {

    private String messages;
    private String model;
    private String stream;
    private String temperature;
    private String top_p;
}
