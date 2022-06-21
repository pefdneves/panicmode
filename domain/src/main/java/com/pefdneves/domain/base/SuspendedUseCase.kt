package com.pefdneves.domain.base

interface SuspendedUseCase<T> {

    suspend operator fun invoke(): T
}
