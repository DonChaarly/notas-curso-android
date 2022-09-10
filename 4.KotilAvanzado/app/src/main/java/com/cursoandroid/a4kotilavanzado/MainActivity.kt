package com.cursoandroid.a4kotilavanzado

import android.app.Person
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import java.lang.Exception
import kotlin.reflect.typeOf

///////////////////      TYPEALIAS          /////////////////////////////

//26 Los typealias tiene que especificarse afuera de la clase
typealias aliasArreglo = IntArray
typealias aliasLambda = ( a: Int, b: Int) -> Int

class MainActivity : AppCompatActivity() {

    /////////////////////     LATEINIT       ///////////////////////////////

    //58 El comando lateinit se utiliza en atributos de la clase, esto permite inicializar los atributos en otro momento
    private lateinit var atributo1: String
    private lateinit var atributo2: String

    //59 Sin embarago este comando no funciona con valores primitivos, estos atributos si o si se tiene que inicializar en este momento
    private var primitivo1: Boolean = false
    private var primitivo2: Int = 0


    /////////////////////////    FUNCION DE EXTENCION       /////////////////////////////
    //1 Se crea una funcion extencion para la clase String
    fun String.noSpaces(): String {
        //2 Lo que va a hacer este metodo es reemplazar los espacios por nada, osea los va a borrar
        return this.replace(" ", "")
    }
    //3 Podemos crear funciones de extencion para todas las clases
    fun IntArray.show(){
        //4 vamos a imprmir el arreglo int de una mejor manera
        print("[")
        for (i in this) print(" $i")
        println("]")
    }

    ///////////////////////////        FUNCIONES DE ORDER SUPERIOR       ////////////////////////
    //6 Las funciones de orden superior reciben como parametro otras funciones y estas las pueden ocupar dentro de su codigo
    //7 Para especificar una funcion como parametro se coloca el nombre del parametro seguido de : entre parentesis los parametros que recibe a su vez esta funcion y con -> indicamos el valor que retorna
    fun calculadora(valor1: Int, valor2: Int, funcion: (Int, Int) -> Int ): Int{
        //8 Le podemos pasar los parametro que recibe la funcion de orden superior a la funcion que recibe
        return funcion(valor1, valor2)
    }

    //9 Creamos las funciones para nuestra funcion de orden superior
    fun sumar(x: Int, y: Int): Int{
        return x + y
    }
    fun restar(x: Int, y: Int): Int{
        return x - y
    }
    fun multiplicar(x: Int, y: Int) = x * y

    //24 Cuando se quiere indicar que la funcion como parametro no regresa nada se coloca la palabra Unit que seria el void en java
    fun recorrerArray(array: IntArray, funcion: (Int) -> Unit){
        for (i in array){
            funcion(i)
        }
    }

    ////////////////////     TRY CATCH EN VARIABLE       ////////////////////////////////

