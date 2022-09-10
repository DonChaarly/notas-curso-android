package com.cursoandroid.a3programacionorienteadaobjetos

//56 Se puede agregar una varaibles normal a la enum class sin embargo se tiene que que asociar a todos las constantes
enum class Dias(var laborable: Boolean) {
    //54 Las constantes se colocan en mayusculas y sin colocar nadamas que su valor
    LUNES(true),
    MARTES(true),
    MIERCOLES(true),
    JUEVES(true),
    VIERNES(true),
    SABADO(false),
    DOMINGO(false);

    //55 Se puede crear metodos en estas clases con normalidad
    fun queDiasES(): String{
        //con un when imprimiremos el dia con ayuda del contexto this
        when(this){
            LUNES -> return "Hoy es Lunes que aburrido"
            MARTES -> return "Hoy es martes que flojera"
            MIERCOLES -> return "Bueno es mierciles"
            JUEVES -> return "Jueves, ya mero viernes"
            VIERNES -> return "Viernes y el cuerpo lo sabe"
            SABADO -> return "Sabadito agusto"
            DOMINGO -> return "Domingo, a descansar todo el dia"
            else -> return "No se que dia es"
        }
    }
}