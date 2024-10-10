package com.ierusalem.kadrlar.core.utils

sealed class ResourceWithLoading <T> (
    val data :T? =null,
    val errorMessage:String? =null
) {
    class Loading <T> (data: T? = null) : ResourceWithLoading<T>(data)
    class Success<T>(data:T): ResourceWithLoading<T>(data)
    class Failure<T>(errorMessage: String) : ResourceWithLoading<T>(null,errorMessage)

    override fun toString(): String {
        return when (this) {
            is Success<*> ->"Success[data=$data]"
            is Loading<T> ->"Loading"
            is Failure ->"Error[exception=$errorMessage]"
        }
    }
}