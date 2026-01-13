package com.zrq.ai.api;

import com.zrq.ai.api.result.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IRAGService {

    Result<List<String>> queryRagTagList();

    Result<String> uploadFiles(String ragTag, List<MultipartFile> files);

    Result<String> analyzeGitRepository(String repoUrl, String userName, String token) throws Exception;
}
