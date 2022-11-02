package com.example.movieappassessment.utils

object LocalReference {
    const val FIRST_INSTALL_RESOURCE = "first_install_resource"
    const val FIRST_INSTALL = "first_install"
}

object MovieEndPoint {
    const val UPCOMING_MOVIE = "movie/upcoming"
    const val POPULAR_MOVIE = "movie/popular"
    const val GENRE_MOVIE = "genre/movie/list"
    const val DETAIL_MOVIE = "movie/{movie_id}"
    const val VIDEO_MOVIE = "movie/{movie_id}/videos"
    const val SEARCH_MOVIE = "search/movie"
    const val DISCOVER_MOVIE = "discover/movie"
}

object MovieKeyConstant {
    const val API_KEY_PARAM = "api_key"
    const val KEY_PAGE = "page"
    const val KEY_LANGUAGE = "language"
    const val INCLUDE_ADULT = "include_adult"
    const val KEY_QUERY = "query"
    const val KEY_WITH_GENRE = "with_genres"
}

object DataBASE {
    const val NAME = "movie_database"
}

object Extended {
    const val ID = "ID"
}

object MESSAGE {
    const val STATUS_SUCCESS = "success"
    const val STATUS_ERROR = "error"
}