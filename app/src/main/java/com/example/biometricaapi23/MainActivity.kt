package com.example.biometricaapi23

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import java.util.concurrent.Executor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun teste (view: View) {

        val biometria: Biometria
        biometria = Biometria()
        biometria.verificarAutenticacao(this)
        autenticar()

    }
    fun autenticar (){
        //executor
        val executor: Executor = ContextCompat.getMainExecutor(this)

        //BiometricPrompt
        val biometriaCompat = BiometricPrompt(this@MainActivity,executor, object : BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                toas("onAuthenticationError")

                super.onAuthenticationError(errorCode, errString)
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)

                tela()
            }

            override fun onAuthenticationFailed() {
                toas("onAuthenticationFailed")

                super.onAuthenticationFailed()

            }
        })

        //NiomectricPrompt info
        val info:BiometricPrompt.PromptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Titulo")
            .setSubtitle("sub")
            .setDescription("descr")
            .setNegativeButtonText("Cancelar")
            .build()

        biometriaCompat.authenticate(info)
    }
    fun tela (){

        startActivity(Intent(this,TesteActivity :: class.java))
    }
    fun toas(texto: String){
        Toast.makeText(this,texto,Toast.LENGTH_SHORT).show()

    }
}