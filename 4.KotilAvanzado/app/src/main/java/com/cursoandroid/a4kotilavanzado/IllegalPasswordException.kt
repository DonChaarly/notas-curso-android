package com.cursoandroid.a4kotilavanzado

import java.lang.Exception

//46 El mensaje que se reciba se manda al constructor del padre
class IllegalPasswordException(message: String): Exception(message) {
}