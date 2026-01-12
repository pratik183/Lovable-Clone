package com.lovable.com.lovable.entity;

import com.lovable.com.lovable.enums.MessageRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatMessage {

    Long id;

    ChatSession chatSession;

    String content;

    MessageRole role;

    String toolCalls; // JSON array of tools called

    Integer tokensUsed;

    Instant createdAt;
}
