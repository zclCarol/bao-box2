package com.bao.auth.dao;

import com.bao.auth.model.SysUserTokenEntity;
import com.bao.auth.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("Select * From sys_user Where User_Id = #{userId}")
    User getUserByUserId(@Param("userId") String userId);

    @Select("Select * From user_token Where User_No = #{userNo}")
    SysUserTokenEntity getTokenByNo(@Param("userNo") Long userNo);

    @Insert({"INSERT INTO user_token ",
            " (",
            "   User_No, Token, Expire_Time, Update_Time",
            " )",
            " VALUES ",
            " (",
            "   #{tokenEntity.userNo}, #{tokenEntity.token}, ",
            "   #{tokenEntity.updateTime}, #{tokenEntity.expireTime} ",
            " )"})
    int saveToken(@Param("tokenEntity") SysUserTokenEntity tokenEntity);

    @Update({"Update user_token ",
            " Set Token = #{tokenEntity.token}, ",
            "     Expire_Time = #{tokenEntity.updateTime}, ",
            "     Update_Time =  #{tokenEntity.expireTime} ",
            "Where User_No = #{tokenEntity.userNo}"})
    int updateTokenByNo( @Param("tokenEntity") SysUserTokenEntity tokenEntity);


}
