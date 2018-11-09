package com.zcx.dao;
import com.zcx.base.dao.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.zcx.entity.User;

@Mapper
public interface UserDao {
    User findById(@Param("id") String id);
}
