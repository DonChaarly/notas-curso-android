package notas

class Teoria {

    /*

                            FUNCIONES DE EXTENCION

    Las funciones de extencion sirven para agregar algun metodo a un Clase para despues utilizar este metodo con sus objetos
    Se pueden agregar estas funciones de extencion a clases como los String, Array, etc
    Estas funciones solo podran utilizarse en la clase que se crearon
    Se crean fuera de cualquier metodo


                            FUNCIONES DE ORDEN SUPERIOR

    Las funciones de orden superior son funciones que entre sus parametros pueden recibir otras funciones
    y dentro de su codigo hacer uso de la funcion que reciba, pasandole parametros si es necesario
    para utilizar una funcion de orden superior al llamarla para pasar la funcion que recibe se coloca ::(nombre de la funcion)


                            LAMBDAS

    Con las lambdas agilizamos la declaracion de funciones, de hecho estas no se tiene que declarar pueden ser anonimas
    Su sintaxys es la siguiente:
                                {(parametro): tipo, (parametro) -> (cuerpo de codigo a ejecutar) }
    Las Lambdas tambien pueden ser asignadas a una variable
    Para retornar un valor en la ultima linea del cuerpo del codigo se coloca el valor que se quire retornar
    Se puede indicar el tipo de parametro que se recibe o no
    Se puede pasar un argumento ya existenete y en el codigo trabajar con el
    Las funciones lambdas puede acceder a codigo externo



                            LAMBDAS Y SU PARAMETRO IT

    Las lambdas al emplearlas en funciones de orden superior muchas veces pueden recibir un parametro que por defecto se llama it
    por ejemplo al crear una array este parametro array sera un indice de los elementos que contiene el arreglo
    Con el metodo setOnClickListener este parametro it tomara el valor del View


                            TYPEALIAS

    Los Typealias son alias que lo ponemos a cadenas de codigo, osea se puede configurar que a una palabra se le asigne
    una oracion o sentencia y cada vez que se escriba esa palabra en realidad se este escribiendo la oracion mucho mas larga


                                Desestructuracion

    La desestructuraion sirve para guardar en varaibles los diferentes datos de un objeto o acceder a ellos mediante el metodo .component()
    Este concepto se puede aplicar a objetos de un data class, mapas, listas, etc


                                BLOQUES TRY CATCH Y FINALLY

    Los bloques Try Catch y Finally Se utilizan para que cuando se produsca un error en tiempo de ejecucion por alguna operacion o un null
    este error no crashee la aplicacion y ejecute el codigo que se le especifique
    Al provocarse un error se lanza una Excepcion de la clase Exception, esta clase a la vez tiene clases hijas, osea excepciones mas especificas
    Las Excepciones mas comues son las siguientes:
        NullPointerException: Esta excepcion se lanza cuando se apunta a un valor null
        ArithmeticException: Esta excepcion se lanza cuando se produce un erro en alguna operacion aritmetica como el dividir entre 0
        SecurityException: Esta excepcion se lanza cuando por ejemplo un usuario quiere acceder a un dato del cual no tiene permiso
        ArryIndexOutOfBoundException: Esta excepcion se lanza cuando se quiere acceder a un dato de un arreglo en un indice que no existe

    En el bloque Try se coloca el codigo que podria mandar una excepcion
    En el bloque Catch se recibe como parametro la excpecion que se puede lanzar y en el cuerpo del codigo se coloca el codigo que queremos que se ejecute en caaso que se lance la excepcion
    En el blque Finally se coloca codigo que siempre se va a ejecutar sin importar si se lazno una excepcion o no


                                GUARDAR BLOQUE TRY CATCH EN UNA VARIABLE

    A una varaible se le puede asignar un bloque Try Catch, Para esto como en la funcion lambda que se tiene que colocar un valor al final del cuerpo del codigo
    Tambien en el bloque Try catch, se tiene que colocar un valor el cual se guardara en la variable, se toma el valor del bloque que se ejecute, el del try o el del catch


                                    THROW EXCEPTIONS

    Cada vez que hay un throw exception se para la apliacion, osea cada vez que se lanza una excepcion se para la aplicacion
    poemos colocar throw excpecions nosotros mismos y modificar lo que se mostrara por pantalla en la expcepcion, principalmente para que
    podamos identificar en que parte se provoco la expepcion

    Asi mismo podemos crear nuestras propias expepcions
    excepciones com ArithmeticException o NullPointerException heredan de la clase Exception
    por ello podemos crear clases que hereden de la clase Exception y con ello ya estaremos creando nuestras propias excepciones


                                SCOPE FUNCTIONS

    Las scope functions se aplican a objetos, con estas funciones podemos ejecutar varias operaciones sobre este objeto como llamar a sus metodos, cambiar el valor de sus argumentos
    Los scope functions se ocupan principalmente para estructurar de una manera mas ordenada el codigo
    hay distintos tipos de scope functions como:
        let
        apply
        run
        with


                                OPERADOR ELVIS   ?:

    El operador elvis se utiliza para ejecutar un codigo u otro dependiendo si la vairable a la que apuntamos es null o no
    En vez de utilizar un operador if, preguntar si la variable es null, ejecutar un codigo o si es null ejecutar un else se utiliza
    el operador elvis el cual es ?:
    Lo que esta a la izquierda se ejecuta si el valor no es null, lo de la derecha se ejecuta si la variable es null


                            LATEINIT Y LAZY

    Estos comandos se utilizan cuando se quiere inicializar un atributo o variable n un momento posterios del memento en que se estan declarando

    lateinit: Este comando se utiliza en los atributos de las clases, cuando se coloca no es necesario especificar un valor al atributo
                Sin embargo es nuestra responsabilidad tener que inicializarlo en algun constructor posteriormente
    lazy: Este comando se utiliza en variables, lo que se hace es declarar una variable y si especificarle un valor, sin embargo el programa no
            creara un espacio en memoria para esta variable hasta el momento en que se mande a llamar esta variable

     */


}