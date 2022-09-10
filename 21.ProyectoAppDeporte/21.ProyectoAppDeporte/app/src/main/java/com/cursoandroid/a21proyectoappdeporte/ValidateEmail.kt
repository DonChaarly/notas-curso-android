package com.cursoandroid.a21proyectoappdeporte

import java.util.regex.Matcher
import java.util.regex.Pattern

class ValidateEmail {

    companion object{
        //61. Se crea una variable pat en la cual especificaremos el patron
        var pat: Pattern?= null
        //62. Se crea una variable mat la cual sera la que evalue el string con el patron
        var mat: Matcher?= null

        //61. Se crea una funcion que recibe un string y con la variable de tipo Pattern y Matcher, evalua el patron que le especificamos
        fun isEmail(email:String): Boolean {
            pat =
                Pattern.compile("^[\\w\\-\\_\\+]+(\\.[\\w\\-\\_]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$")
            mat = pat!!.matcher(email)
            return mat!!.find()
        }
    }


}