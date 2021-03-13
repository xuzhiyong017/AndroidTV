package com.sky.smarttvandroid

import android.app.Application
import com.didichuxing.doraemonkit.DoraemonKit
import com.tuzhenlei.crashhandler.CrashHandler

class StartApp : Application(){

    override fun onCreate() {
        super.onCreate()
        CrashHandler.getInstance().init(this,true)
        DoraemonKit.install(this, "fa75146e7622a7fbffbb84371b08053e")
    }
}