package com.zrq.ai.controller.test;

import com.zrq.ai.api.result.Result;
import org.springframework.ai.chat.ChatResponse;

/**
 * @author zrq
 * @time 2026/1/12 21:43
 * @description
 */
public class LearnTest {
    public static void main(String[] args) {
        LearnTest.<Long>test(5L);
        LearnTest.<String>test("zrq");

        Result.<ChatResponse>builder().code("1").data(null).info("haha").build();
    }

    private static <T> void test(T t) {
    }
}
