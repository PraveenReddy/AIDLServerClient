package com.example.aidlclient

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.aidlclient.databinding.ActivityMainBinding
import com.example.aidlserver.IMathsInterface

class MainActivity : AppCompatActivity() {
    var mathsInterface: IMathsInterface? = null
    val TAG = "AIDLClient"

    private val PKGNAME = "com.example.aidlserver"
    private val SERVICENAME = "com.example.aidlserver.IMathsService"

    private var binding: ActivityMainBinding? = null
    private var val1: Int? = null
    private var val2: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding!!.root
        setContentView(view)

        initializeService()

        binding!!.btnData.setOnClickListener {
            val1 = 10
            val2 = 20

            Log.i(
                TAG, "\n" +
                        "addition  " + mathsInterface?.addition(val1!!, val2!!) + "\n" +
                        "substraction  " + mathsInterface?.substraction(val1!!, val2!!) + "\n" +
                        "multiplication  " + mathsInterface?.multiplication(val1!!, val2!!) + "\n" +
                        "division  " + mathsInterface?.division(val1!!, val2!!)
            )
            Log.i(TAG,"$val1  $val2")
        }
    }

    private fun initializeService() {
        Log.i(TAG, "Inside Service")
        val mConnection: ServiceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                mathsInterface = IMathsInterface.Stub.asInterface(service)
                Log.i(TAG, "Service Connected")
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                Log.i(TAG, "Service Disconnected")
            }
        }
        val serviceCmpName = ComponentName(PKGNAME, SERVICENAME)
        val serviceIntent = Intent()
        serviceIntent.component = serviceCmpName
        bindService(serviceIntent, mConnection, Context.BIND_AUTO_CREATE)
    }
}