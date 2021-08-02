package com.praneeth.assignment.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Album")
class AlbumEntity(@ColumnInfo(name = "Image") var image: String?,
                  @ColumnInfo(name = "Time") var name: String?,) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    var id: Int? = null

}