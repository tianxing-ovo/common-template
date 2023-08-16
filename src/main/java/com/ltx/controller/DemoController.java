package com.ltx.controller;

import com.ltx.annotation.PreAuthorize;
import com.ltx.util.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

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
