package cn.easybuy.mapper;

import cn.easybuy.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
   public int add(User user) throws Exception;//新增用户信息

   public  int update(User user) throws Exception;//更新用户信息

    public int deleteUserById(@Param("id") String id) throws Exception;

    public List<User> getUserList(@Param("currentPageNo") Integer currentPageNo,@Param("pageSize") Integer pageSize)throws Exception;

    public Integer count() throws Exception;

    public User getUser(@Param("id")Integer id,@Param("loginName")String loginName) throws Exception;
}
