package com.example.diego.appcontactos.Models

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import com.example.diego.appcontactos.R
import kotlinx.android.synthetic.main.activity_mail.*

class MailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mail)
        //Si apacha el boton de enviar
        sendButton.setOnClickListener {
            var recipient = recipient.text.toString().trim()//Obtiene el destinatario del Edit Text
            var subject = subject.text.toString().trim()//Obtiene el asunto
            var message = mensaje.text.toString().trim()//Obtiene el mensaje
            sendEmail(recipient, subject, message)//Usa la funcion de abajo
        }

    }
    private fun sendEmail(recipient: String, subject: String, message: String){
        val mIntent = Intent(Intent.ACTION_SEND)//Intent para mandar mail
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))//Toma el recipient y lo manda como Extra en el Intent
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)//Toma el asunto y lo manda al Intent como Extra
        mIntent.putExtra(Intent.EXTRA_TEXT, message)//Toma el mensaje

        try {
            startActivity(Intent.createChooser(mIntent, "Choose email client..."))//lanza la actividad

        }
        catch (e: Exception){
            //Si no funciona
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }
}
