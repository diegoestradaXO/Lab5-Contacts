package com.example.diego.appcontactos.Models

import android.app.Application

class MyApplication: Application() {
    companion object {
        //Lista de contactos
        var contacts: ArrayList<Contact> = ArrayList()

    }
}