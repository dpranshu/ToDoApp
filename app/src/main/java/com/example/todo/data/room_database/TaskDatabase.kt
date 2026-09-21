package com.example.todo.data.room_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//abstract class private class
@Database(entities = [TaskItem::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {      //This class represents my Todo app's database

                                                    // database se dao ko connect karna
    abstract fun taskDao(): TaskDao                 //So when you want to perform database operations, you get the DAO:

                                                    //database ka instance banana hai
                                                    ////ye is liye use kara hai kyo ki we want pure app me database ka ek hi object rhega
    companion object {

        @Volatile
        private var INSTANCE: TaskDatabase? = null  //INSTANCE basically means: "Do we already have a database object?"

        fun getDatabase(context: Context): TaskDatabase{  //Give me the database.
            return INSTANCE ?: synchronized(this) { //elvis operator //INSTANCE ?: Means:"If INSTANCE is NOT null, return it."
                Room.databaseBuilder(                     //databaseBuilder() needs 3 arguments:
                    context.applicationContext,           // Context
                    TaskDatabase::class.java,      // Database class
                    "task_database"                // Database file name
                )
                    .fallbackToDestructiveMigration(true)     //if we increase our database version then purana table delete hojayega
//                    .addMigrations()                    //for manual migration
                    .build()
                    .also{ INSTANCE = it }
            }

        }



    }


}