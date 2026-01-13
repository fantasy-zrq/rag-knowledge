package com.zrq.ai.controller.web;

import com.zrq.ai.api.IRAGService;
import com.zrq.ai.api.result.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author zrq
 * @time 2026/1/12 21:17
 * @description
 */
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@Slf4j(topic = "RAGController")
@RequestMapping("/api/v1/rag")
public class RAGController {

    private final IRAGService ragService;

    @GetMapping(value = "query_rag_tag_list")
    public Result<List<String>> queryRagTagList() {
        return ragService.queryRagTagList();
    }

    @PostMapping(value = "file/upload", headers = "content-type=multipart/form-data")
    public Result<String> uploadFiles(@RequestParam String ragTag, @RequestParam("files") List<MultipartFile> files) {
        return ragService.uploadFiles(ragTag, files);
    }

    @PostMapping(value = "analyze_git_repository")
    public Result<String> analyzeGitRepository(String repoUrl, String userName, String token) throws Exception {
        return ragService.analyzeGitRepository(repoUrl, userName, token);
    }

}
