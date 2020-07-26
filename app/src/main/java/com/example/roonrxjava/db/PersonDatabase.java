package com.example.roonrxjava.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roonrxjava.entity.Person;

@Database(entities = {Person.class}, version = 1, exportSchema = false)
public abstract class PersonDatabase extends RoomDatabase   {
    private static volatile PersonDatabase INSTANCE;
    public abstract PersonDao personDao();

    public static PersonDatabase getPersonInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (PersonDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PersonDatabase.class, "Person")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
