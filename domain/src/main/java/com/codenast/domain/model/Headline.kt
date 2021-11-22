package com.codenast.domain.model

import java.io.Serializable

data class Headlines(
    val totalResults: Int,
    val articles: List<Articles>
) : BaseModel()

data class Articles(
    val description: String?,
    val author: String?,
    val url: String?,
    val id: String?,
    val appUrl:String?,
    var like: Int? = 0,
    var comment: Int? = 0
) : Serializable