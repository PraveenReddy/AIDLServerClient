package com.example.aidlserver

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var mathsInterface: IMathsInterface? = null
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mathsIntent = Intent(this, IMathsService::class.java)
        mathsIntent.setClassName("com.example.aidlserver", "com.example.aidlserver.IMathsService")
        bindService(mathsIntent, mConnection, BIND_AUTO_CREATE)
    }

    private var mConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            mathsInterface = IMathsInterface.Stub.asInterface(p1)
            Log.i(TAG, "Service Connected")
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            Log.i(TAG, "Service Disconnected")
        }

    }
}