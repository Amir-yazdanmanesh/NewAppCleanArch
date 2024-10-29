package com.yazdanmanesh.usecase

abstract class UseCase<out R> {
    protected abstract fun buildUseCase(): R

    fun execute(): R = buildUseCase()
}
