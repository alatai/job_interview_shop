package com.saihou.mapper;

import com.saihou.entity.User;
import com.saihou.query.UserQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ユーザ
 */
@Repository("userMapper")
public interface UserMapper {

    List<User> findAll();

    User findByName(String name);

    User findById(Integer id);

    int insert(User user);

    int update(User user);

    int delete(Integer id);

    User findByCondition(UserQuery query);
}