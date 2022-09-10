package com.cursoandroid.a3programacionorienteadaobjetos

import android.util.Log

//22 Esta clase tambien hereda de la clase persona para utiilzar sus caracteristicas
class Cocinero(name: String, passport: String?, private var tipoCocina: String = "Mariscos"): Persona(name, passport),
            //35 Se implementa la interface IInteractuar
            IInteractuar {

    //37 Los atributos de las interfaces tambien se tiene que implementar
    override var saludo: String
        get() = TODO("Not yet implemented")
        set(value) {}

    //21 Para poder sobreescrivir la funcionalidad de un metodo creado en la clase padre se debe escrivir el comentario override
    override fun saludar() {
        //23 Con super llamamos al metodo del padre, con esto se ejecutara el metodo saludar normalmente
        super.saludar()
        //24 Y asi agregamos algun otro pedazo de codigo que queremos que se ejecute aparte del que ya tiene el padre
        print("y mi tipo de cocina es ${this.tipoCocina}")
    }

    //36 Se deben implementar todos los metodos de la interface
    override fun hola() {
        Log.i("Interaccion","hola soy un Cocinero")
    }

    override fun despedirce() {
        Log.i("Interaccion","El cocinero se despide")
    }

    override fun decirNombre() {
        Log.i("Interaccion","Mi nombre es ${this.name}")
    }

    //Metodos get y set
    fun getTipoCocina(): String{
        return this.tipoCocina
    }
    fun setTipoCocina(cocina: String){
        this.tipoCocina = cocina
    }

    //44 Para crear una clase dentro de otra simplemente se coloca el comando class y se sigue normal
    class Mesero{
        private var nombreDelMesero: String = "Juan"
        fun presentacion(){
            println("MI nombre es ${this.nombreDelMesero}")
        }
    }

    //45 Para crear una inner class se coloca inner antes, esta clase podra acceder a los datos de la clase en donde es creada
    inner class Pinche{
        private var nombreDelPinche: String = "Ratatui"
        fun presentacion(){
            //46 Se puede acceder a la variable nombre que esta declarada en la clases cocinero, sin embargo si hay una variable con el mismo nombre en esta tomara ese dato
            print("Mi nombre es ${this.nombreDelPinche} y soy pinche de ${name}")
        }
    }


}