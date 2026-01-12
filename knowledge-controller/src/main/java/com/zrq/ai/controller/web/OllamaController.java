package com.zrq.ai.controller.web;

import com.zrq.ai.api.IAiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

/**
 * @author zrq
 * @time 2026/1/11 19:11
 * @description
 */
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@Slf4j(topic = "OllamaController")
@RequestMapping("/api/v1/ollama")
public class OllamaController {

    private final IAiService iAiService;

    @GetMapping("/generate")
    public ChatResponse generate(@RequestParam("model") String model, @RequestParam("message") String message) {
        return iAiService.generate(model, message);
    }

    @GetMapping(value = "/generateStream", produces = "text/event-stream")
    public Flux<ChatResponse> generateStream(@RequestParam("model") String model, @RequestParam("message") String message) {
        return iAiService.generateStream(model, message);
    }
}
