package com.example.springbootstudy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xhq
 * @version 1.0
 * @description Create by 2023/3/23 10:28
 */
@RestController
public class SearchController {
    @GetMapping("/search")
    public String search(@RequestParam("query") String query) {
        // 调用搜索引擎API进行搜索
        return "success";
    }
}
