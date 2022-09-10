package com.cursoandroid.a2introduccionkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class `5NullYNullSafety`: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
                        NULL

        Los valores null son otro tipo de dato, estos valores significan nulo, osea que no tiene nada
        ni un cero ni un String sin palabras, es simplemente nada



         */

        //Si no se le asigna alguno valor a una variable, esta mandara error
        //Si se opera con un null tambien se provocara un error
        var valorNuloConError: String = null


        /*
                        NULL SAFETY

        un null safety es un operador que nos permite operar con nulls

         */

        //Al declarar una variable para indicar que el valor puede ser null se le agrega un ?
        var valorNull: String? = null

        //Si en una linea nos manda un error el compilador diciendo que la varaible con la que estamos operando puede
        //puede llegar a ser null y nosotros estamos seguros que no, pero tenemos que estas seguros seguros
        //Se coloca !! para indicar esto

        print(valorNuloConError)!!








    }


}