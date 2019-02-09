package com.example.diego.appcontactos

import android.content.ContentValues
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.diego.appcontactos.Models.Contact
import com.example.diego.appcontactos.Models.MyApplication
import kotlinx.android.synthetic.main.activity_update_contact.*
/*
Activity para actualizar la informacion de un contacto seleccionado en el MainActivity
 */
class UpdateContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_contact)
        //Se colocan los nombres actuales del contacto seleccionado en la lista del MainActivity
        val currentName= intent.getStringExtra("currentName")
        val currentTel= intent.getStringExtra("currentTel")
        val currentMail= intent.getStringExtra("currentMail")

        //Se llenan los espacios de los EditText con los valores actuales
        newName.setText(currentName)
        newEmail.setText(currentMail)
        newNumber.setText(currentTel)

        //Boton para regresar al MainActivity
        back.setOnClickListener{
            val intent  = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //Boton para guardar cambios
        change.setOnClickListener{
            //Create y set de valores en VALUE para pasarse como parametro en la funcion Update del ContentProvider
            val values= ContentValues()
            values.put(ContactProvider.NAME, newName.text.toString())
            values.put(ContactProvider.NUMBER, newNumber.text.toString())
            values.put(ContactProvider.MAIL, newEmail.text.toString())
            //Update de la db
            contentResolver.update(ContactProvider.CONTENT_URI,values,(intent.getIntExtra("item",0)-1).toString(),null)
            //Actualizacion del contacto en la lista principal
            MyApplication.contacts.set(intent.getIntExtra("item",0), Contact(newName.text.toString(),newNumber.text.toString(),newEmail.text.toString()))
            Toast.makeText(applicationContext,"Contacto editado de manera exitosa...", Toast.LENGTH_SHORT).show()
        }
    }
}
