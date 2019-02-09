package com.example.diego.appcontactos

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Build.VERSION_CODES.M
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.diego.appcontactos.Models.Contact
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.diego.appcontactos.Models.AddContactActivity
import com.example.diego.appcontactos.Models.ContactInformation
import com.example.diego.appcontactos.Models.MyApplication
import com.example.diego.appcontactos.Models.MyApplication.Companion.contacts

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val URL = "content://com.example.diego.appcontactos.ContactProvider"
        val contact= Uri.parse(URL)
        val c = contentResolver.query(contact, null, null, null, "name")
        //logica para poner toda la informacion necesaria en la list view y ademas agregar la necesaria a my application



        //Se crea el adapter y se asocia  a la lista de contactos creada
        val adapter= ArrayAdapter(this, android.R.layout.simple_list_item_1,MyApplication.contacts)
        contactList.adapter= adapter

        //Se implementa el Click listener a la listView para seleccionar un elemento de la lista
        contactList.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val intent: Intent = Intent(this@MainActivity, ContactInformation::class.java)
                val itemToGet=position
                intent.putExtra("itemToGet",itemToGet)
                startActivity(intent)
            }
        }
        // Abrir la actividad de "Agregar contacto" al apachar el boton
        addContact.setOnClickListener{
            val intent  = Intent(this, AddContactActivity::class.java)
            startActivity(intent)
        }

        contactList.onItemLongClickListener= object: AdapterView.OnItemLongClickListener {
            override fun onItemLongClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long): Boolean {
                contentResolver.delete(ContactProvider.CONTENT_URI,position.toString(),null)
                MyApplication.contacts.removeAt(position)
                adapter.notifyDataSetChanged()
                return true
            }
        }


    }

}
