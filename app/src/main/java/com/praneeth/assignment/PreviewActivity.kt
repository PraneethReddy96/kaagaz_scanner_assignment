package com.praneeth.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.praneeth.assignment.adapters.previewAdapter
import com.praneeth.assignment.data.ImagesDao
import com.praneeth.assignment.data.ImagesEntity
import com.praneeth.assignment.data.ImagesRoomDataBase
import com.praneeth.assignment.repository.Repository
import com.praneeth.assignment.viewmodels.MyViewModel
import com.praneeth.assignment.viewmodels.MyViewModelFactory
import kotlinx.android.synthetic.main.activity_preview.*
import kotlinx.android.synthetic.main.activity_preview.rvFoldersRecyclerView

class PreviewActivity : AppCompatActivity() {

    lateinit var viewModel: MyViewModel
    lateinit var imagesDb: ImagesRoomDataBase
    lateinit var imagesDao: ImagesDao
    var dataList = mutableListOf<ImagesEntity>()
    lateinit var previewAdapter: previewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        var searchText = intent.getStringExtra("name")
        tvMyFolderName.text =searchText


        imagesDb = ImagesRoomDataBase.getImageDataBase(this)
        imagesDao = imagesDb.getImagesDao()

        val repository = Repository(imagesDao)
        val viewModelFactory = MyViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MyViewModel::class.java)


        val llManager = GridLayoutManager(this, 2)
        previewAdapter = previewAdapter(dataList)
        rvFoldersRecyclerView.layoutManager = llManager
        rvFoldersRecyclerView.adapter = previewAdapter



        viewModel.displaySelectedItems(searchText!!).observe(this, Observer {


            dataList.clear()
            dataList.addAll(it)
            previewAdapter.notifyDataSetChanged()


        })



    }
}