package com.zrq.ai.controller.serviceimpl;

import com.zrq.ai.api.IAiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * @author zrq
 * @time 2026/1/11 19:11
 * @description
 */
@Service
@RequiredArgsConstructor
@Slf4j(topic = "OllamaServiceImpl")
public class OllamaServiceImpl implements IAiService {

    private final OllamaChatClient ollamaChatClient;

    @Override
    public ChatResponse generate(String model, String message) {
        return ollamaChatClient.call(new Prompt(message, OllamaOptions.create().withModel(model)));
    }

    @Override
    public Flux<ChatResponse> generateStream(String model, String message) {
        return ollamaChatClient.stream(new Prompt(message, OllamaOptions.create().withModel(model)));
    }
}
