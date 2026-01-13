package com.zrq.ai.api.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zrq
 * @time 2026/1/12 21:06
 * @description
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Result<T> {
    private String code;
    private String info;
    private T data;
}
