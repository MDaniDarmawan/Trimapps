package com.example.trimapps.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntity.class}, version = 1)

public abstract class UserDatabase extends RoomDatabase {

    private static final String dbName = "user"; // Nama basis data yang akan digunakan.
    private static UserDatabase userDatabase; // Variabel untuk menyimpan instance basis data.

    // Metode untuk mendapatkan instance tunggal dari UserDatabase.
    public static synchronized UserDatabase getUserDatabase(Context context) {

        // Membangun instance basis data jika belum ada.
        if (userDatabase == null) {
            userDatabase = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, dbName)
                    .fallbackToDestructiveMigration() // Jika skema berubah, basis data akan dihancurkan dan dibuat ulang.
                    .build();
        }

        return userDatabase;
    }

    // Metode abstrak untuk mendapatkan objek Dao (Data Access Object) yang akan digunakan untuk berinteraksi dengan entitas pengguna.
    public abstract UserDao userDao();

}
