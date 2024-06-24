package com.jhh.rl.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jhh.rl.dto.response.user.LoginResponse;
import com.jhh.rl.dto.request.user.*;
import com.jhh.rl.entity.User;
import com.jhh.rl.mapper.UserMapper;
import com.jhh.rl.service.UserService;
import com.jhh.rl.utils.Result;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private HttpSession httpSession;


    /*
     * 登录功能：
     * 验证账号密码
     * 存储会话信息
     * 更新登陆时间
     * */
    @Override
    public Result<LoginResponse> findUserByAccount(LoginRequset login) {

        // 在数据库表查找用户
        User user = findUserByAccount(login.getAccount());

        // 账号不存在
        if (user == null) {
            return Result.fail("登录失败, 账户不存在");
        }

        // 密码错误
        if (!login.getPassword().equals(user.getPassword())) {
            return Result.fail("登录失败, 密码错误");
        }

        /*
         * httpSession: 这是一个HttpSession对象，用来存储与一个特定用户会话相关的信息
         * 每个用户都有自己的HttpSession对象
         * 在用户成功登录后，将用户的信息存储在会话中
         * 这样用户在不同的请求之间就无需重新进行身份验证
         * 这是Web应用中常见的做法，以保持用户登录状态
         * */
        httpSession.setAttribute("user", user);

        /*
         * 验证密码成功后，更新数据库中的用户登陆时间
         */

        // 获取登陆时间
        Date now = new Date(); // 获取当前的日期时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 设置你希望的格式
        String formattedLoginTime = formatter.format(now); // 格式化当前日期时间为字符串

        // 登陆时间更新到数据库
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("account", user.getAccount()).set("login_time", formattedLoginTime);
        int updateCount = userMapper.update(null, updateWrapper);

        // 检查是否成功更新了记录
        if (updateCount > 0) {
            // 更新成功的处理
            LoginResponse loginResponse = getLoginResponse(user, formattedLoginTime);
            return Result.ok("登录成功", loginResponse);
        } else {
            // 更新失败的处理
            return Result.fail("更新登陆时间时发生错误");
        }
    }


    /*
     * 根据用户id查询用户信息
     * */
    @Override
    public Result<User> getUserInfo(Integer id) {

        // id 不合法
        if (id == 0)
            return Result.fail("无效输入");

        // 查找用户
        User user = userMapper.selectById(id);

        // 用户不存在
        if (user == null) {
            return Result.fail("用户id不存在");
        }

        // 用户存在：密码字段置为空
        user.setPassword("");

        return Result.ok("获取用户信息成功", user);
    }

    /*
     * 根据用户id修改密码
     * */
    @Override
    public Result<Void> updatePassword(UpdatePasswordRequset updatePassword) {

        // 查找用户
        User user = userMapper.selectById(updatePassword.getUserId());

        //用户不存在
        if (user == null) {
            return Result.fail("用户不存在");
        }

        // 旧密码错误
        if (!updatePassword.getOldPassword().equals(user.getPassword())) {
            return Result.fail("旧密码错误");
        }

        // 新旧密码相同
        else if (updatePassword.getOldPassword().equals(updatePassword.getNewPassword())) {
            return Result.fail("新密码不能与旧密码相同");
        }

        // 更新数据库
        user.setPassword(updatePassword.getNewPassword());
        userMapper.updateById(user);

        return Result.ok("密码修改成功, 请重新登录");
    }

    /*
     * 根据用户id修改邮箱
     * */
    @Override
    public Result<Void> setEmail(UpdateEmailRequset updateEmail) {

        // 查找用户
        User user = userMapper.selectById(updateEmail.getUserId());

        // 用户不存在
        if (user == null) {
            return Result.fail("用户不存在");
        }

        // 更新数据库
        user.setEmail(updateEmail.getEmail());
        userMapper.updateById(user);

        return Result.ok("修改邮箱成功");
    }

    @Override
    public Result<Void> sendFeedback(FeedbackRequset feedBack) {

        return null;
    }

    /*
     * 根据account添加用户
     * */
    @Override
    public Result<Void> addUser(AddUserRequset addUser) {

        // 查找用户
        User user = findUserByAccount(addUser.getAccount());

        // 用户已存在
        if (null != user) {
            return Result.fail("用户名已存在，请重试");
        }

        // 密码不为空
        if (StringUtils.isNotBlank(addUser.getPassword())
                && StringUtils.isNotBlank(addUser.getConfirmedPassword())) {

            // 两次密码不一致
            if (!addUser.getPassword().equals(addUser.getConfirmedPassword())) {
                return Result.fail("两次密码不一致");
            }

            // 密码不为空且两次密码一致
            else {

                // 新建用户
                User newUser = new User();

                // 日期格式化
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

                newUser.setCreateTime(sdf.format(new Date()));
                newUser.setAccount(addUser.getAccount());
                newUser.setUsername(addUser.getUsername());
                newUser.setPassword(addUser.getPassword());
                newUser.setUserStatus(addUser.getUserStatus());

                // 插入用户
                int i = userMapper.insert(newUser);

                // 插入失败
                if (i == 0) {
                    return Result.fail("创建用户失败");
                }

                // 插入成功
                return Result.ok("创建用户成功");
            }
        }

        // 密码为空
        else {
            return Result.fail("密码不能为空");
        }
    }

    @Override
    public Result<List<User>> pagelist(GetUserListRequset getUserList) {
        IPage<User> ipage = new Page<>(getUserList.getPage_no(), getUserList.getPage_size());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        String account = getUserList.getAccount();
        String username = getUserList.getUsername();
        String user_status = getUserList.getUser_status();

        if (Objects.equals("全部", user_status) || user_status.isEmpty()) {
            queryWrapper.like("username", username)
                    .like("account", account);
        } else {
            queryWrapper.like("username", username)
                    .like("account", account)
                    .eq("user_status", user_status);
        }

        queryWrapper.orderByDesc("id");

        ipage = userMapper.selectPage(ipage, queryWrapper);
        List<User> list = ipage.getRecords();

        if (list.isEmpty()) return Result.fail("查询用户列表失败");

        return Result.ok("查询用户列表成功", list, list.size());
    }

    @Override
    public Result<Void> updateUserInfo(UpdateUserInfoRequset updateUserInfo) {

        // 根据id获取用户
        User user = userMapper.selectById(updateUserInfo.getUserId());

        // 用户不存在
        if (user == null) return Result.fail("用户不存在");

        // 校验修改后的用户状态
        if (!updateUserInfo.getUserStatus().equals("正常") && !updateUserInfo.getUserStatus().equals("封禁")) {
            return Result.fail("用户状态修改失败");
        }

        user.setUserStatus(updateUserInfo.getUserStatus());

        // 校验修改后的用户密码：不能为空
        if (!updateUserInfo.getPassword().isEmpty()) {
            user.setPassword(updateUserInfo.getPassword());
        }

        // 更新用户信息
        userMapper.updateById(user);

        return Result.ok("用户信息修改成功");
    }

    @Override
    public Result<Void> deluser(Integer id) {

        // id无效
        if (id == 0) return Result.fail("无效输入");

        // 封禁用户
        User user = userMapper.selectById(id);

        // 要封禁的用户不存在
        if (user == null) return Result.fail("用户不存在");

        // 封禁用户
        user.setUserStatus("封禁");
        int i = userMapper.updateById(user);

        // 封禁用户失败
        if (i == 0) return Result.fail("用户封禁失败");

        return Result.ok("用户已被封禁");
    }

    private User findUserByAccount(String account) {

        // QueryWrapper 是 MyBatis-Plus 提供的查询条件构造器，可以方便地构建复杂的查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 第一个参数是数据库列名
        queryWrapper.eq("account", account);
        return userMapper.selectOne(queryWrapper);
    }
    @NotNull
    private static LoginResponse getLoginResponse(User user, String formattedLoginTime) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUser_id(user.getId());
        loginResponse.setUsername(user.getUsername());
        loginResponse.setIdentity(user.getIdentity());
        loginResponse.setAccount(user.getAccount());
        loginResponse.setPassword("");
        loginResponse.setUser_status(user.getUserStatus());
        loginResponse.setCreate_time(user.getCreateTime());
        loginResponse.setLogin_time(formattedLoginTime);
        return loginResponse;
    }


}
