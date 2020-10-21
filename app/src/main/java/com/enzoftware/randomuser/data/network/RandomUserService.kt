package com.enzoftware.randomuser.data.network

import retrofit2.http.GET


/**
 * Created by Enzo Lizama Paredes on 10/20/20.
 * Contact: lizama.enzo@gmail.com
 */

interface RandomUserService {
    @GET("/api")
    suspend fun fetchRandomUser(): RandomUserResponse

}