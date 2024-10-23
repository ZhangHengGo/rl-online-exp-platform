package com.jhh.rl.controller;

import com.jhh.rl.dto.response.user.LoginResponse;
import com.jhh.rl.dto.request.user.*;
import com.jhh.rl.entity.User;
import com.jhh.rl.service.UserService;
import com.jhh.rl.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 自动将数据转化为json
public class UserController {
    @Autowired
    private UserService userService;

    /*
    * 用户登录
    * */
    @PostMapping("/user/login")
    public Result<LoginResponse> login(@Validated @RequestBody LoginRequset login) {
        /*
        @RequestBody 告诉 Spring 框架需要将 HTTP 请求体中的 JSON 或 XML 数据转换为 Java 对象
        @Validated 可以用Login类的规则进行数据验证
        */
        System.out.println("请求");
        return userService.findUserByAccount(login);

    }

    /*
     * 根据用户id获取用户信息
     * */
    @GetMapping("/user/getuserinfo/{user_id}")
    public Result<User> getUserInfo(@PathVariable("user_id") Integer userId) {
        return userService.getUserInfo(userId);
    }

    /*
    * 更新密码
    * */
    @PostMapping("/user/updatepwd")
    public Result<Void> updatePassword(@RequestBody UpdatePasswordRequset updatePassword) {
        return userService.updatePassword(updatePassword);
    }

    /*
     * 更新邮箱
     * */
    @PostMapping("/user/setemail")
    public Result<Void> setEmail(@RequestBody UpdateEmailRequset email) {
        return userService.setEmail(email);
    }

    /*
     * 发送问题反馈
     * */
    @PostMapping("/user/sendfeedback")
    public Result<Void> sendFeedback(@RequestBody FeedbackRequset feedBack) {
        return userService.sendFeedback(feedBack);
    }


    /*
    * 以下是后台操作
    * */

    /*
     * 添加用户
     * */
    @PostMapping("/back/user/adduser")
    public Result<Void> adduser(@RequestBody AddUserRequset addUser) {
        return userService.addUser(addUser);
    }

    /*
     * 分页获取用户列表
     * */
    @PostMapping("/back/user/getuserlist")
    public Result<List<User>> pagelist(@RequestBody GetUserListRequset getUserList) {
        return userService.pagelist(getUserList);
    }

    /*
     * 修改用户信息
     * */
    @PostMapping("/back/user/updateuserinfo")
    private Result<Void> updateUserInfo(@RequestBody UpdateUserInfoRequset updateUserInfo) {
        return userService.updateUserInfo(updateUserInfo);
    }

    /*
     * 删除用户
     * */
    @GetMapping("/back/user/deluser")
    public Result<Void> deluser(@RequestParam("user_id") Integer id) {
        return userService.deluser(id);
    }
}
