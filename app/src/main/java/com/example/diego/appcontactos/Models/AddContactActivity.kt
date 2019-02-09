package com.example.diego.appcontactos.Models

import android.content.ContentValues
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.diego.appcontactos.ContactProvider
import com.example.diego.appcontactos.MainActivity
import com.example.diego.appcontactos.Models.MyApplication.Companion.contacts
import com.example.diego.appcontactos.R
import kotlinx.android.synthetic.main.activity_add_contact.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class AddContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        //Boton para regresar al menu
        back.setOnClickListener{
            val intent  = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        //Boton para agregarcontacto
        create.setOnClickListener {
            val values = ContentValues()
            values.put(ContactProvider.NAME, nameInp.text.toString())
            values.put(ContactProvider.NUMBER, numberInp.text.toString())
            values.put(ContactProvider.MAIL, emailInp.text.toString())
            if (nameInp.text.toString() !="" && numberInp.text.toString()!=""&& emailInp.text.toString()!=""){
                val uri = contentResolver.insert(ContactProvider.CONTENT_URI, values)
                MyApplication.contacts.add(Contact(nameInp.text.toString(),numberInp.text.toString(),emailInp.text.toString()))

                Toast.makeText(applicationContext,"Se ha agregado el contacto de manera exitosa",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(applicationContext,"No se ha podido agregar el contacto",Toast.LENGTH_SHORT).show()
            }
        }
    }

 }

