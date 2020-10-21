package com.enzoftware.randomuser.domain.usecase

import com.enzoftware.randomuser.data.repository.RandomUserRepository
import com.enzoftware.randomuser.domain.model.RandomUser
import com.enzoftware.randomuser.utils.CallResult
import javax.inject.Inject


/**
 * Created by Enzo Lizama Paredes on 10/20/20.
 * Contact: lizama.enzo@gmail.com
 */


class FetchRandomUserUseCase @Inject constructor(private val randomUserRepository: RandomUserRepository){
    suspend fun fetchRandomUser(): CallResult<RandomUser> = randomUserRepository.retrieveRandomUser()
}