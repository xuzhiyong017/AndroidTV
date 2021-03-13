package com.sky.smarttvandroid

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.permissionx.guolindev.PermissionX
import com.permissionx.guolindev.callback.RequestCallback

class SplashActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PermissionX.init(this)
            .permissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .request(object : RequestCallback {
                override fun onResult(
                    allGranted: Boolean,
                    grantedList: MutableList<String>?,
                    deniedList: MutableList<String>?
                ) {
                    if(allGranted){
                        startActivity(Intent(this@SplashActivity,MainActivity::class.java))
                    }else{
                        finish()
                    }
                }
            })
    }
}