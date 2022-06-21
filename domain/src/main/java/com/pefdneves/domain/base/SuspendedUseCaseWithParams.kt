package com.pefdneves.domain.base

interface SuspendedUseCaseWithParams<T, U> {

    suspend operator fun invoke(param: U): T
}
