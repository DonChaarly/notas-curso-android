package notas

class Teoria {

    /*
                            TEXT

    Este elemento como su nombre lo dice, nos permite mostrar cadenas de texto en la interfaz de usuario
    Es de los mas basico que hay


                            TEXT VIEW

    Este view se utiliza para mostrar una cadena de texto solamente, no es una entrada de texto ni nada, solamente
    muestra informacion.


                            EVENTOS

    Son sucedos que ocurren en en tiempo de ejecucion, osea cuando ya se ha lanzado la aplicacion, y estan asociados al
    view, como un click sobre el elemento, un click largo, cuando el cursos se pone sobre el elemento, etc. Todos estos
    son eventos


                                EDIT TEXT

    Los edit text son utilizado para capturar informacion que el usuario deba introducir, como por ejemplo cuando
    se introduce una contraseña o cuando se introduce un numero de telefono, etc.
    en el layout del activitie se muestra un menu con todos los tipos de editText que existen, sin embargo todos estos
    son objeto de tipo edit Text que al cambiar la propiedad de inputType, cambiamos la forma en que se interacciona con
    el edit text
    Al cambiar el atributo de inputType por alguno de los siguientes, se interactuara diferente

    text                    Recibe texto plano simple
    textPersonName          Texto correspondiente al nombre de una persona
    textPassword            Protege los caracteres que se van escribiendo con puntos
    numberPassword          Constraseña de solo numeros enmascarada con puntos
    textEmailAddress        Texto que sera utilizado en un campo para emails
    phone                   Texto asociado a un numero de telefono
    textPostalAddress       Para ingresar textos asociados a una direccion postal
    testMultiLine           Permite multiples lineas en el campo de texto
    time                    Texto para determinar la hora
    date                    texto para determinar la fecha
    number                  Texto con carcteres numericos
    numberSigned            Permite numeros con signo
    numberDecimal           Para ingresar numeros decimales


                        AUTOCOMPLATE TEXT VIEW

    Este view esta pensado para cuando el usuario introduce alguna cadena, se le muestre una serie de opciones de cadenas que
    coincidan con lo que esta escribiendo
    Para este view es necesario tener un array al que se le asociara.
    Este array puede declararse en los recursos en values y en strings
    A su vez es necesario asignarle un adapteador que sera el encargado de vincular las opciones con lo que escriba el usuario


                        MULTI AUTO COMPLATE TEXT VIEW

    Este view funciona igual que el autocomplate text view, sin embargo este puede hacer que no solamente para la primera palabra o cadena de strings que
    se introdusca, sino que cada vez que el usuario introduzca un espacio, se coloca una coma y al escribir otra palabra se sigua mostrando las ayudas
    se necesita asociar un array y un adaptador
    Pero tambien un Tokenizer




    */
}