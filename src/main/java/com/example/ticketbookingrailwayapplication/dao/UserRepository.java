package com.example.ticketbookingrailwayapplication.dao;

import com.example.ticketbookingrailwayapplication.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String userName);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.firstName = :#{#user.firstName},u.lastName = :#{#user.lastName},u.username = :#{#user.username},u.password = :#{#user.password} WHERE u.id = :id")
    int updateById(@Param("user") User user, @Param("id") long id);
    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.firstName = :#{#user.firstName},u.lastName = :#{#user.lastName},u.username = :#{#user.username} WHERE u.id = :id")
    int updateByIdNoPass(@Param("user") User user, @Param("id") long id);
}