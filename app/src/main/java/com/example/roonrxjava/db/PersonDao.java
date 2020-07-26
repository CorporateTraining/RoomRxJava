package com.example.roonrxjava.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.roonrxjava.entity.Person;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable createPerson(Person person);

    @Query("select * from person")
    Single<List<Person>> getPersons();

}
