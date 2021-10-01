package com.example.notepadpro.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

fun<T> LiveData<T>.getOrAwaitTheValue() : T {
    var data: T? = null
    val latch = CountDownLatch(1)



        val  observer = object : Observer<T>{
            override fun onChanged(t: T) {
                data = t
                this@getOrAwaitTheValue.removeObserver(this)
                Log.d("viewModel", "onChanged: data-"+data)
                latch.countDown()
            }
        }
        observeForever(observer)


        try {
            when {
                !latch.await(2, TimeUnit.SECONDS) -> {
                    throw TimeoutException("Its time out")
                }
            }
        } finally {
            removeObserver(observer)
        }
    return data as T
}