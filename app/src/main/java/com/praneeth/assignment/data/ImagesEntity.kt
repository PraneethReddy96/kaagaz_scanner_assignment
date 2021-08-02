package com.praneeth.assignment.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ImageTable")
    class ImagesEntity (@ColumnInfo(name = "Image") var image: String?,
                        @ColumnInfo(name = "Time") var time: Long?,
                        @ColumnInfo(name = "Album") var album: String?,
                        @ColumnInfo(name = "key") var key: Int?,){


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    var id: Int? = null


}