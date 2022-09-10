package com.cursoandroid.coleccionesyarreglosmetodos

import androidx.core.text.isDigitsOnly

fun main(){

    var cadena: String = "Cadena de prueba"
    var cadena2: String = "Cadena de prueba 2"


    //Devuelve el largo de caracteres de la cadena
    println(cadena.length)
    //Concatena las cadenas que se coloquen
    println(cadena.plus(cadena2))
    //Devuelve un map de llave valor donde la llave sera lo que devuelva el codigo que se coloco y el valor seran todos los char del string
    cadena.associateBy { it.hashCode() }
    //Devuelve una lista donde los elementos seran subCadenas del tamano que se le especifique
    println(cadena.chunked(2))
    //Devuelve el codigo unicode del elemento con indice indicado
    println(cadena.codePointAt(9))
    //Devuelve un boolean dependiendo si la cadena contiene la cadena colocada como parametro
    println(cadena.contains("de"))
    //Devuelve un booleano dependiendo si la cadena es iguala la especificada
    println(cadena.contentEquals(cadena2))
    //Devuelve la cuenta de caracteres que tiene la cadena
    println(cadena.count())
    //Devuelve un string con los primero n caracteres removidos
    println(cadena.drop(3))
    //Devuelve un string con los ultimos n caracteres removidos
    println(cadena.dropLast(3))
    //Devuelve el char que que se encuentra en el indice indicado
    println(cadena.elementAt(7))
    //Devuelve el char que se encuentra en el indice indicado y si no existe se regresa null
    println(cadena.elementAtOrNull(40))
    //Codifica el string en un arreglo de bytes en UTF8
    println(cadena.encodeToByteArray())
    //Devuelve un booleano dependiendo de si la cadena termina con el char indicado
    println(cadena.endsWith("a"))
    //Devuelve un booleano dependiendo de si la cadena es igual a la otra colocada
    println(cadena.equals(cadena2))
    //Devuelve una cadena solo con los caracteres que cumplen con la condicion
    println(cadena.filter { it -> it.code > 35 })
    //Devuelve el primer caracter que cumple con la condicion
    println(cadena.find { it -> it.code == 20 })
    //Devuelve el primer caracter con el que cuenta la cadena
    println(cadena.first())
    //
    println(cadena.first { char ->
        arrayListOf<Char>('[', '{', '(').contains(char)
    })
    //Recorre la cadena y va concatenando los resultados del codigo tras cada iteracion
    println(cadena.fold("a"){acumulacion, i -> acumulacion + i})
    //Lo mismo pero en sentido contrario
    println(cadena.foldRight("a"){acumulacion, i -> acumulacion + i})
    //Recorre los elementos de la cadena
    println(cadena.forEach { char -> println(char) })
    //Devuelve el caracter enel indice especificado y si no existe regresa null
    println(cadena.getOrNull(40))
    //Devuelve el codigo hash de la cadena
    println(cadena.hashCode() == cadena2.hashCode())
    //Si la cadena es vacia ejecuta el codigo establecido dentro
    cadena.ifBlank { println("esta vacia") }
    //Si la cadena es vacia ejecuta el codigo establecido dentro
    cadena.ifEmpty {  }
    //Devuelve el indice del primer caracter que coincida
    println(cadena.indexOf("a"))
    //Devuelve el indice del primero caracter que coindica a partir del indice indicado
    println(cadena.indexOf("a", 2))
    //Devuelve un rango del tamano de los indices
    println(cadena.indices)
    //Devuelve un booleano dependiendo de si la cadena es vacia
    println(cadena.isBlank())
    //Devuelve un booleano dependiendo de si la cadena es vacia
    println(cadena.isEmpty())
    //Devuelve un booleano dependiendo de si la cadena no es vacia
    println(cadena.isNotBlank())
    //Devuelve un booleano dependiendo de si la cadena no es vacia
    println(cadena.isNotEmpty())
    //Devuelve un objeto iterador de la cadena
    val cadenaIterable = cadena.iterator()
    while(cadenaIterable.hasNext()){
        println(cadenaIterable.next())
    }
    //Devuelve el ultimo caracter de la cadena
    println(cadena.last())
    //Devuelve el ultimo indice de la cadena
    println(cadena.lastIndex)
    //Devuelve el largo de la cadena
    println(cadena.length)
    //Devuelve un string con la cadena convertida a caracteres minuscula
    println(cadena.lowercase())
    //Devuelve unalista con los caracteres pero transformados con el codigo especificado
    println(cadena.map { it -> it + "a" })
    //Devuelve el valor maximo de la cadena
    println(cadena.maxOf { it.code })
    //Devuelve el valor minimo de la cadena
    println(cadena.minOf { it.code })
    //Devuelve un booleano dependiendo de si el string tiene caracteres o no
    println(cadena.none())
    //Ejecuta el codigo especificado en cada char y devuelve la cadena resultante
    println(cadena.onEach {  })
    //Devuelve la cadena si no es null y en caso de ser null devuelve una cadena vacia
    println(cadena.orEmpty())
    //Devuelve la cadena pero con el tamano especificado, rellenando los espacios sobrantes al final con el char especificado, puede colocarse un espacio como char
    println(cadena.padEnd(30, 'b'))
    //Devuelve la cadena pero con el tamano especificado, rellenando los espacios sobrantes al inicio con el char especificado, puede colocarse un espacio como char
    println(cadena.padStart(30, 'a'))
    //Devuelve la cadena concatenada con la cadena especificada
    println(cadena.plus(cadena2))
    //Devuelve una cadena con una identacion en cada linea
    println(cadena.prependIndent())
    //Devuelve un char aleatorio de la cadana
    println(cadena.random())
    //Devuelve una cadena con el pedazo de cadena especificado eliminado, solo lo elimina si lo encuentra
    println(cadena.removePrefix("Cadena"))
    //Devuelve una cadena eliminando el rango especificado
    println(cadena.removeRange(3..7))
    //Devuelve una copia de la cadena pero repitiendola la cantidad de veces que se especifica
    println(cadena.repeat(2))
    //Reemplaza el pedazo de cadena especificado por otro
    println(cadena.replace("Cadena", "String"))
    //Despues de el string especificado, se reemplazo toodo lo demas por lo que se especifique
    println(cadena.replaceAfter("Cadena", "esto se reemplaza"))
    //Toodo lo que esta antes del string especificado es reemplazado por lo que se especifique
    println(cadena.replaceBefore("Cadena", "Esto se reemplaza antes"))
    //Devuelve la cadena con el orden invertido
    println(cadena.reversed())
    //Regresa una lista con la acumulacion de toodos los valores resulantes de la operacion
    println(cadena.scan(""){acc, c -> acc + c})
    //Regresa un string con los caracterse perteniencientes al rango especificado
    println(cadena.slice(3..9))
    //Regresa una lista en la cual los elmentos seran la cadena separada por el deliminator que se le coloque
    println(cadena.split(" "))
    //Devuelve un booleano dependiendo de si empieza con el string que se especifica
    println(cadena.startsWith("Cadena"))
    //Devuelve un charSequence con los caracteres pertenecientes al rango establecido
    println(cadena.subSequence(3..9))
    //Devuelve un string con los c aractres pertenecientes al rango establecido
    println(cadena.substring(3..9))
    //Devuelve un string con todos los caracteres encontrados depues de la cadena especificada
    println(cadena.substringAfter("Cadena"))
    //Devuelve un string con todos los caracteres encontrados antes de la cadena especificada
    println(cadena.substringBefore("p"))
    //Devuele la suma de todos los caractres de la cadena
    println(cadena.sumOf { it.code })
    //Devuelveuna cadena con los primero n caractres especificados
    println(cadena.take(3))
    //Devuelve una cadena con los ultimo n caractres especificados
    println(cadena.takeLast(4))
    //Devuelve una lista de los caracteres de la cadena
    println(cadena.toList())
    //Devuelve una cadena pero eliminando los espacios al incio y al final de la cadena
    println(cadena.trim())
    //Devuelve una cadena pero eliminando los espacios al final de la cadena
    println(cadena.trimEnd())
    //Devuelve una cadena pero eliminando los espacios al incio de la cadena
    println(cadena.trimStart())
    //Devuelve la cadena pero con los caracteres en mayuscula
    println(cadena.uppercase())

}