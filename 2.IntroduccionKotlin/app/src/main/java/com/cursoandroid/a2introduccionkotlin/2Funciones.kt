package com.cursoandroid.a2introduccionkotlin

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class `2Funciones`: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Codigo 1
        funcionNormal()

        // Codigo 2
        funcionConParametros("Carlos")

        //Codigo 3
        var resultadoProducto = funcionConRetorno(3, 6)

        //Codigo 4
        var resultadoSuma = suma(2, 5)
        println(resultadoSuma)



    }


    /*
      Las funciones son piezas de codigo que pueden ejecutarse varias veces y pueden llamarase
      desde diferentes partes del codigo sin necesidad de escribirlas nuevamente
     */

    /////////////////////////////  SINTAXIS Y LLAMADO     ////////////////////////////////

    //Para delcarar un funcion se coloca la palabra fun seguido del nombre de la funcino, seguido de parentesis y lo que este entre llaves es el codigo que se va a ejecutar
    fun funcionNormal(){
        //Imprimimos un saludo solamente
        println("Saludos a todos")
    }

    //Para llamar una funcion se escribe el nombre de la funcion con parentesis
    //Se solo se puede llamar a las funciones dentro de un codigo del ciclo de vida del Activity
    //Codigo 1


    //////////////////////////////       FUNCIONES CON PARAMETROS     ///////////////////////////

    //Las funciones con parametros reciben informacion que pueden utilizar dentro del codigo
    //Los parametros que necesita la funcion se especifica dentro del parentesis, se coloca un nombre para llamar a esta variable y se tiene que especificar el tipo de datos tambien

    fun funcionConParametros(nombre: String){
        //Vamos a enviarle un saludo a la persona que nos especifiquen
        println("Saludos $nombre")
    }

    //Para llamar esta funcion ahora entre parentesis se le tiene que espedificar un valor del tipo que especifica la funcion
    //Codigo 2


    ///////////////////////////      FUNCIONES CON RETORNO       ////////////////////////////////

    //Las funciones no solamente reciben informacion tambien pueden regresar algun valor
    //Se tiene que especificar que tipo de valor regresa la funcion, esto se especifica despues de los parentesis y antes de las llaves
    //Dentro del codigo de la funcion se coloca la palabra return para indicar lo que va a regresar

    fun funcionConRetorno(factor1: Int, factor2: Int): Int {
        //Se va a hacer la multiplicacion de los dos factores y se regresara el resultado
        return (factor1 * factor2)
    }

    //Para llamar esta funcion se le tiene que pasar los parametros pero tambien se pueden crear una nueva variable para almacenar el valor que regresa la funcion
    //Codigo 3


    ////////////////////////////         FUNCIONES CON RETORNO SIN ESPECIFICAR         /////////////////

    //Se igual en las variables se puede omitir el tipo de dato que es la varaible haciendo que android intuya de que tipo se trata
    //Igual es en las funciones

    //La funcion ejecuta lo que esta a la derecha del = y debido a que a la funion le estamos asignando con el = ese codigo estamos diciendo que retornara un Int
    fun suma(x: Int, y: Int) = x + y

    //La funcion se ocupa con normalidad
    //Codigo 4





}