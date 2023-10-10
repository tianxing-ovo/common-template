package com.ltx.controller;

import com.ltx.annotation.PreAuthorize;
import io.github.tianxingovo.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限控制
 */
@RestController
@Slf4j
public class AuthorityController {


    /**
     * 实现自定义权限控制
     */
    @PreAuthorize(hasAnyRole = "admin")
    @GetMapping("/demo")
    public R demo(@RequestParam("role") String role) {
        System.out.println(role);
        return R.ok("success");
    }
}
