package com.cursoandroid.a13viewgroups

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class calculadora : AppCompatActivity() {
    var num: String = "Total: "
    var num1: String = ""
    var num2: Float = 0F
    var result: Float = 0F
    var operadores: MutableList<Char> = mutableListOf()
    var numbers: MutableList<Float> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)

    }

    fun capturaBoton(v: View){
        var total = findViewById<TextView>(R.id.tvTotal)

        if (v.tag != "C" && v.tag != "del" && v.tag != "=" && v.tag != "÷" && v.tag != "x" && v.tag != "-" && v.tag != "+"){
            if (num != "Total: NO SE PUEDE DIVIDIR ENTRE 0"){
                num += v.tag
                total.text = num
                num1 += v.tag
            }else{
                num = v.tag.toString()
                total.text = num
                num1 = v.tag.toString()
            }
        }
        when{
            v.tag == "C" -> {
                num = "Total: "
                total.text = num
                num1 = ""
                numbers.clear()
                operadores.clear()
                result = 0F
            }
            v.tag == "del" -> {
                if (num != "Total: "){
                    num = num.dropLast(1)
                    total.text = num
                    num1 = num1.dropLast(1)
                    var ultimo = num.last()
                    if (ultimo == 'x' || ultimo == '÷' || ultimo == '-' || ultimo == '+'){
                        operadores.removeLast()
                    }
                }

            }
            v.tag == "÷" -> {
                if (num1 != ""){
                    num += "÷"
                    total.text = num
                    operadores.add('÷')
                    num2 = num1.toFloat()
                    num1 = ""
                    numbers.add(num2)
                }
            }
            v.tag == "x" -> {
                if (num1 != ""){
                    num += "x"
                    total.text = num
                    operadores.add('x')
                    num2 = num1.toFloat()
                    num1 = ""
                    numbers.add(num2)
                }
            }
            v.tag == "-" -> {
                if (num1 != ""){
                    num += "-"
                    total.text = num
                    operadores.add('-')
                    num2 = num1.toFloat()
                    num1 = ""
                    numbers.add(num2)
                }
            }
            v.tag == "+" -> {
                if (num1 != ""){
                    num += "+"
                    total.text = num
                    operadores.add('+')
                    num2 = num1.toFloat()
                    num1 = ""
                    numbers.add(num2)
                }
            }
            v.tag == "=" -> {

                if (num1 != ""){
                    num2 = num1.toFloat()
                    numbers.add(num2)
                    var index = 0
                    while (operadores.contains('x') || operadores.contains('÷')){
                        if (operadores[index] == 'x'){
                            result = numbers[index] * numbers[index + 1]
                            numbers[index] = result
                            numbers.removeAt(index + 1)
                            operadores.removeAt(index)
                        }else if(operadores[index] == '÷'){
                            if(numbers[index + 1] != 0F){
                                result = numbers[index] / numbers[index + 1]
                                numbers[index] = result
                                numbers.removeAt(index + 1)
                                operadores.removeAt(index)
                            }else{
                                num = "Total: NO SE PUEDE DIVIDIR ENTRE 0"
                                total.text = num
                                num1 = ""
                                numbers.clear()
                                operadores.clear()
                                result = 0F
                            }
                        }else{
                            index += 1
                        }
                    }
                    index = 0
                    while (!operadores.none()){
                        if (operadores[index] == '+'){
                            result = numbers[index] + numbers[index + 1]
                            numbers[index] = result
                            numbers.removeAt(index + 1)
                            operadores.removeAt(index)
                        }else if(operadores[index] == '-'){
                            result = numbers[index] - numbers[index + 1]
                            numbers[index] = result
                            numbers.removeAt(index + 1)
                            operadores.removeAt(index)
                        }
                    }
                    if (num != "Total: NO SE PUEDE DIVIDIR ENTRE 0"){
                        num = "Total: " + numbers[0].toString()
                        num1 = numbers[0].toString()
                        numbers.removeAt(0)
                        total.text = num
                    }
                }
            }
        }
    }
}