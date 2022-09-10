package com.cursoandroid.a3programacionorienteadaobjetos

//7 Para que se pidan especificar los datos de algunos atributos al inicializar algun objeto, se especifica en la declaracion de la clase dichos atributos
//8 Se puede colocar valores por defecto en dichos atributos
//14 Para poder heredar esta clase se tiene que colocar el moficador open para abrir esta clase a herencia
open class Persona(
            //16 El siguiente atributo es publico, puede utilizarse y heredarse en todos lados
            var name: String = "Anonimo",
            //17 El siguiente atributo es privado, No puede utilizarse ni heredarse
            private var passport: String? = null)
            //30 Se hereda de la clase abstacta SerVivo
            : SerVivo(){


    //1 Se definen los atributos de la clase
    //2 El primer atributo sera la caracteristica vivo, sera un boolano indicara si la persona se encuentra viva o muerta
    //18 El siguiente atributo es protected, no puede utilizarse por otras clases pero si se puede heredar
    protected var alive: Boolean = true


    //9 Los constructores son funciones que llevan el mismo nombre de la clase
    fun Persona(){
        //10 Al ser funciones se le puede agregar cualquier codigo al constructor
        this.name = "Juan"
        this.passport = "9B21FE32"
    }


    //3 Se crea el primer metodo el cual al llamarlo podra cambiar el valor del atributo alive
    fun die(){
        this.alive = false
    }
    //20 Para hacer que un metodo se pueda sobreescrivir se escribe el modificador open
    open fun saludar(){
        println("Hola mi nombre es ${this.name}")
    }

    //31 Los metodos abstactos deben ser implementados
    override fun TipoSerVivo() {
        print("Soy de tipo Persona")
    }

    //11 Los metodos get Nos sirven para recuperar los datos de los atributos de las clases
    internal fun getName(): String{
        //Se hace el return del name
        return this.name
    }
    //12 Los metodos set Nos sirven para modificar los atributos de las clases
    internal fun setName(name: String){
        //Al atributo name de la clase se le asigna el valor name que esta recibiendo este metodo
        this.name = name
    }

    fun getPassport(): String?{
        return this.passport
    }
    fun setPassport(passport: String){
        this.passport = passport
    }
    //19 Los metodos get y set de un atributo protected deben tener el modificador internal para poder funcionar
    internal fun getAlive(): Boolean{
        return this.alive
    }
    internal fun setAlive(alive: Boolean){
        this.alive = alive
    }




}