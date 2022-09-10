package com.cursoandroid.a2introduccionkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class `3Arreglos`: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
                             ARREGLOS
        Los arreglos son coleccinoes de elementos ordenados los cuales se pueden agrupar bajo un mismo nombre,
        cambiando el indice de su posicion para recuperarlos

        */


        /////////////////////////////////////   ARRAYS        //////////////////////////////////////

        /*
            Los arrays se declaran como otra variable, se especifica que es de tipo array y
            entre <> se especifica el tipo de dato que contiene el array
            Para declarar valores se utiliza el metodo arrayOf y entre parentesis se pasan los valore que contendra el array
            Una vez definidos el tamano del array no se pude modificar
         */

        var arrayStrings: Array<String> = arrayOf("solo", "Casa", "Regalo")

        //Para llamar un dato de un array se coloca el numero de indice entre corchetes del dato que se quiere
        println(arrayStrings[2])

        //Para modificar el valor de un dato dentro del array se utiliza el metodo set, se especifica el indice y el nuevo valor
        arrayStrings.set(2,"Pato")
        //Tambiien se puede especificar el dato array y definirle un uevo valor
        arrayStrings[1] = "Cascara"

        //Tamano de un array
        println(arrayStrings.size)
        //Cambiar un valor del array
        println(arrayStrings.set(2, "Castellano"))
        //Obtener un valor del array
        println(arrayStrings.get(2))
        //Convertir a string el arreglo
        println(arrayStrings.toString())
        //Devuelve un arreglo de los indices del array
        println(arrayStrings.indices)
        //Devuleve un objeto iterable de los string
        println(arrayStrings.iterator())


        ///////////////////////////////////      MATRICES       /////////////////////////////////

        //Si dentro de un array se coloca otro array este se convierte en una matriz
        //Se pueden hacer matrices de varios elementos menos de strings
        //No es recomendable combinar varios tipos de matrices dentro de una misma

        var matrizVariada = arrayOf(
            //Dentro del primer arrayOf se puede colocar otro array indicando el tipo de dato del array interno
            intArrayOf(2, 4, 53, 2),
            //Los array internos no necesitan ser del mismo tamano
            floatArrayOf(3f, 5f),
            //Para colocar un nuevo array se coloca una coma
            booleanArrayOf(true, false, false)
        )

        var matriz = arrayOf(
            intArrayOf(2, 3, 5),
            intArrayOf(1, 6, 6),
            intArrayOf(1, 5, 1, 6, 2, 2)
        )

        //Para llamar una matriz se define el indice del arreglo padre y el indice del arreglo hijo
        matriz[0][2]




        ////////////////////////////////       BUCLE FOR        ////////////////////////////////

        //Se puede utilizar el bucle for para recorrer los datos de una matriz
        //Se especifica una variable en donde se va a guardar el dato que se este iterando en ese momento,
        //Despues se utiliza el comando in seguido de la variable que se va a iterar

        for ( i in arrayStrings){
            //Se imprimira el valor del array que se este guardando en i en ese momento
            println(i)
        }

        //Si se utiliza el metodo .indices se obtendra el numero del indice que se este iterando
        for ( i in arrayStrings.indices){
            //Se imprime el indice y utilizando el indice accedemos al valor del arreglo
            println("Indice $i. valor ${arrayStrings[i]}")
        }

        //Si se utilza el metodo size, se obtiene el tamano del arreglo
        //Al colocar .. se esta especificando un rango
        for ( i in 0 .. (arrayStrings.size)){
            //el recorrido sera del 0 hasta el numero del tamano del arreglo
            println(i)
        }



        ////////////////////////////          BUCLES ANIDADOS     ////////////////////////////////

        //Se puede definir un bucle dentro de otro y asi sucecivamente

        //El for recorrera los valores de la matriz
        for (i in (0 until matriz.size)){
            //El siguiente for recorrera los datos del array i, guardando el valor de la matriz hijo el la literal j
            for (j in (0 until matriz[i].size)){
                //Imprimimos el valor
                println("Posicion [$i][$i] : ${matriz[i][j]}")
            }
        }






    }


}

