package com.enzoftware.randomuser.data.repository

import com.enzoftware.randomuser.data.network.RandomUserService
import com.enzoftware.randomuser.domain.model.RandomUser
import com.enzoftware.randomuser.utils.CallResult
import com.enzoftware.randomuser.utils.safeApiCall
import java.io.IOException
import javax.inject.Inject


/**
 * Created by Enzo Lizama Paredes on 10/20/20.
 * Contact: lizama.enzo@gmail.com
 */

class RandomUserRepository @Inject constructor(private val randomUserService: RandomUserService) {

    private suspend fun fetchRandomUser(): CallResult<RandomUser> {
        val result = randomUserService.fetchRandomUser().results.first()
        return when {
            result.email.isNotEmpty() -> CallResult.Success(result)
            else -> CallResult.Error(IOException("Error finding random user"))
        }
    }


    suspend fun retrieveRandomUser() = safeApiCall(
        call = {
            fetchRandomUser()
        }, errorMessage = "Failed fetch random user"
    )

}