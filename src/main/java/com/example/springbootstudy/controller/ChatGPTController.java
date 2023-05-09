package com.example.springbootstudy.controller;

import com.example.springbootstudy.entity.ChatGptReuqest;
import com.plexpt.chatgpt.ChatGPT;
import com.plexpt.chatgpt.util.Proxys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xhq.service.ChatGptService;
import java.net.Proxy;

/**
 * @author xhq
 * @version 1.0
 * @description Create by 2023/2/21 13:17
 */
@RestController
@RequestMapping("/chatgpt")
public class ChatGPTController {


    @Autowired
    private ChatGptService chatGptService;


    @PostMapping("/res")
    public String response(@RequestBody ChatGptReuqest reuqest){
        //国内需要代理 国外不需要
        Proxy proxy = Proxys.http("127.0.0.1", 7890);


        ChatGPT chatGPT = ChatGPT.builder()
                .apiKey("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")
                .proxy(proxy)
                .apiHost("https://api.openai.com/") //反向代理地址
                .build()
                .init();

        String res = chatGPT.chat(reuqest.getMessages());
        System.out.println(res);
        return res;

//        ChatGPT chatGPT = ChatGPT.builder()
//                .apiKey("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")
//                .proxy(proxy)
//                .timeout(900)
//                .apiHost("https://api.openai.com/") //反向代理地址
//                .build()
//                .init();
//
//        Message system = Message.ofSystem("你现在是一个诗人，专门写七言绝句");
//        Message message = Message.of("写一段七言绝句诗，题目是：火锅！");
//
//        ChatCompletion chatCompletion = ChatCompletion.builder()
//                .model(ChatCompletion.Model.GPT_3_5_TURBO.getName())
//                .messages(Arrays.asList(system, message))
//                .maxTokens(3000)
//                .temperature(0.9)
//                .build();
//        ChatCompletionResponse response = chatGPT.chatCompletion(chatCompletion);
//        Message res = response.getChoices().get(0).getMessage();
//
//        String answer = res.getContent();
//
//        return answer;

    }

    @PostMapping("/chat")
    public String getChat(@RequestBody String question){
        String answer = chatGptService.sendMessage(question);

        return answer;
    }



}
