package com.praneeth.assignment.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.praneeth.assignment.data.AlbumEntity
import com.praneeth.assignment.data.ImagesEntity

import com.praneeth.assignment.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel(val repository: Repository) : ViewModel() {




    fun saveData(time: Long, uri : String){

        CoroutineScope(Dispatchers.IO).launch {
            repository.addData(time,uri)

        }
    }



    fun displayAlbumsList() : LiveData<List<AlbumEntity>> {



        return    repository.displayAlbums()

    }


    fun displaySelectedItems(name:String): LiveData<List<ImagesEntity>>{


        return repository.displaySelectedItem(name)
    }





}