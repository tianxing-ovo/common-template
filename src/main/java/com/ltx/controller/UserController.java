package com.ltx.controller;

import com.ltx.annotation.PreAuthorize;
import com.ltx.entity.Result;
import com.ltx.entity.po.User;
import com.ltx.entity.request.UserRequestBody;
import com.ltx.exception.CustomException;
import com.ltx.mapper.UserMapper;
import com.ltx.util.UserContext;
import com.ltx.valid.InsertGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author tianxing
 */
@RestController
@Validated
@Slf4j
public class UserController {

    @Resource
    private UserMapper userMapper;

    /**
     * 查询用户信息
     *
     * @return 通用响应对象
     */
    @PreAuthorize(hasAnyRole = "admin")
    @GetMapping("/users")
    public Result query(@RequestParam("role") String role) {
        log.info("role: {}", role);
        return Result.success().put("user", UserContext.get());
    }

    /**
     * 查询指定用户
     */
    @GetMapping("/users/{id}")
    @Cacheable(value = "userCache", key = "T(com.ltx.constant.RedisConstant).CACHE_USER_KEY+#id", unless = "#result == null")
    public User query(@PathVariable Integer id) {
        return userMapper.selectById(id);
    }

    /**
     * 查询用户列表
     *
     * @param requestBody 请求体
     * @return 用户列表
     */
    @PostMapping("/users/list")
    @Cacheable(value = "userCache", key = "T(com.ltx.constant.RedisConstant).CACHE_USER_KEY + (#requestBody == null ? 'allUsers' : #requestBody.id + ':' + #requestBody.age + ':' + #requestBody.name)", unless = "#result == null || #result.size() == 0")
    public List<User> queryUserList(@RequestBody(required = false) UserRequestBody requestBody) {
        return userMapper.queryUserList(requestBody);
    }

    /**
     * 新增
     *
     * @param user 用户
     * @return 用户
     */
    @PostMapping("/users")
    @CachePut(value = "userCache", key = "#user.id")
    public User add(@RequestBody User user) {
        userMapper.add(user);
        return user;
    }

    /**
     * 删除
     *
     * @param id 用户id
     */
    @PostMapping("/users/{id}")
    @CacheEvict(value = "userCache", key = "#id")
    public void delete(@PathVariable Integer id) {
        userMapper.deleteById(id);
    }

    /**
     * 更新
     *
     * @param id 用户id
     */
    @PutMapping("/users/{id}")
    @CacheEvict(value = "userCache", key = "#id")
    public void update(@PathVariable Integer id) {
        User user = new User().setId(id).setAge(20);
        userMapper.updateById(user);
    }

    /**
     * 测试国际化功能
     */
    @GetMapping("/test-i18n")
    public void testInternationalization() {
        throw new CustomException(404, "未找到");
    }

    /**
     * 测试实体类校验功能
     *
     * @param requestBody 请求体
     * @return 通用响应对象
     */
    @PostMapping("/test-validation")
    public Result testValidation(@Validated(InsertGroup.class) @RequestBody UserRequestBody requestBody) {
        return Result.success().put("requestBody", requestBody);
    }

    /**
     * 测试单个参数校验功能
     *
     * @param name 名字
     * @return 通用响应对象
     */
    @GetMapping("/test-validation")
    public Result testValidation(@RequestParam("name") @Size(min = 1, max = 10) String name) {
        return Result.success().put("name", name);
    }
}
