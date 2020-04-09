package com.eebros.asan.usecases

import com.eebros.asan.repository.MainRepository
import javax.inject.Inject

class CheckTokenUseCase @Inject constructor (
    val repository: MainRepository
) {
    fun execute(token: String, custId: Long) = repository.checkToken(token, custId)
}