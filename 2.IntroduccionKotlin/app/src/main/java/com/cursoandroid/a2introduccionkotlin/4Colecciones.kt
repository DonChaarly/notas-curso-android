package com.cursoandroid.a2introduccionkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class `4Colecciones`: AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
                    COLECCIONES
        Las colecciones son parecidas a los arreglos, tambien pueden guardar conjuntos de datos
        Sin embargo las colecciones son en si clases las cuales tienen asociados metodos muy utilies

        */

        //////////////////////////////     SET inmutable    ///////////////////////////////////

        /*
                El set es una coleccion elementos la cual no tiene un orden y en la cual los valores no se pueden repetir
                La coleccion set es similar a un arreglo, sin embargo, una vez definida la colecion al ser inmutable no se puede modificar
        Si se le especifica un tipo de dato, todos los datos que se definan tienen que respetar este tipo de dato
        Si no se le especifica el tipo de dato, se puede definir cualqueir tipo de dato

         */

        val setTipoInt: Set<Int> = setOf(3, 5, 2, 6)
        val setMezclado = setOf("Carlos", 3, 6, 1f)

        //Algunos metodos que se pueden aplicar a esta coleccion son los siguiente

        //el metodo .size nos regresa el tamano del set que se especifique
        var tamanoSet: Int = setMezclado.size

        //El metodo .contains() nos regresa un boolean true o false dependiendo si el valor que le proporcionamos se encuentra en la coleccion
        var estaEnSet = setMezclado.contains("Carlos")


        /////////////////////////////      SET MUTABLES     ///////////////////////////////////////

        //Los MutableSet son igual que los set noramles, pero estos si se puedenmodificar
        //Se tiene que especificar que se trata de un MutableSet

        val setMutable: MutableSet<Int> = mutableSetOf(3, 5, 2)

        //Para agregar un nuevo valor al set se utiliza el metodo .add(), y se agrega el valor hasta el final sel set
        setMutable.add(6)

        //para eliminar un valor del set se utiliza el metodo remove() y se pasa el parametro que se quiere eliminar
        setMutable.remove(3)

        //Tambien se puede eliminar el contenido de toda la lista con el metodo clear()
        setMutable.clear()


        ////////////////////////////     LIST inmutable      /////////////////////////////////////

        /*
            Las listas son colecciones de elementos los cuales si tienen un orden y pueden duplicarse los elementos
            Al tener un orden cada elemento va a tener un indice especifico
            las listas inmutables no se pueden modificar
            Las listas tienen asociados varios metodos muy utiles
         */

        //Las listas se declaran como las otras coleciones
        val listaInmutable: List<String> = listOf("Perro", "Vaca", "Gallina")

        //Para obtener un dato de la lista se coloca su indice entre corchetes
        listaInmutable[2]

        //Entre los metodos que se le pueden aplicaar a las listas estan

        //Para obtener el tamano de la lista
        listaInmutable.size
        //Para verificar si un elemento esta en la lista
        listaInmutable.contains("Vaca")
        //Para obtener el primer componente de la lista
        listaInmutable.first()
        //Para obtener el ultimo elemento de la lista
        listaInmutable.last()
        //Para recuperar un elemento, similar a solo colocar los corchetes
        listaInmutable.elementAt(2)
        //Verificar si la lista esta vacia
        listaInmutable.none()
        //Que nos devuelva el primer elemento y si la lista esta vacia que nos regrese null
        listaInmutable.firstOrNull()


        ///////////////////////////////      LISTAS MUTABLES    /////////////////////////////

        //Es los mismo que las listas inmutables solo que la declaracion cambia ahora sera mutableList()

        var listaMutable: MutableList<Int> = mutableListOf(3, 352, 232, 625)

        //para agregar un nuevo elemento a la lista, este elemento se agregara al final de la lista
        listaMutable.add(3215)

        //para eliminar un elemento de la lista, se puede colocar el indice
        listaMutable.removeAt(2)
        //Tambien hay otras formas como colocando el elemento que se eliminara
        listaMutable.remove(352)
        //Para eliminar todos los elementos de la lista
        listaMutable.clear()


        ////////////////////////////        MAPAS INMUTABLES     ////////////////////////////

        //Los mapas funcionan definiendo una llave y un valor, osea que se definen dos datos
        //El primer dato la hace de llave y el segundo dato sera el valor asociado a esa llave
        //La llave y el valor pueden ser de diferentes tipos
        //Varios de los metodos antes mencionados sirven para este tipo de coleccion

        //Se coloca la llave seguido de el comando to para separar la llave del valor
        var mapaInmutable: Map<Int, String> = mapOf(1 to "Mexico", 2 to "Colombia", 3 to "Ecuador" )

        //Para recuperar un elemento del mapa se puede utilizar la llave, (hay diferentes formas)
        mapaInmutable[3]

        /////////////////////////         MAPAS MUTABLES   /////////////////////////////////

        //Los mapas inmutables funcionan igual sin embargo a estos se les puede agregar o eliminar un valor

        //Para definir un mapa mutable se utiliza la palabra MutableMap<>()
        //Se puede dejar vacio el mapa no hay problema
        var mapaMutable = mutableMapOf<String, Float>()


        //Para agregar un nuevo elemento se utiliza el metodo put()
        mapaMutable.put("Coca", 23f)
        mapaMutable.put("Pepsi", 22f)

        //Para eliminar un elemento se utiliza el metodo remove(), hay diferentes tipos se puede colocar la llave
        mapaMutable.remove("Coca")





    }


}