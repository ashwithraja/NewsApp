package com.codenast.data.model

import com.codenast.domain.model.Like
import com.google.gson.annotations.SerializedName

data class LikeEntity(@SerializedName("likes") var count: Int)

public object LikeEntityConvertor {
    fun convertLikeEntityToDomain(likeEntity: LikeEntity): Like = Like(likeEntity.count)
}