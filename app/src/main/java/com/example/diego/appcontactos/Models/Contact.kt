package com.example.diego.appcontactos.Models

class Contact (val name:String,val number:String,val email:String) {
        //Modelo para un contacto y su respectivo ToString
    override fun toString(): String {
        return "$name - $number"

    }
}