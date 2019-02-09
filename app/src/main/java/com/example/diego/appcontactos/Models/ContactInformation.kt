package com.example.diego.appcontactos.Models

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.diego.appcontactos.MainActivity
import com.example.diego.appcontactos.R
import kotlinx.android.synthetic.main.activity_contact_information.*


class ContactInformation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_information)
        //Boton para regresar al Inicio
        back.setOnClickListener{
            val intent  = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        //Obtiene la info del contacto seleccionado en la listView de la Main Activity
        val info = intent.getIntExtra("itemToGet",0)
        val itemToShow= MyApplication.contacts[info]
        //Se colocan los datos obtenidos anteriormente en los TextViews
        name.setText(itemToShow.name)
        number.setText(itemToShow.number)
        email.setText(itemToShow.email)

        //Si apacha en el numero , lo llamara
        number.setOnClickListener{
            val intent: Intent = Intent(Intent.ACTION_DIAL)
            intent.setData(Uri.parse("tel:${number.text}"))
            startActivity(intent)
        }
        //Si apacha en el email, se abrira el menu para componer un mensaje
        email.setOnClickListener{
            val intent  = Intent(this, MailActivity::class.java)
            startActivity(intent)
        }

    }
}
