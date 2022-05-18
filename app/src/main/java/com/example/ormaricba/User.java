package com.example.ormaricba;
import androidx.room.Entity;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    Integer id;

    @ColumnInfo(name="name")
    String name;

    @ColumnInfo (name="email")
    String email;

    @ColumnInfo (name="password")
    String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        password = newPassword;
    }


}