package com.ltx.controller;


import com.ltx.constant.Constant;
import io.github.tianxingovo.common.R;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 上传和下载
 */
@RestController
@Slf4j
public class UploadAndDownloadController {

    /**
     * 单个文件上传
     *
     * @param file 文件
     */
    @PostMapping("/singleUpload")
    public R uploadFile(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            try {
                file.transferTo(Paths.get(Constant.BASE_PATH, fileName));
            } catch (IOException e) {
                log.error("错误信息:{},异常类型:{}", e.getMessage(), e.getClass());
                return R.error(201, "文件上传失败");
            }
            return R.ok("文件上传成功");
        }
        return R.error(201, "文件为空,请上传文件");
    }

    /**
     * 多个文件上传
     *
     * @param files 文件数组
     */
    @PostMapping("/multipleUpload")
    public R uploadFiles(@RequestParam("files") MultipartFile[] files) {
        List<String> successMessageList = new ArrayList<>(); //成功的消息
        List<String> errorMessageList = new ArrayList<>(); //失败的消息
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                try {
                    file.transferTo(Paths.get(Constant.BASE_PATH, fileName));
                    successMessageList.add(fileName + " 上传成功");
                } catch (IOException e) {
                    log.error("错误信息:{},异常类型:{}", e.getMessage(), e.getClass());
                    errorMessageList.add(fileName + " 上传失败");
                }
            } else {
                errorMessageList.add("文件为空,请上传文件");
            }
        }
        StringBuffer sb = new StringBuffer();
        successMessageList.forEach(s -> sb.append(s).append(" "));
        errorMessageList.forEach(s -> sb.append(s).append(" "));
        return R.ok(sb.toString());
    }

    /**
     * 文件下载
     *
     * @param fileName 文件名称
     */
    @SneakyThrows
    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> download(@PathVariable String fileName) {
        FileSystemResource resource = new FileSystemResource(Paths.get(Constant.BASE_PATH, fileName));
        HttpHeaders headers = new HttpHeaders();
        // "application/octet-stream"
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name());
        // 附件
        headers.setContentDispositionFormData("attachment", fileName);
        return ResponseEntity.ok().headers(headers).body(resource);
    }
}
