package com.example.roonrxjava.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "person")
public class Person {
    @PrimaryKey
    @NonNull
    public String name;
    public int gender;
    public int age;

    public Person(@NonNull String name, int gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    @Override
    public String toString() {
        return "姓名：" + name +
                "性别：" + gender +
                "年龄：" + age;
    }
}
