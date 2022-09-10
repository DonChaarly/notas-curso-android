package com.cursoandroid.a2introduccionkotlin

class `1VariablesYOperadores` {

    //Las constantes se declaran en un bloque companion
    companion object{
        //las constantes vienen precedidas de la etiqueta const
        const val constante = "EUR"
    }

    fun variablesYOperadores(){
        //////////////////////           VARIABLES, VALORES Y CONSTANTES      ///////////////////////////////////
        //nota: kotlin puede inferir el tipo de variable, en algunos casos no es necesario especificar este valor
        //      no se puede cambiar el tipo de dato

        //Variable: Puede cambiar su valor
        var varibale = "Carlos"
        varibale = "Karla"

        //Valores: No pueden cambiar su valor
        val valor = "Panchito"
        //valor = "Ricardo" no se puede

        //Constantes: no pueden crearse como variables locales tiene que crearse hasta arriba de la clase
        //const val constante = "EUR" no se puede



        //////////////////////////////      TIPOS DE DATOS            ///////////////////////////////////////////////////
        //nota: El tipo de dato comienza por mayuscula

        //Tipo String: cadenas de texto, tiene que ir entre comillas dobles
        var string: String = "Rafael"

        //Tipo Char: Acepta solo un caracter, tiene que ir entre comillas simples
        var char: Char = 'd'

        //Tipo Boolean: Valores verdadero o falso
        var boolaen: Boolean = true

        //Tipo Int (32 bits): Numeros enteros sin punto decimal
        var int: Int = 5

        //Tipo Float (32 bits): Numero con punto decimal, se tiene que poner una f
        var float: Float = 5.23f

        //Tipo Double (64 bits): Numeros con punto decimal, pero estos ocupan mas memoria
        var double: Double = 5210.35

        //Tipo Long (64 bits): Numeros sin punto decimal, pero estos ocupan mas memoria
        var long: Long = 523

        //Tipo Short (16 bits): Numeros sin punto decimal, pero estos ocupan menos menoria
        var short: Short = 32

        //Tipos Byte (8 bits): Numeros sin punto deciaml, son los que menos menoria ocupan
        var byte: Byte = 3

        //Tipo Any: Este tipo significa cualquiera, en ocasiones declararemos variables en las cuales pueden guardarse distintos tipos de valores, por ellos colocamos el tipo Any
        var cualquierTipo: Any = 3



        ////////////////////////////////            OPERADORES ARITMETICOS      /////////////////////////////////////

        //En kotlin se tiene los operadores comunes, no cambiar
        //Se siguen las mismas regals de la aritmeticas en cuestion de gerarica de valores
        //Si se asigna un valor Int y se tiene una division no se guardara el valor decimal

        var ejemploOperadores: Int = 2 + 2 - 2 * 2 / 2 % 2

        //Preincremento: Se incrementa el valor una unidad en la misma linea de codigo
        var preincremento = ++ejemploOperadores

        //Predecremento: Se decrementa el valor una unidad en la misma linea de codigo
        var predecremento = --ejemploOperadores

        //Postincremento: Se incrementa el valor una unidad en la hasta la siguiente ves que es llamado
        var postincremento = ejemploOperadores++

        //Postdecremento: Se decrementa el valor una unidad en la hasta la siguiente ves que es llamado
        var postdecremento = ejemploOperadores--

        //Operadores abrebiados (+=, -=, *=, /=, %=)
        //En kotlin tambien se tienen estos operadores que le modifican el valor que le asignemos a la misma variable
        var opAbrebiado = 5
        opAbrebiado += 2 //Se le suma dos unidades a la misma variable

        //Operadores de comparacion (==, !=, >, <, >=, <=)
        //En kotlin se tiene tambien los operadores de comparacion comunes, Regresan un valor booleano
        var opComparacion: Boolean = ejemploOperadores == opAbrebiado



        ///////////////////////////        IF Y ELSE           //////////////////////////////////////////

        //IF: Funciona normal, se recibe la condicion entre parentesis y se coloca el codigo a ejecutar entre llaves
        //El else funciona normal tambien
        if( int < short){
            int += 2
        }else{
            int += 1
        }

        //Si se ejecuta solo una line de codigo se pueden omitir las llaves
        if (int < short) int += 2
        else int += 1

        ////////////////////////////       WHEN       ///////////////////////////////////

        //El When no es lo mismo, es mas bien como un switch
        //Recibe un valor y dentro del bloque de codigo se especifican las opciones
        var valorWhen = 0
        when(valorWhen){
            //Si el valor de valorWhen es 0 entonces imprime un saludo
            0 -> print("Saludos")
            //Si el valor de valorWhen es 1 entonces imprime una despedida
            1 -> print("Adios")
            //Si el valor de valorWhen es 2 entonces dice buenas tardes
            2 -> print("Buenas tardes")
            //Tambien se puede colocar un else para especificar que hacer en caso de que ninguna opcion coincida
            else -> print("Saquese por ahi")
        }

        ////////////////////////     AND Y OR       ///////////////////////////////////////

        //AND: Se utiliza &&
        //OR: Se utiliza ||

        ////////////////////////    WHILE           /////////////////////////////////////

        //El while es lo qu seria el when, siempre y cuando se cumpla la condicion, se ejecutara el codigo

        var condicion = 0
        while(condicion < 3){
            print("La condicion es: ${condicion}")
            condicion += 1
        }

        ///////////////////////     DoWhile         /////////////////////////////////////

        //El operador doWhile ejecuta una vez el codigo sin importar si la condicion se cumple o no

        do{
            print("La condicion es: ${condicion}")
            condicion += 1
        }while (condicion > 1)


        ////////////////////////     FOR           ////////////////////////////////////

        //El bucle funciona especificando un rango sobre el cual va a iterar
        //Se especifica una literal donde se va a guardar el valor del elemento que se este iterando
        //Se coloca un in seguido del rango en el que se va a iterar

        for (i in 0 .. 3){
            println(i)
        }

        //Tambien se puede utilizar el comando until, sin embargo el ultimo valor del rango no se tomara en cuenta
        for (i in (0 until 3)){
            println(i)
        }


        ////////////////////////////     Break      /////////////////////////////////

        //El break es igual, el break rompe cualquier bucle si se ejecuta
        var condicionBreake = 2
        while(condicionBreake < 5){
            print("la condicion es: ${condicionBreake}")
            condicionBreake += 1
            //Si la condicion llega a 4 se ejecutara la linea breake, rompiendo el bucle
            if(condicionBreake == 4){
                break
            }
        }
    }
}