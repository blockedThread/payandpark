package com.payandpark.payandpark.user.repository;

import com.payandpark.payandpark.exception.ResourceNotFoundException;
import com.payandpark.payandpark.exception.ResourceNotSavedException;
import com.payandpark.payandpark.user.model.CreateUserRequest;
import com.payandpark.payandpark.user.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Log4j2
@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createUser(CreateUserRequest request) {
        String sql = "insert into pl.user_master (mobile) values(" + request.getMobile() + ")";
        try {
            log.info("Query :: {}", sql);
            jdbcTemplate.execute(sql);
        } catch (Exception e) {
            log.error("Unable to create user for mobile :: {}", request.getMobile());
            throw new ResourceNotSavedException("Unable to create user");
        }
    }

    public User fetchUserById(int userId) {
        String sql = "select id, mobile from pl.user_master where id = " + userId;
        try {
            log.info("Query :: {}", sql);
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class));
        } catch (Exception e) {
            log.error("Unable to fetch user by id :: {}", userId);
            throw new ResourceNotFoundException("Unable to fetch user by id :: " + userId);
        }
    }
}
