package com.codenast.data.utills

object NetworkUtills {

    fun getCommonQueryParams(): Map<String, String> {
        return mapOf(
            Constants.KEY_COUNTRY to Constants.COUNTRY,
            Constants.KEY_API_KEY to Constants.API_KEY
        )
    }

    fun getCommentUrl(url: String?): String? {
        return Constants.COMMENTS_URL.plus(filterUrl(url))
    }
    fun getLikeUrl(url: String?): String? {
        return Constants.LIKE_URL.plus(filterUrl(url))
    }

    private fun filterUrl(url: String?): String? {
        if (url?.contains("https://") == true) {
            var updatedUrl = url.replace("https://", "")
            updatedUrl = url.replace(("[^\\w]").toRegex(), "-")
            return updatedUrl
        }

        return null
    }
}