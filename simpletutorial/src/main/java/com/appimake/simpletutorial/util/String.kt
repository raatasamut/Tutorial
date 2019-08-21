package com.appimake.simpletutorial.util

import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import com.appimake.simpletutorial.intf.ITutorialCallback
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

fun String.getBitmapFromBase64Link(cb: ITutorialCallback) {
    GlobalScope.launch {
        try {
            val okHttpClient = OkHttpClient()
            val request = Request.Builder()
                .url(this@getBitmapFromBase64Link)
                .build()
            val response = okHttpClient.newCall(request).execute()
            val raw = response.body()?.string()

            raw?.let {
                val ra = Base64.decode(it, Base64.DEFAULT)
                cb.updated("img", 200, BitmapFactory.decodeByteArray(ra, 0, ra.size))

            }
        } catch (e: Exception) {
            Log.d("Exception", e.message.toString())
        }
    }
}