    //38 vamos a crear un metodo para esto, Debido a que la variable puede guardar un int o un string colocamos como retorno un valor Any el cual es cualquiera
    fun value_try(a: Int, b: Int): Any{
        //39 En la variable res guardaremos el valor que nos revuelva el try o el catch
        var res =
            try{
                println("La division de $a/$b es = ${a/b}")

                //40 Se tiene que devolver un valor al final del bloque
                a/b
            }catch(e: Exception){
                println("Vamos a manejar este error")

                //41 Se devuelve un valor al final del bloque
                "Division no permitida"
            }
        return res
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ///////////////////     FUNCIONES DE EXTENCION      /////////////////////////
        //5 Para utilizar estas funciones de extencion se necesita crear un objeto de la clase y simplemente se llama a la funcion
        var stringPrueba = "     Hola   Estoy   Cabron   "
        println(stringPrueba)
        println(stringPrueba.noSpaces())
        var arregloPrueba = intArrayOf(3, 5, 9, 2)
        println(arregloPrueba)
        println(arregloPrueba.show())

        ///////////////////        FUNCIONES DE ORDER SUPERIOR      /////////////////////
        //10 Para pasar como argumento una funcion a una funcion de orden superior se coloca ::(nombre de funcion)
        var resultadoPrueba = calculadora(3, 5, ::sumar)
        println(resultadoPrueba)
        resultadoPrueba = calculadora(3, 5, ::restar)
        println(resultadoPrueba)
        resultadoPrueba = calculadora(3, 5, ::multiplicar)
        println(resultadoPrueba)

        //////////////////         LAMBDAS           /////////////////////////////////
        //11 Las funciones lambda se puede asignar a una varaible
        //12 Se coloca entre llaves {} la funcion lambda, dentro se tienen los parametro seguido de un -> y el codigo a ejecutar
        var funcion = {x: Int, y: Int -> x + y}

        //13 Esta varaible que realmente es una funcion se puede pasar como parametro en una funcion de orden superios que requiera una funcion
        resultadoPrueba = calculadora(2,5, funcion)
        println(resultadoPrueba)

        //14 La variable funcion puede a su vez cambiar su valor, por ende cambiar la funcion lambda
        funcion = {x: Int, y: Int -> x - y}
        resultadoPrueba = calculadora(2, 5, funcion)
        println(resultadoPrueba)

        //15 Se puede cololcar la funcion lambda directamente como argumento , esto seria una lambda anonima
        resultadoPrueba = calculadora(2, 5, {x: Int, y: Int -> x + y})
        println(resultadoPrueba)

        //16 Para que la funcion lambda retorne un valor, en la ultima linea del cuerpo del codigo se coloca el valor que se quiere retornar (Se coloco la lambda abajo para tener mejor vision
        resultadoPrueba = calculadora(2, 5, {x, y ->
            var valor = 1
            for (i in 1..y){
                valor *= x
            }

            valor
        })
        println(resultadoPrueba)

        //17 Para las funciones de orden superior las lambdas que se colocan como parametros pueden salir de los parentesis precisamente para tener mejor vision del codigo del lambda
        resultadoPrueba = calculadora(2, 5) { x, y ->
            var valor = 1
            for (i in 1..y) {
                valor *= x
            }

            valor
        }
        println(resultadoPrueba)


        /////////////////////////    LAMBDAS Y SU PARAMETRO IT    /////////////////////////

        //18 Los arrays en su constructor pueden recibir el tamaño del arreglo y una funcion lambda y dentro de esta especificar el valor que tendran los datos dentro del arreglo
        var array1 = IntArray(10) {5}
        println("array1: "); array1.show()

        //19 El valor que tomara it en la declaracion de un arreglo sera el del indice de manera iterativa de los datos del arreglo
        var array2 = IntArray(10) {it}
        println("array2: "); array2.show()
        //20 Podemos interactuar con este parametro it, por ejemeplo multiplicandolo por 2
        var array3 = IntArray(10) {it * 2}
        println("array3: "); array3.show()
        //21 Para cambiar el nombre del parametro it simplemente colocamos otro nombre en el parametro de la funcion lambda
        var array4 = IntArray(10) {i -> i * 3}
        println("array4: "); array4.show()

        //22 Esto no es parte de la clase pero lo necesitamos para explicar lo siguiente
        var botonEnPantalla: Button = findViewById(R.id.button1)

        //23 El metodo setOnClickListenner tambien recibe una lambda como parametro y su parametro it sera el objeto View el cual
        botonEnPantalla.setOnClickListener(){view -> Toast.makeText(view.context, "Hola perros", Toast.LENGTH_SHORT).show()}

        //25 Las lambdas pueden acceder a codigo externo como variables o funciones
        var resultadoSuma = 0
        recorrerArray(array4){
            resultadoSuma += it
        }
        println("La suma de todos los elementos del array4 es: $resultadoSuma")


        ///////////////////     TYPEALIAS          //////////////////////////////////////

        //27 Para utilizar un typealias configurado en la parte de arriba, simplemente se escribe la palabra clabe que se configuro

        //28 En la siguiente linea en vez de aliasArreglo en realidad se tiene IntArray
        var array5 = aliasArreglo(5){it}


        ///////////////////    DESESTRUCTURACION  //////////////////////////////////////


        //28 Con la desestructuracion podemos guardar los datos de un objeto, arreglo, etc en diferentes vairables
        var juan = Persona("Juan", "Perez", 2313553)
        //29 Para especificar los nombres de las variables, estas se escriben entre parentesis
        var (nombre1, apellido1, numero1) = juan
        println("$nombre1 $apellido1 $numero1")

        //30 Si se queire salta un dato y no guardarlo, se coloca un _
        var (nombre2, _, numero2) = juan
        println("$nombre2  $numero2")

        //31 Tambien se puede acceder a los valores medianete el metodo component()
        println("${juan.component1()} ${juan.component2()} ${juan.component3()}")
        
        var mapa1 = mutableListOf(1 to "Carlos", 2 to "Sebastian", 3 to "Camerino")
        //32 Se puede desestructurizar un mapa en un bucle for y asi obtener ambos dato, llave y valor
        for ((id, palabra) in mapa1 ){
            println("$id $palabra")
        }


        /////////////////////////    TRY CATCH FINALLY     //////////////////////////////

        //33Los bloque try catch capturan expeciones que pueden lanzarse por un error en tiempo de ejecucion

        //34 En el bloque Try se coloca el codigo que presentimos puede lanzar una excepcion
        try{
            print("Division 5/0 = ${5/0}")
        }//35 En el bloque catch se guarda como parametro la excepcion que se puede lanzar, la clase padre es Excepcion entonces para no tener errores normalmente se coloca esta como tipo de objeto
        catch(e: Exception){
            //36 En esta parte estara el codigo que se ejecutara en caso de excepcion
            println("Vamoos a manejar esta expecion")
        }//37 El bloque finally se ejecuta siempre no importa si hay o no expecion
        finally{
            println("Este texto pase lo que pase se va a imprimir")
        }


        /////////////   TRY CATCH EN UNA VARUIABLE    //////////////////////////////

        //42 Podemos llamar a la funcion en la cual se le asigno a una variable un bloque try Catch, los valores 10 entre 2 no ocasionaran un error
        println(value_try(10, 2))
        //43 Los valores 10 entre 0 si ocasionaran un error
        println(value_try(10, 0))



        //////////////////////////    THROW EXCEPTION      /////////////////////////////////

        //44 Vamos a lanzar una expepcion dependiendo si la constraseña es menos a 6 caracteres
        var password = "12344567"
        if (password.length < 6){
            //45 Para personalizar lo que se muestra vamos a colocar el throw el tipo de excepcion y el mensaje que queremos que se muestre
            //47 Podemos crear nuestras propias excepciones y utilizarlas
            throw IllegalPasswordException("Password muy corta")
        }else{
            println("Password segura")
        }


        ////////////////////////     SCOPE FUNCTIONS         ///////////////////////////

        //48 Los scope functions se utilizan cuando se ejecutan varias acciones sobre un mismo objeto

        var pancho = Persona("Pancho", "Pinos", 952333992)

        //49 Hay diferentes tipos de scope functions

        //50 Con let utilizamos it para acceder a atributos y metodos del objeto y ejecutar acciones
        pancho.let{
            it.decirHola()
            it.nombre = "Panchito"
            it.apellido = "Riata"
        }

        //50 con apply, utilizamos this para acceder a atributos y metodos del objeto y ejecutar acciones
        var jose = Persona("Jose", "Carsen", 93211335).apply {
            this.decirHola()
            this.nombre = "Panchote"
            this.apellido = "Juarez"
        } //51 Tambien se puede ejecutar un bloque also por si queremos seguir realizando cambios en jose, este bloque utiliza it
        .also {
            it.decirHola()
            it.nombre = "Karla"
            it.apellido = "Partirae"
        }

        //52 Con run utilizamos this, pero aparte podemos colcoar un valor al final del bloque el cual se guardara en la variable que estemos creando
        var pablo = Persona("Renato", "Sanchez", 3329991).run {
            this.decirHola()
            this.numero = 221134

            "Esto guardamnos dentro del bloque run"
        }
        println(pablo)

        //53 Con with se hace lo mismo que con run, sinembargo with se escribe al principio
        var martha = with(Persona("Martha", "Lopez", 3322110)){
            this.decirHola()
            this.numero = 32111332

            "Esto guardamnos dentro del bloque with"
        }
        println(martha)


        /////////////////////////        OPERADOR ELVIS   ?:    ////////////////////////////

        //54 El operador elvis se emplea para verificar que una variable es null o no y dependiendo de eso se ejecuta un codigo y otro

        //55 Vamos a crear una variable la cual no sera null
        var pais: String? = "Rusia"

        //56 A pais le asignamos el mismo valor pero en mayusculas si no es null y si es null le asignamos la cadena "Desconocido"
        pais = pais?.uppercase() ?: "DESCONOCIDO"
        println(pais)

        //57 Creamos un valor null para provar el operador elvis
        var ciudad: String? = null
        ciudad = ciudad?.uppercase() ?: "DESCONOCIDO"
        println(ciudad)


        ////////////////////      LAZY       /////////////////////////////////

        //60 El comando lazy lo que hace es que el programa crea el espacio en memoria hasta que la variable es llamada
        //61 Tambien es aplicable solo a valores

        val variableLazy: String by lazy { "Variable Lazy" }
        print(variableLazy)


        //////////////////          CAST        /////////////////////////////////

        /*62 Si se tiene un objeto de un tipo y se necesita que este mismo objeto se convierta a
             otro tipo para poder trabajar con este, esto se puede hacer mediante un cast */

        //63 Para hacer un cast en kotlin, se coloca el comando as despues del objeto que queremos castear

        //64 El siguiente numero es de tipo Int
        var numeroInt: Int = 33
        //65 Lo vamos a castear a tipo Float
        var numeroFloat = numeroInt as Float

        print(numeroFloat)







        


    }
}