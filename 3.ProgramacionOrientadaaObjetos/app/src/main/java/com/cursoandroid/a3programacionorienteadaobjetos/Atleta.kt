package com.cursoandroid.a3programacionorienteadaobjetos

import android.util.Log


//15 Para heredar una clase se colocan : y el nombre de la clase que se quiere heredar, y se pasan los datos de los atributos que pide la clase padre
class Atleta(name: String, passport: String?, private var deporte: String = "futbol"): Persona(name, passport),
            //35 Se implementa la interface IInteractuar
            IInteractuar {

    //37 Los atributos de las interfaces tambien se tiene que implementar
    override var saludo: String
        get() = TODO("Not yet implemented")
        set(value) {}

    //42 Se pueden crear objetos de otras clases en un clase ajena
    private lateinit var cocineroDelAtleta: Cocinero

    //43 Los objetos de otras clases pueden ser utilizados en metodos de esta clase tambien
    fun asignarCocinero(cocinero: Cocinero){
        this.cocineroDelAtleta = cocinero
        println("Mi cocinero sera $cocinero")
    }


    //43 Los objetos de otras clases tambien se pueden utilizar dentro de

    //21 Para poder sobreescrivir la funcionalidad de un metodo creado en la clase padre se debe escrivir el comentario override
    override fun saludar(){
        //23 Con super llamamos al metodo del padre, con esto se ejecutara el metodo saludar normalmente
        super.saludar()
        //24 Y asi agregamos algun otro pedazo de codigo que queremos que se ejecute aparte del que ya tiene el padre
        print("y practico el deporte ${this.deporte}")
    }

    //36 Se deben implementar todos los metodos de la interface
    override fun hola() {
        Log.i("Interaccion","hola soy un Atleta")
    }

    override fun despedirce() {
        Log.i("Interaccion", "El atleta se despide")
    }

    override fun decirNombre() {
        Log.i("Interaccion","Mi nombre es ${this.name}")
    }


    //Metodos get y set
    fun getDeporte(): String{
        return this.deporte
    }
    fun setDeporte(deporte: String){
        this.deporte = deporte
    }
    fun getCocineroDelAtleta(): Cocinero{
        if(this.cocineroDelAtleta != null){
            return this.cocineroDelAtleta
        }
        else{
            return Cocinero(this.name, this.getPassport(), "Casera")
        }
    }
    fun setCocineroDelAtleta(cocinero: Cocinero){
        this.cocineroDelAtleta = cocinero
    }






}