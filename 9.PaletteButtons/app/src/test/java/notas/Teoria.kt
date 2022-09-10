package notas

class Teoria {

    /*
    notas: Mas informacion que puede relacionarse con los botoners se encuentr en el proyecto elementosText


                            BUTTONS

    Los botones son otro tipo de view los cuales estan pensado para capturar la accion de click que
    hace el usuario para seleccionar alguna opcion.

   Los botones por defecto bienen lo que se dice capados, osea no se puede acceder a todos sus atributos por defecto
   Para poder acceder a todos los atributos, cuando se delcara el boton en el xml se le tiene que colocar
        android.widget.Button
   Esto ultimo lo hacen porque si se accede a todos los elementos, se convertiria en un textview practimanete,
   android hace esto para conservar la puresa de la raza, si es un button que se trate como button, si es textview,
   que se trate como textview, esto mismo lo hace con la mayoria de elementos


                    PERSONALIZACION DE BOTONES

    Para personalizar los botones se pueden configurar muchos de los atributos  con los que cuenta sin embargo,
    una de las mejores maneras para hacer esto es creando un fondo con un archivo xml en la carpeta de drawable,
    para despues aplicar este estilo al atributo background del boton


                    CHIP GROUPS Y CHIPS

    Los chips groups son conjuntos de chips los cuales se utilizan por ejemplo para seleccionar una serie de opciones
    como gustos, o habilidades, etc. Se puede seleccionar mas de uno a la vez
    Se necesita crear un chips group primero ya que este es el que contendra los chips


                        ESTILOS EN THEMES

    Debido a que a veces se quiere apliacar un mismo estilo en varios elementos a la vez, se puede optar por
    crar un estilo propio en las carpeta de recursos, values y themes


                        CAMBIAR ATRIBUTO ESTABLECIENDO CONDICION DESDE XML

    Se puede cambiar algun atributo del cualquier elemento view, dependiendo de una funcion que se le etablezca
    Sin necesidad de definir la funcion desde el codigo, tododesde el xml

    Para crear esta funcion o condicion se crea un archivo xml, esto se puede guardar en drawable
    con la herramienta selector se definen los item que van a ser seleccionados dependiendo de la condicion
    A cada item se le aplica un atributo que sea una condicion como state_chacke la cual se le define un estado, ya sea false o True
    y si dependiendo del estado en el que se encuentre el elemento, se utilizara el item con el que coindice el estado


                        RADIO GROUPS Y RADIO BUTTONS

    Los Radio Buttons son botones los cuales representan una eleccion entre un conjunto de mas radioButtons
    y de estas conjunto de radioButtons solo se puede seleccionar uno a la vez

    Los radio Groups son areas invisibles en donde se deben conjuntar los radioButtons de una misma eleccion


                        CHECK BOX

    Este tipo de boton tambien puede representar una opcion dentro de un conjunto de varias opciones, sin embargo
    no tiene porque ir agrupado dentro de un contenedor de checkBoxes
    Con este elemento se pueden seleccionar varios check boxes a la vez si se quisiera


                        TOGGLE BUTTON

    Este boton es utilizado para representar dos estados, ya sea apagado y encendido o cualquier otra cosa
    Sin embargo este boton no es muy atractivo visualmente, se puede personalizar pero existe otros botones
    mejores para esta funcion

                    SWITCH

    Los botones switch son parecidos a los toggle Button, ya que estos representan un estado, como encendido y apagado
    sin embargo estos botones son por defecto mas atractivos que un toggle button


                FLOATING ACTION BUTTON

    Este boton es un boton normalmente circulas el cual normalmente se encuentra en la esquina inferior derecha
    el cual siempre esta por encima de tod, nada lo tapa





     */
}