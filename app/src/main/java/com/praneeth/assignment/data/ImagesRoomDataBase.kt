package com.praneeth.assignment.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ImagesEntity::class, AlbumEntity::class], version = 2)
abstract class ImagesRoomDataBase : RoomDatabase() {


    abstract fun getImagesDao(): ImagesDao

    companion object {

        var INSTANCE: ImagesRoomDataBase? = null

        fun getImageDataBase(context: Context): ImagesRoomDataBase {

            if (INSTANCE == null) {

                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    ImagesRoomDataBase::class.java,
                    "Image_DataBase"
                )

                INSTANCE = builder.build()

                return INSTANCE!!


            } else

                return INSTANCE!!


        }


    }

}