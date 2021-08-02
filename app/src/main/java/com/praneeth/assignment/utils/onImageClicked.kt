package com.praneeth.assignment.utils

import android.net.Uri
import com.praneeth.assignment.data.ImagesEntity

interface onImageClicked {

    fun getUri(imagesEntity: ImagesEntity)

}