package com.cursoandroid.a3programacionorienteadaobjetos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    //49 Para crear un objeto anonimo se coloca lo siguiente, este objeto no se puede crear dentro de una funcion
    object piedra{
        //Aqui se puede declarar variables y metodos
        var tipoPiedra = "Granito"
        fun lanzarPiedra(){
            println("Se ha lanzado la piedra")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //4 Para crear un objeto de la clase que creamos se declara una varaible como normalmente se hace
        var juan: Persona = Persona()
        //5 Se le puede modificar los atributos a juan
        //juan.alive = false
        // 6 Se pueden llamar los metodos asociados a juan
        juan.die()
        //13 Para poder acceder y modificarlos atributos privados de la clase y objeto Persona se utilizan los metodos get y set
        juan.getAlive()
        juan.setAlive(false)

        //38 Para crear un objeto a partir de una interfaz
        //38 El objeto sera de tipo IInteractuar pero se crea el objeto Atleta
        var atleta: IInteractuar = Atleta("Carlos", "23510", "Natacion")
        //39 Cramos un cocinero igual de tipo IInteractuar
        var cocinero: IInteractuar = Cocinero("Salma", "230234fs", "Gourmet")

        //40 Al llamar un metodo de la interface, en realidad se ejecuta la implementacion del objeto al que este apuntando
        //41 Si le damos ctrl+click(en hola) nos lleva a la declaracion del metodo que esta en la interface
        atleta.hola()
        cocinero.hola()

        //47 Para crear un objeto de la clase Mesero
        var mesero1: Cocinero.Mesero = Cocinero.Mesero()
        //48 Para crear un objeto de la clase Pinche
        var pinche1: Cocinero.Pinche = Cocinero("Pablo", "3215332", "Italiana").Pinche()

        //50 Si se puede utilizar el objeto anonimo dentro de una funcion
        piedra.lanzarPiedra()

        //53 Los objetos de data class se crean con normalidad
        var sarteGrande: Sarten = Sarten(23f, true, false)

        //57 Para declarar un objeto de un enum class
        var hoy: Dias = Dias.MARTES
        //58 Se puede obtener todos los datos de la enum class, el metodo values nos devuelve una lista con todos las constantes
        var semana: Array<Dias> = Dias.values()

        //59 podemos acceder a los metodos de la clase enum y a sus atributos
        hoy.queDiasES()


    }


}