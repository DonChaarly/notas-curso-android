package com.cursoandroid.coleccionesyarreglosmetodos

import kotlin.reflect.typeOf

fun main(){

    var arrayStrings: Array<String> = arrayOf("solo", "Casa", "Regalo")
    var arrayStrings2: Array<String> = arrayOf("solo", "Cascara", "Castellano")

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
    println(arrayStrings.contentToString())
    //Devuelve el numero de elementos que tiene el array
    arrayStrings.count()
    //Devuelve un booleano dependiendo de si el arreglo esta vacio
    arrayStrings.isEmpty()
    //Devuelve un booleano dependiendo de si el arreglo no tiene elementos
    arrayStrings.none()
    //Devuelve el elemento que se indica, si no existe devuleve null
    println(arrayStrings.elementAtOrNull(1))
    //Devuelve el elemento del indice indicado, y si no existe devuelve null
    println(arrayStrings.getOrNull(6))
    //Devuelve un arreglo de los indices del array
    println(arrayStrings.indices)
    //Devuelve el numbero del ultimo indice del arreglo
    println(arrayStrings.lastIndex)
    //Devuelve el indice del elemento que se especifique
    println(arrayStrings.indexOf("solo"))
    //Devuelve el ultimo elemento del arreglo
    println(arrayStrings.last())
    //Devuelve el primer elemento del arreglo
    arrayStrings.first()
    //Devuleve un objeto iterable de los string
    val arrayIterator = arrayStrings.iterator()
    //Devuelve verdadero si hay algun elemnto mas a iterar
    while (arrayIterator.hasNext()){
        //Devuelve el siguiente elemento a iterar
        println(arrayIterator.next())
    }
    //Devuelve true si el contenido del otro array es igual
    println(arrayStrings.contentEquals(arrayStrings2))
    //Devuelve el codigo hash del contenido del string, esto es lo que hace equals
    if(arrayStrings.contentHashCode() == arrayStrings2.contentHashCode()) println(true)
    //Crea un clone del array
    val arrayStrings3 = arrayStrings.clone()
    //Devuelve una copia del arreglo con el nuevo tamano que se le especifico
    val arrayStrings4 = arrayStrings.copyOf(5)
    //DEvuelve uan copia del arreglo desde el rango de indices que se le haya colocado, los indices deben existir
    val arrayStrings5 = arrayStrings.copyOfRange(1, 2)
    //Rellena un arreglo con la palabra que se le coloque en el rango que se le indica
    arrayStrings4.fill("cascara",2, 4)
    //Devuelve una copia del areglo pero agregando el elemento que se indica
    val arrayStringss5 = arrayStrings4.plusElement("cabello")
    //Devuelve una copia del arreglo pero agreando el array o coleccion que se le indique
    val arrayStrings6 = arrayStrings4.plus(arrayStrings5)
    //Invierte el orden del arreglo, no devuelve nada
    arrayStrings.reverse()
    //Invierte el orden del arreglo, en el rango especificado
    arrayStrings.reverse(1,2)
    //Devuelve una copia del array pero con los elementos invertidos en orden
    val arrayStrings7 = arrayStrings.reversedArray()
    //Devuelve una lista del array pero con los elementos invertidos en orden
    val listString7 = arrayStrings.reversed()
    //Cambia el orden de los elementos del arreglo de manera aleatoria
    arrayStrings.shuffle()
    //Devuelve un array con los elementos de los indices especificados en el arreglo
    val arrayStrings8 = arrayStrings.sliceArray(0..1)
    //Devuelve una lista con los elemenetos de los indices especificados en el arreglo
    val listString8 = arrayStrings.slice(0..1)
    //Ordena los elementos del arreglo
    arrayStrings.sort()
    //Ordena los elementos del arreglo en el rango de indices especificado
    arrayStrings.sort(2, 3)
    //Devuelve una copia del arreglo con los elementos ordenados
    val arrayStrings9 = arrayStrings.sortedArray()
    //Devuelve una lista del arreglo con los elementos ordenados
    val listString9 = arrayStrings.sorted()
    //Devuelve una lista del arreglo con los elementos ordenados de manera descendiente
    val arrayStrings10 = arrayStrings.sortedArrayDescending()
    //Devuelve una lista del arreglo con los elementos ordenados de manera descendiente
    val listString10 = arrayStrings.sortedDescending()
    //Devuelve true o false dependiendo de si todos los elemetnos cumplen con la condicion especificada
    arrayStrings.all { name -> name.length > 3 }//true
    //Devuelve true o false dependiendo de si alguno de los elementos cumple con la condicion especificada
    arrayStrings.any { name -> name.length > 7 }
    //Crea un mapa llave valor, donde la llave sera el elemento dentro de las llaves y los valores, los elementos del arreglo
    //Si se repite una llave, se sobreescribira el valor
    arrayStrings.associateBy { it.length }
    //Devuelve un objeto iterable del array que se especifico
    val arrayStringsIterable = arrayStrings.asIterable()
    //Devuelve true o false dependiendo de si el arreglo contiene el elemento especificado
    arrayStrings.contains("todo")
    //Copia el arreglo en un arreglo ya creado, sustituyendo sus valoes
    arrayStrings.copyInto(arrayStrings2)
    //Devuelve una lista de los elementos que son unicos dentro del arreglo
    arrayStrings.distinct()
    //Devuelve una lista que contiene todos los elementos excepto los primeros n elmentos indicados
    arrayStrings.drop(2)
    //Devuelve una lista que contiene los elementos que cumplen con la condicion establecida
    arrayStrings.filter { name -> name.length > 3 }
    //Devuelve el primer elmento que satisface la condicion especificada
    arrayStrings.find { name -> name.length > 3 }
    //Devuelve el ultimo elemento que satisface la condicion especificada
    arrayStrings.findLast { name -> name.length > 3 }
    //Devuelve la acumulacion de valores del arreglo, guardando el valor acumulado en cada iteracion
    val valorInicial = "hola"
    arrayStrings.fold(valorInicial){valorAcumulado, i ->
        valorAcumulado + i
    }
    //Devuelve la acumulacion de valores del arreglo pero con el recorrido invertido
    arrayStrings.foldRight(valorInicial){valorAcumulado, i ->
        valorAcumulado + i
    }
    //Hace un forEach del arreglo
    arrayStrings.forEach { it -> println(it) }

    //Devuelve una lista con todos los valores que se van acumulando con las interaciones
    val valores = arrayStrings.runningFold(valorInicial){valorAcumulado, i ->
        valorAcumulado + i
    }
    //Devuelve una lista en donde los elementos seran las letras de cada string en este caso del arreglo
    val listaFlat = arrayStrings.flatMap { it.asIterable() }
    //Devuelve un mapa de elementos que son agrupados segun la condicion que se establezca
    arrayStrings.groupBy { it.length< 3  }
    //Recorre los elementos del arreglo, y se les puede modificar de acuerdo a la funcion que se establezca, depues de devuelve una lista con los elementos ya modificados
    val listaMap = arrayStrings.map { it -> it + "hola" }
    //Devuelve un elemnto al azar
    arrayStrings.random()
    //Devuelve un set con los elementos que estan en ambos arreglos especificados
    arrayStrings.intersect(arrayStrings2.asIterable())
    //Devuelve un set con los elementos que estan en este array y que no tiene el otro
    arrayStrings.subtract(arrayStrings2.asIterable())


















}