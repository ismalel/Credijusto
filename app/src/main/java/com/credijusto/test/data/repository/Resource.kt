package com.credijusto.test.data.repository

sealed class Resource<out T: Any> (
    open val data: T? = null,
    open val message: String? = null
) {
    data class SUCCESS<out T : Any>(override val data: T) : Resource<T>(data)
   data  class ERROR<out T: Any>(override val data: T? = null, override val message: String) : Resource<T>(data, message)
    class LOADING<out T: Any> : Resource<T>()

}
