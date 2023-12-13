package com.bao.auth.service;


import com.bao.auth.common.Md5Util;
import com.bao.auth.common.R;
import com.bao.auth.common.TokenGenerator;
import com.bao.auth.dao.UserMapper;
import com.bao.auth.model.SysUserTokenEntity;
import com.bao.auth.model.User;
import com.bao.auth.model.UserLoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service("userService")
public class UserService {

    //默认的TOKEN过期时间
    private final static int EXPIRE = 3600 * 12;

    @Autowired
    private UserMapper userMapper;

//    @Autowired
//    public UserService(UserMapper userMapper) {
//        this.userMapper = userMapper;
//    }
    public User getUserByUserId(String userId) {
        return userMapper.getUserByUserId(userId);
    }

    public R login(UserLoginForm form){
        //判断用户和密码
        String psw = form.getPassword();
        psw = Md5Util.getMd5(psw);
        User user = userMapper.getUserByUserId(form.getUserId());
        if(user == null || !psw.equals(user.getUserPassword())) {
            return R.error("账号或密码不正确");
        }

        if(!user.getUserStatus()){
            return R.error("账号已被锁定,请联系管理员");
        }

        //生成token返回
        R r = createToken(user.getNo());
        r.put("user",user);

        return r;
    }


    private R createToken(long userNo) {

        //生成一个token
        String token = TokenGenerator.generateValue();

        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        SysUserTokenEntity tokenEntity = userMapper.getTokenByNo(userNo);
        if(tokenEntity == null){
            tokenEntity = new SysUserTokenEntity();
            tokenEntity.setUserNo(userNo);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //保存token
            userMapper.saveToken(tokenEntity);
        }else{
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //更新token
            userMapper.updateTokenByNo(tokenEntity);
        }

        R r = R.ok().put("token", token).put("expire", EXPIRE);

        return r;
    }

}
