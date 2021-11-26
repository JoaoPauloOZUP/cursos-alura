package com.zupacademy.scheduleofstudent.firebase

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class SSFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.d("TOKEN", "AQUI $token")
    }
}