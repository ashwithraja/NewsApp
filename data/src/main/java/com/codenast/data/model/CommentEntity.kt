package com.codenast.data.model

import com.codenast.domain.model.Comment
import com.google.gson.annotations.SerializedName

data class CommentEntity(@SerializedName("comments") var count: Int)

object CommentEntityConvertor {
    fun convertEntiityToDomain(coment: CommentEntity): Comment = Comment(coment.count)
}
