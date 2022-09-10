package com.cursoandroid.a11viewsocultos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker
import android.widget.TextView
import com.cursoandroid.a11viewsocultos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //16. Se necesita crear la siguiente variable la cual por lo general siempre se llama binding la cual sera del tipo de actividad enla que estemos, cuidado en colocar Main si no se esta en el activity Main
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //17. Se inicializa el objeto binding con el metodo inflate pasando como parametro layoutInflater
        binding = ActivityMainBinding.inflate(layoutInflater)

        //setContentView(R.layout.activity_main)


        ///////////////             NUMBER PICKER          ////////////////////////////////

        //7. Se recuperar un NumberPikcer con normalidad
        var npEjemplo = findViewById<NumberPicker>(R.id.npEjemplo)
        var tvNumPikcer = findViewById<TextView>(R.id.tvNumPicker)
        //8. Se pueden modificar sus propiedades
        npEjemplo.minValue = 0
        npEjemplo.maxValue = 59
        npEjemplo.value = 5
        //9. Se escoge el wrapSelector el cual va a ser la forma en que se muestran los numeros, en este caso sera en forma de rueda
        npEjemplo.wrapSelectorWheel = true
        //10. Se le puede indicar que los numero aparezcan en un formato en especifico
        npEjemplo.setFormatter { i -> String.format("%02d", i) }

        //11. Se puede configuara el evento setOnValueChangeListener
        //12. En el lambda se tendran tres parametros, el propio NumberPicker, el valor antiguo y el nuevo valor
        npEjemplo.setOnValueChangedListener { numberPicker, oldVal, newVal ->
            tvNumPikcer.text = "NumberPicker: Antes (${oldVal}) - Ahora (${newVal})"
        }


        //////////////////              VIEW BINDING       /////////////////////////////////

        //18 Ahora se puede hacer referencia a los objetos del layout de la siguiente forma
        binding.tvNumPicker.text = "Cambio de texto haciendo uso del view Binding"
        //19 Con la variable binding se tiene acceso a todos los elemntos del layout y a sus propiedades
    }
}