package notas

class Teoria {

    /*
                    CLASES

    Las clases son archivos que cuentan con atributos y metodos
    Estas clases sirven para definir nuevos tipos de valores como nosotros queramos
    A parte de los tipos de valores que ya tenemos como Int, String, Float, etc (Estas tambien son clases ya definidas)

                ATRIBUTOS

    Los atributos son carcteristicas que tendran las clases como por ejemplo
    se puede crear una clase Persona a la cual se le podra dotar de diferentes atributos
    como por ejemplo sexo, altura, nombre, apellido, hobby
    Los atributos sirven para crear objetos con diferentes caracteristicas


                METODOS
    Los metodos son bloques de codigo que dotan de funcionalidades a las clases
    Estos metodos pueden hacer uso de los atributos de la clase y


                OBJETOS

    Los objetos son variables creadas con la clase que hicimos
    Por asi decirlo son hijos de la clase que creamos

                    CONSTRUCTOR

    Los constructores son funciones que llevan el mismo nombre de la clase. Y que nos sirven para
    inicializar atributos del objeto al momento de declarar el objeto


                    ENCAPSULAMIENTO

    Normalmente no se podra acceder a los atributos de un objeto simplemente, ya que estos estaran privados por seguridad
    Para que no cualquier persona acceda a estos valores, por ello se colocan en privado
    El encapsulamiento consiste en crear metodos para obtener o modificar estos atributos privados


                    GET y SET

    Los metodos get y set nos sirven para acceder (GEt) y modificar (SET) los datos de los atributos privados de
    los objetos


                    HERENCIA

    Se pueden crear nuevas clases las cuales hereden de otras clases, esto quiere decir que las caracteristicas de la
    clase padre pasarian a la clase hijo, tanto atributos como metodos
    Por ejemplo, se puede crear una clase padre llamada persona, esta clase tendra como atributos el nombre y sexo
    Y se puede crear una clase llamada Atleta que herede la clase persona, entonces los atributos de nombre y sexo se
    heredarian, y se puede crear otro atributo que sea propio de la clase atleta como el deporte que se practica
    Solo se puede heredar de una clase a la vez


                    MODIFICADORES DE ACCESO (PUBLIC, PRIVATE, PROTECTED, INTERNAL)

    Los modificadore de acceso son caracteristicas que se le pueden dar a los atributos o funciones o hasta a las clases
    para restringir su uso en otras clases
    Public: Este modificador es el que se tiene por default, si no se colcoa ningun modificador, es el que se tiene por defecto
            Lo que significa es que los atributos o metodos seran accesibles desde cualquier clase
    Private: Este modificador restringe el uso del atributo o metodo en todos lados, Por ello se utilizan los metodos get y set
            estos atributos no pueden ser heredado
    Protected: Este modificador es similar al private, sin embargo los atributos y metodos si pueden ser heredados
    Internal: Este comando permite a los metodos get y set de los atributos protected poder utilizarse


                    POLIMORFISMO

    El polimorfismo es refiere a cuando una clase hija hereda un metodo y lo sobreescrive pero le modifica el funcionamiento
    acorde a su clase

                        METODOS OVERRIDE

    Si se quiere declarar un metodo en una clase que heredo de otra, no se puede utilizar los nombre de los metodos que ya han
    sido declarados en la clase padre
    Solamente colocando la anotacion de override se puede sobreescrivir el funcionamiento de ese metodo y colocar el codigo que se quiere seguir
    Asi mismo es necesario colocar la anotacion de open en el metodo de la clase padre para dar permiso a que se pueda sobreescrivir


                        LLAMADA A SUPER

    El comando super es similar a this, ya que this nos sirve para acceder a las funciones y metodos de la clase en la que nos encontremos
    pues super hara lo mismo pero con la clase padre de la que heredemos, con super podremos ser capaces de llamar a los metodos de la clase padre


                        CLASES ABSTRACTAS

    Las clases abstractas sirven para definir un objeto muy generico del cual despues se puede heredar, de hecho estas clases estan hechas
    para ser heredadas ya que no se pueden hacer objetos de estas clases
    Las clases que hereden de una clase abstacta tiene que implementar todos sus metodos abstactos

                        METODOS ABSTACTOS

    Los metodos abstractos son metodos que no tienen una implementacion, osea que no tiene codigo que ejecutar, si una clase tiene un metodo abstacto
    se tiene que hacer a la clase abstacta
    Las clases que hereden estos metodos son los encagados de implementar su codigo


                        INTERFACES

    Las interfaces son similares a las clases abstactas, debido a que tambien contienen metodos abstactos
    Sin embargo las abstactas se utilizan para generalizar caracteristicas de una clase
    Las interfaces se utilizan para generalizar funcionalidades de una clase
    Las interfaces no son clases osea que no tienen un constructor
    Una clase puede heredar varias interfaces


                            COLABORACION ENTRE CLASES

    Se pueden crear objetos de otras clases en clases ajenas y utilizar estos objetos en sus metodos


                        SUBCLASES

    Las SubClases son clases que se crean dentro de otras clases
    Las hay de dos tipos,
    Clases que simplemente se crearon dentor de otra
        Estas clases no pueden utilzar nada de la clases en donde estan siendo creadas
    Clases que son creadas dentor de otras pero tienen el modificador inner
        Con este modificador podemos utilizar las variables y metodos de la clase en donde es creada


                        OBJETOS ANONIMAS

    Los objetos anonimos son objetos que no cuentan con una clase, son creados en alguna clase colcoando
    object (el nombre de la varaible){
        (aqui se pueden declarar variables y metodos
    }
    sin embargo no se pueden crear mas objetos a partir de este
    Y estos objetos no pueden ser creados dentro de una funcion


                        DATA CLASES

    Las data class son clases que estan pensadas para albergar solamente atributos, nada de metodos,
    Se pueden crear metodos pero no es esa la idea, deben ser puros atributos los que contengan


                        ENUM CLASS

    Los enum Class son clases que sirven para declarar un conjunto de valores constantes
    Tambien se pueden crear funciones dentro de esta clase que utilicen estos valores
    Asi mismo se le pueden asociar variables a las constantes, convirtiendolas en una especie de objetos



     */
}