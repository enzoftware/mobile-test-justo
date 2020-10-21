package com.enzoftware.randomuser.data.network

import com.enzoftware.randomuser.domain.model.RandomUser
import com.squareup.moshi.JsonClass


/**
 * Created by Enzo Lizama Paredes on 10/20/20.
 * Contact: lizama.enzo@gmail.com
 */
@JsonClass(generateAdapter = true)
data class RandomUserResponse(
    val results: List<RandomUser>,
    val info: ApiInfoResponse
)


@JsonClass(generateAdapter = true)
data class ApiInfoResponse(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String
)
