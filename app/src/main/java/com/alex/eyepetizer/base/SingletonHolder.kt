package com.alex.eyepetizer.base

/**
 * 带参数的单例模板类
 */
open class SingletonHolder<out T, in A, in B> (creator: (A, B) -> T) {
    private var creator:((A, B) -> T)? = creator
    @Volatile
    private var instance: T? = null

    fun getInstance(arg:A, argb:B): T{
        val i = instance
        if (i != null){
            return i
        }

        return synchronized(this){
            val i2 = instance
            if (i2 != null){
                i2
            }else{
                val created = creator!!(arg, argb)
                instance = created
                creator = null
                created
            }
        }
    }

}