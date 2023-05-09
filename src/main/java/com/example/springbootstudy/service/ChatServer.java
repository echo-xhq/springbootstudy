package com.example.springbootstudy.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xhq
 * @version 1.0
 * @description Create by 2023/3/25 13:34
 */
@ServerEndpoint("/chat")
@Component
public class ChatServer {

    // 存储WebSocket会话列表
    private static List<Session> sessions = new ArrayList<>();

    @OnOpen
    public void onOpen(Session session) {
        // 添加WebSocket会话到列表
        sessions.add(session);
        System.out.println("WebSocket连接已打开");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            // 解析JSON格式的消息
            JSONObject data = JSONObject.parseObject(message);
            // 获取消息发送者和消息内容
            String from = data.getString("from");
            String to = data.getString("to");
            String content = data.getString("message");
            // 构造返回消息
            JSONObject response = new JSONObject();
            response.put("from", "server");
            response.put("message", content);
            // 标记消息来自web端还是服务器端
            if ("web".equals(from)) {
                response.put("fromType", "web");
            } else {
                response.put("fromType", "server");
            }
            // 如果消息接收者是服务器，则将消息发送给所有客户端
            if ("server".equals(to)) {
                for (Session s : sessions) {
                    s.getBasicRemote().sendText(response.toJSONString());
                }
            } else {
                // 如果消息接收者是客户端，则
                for (Session s : sessions) {
                    if (s.getId().equals(to)) {
                        s.getBasicRemote().sendText(response.toJSONString());
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @OnClose
    public void onClose(Session session) {
        // 从WebSocket会话列表中移除会话
        sessions.remove(session);
        System.out.println("WebSocket连接已关闭");
    }

}