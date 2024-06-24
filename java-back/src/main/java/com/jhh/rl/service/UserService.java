package com.jhh.rl.service;

import com.jhh.rl.dto.response.user.LoginResponse;
import com.jhh.rl.dto.request.user.*;
import com.jhh.rl.entity.User;
import com.jhh.rl.utils.Result;

import java.util.List;

public interface UserService {
    /*
    * 根据用户名查找用户
    * */
    Result<LoginResponse> findUserByAccount(LoginRequset login);

    Result<User> getUserInfo(Integer userId);

    Result<Void> updatePassword(UpdatePasswordRequset updatePassword);

    Result<Void> setEmail(UpdateEmailRequset updateEmail);

    Result<Void> sendFeedback(FeedbackRequset feedBack);

    Result<Void> addUser(AddUserRequset addUser);

    Result<List<User>> pagelist(GetUserListRequset getUserList);

    Result<Void> updateUserInfo(UpdateUserInfoRequset updateUserInfo);

    Result<Void> deluser(Integer id);


}
