package com.praneeth.assignment.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.praneeth.assignment.data.AlbumEntity
import com.praneeth.assignment.data.ImagesDao
import com.praneeth.assignment.data.ImagesEntity

class Repository(val imagesDao: ImagesDao) {

    var displayList = mutableListOf<Display>()

    companion object {

        var key = 1
    }

    suspend fun addData(time: Long, uri: String) {

        if (imagesDao.loadLastTask() == null) {


            val imagesEntity = ImagesEntity(uri, time, "Album" + " " + "$key", key)

            imagesDao.insertData(imagesEntity)

            displayList.add(Display("Album" + " " + "$key", uri))

            var albumEntity = AlbumEntity(uri, "Album" + " " + "$key")

            imagesDao.insertAlbums(albumEntity)


        } else {

            val size = imagesDao.getData().size


            val previousTime = imagesDao.getData()[size-1].time
            Log.d("size", previousTime.toString())
            if (time - previousTime!! > 60000) {

                key = key + 1

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