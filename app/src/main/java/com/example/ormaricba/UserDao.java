package com.example.ormaricba;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert
    void registerUser(User user);




    @Query("SELECT * from users where email=(:email) and password=(:password)")
    User login(String email, String password);


    @Query("SELECT * FROM users WHERE email =:someEmail")
    User getEmail(String someEmail);
}