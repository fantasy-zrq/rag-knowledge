package com.zrq.ai.app.test;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.ai.document.Document;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.PgVectorStore;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.PathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

/**
 * @author zrq
 * @time 2026/1/13 15:29
 * @description
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class JGitTest {
    @Resource
    private OllamaChatClient chatClient;

    @Resource
    private TokenTextSplitter tokenTextSplitter;

    @Resource
    private SimpleVectorStore simpleVectorStore;

    @Resource
    private PgVectorStore pgVectorStore;

   @Test
    public void cloneResp() throws IOException, GitAPIException {
        String username = "FFTX123";
        String token = "3ee696e4c6d07145b6faed1f1aec2790";

        String respUrl = "https://gitee.com/FFTX123/small-mybatis";
        String localPath = "./clone_resp";
        FileUtils.deleteDirectory(new File(localPath));
        Git.cloneRepository()
                .setURI(respUrl)
                .setDirectory(new File(localPath))
                .setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, token))
                .call()
                .close();
    }

    @Test
    public void test_file() throws IOException {

            Files.walkFileTree(Paths.get("./clone_resp"), new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                    log.info("文件路径:{}", file.toString());

                    PathResource resource = new PathResource(file);
                    TikaDocumentReader reader = new TikaDocumentReader(resource);

                    List<Document> documents = reader.get();
                    List<Document> documentSplitterList = null;
                    try {
                        documentSplitterList = tokenTextSplitter.apply(documents);
                    } catch (Exception e) {

                    }

                    documents.forEach(doc -> doc.getMetadata().put("knowledge", "clone_resp_rag"));
                    documentSplitterList.forEach(doc -> doc.getMetadata().put("knowledge", "clone_resp_rag"));

                    pgVectorStore.accept(documentSplitterList);

                    return FileVisitResult.CONTINUE;
                }
            });

    }
}
