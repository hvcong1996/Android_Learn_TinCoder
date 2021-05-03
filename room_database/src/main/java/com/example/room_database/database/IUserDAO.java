package com.example.room_database.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.room_database.User;

import java.util.List;

@Dao
public interface IUserDAO {

    @Query("SELECT * FROM user")
    List<User> getUsers();

    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM user WHERE name = :userName")
    List<User> checkUser(String userName);

    @Update
    void UpdateUser(User user);

    @Delete
    void DeleteUser(User user);

    @Query("DELETE FROM user")
    void DeleteAllUser();

    @Query("SELECT * FROM user WHERE name LIKE '%' || :userName || '%'")
    List<User> SearchUser(String userName);
}
