package com.cursoandroid.a4kotilavanzado

data class Persona(var nombre: String, var apellido: String, var numero: Int) {


    fun decirHola(){
        println("Hola soy ${this.nombre}")
    }
}