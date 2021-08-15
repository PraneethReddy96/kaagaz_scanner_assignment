package com.praneeth.assignment.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.praneeth.assignment.data.AlbumEntity
import com.praneeth.assignment.data.ImagesDao
import com.praneeth.assignment.data.ImagesEntity

class Repository(val imagesDao: ImagesDao) {




    suspend fun addData(time: Long, uri: String) {

        if (imagesDao.loadLastTask() == null) {


            val imagesEntity = ImagesEntity(uri, time, "Album" + " " + 1, 1)
            imagesDao.insertData(imagesEntity)


            var albumEntity = AlbumEntity(uri, "Album" + " " + 1)
            imagesDao.insertAlbums(albumEntity)


        } else {

            val size = imagesDao.getData().size
            val previousTime = imagesDao.getData()[size-1].time
            var key= imagesDao.getData()[size-1].key

            if (time - previousTime!! > 30000) {

                key = key?.plus(1)

                val imagesEntity = ImagesEntity(uri, time, "Album" + " " + "$key", key)
                imagesDao.insertData(imagesEntity)

                var albumEntity = AlbumEntity(uri, "Album" + " " + "$key")

                imagesDao.insertAlbums(albumEntity)

            } else {

                val imagesEntity = ImagesEntity(uri, time, "Album" + " " + "$key", key)
                imagesDao.insertData(imagesEntity)


            }
        }
    }


    fun displayAlbums(): LiveData<List<AlbumEntity>> {


        return imagesDao.getAlbumData()

    }



    fun displaySelectedItem(name :String): LiveData<List<ImagesEntity>>{

        return imagesDao.getElements(name)

    }



    fun getAllImages(): LiveData<List<ImagesEntity>>{

        return  imagesDao.getAllData()

    }


}