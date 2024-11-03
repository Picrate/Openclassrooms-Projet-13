package com.yourcaryourway.chatapp.controller;

import com.yourcaryourway.chatapp.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

/**
 * Controller class for handling chat-related functionality.
 */
@Controller
public class ChatController {

    /**
     * Registers a user for chat.
     *
     * param chatMessage The chat message containing the sender's information.
     * param headerAccessor The SimpMessageHeaderAccessor object used to access session attributes.
     * return The registered chat message.
     */
    @MessageMapping("/register")
    @SendTo("/topic/public")
    public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

    /**
     * Sends a chat message to all connected users.
     *
     * param chatMessage The chat message to be sent.
     * return The sent chat message.
     */
    @MessageMapping("/send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }
}