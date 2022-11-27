package com.example.aidlserver

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class IMathsService : Service() {

    val TAG = "IMathsService";

    override fun onBind(intent: Intent): IBinder {
        Log.i(TAG, "onBind Method")
        return mBinder;
    }

    private var mBinder: IMathsInterface.Stub = object : IMathsInterface.Stub() {
        override fun addition(val1: Int, val2: Int): Int {
            return val1 + val2
        }

        override fun substraction(val1: Int, val2: Int): Int {
            return val1 - val2
        }

        override fun multiplication(val1: Int, val2: Int): Int {
            return val1 * val2
        }

        override fun division(val1: Int, val2: Int): Int {
            return val1 / val2
        }
    }
}