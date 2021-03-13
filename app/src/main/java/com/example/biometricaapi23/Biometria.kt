package com.example.biometricaapi23

import android.content.Context
import android.os.Build
import androidx.biometric.BiometricManager

class Biometria {
    fun verificarAutenticacao (context: Context) : Boolean {

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return false
        }
        val biometricManager: BiometricManager = BiometricManager.from(context)

        when(biometricManager.canAuthenticate()){
            BiometricManager.BIOMETRIC_SUCCESS -> return true // sucesso
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> return false // e api 23 mais nao tem biometria
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> return false // exite mais nao configurado corretamente
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> return false // existe mais nao disponivel
        }
        return false

    }
}