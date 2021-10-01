package com.example.notepadpro.FireBase

import android.util.Log
import com.google.firebase.ktx.BuildConfig
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

object RemoteConfigUtils {

    val TAG:String = "RemoteConfigUtils"

    var SHOW_MAINTAINANCE_TEXT = "SHOW_MAINTAINANCE_TEXT"


    private lateinit var remoteConfig:FirebaseRemoteConfig

    init{
        remoteConfig = getFireBaseConfig()
    }

    private fun getFireBaseConfig(): FirebaseRemoteConfig {
         val remoteConfig:FirebaseRemoteConfig = Firebase.remoteConfig

         val remoteConfigSettings:FirebaseRemoteConfigSettings = remoteConfigSettings {
             minimumFetchIntervalInSeconds =if(BuildConfig.DEBUG)
             {
                 0
             }else{
                 60*60
             }
         }

        remoteConfig.apply {
            setConfigSettingsAsync(remoteConfigSettings)
//            setDefaultsAsync()
            fetchAndActivate().addOnCompleteListener {
                Log.d(TAG, "getFireBaseConfig: completed")
            }
        }
        return remoteConfig
    }

    fun getMaintainanceCheck():Boolean = remoteConfig.getBoolean(SHOW_MAINTAINANCE_TEXT)

}