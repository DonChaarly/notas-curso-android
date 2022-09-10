package com.cursoandroid.coleccionesyarreglosmetodos


fun main(){

    var string = "Hola com/o, estan."

    var regex: Regex = Regex("[.,/]")

    println(string.replace(regex, ""))

}