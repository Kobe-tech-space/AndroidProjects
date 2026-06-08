package com.example.course6_3;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.course6_3.dao.BookDao;
import com.example.course6_3.entity.BookInfo;

@Database(entities = {BookInfo.class},version = 1,exportSchema = true)
public abstract class BookDatabase extends RoomDatabase {
    public abstract BookDao bookDao();
}
