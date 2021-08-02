package com.praneeth.assignment.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ImagesDao {

    @Insert
   fun insertData(imagesEntity: ImagesEntity)

    @Query(value ="select * from ImageTable")
    fun getData(): MutableList<ImagesEntity>

    /* Function to check whether the table is empty */
    @Query("SELECT * FROM ImageTable ORDER BY id LIMIT 1")
    fun loadLastTask(): ImagesEntity

    @Insert
    fun insertAlbums(albumEntity: AlbumEntity)


    @Query(value ="select * from Album")
    fun getAlbumData(): LiveData<List<AlbumEntity>>

    
    @Query(value = "Select * from ImageTable WHERE album like :searchTerm")
    fun getElements(searchTerm :String) : LiveData<List<ImagesEntity>>


 @Query(value ="select * from ImageTable")
 fun getAllData(): LiveData<List<ImagesEntity>>


}