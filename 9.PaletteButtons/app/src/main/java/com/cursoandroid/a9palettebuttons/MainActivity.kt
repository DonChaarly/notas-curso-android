package com.cursoandroid.a9palettebuttons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton

    class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /////////////////////        CHIP GROUP Y CHIP     /////////////////////////

        //20. Para recuperar un chipGroup del layout se hace los mismo que todos los view
        var cgHabitaciones: ChipGroup = findViewById(R.id.cgHabitciones)

        //21. Definimos una variable llamada chip donde se guarde alguno chip de chipGroup
        var chip: Chip

        //22. Par recorrer los elementos del chipGroup podemos utilizar un bucle for, con esto podemos aplicar un codigo a todos los chips que contenga el grupo
        //23. Con childCount obtenemos el numero de chips que tiene el chip group
        for (i in 0 .. cgHabitaciones.childCount -1){
            //24 Guardamos el chip que estemos recorriendo en este momento en la variable chip
            //25. Con el elemento getChilAt() se puede obtener el chip indicando el indice, sin embargo esto devuelve un objeto view, lo podemos castear a un objeto chip
            chip = cgHabitaciones.getChildAt(i) as Chip

            //26. Podemos cambiar atributos de los chips tambien desde codigo como cualqueir otro view
            chip.textAlignment = View.TEXT_ALIGNMENT_CENTER

            //27. Podemos configurar eventos, en este caso el evento de cuando se de click en el icono de cierre del chip el cual es setOnCloseIconClickListener
            chip.setOnCloseIconClickListener(){
                //28. Cuando este evento se de vamos a remover el elemento al que se este haciendo referencia en este momento con el metodo removeView
                cgHabitaciones.removeView(it)
            }
            //29. Otro evento que podemos configurar es el OnClickListener
            chip.setOnClickListener(){
                //31 El it que nos pasa la lambda es de tipo view, lo necesitamos de tipo chip, vamos a hacer un cast
                var aux = it as Chip
                //30 Cuando este evento se de, vamos a mostrar un mensaje por panalla
                Toast.makeText(this, "${aux.text} pulsado", Toast.LENGTH_SHORT).show()
            }
        }

        //32 Asi mismo desde codigo se puede agregar mas chips al chipgroup, para esto se crea una variable chip de tipo Chip
        val chipNew = Chip(this)

        //33 A este chip se le pueden modificar su propiedades tambien, vamos a utilizar las funciones scope para practicar
        chipNew.let{
            it.text = "Opcion"
            it.chipIcon = ContextCompat.getDrawable(this, R.drawable.ic_coche)
            it.isChipIconVisible = false
            it.isCloseIconVisible = true
            it.isClickable = true
            it.isCheckable = false
        }

        //34 Agregamos el nuevo chip al chip group, el metodo espera un view asi que el chip tiene que castearse a este objeto
        cgHabitaciones.addView(chipNew as View)
        //35 Se le puede configuara un evento a este chip tambien
        chipNew.setOnCloseIconClickListener() {
            cgHabitaciones.removeView(chipNew as View)
        }


        ///////////////////////////       RADIO GROUPS Y RADIO BUTTONS      /////////////////////////////////////////

        //38. Para recuperar un radiogroup, se hace igual como cualquier view
        var rgVacaciones = findViewById<RadioGroup>(R.id.rgVacaciones)

        //39. Podemos acceder a sus elementos por medio del metodo getChildAt (como en los chipsGroups), y se pasa como parametro el indice del elemento que quremos
        //40. este metodo nos devuelve un view por defecto, pero se puede castear a un tipo RadioGroup
        var playa = rgVacaciones.getChildAt(1) as RadioButton

        //41 Tambien con este view desde codigo podemos modificar todas sus propiedades

        //42 Para asignar el radioButton seleccionado por defecto
        rgVacaciones.check(playa.id)


        //////////////////////////       CHECKBOXES       //////////////////////////////////////////////////

        //49. Se recupera un checkbox, se hace igual como cualquier view
        var cbSeguro = findViewById<CheckBox>(R.id.cbSeguro)

        //50 Tambien se pueden modificar sus propiedades desde codigo
        cbSeguro.isEnabled = true
        cbSeguro.isChecked = true

        //51 Se le puede asignar tambien eventos OnClick que llamen a funciones declaradas en codigo


        //////////////////////////        TOGGLE BUTTON      //////////////////////////////////////

        //54 Se recupera un toggle button, se hace igual com cualquier view
        var tbNoSi = findViewById<ToggleButton>(R.id.tbNoSi)

        //55 Se le puede cambiar su propiedades tambien desde el codigo

        //56 Se puede establecer por ejemplo la propiedad de un evento, y con una funcion lambda indicar el codigo que quremos que se ejecute
        //57 En la lambda se tiene dos parametros, el view y el si esta marcado o no
        tbNoSi.setOnCheckedChangeListener {view, isChecked ->
            if(isChecked) Toast.makeText(this, "Toggle Activado", Toast.LENGTH_SHORT).show()
            else Toast.makeText(this, "Toggle desactivado", Toast.LENGTH_SHORT).show()
        }


        //////////////////////////              SWITCH     /////////////////////////////////////

        //60 Se recupera un switch, se hace igual como cualquier view
        var swCoche = findViewById<Switch>(R.id.swCoche)

        //61 Tambien se pueden cambiar sus atributos desde codigo y especificar un codigo para atributos relacionados con eventos

        swCoche.setOnCheckedChangeListener {view, isChecked ->
            if(isChecked) Toast.makeText(this, "Switch activado", Toast.LENGTH_SHORT).show()
            else Toast.makeText(this, "Switch desactivado", Toast.LENGTH_SHORT).show()
        }


        ////////////////////////////////        FLOATING ACTION BUTTON        ///////////////////////////////

        //64 Se recupera un floating action button, se hace igual com ocualquier view
        var fabButon = findViewById<FloatingActionButton>(R.id.floatingActionButton)

        //65 Se puede cambiar sus atributos del boton igual que cualquier view, como tambien los atributos que tiene que ver con los eventos
        fabButon.setOnClickListener{
            Toast.makeText(this, "Floating Button Pulsado", Toast.LENGTH_SHORT).show()
        }

    }

    //////////////////////   FUNCIONES PARA EVENTOS ON CLICK         /////////////////////////////

    /*43 Cuando se establece una funcion que se va a ejecutar en un evento onClick, esta funcion
        no debe estar privada, ya que al momento de que se necesite ejecutar, no se podra acceder a
        ella y por ende la apliacion fallara */

    //44 Se necesita recibir como parametro un objeto view ya que este es el que manda kotlin independientemente si se ejecuto en un boton, en un editText, etc
    fun onRadioButtonClicked(view: View){
        //45 Se puede hacer una comprobacion de si el objeto que se recibe es un radiobutton, en caso de ser true, se ejecutara el codigo
        if(view is RadioButton){
            //46. en una variable checke con elmetodo isChecked guardamos true o false dependiendo si se seleccionado el boton o no
            var checked = view.isChecked

            //47 Con un When establecemos varias opciones dependiendo dependiendo de que readioButton se ha seleccionado
            when(view.id){
                R.id.rbPlaya -> {
                    if (checked) Toast.makeText(this, "Vamos a la playa", Toast.LENGTH_SHORT).show()
                }
                R.id.rbMontaña -> {
                    if (checked) Toast.makeText(this, "Vamos a la Montaña", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //52 Este metodo se lanzara en los checkBoxes
    fun onCheckBoxClicked(view: View){
        if(view is CheckBox){
            var checked = view.isChecked

            when(view.id){
                R.id.cbSeguro -> {
                    if (checked) Toast.makeText(this, "Seguro contratado", Toast.LENGTH_SHORT).show()
                    else Toast.makeText(this, "Seguro Anulado", Toast.LENGTH_SHORT).show()
                }
                R.id.cbCancelable -> {
                    if (checked) Toast.makeText(this, "La reserva podra ser cancelada", Toast.LENGTH_SHORT).show()
                    else Toast.makeText(this, "La reserva NO podra ser cancelada", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }



}
