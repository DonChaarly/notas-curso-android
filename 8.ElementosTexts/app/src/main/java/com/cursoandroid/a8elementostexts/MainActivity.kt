package com.cursoandroid.a8elementostexts

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ////////////////////////    ELEMENTO TEXT VIEW      //////////////////////////////////////

        /* 3. Para poder guardar la referencia de un objeto de tipo view que hay en la iu se declara
           una variable y llamando al metodo findViewById recuperaremos un elemento tipo View por su id, entre <> se indica el tipo de view */
        //4. Entre parentesis se coloca la direccion del objeto view, se coloca primero la clase R seguida de id y finalmente el nombre que se le coloco al view
        //5. Se puede colocar el mismo nombre a la variable que el que tiene el view, sin embargo no es necesrio
        var tvEjemplo = findViewById<TextView>(R.id.tvEjemplo)

        //6. Al ya tener el objeto textview en una variable, este puede ser modificado desde este codigo mediante sus atributos
        //7. Todos los cambios se realizaran una vez se ha lanzado el la apliacion y se ejecute este codigo

        //8. El metodo text se utiliza para modificar el contenido se utiliza
        tvEjemplo.text = "Texto cambiado desde codigo"
        //9. El metodo setTextColor se utiliza para cambiar el color del texto, entre parentesis se especifica el color
        tvEjemplo.setTextColor(Color.RED)
        //10. Para modificar el typeFace
        tvEjemplo.setTypeface(Typeface.MONOSPACE, Typeface.BOLD)


        /////////////////////////          EVENTOS          /////////////////////////////////////////

        //11. Los eventos son sucesos asociados al elemento view y que ocurren en la apliacion, como dar un click, colocar el cursos sobre, etc.

        /* 12. El evento setOnClickListener se llama cada vez que se ha hecho click sobre el elemento
           Hay dos opciones para este metodo, donde se coloca pasamos como parametro un lambda entre parentesis
           o donde escribimos directamente el lambda despues de unas llaves. */
        tvEjemplo.setOnClickListener {
            //13. Mostramos un mensje en pantalla con un toast cuando se hace click sobre el elemento
            Toast.makeText(this, "TextView clicked", Toast.LENGTH_SHORT).show()
            //14. Podemos hacer tambien por ejemplo que cambie de color el texto, se puede hacer lo que queramos.
            tvEjemplo.setTextColor(Color.GREEN)
        }

        ///////////////////////////      EDIT TEXT          //////////////////////////////////////

        //16. Los edit text son views que permiten capturar informacion del usuario

        //17. guardamos en una variable el edit text de la interface.
        var etEjemplo: EditText = findViewById(R.id.etEjemplo)

        //18. Podemos asociar el evento addTextChangedListener el cual se ejecutara cuando se cambie el contenido del editText
        etEjemplo.addTextChangedListener {
            //19 Vamos a enviar un error con el metodo setError que se ejecute si la longitud de la cadena es igual a 0
            //20 Para obtener la longitud de la cadena accedemos recuperamos el text del objeto view y con el metodo length sabemos la longitud
            if(etEjemplo.text.length == 0) etEjemplo.setError("Campo vacio")
        }

        //21 Para seleccionar una determinada cantidad de caracteres de la cadena, se puede utilizar el metodo setSelction al cual se le pasa el inicio y final de la seleccion
        etEjemplo.setSelection(0, 3)
        //22 La propiedad selectionStar y selectionEnd indican en que indice inicia la seleccion y en que indice termina
        var inicioSeleccion = etEjemplo.selectionStart
        var finalSeleccion = etEjemplo.selectionEnd
        //23 El metodo selectAll seleccionamos toda la cadena
        etEjemplo.selectAll()


        //////////////////////      AUTOCOMPLATE TEXT VIEW   //////////////////////////////////

        /*26 Los autocomplate text view son elementos que nos permiten mostrar al usuario una serie de opciones
             que coincidan con las letras que este escribiendo
             Para esto se puede asociar un arrayList a este elemento para mostrar la serie de opciones*/

        //27. Guardamos en una variable el autocomplate text view
        var autocomplateTextView: AutoCompleteTextView = findViewById(R.id.autoCompleteTextView)

        //28. El arrayList puede declararse aqui o en un archivo de string en recursos
        //29 Para recuperar el array, accedemos a los recursos por medio del comando resources
        var countries: Array<String> = resources.getStringArray(R.array.countries_array)

        //30 Se necesita crear tambien un adaptador el cual sera el encargado de vincular las opciones con lo que escribe el usuario
        //31 Se crea el objeto ArrayAdapeter, pasando a su constructor el contexto, el layout osea el aaspecto visual de las opciones, y se pasa los datos osea el array
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, countries)

        //32 Finalmente se pasa el establece el adaptador para el objeto autocomplateTextView
        autocomplateTextView.setAdapter(adapter)


        //////////////////       MULTI AUTO COMPLATE TEXT VIEW    /////////////////////////////////

        /*34 Este elemento funciona igual que el autocomplate text view, sin embargo este ultimo solamente muestra las opciones la primera vez que se utiliza
             Pero el multi puede mostrar varias veces la ayuda con forme se van agregando paalbras*/

        //35 Se guarda en una variable el multiAutoComplateTextView
        var multiAutoCompleteTextView = findViewById<MultiAutoCompleteTextView>(R.id.multiAutoCompleteTextView)

        //Se necesita asociar un adptador para que vincule los datos del array con lo que escriba el usuario
        multiAutoCompleteTextView.setAdapter(adapter)
        //36 Tambien es necesario especificar un tokenizer que sera la forma en que se separan las cadenas que se introduzcan para seguir mostrando la ayuda, en este caso sera por medio de una coma
        multiAutoCompleteTextView.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())



    }
}