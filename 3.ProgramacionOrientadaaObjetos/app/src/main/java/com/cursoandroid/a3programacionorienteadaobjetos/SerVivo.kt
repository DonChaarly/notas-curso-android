package com.cursoandroid.a3programacionorienteadaobjetos

//25 Para declarar una clase como abstacta se coloca el modificador abstract, no es necesario colocar open porque estas clases estan hechas para ser heredadas
//26 Se puede declara atributos a esta clase que pueden heredar otras clases
abstract class SerVivo(protected var tipo: String = "humano") {

    //27 Los metodos abstactos no tiene ninguna implementacion y estos se marcan como abstract
    //28 Los metodos abstactos deben ser implementados a fuerza por las clases que hereden de esta
    abstract fun TipoSerVivo()

    //29 Tambien se pueden declarar metodos normales que pueden ser heredados
    internal fun getTipo(): String{
        return this.tipo
    }
    internal fun setTipo(tipo: String){
        this.tipo = tipo
    }

}