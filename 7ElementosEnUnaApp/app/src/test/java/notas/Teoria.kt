package notas

class Teoria {

    /*
                            ACTIVITIES

    Los activities son todas las ventanas que puede tener una aplicacion
    Activity: Es el componente principal de una aplicacion android
               Al crear una nueva aplicacion con un empty activity tenemos dos ficheros MainActivity los cuales estan enlazados\
               Crear un nuevo activity: Se da click derecho en la carpeta de app y seleccionamos new Activity
    Ciclo de vida de un activity: El ciclo de vida es una serie de metodos que se ejecutan desde que se lanza la aplicacion hasta que esta es cerrada
                Inicia cuando el activity es lanzado, en este momento se lanzan los metodos onCreate(), onStart() y onResume(),
                                  Despues el activity se queda ejecutandose en la memoria,
                                  En caso de ejecutarse otra aplicacion y salir por un momento de la apliacion Se lanza el metodo onPause(),
                                  En caso de regresar a la aplicacion se lanza nuevamente el metodo orResume()
                                  Si la aplicacion se queda en estado de pausa mucho tiempo, el activity se para ejecutandose el metodo onStop()
                                  Si se vuelve a la aplicacion en este estado, se ejecuta el metodo onStart() nuevamente
                                  En caso que el sistema operativo necesite memoria para otras aplicaciones se decide si se destruye el proceso ejecutandose el metodo onDestroy()
    nota: se puede programar cualquier cosa dentro de los metodos del ciclo de vida.
    IU: Esto significa Interface User


                            FRAGMENTS

    Un fragment es una seccion modular de una actividad la cual tiene su propio ciclo de vida la cual depende a su vez del ciclo de vida del activie del que sea parte.
    Los fragments al ser modulos, pueden reutilizarse en varias partees de la interface.
    Los metodos que hay en el ciclo de vida de un fragment son practiamente iguales.



                            VIEW Y VIEW GROUP

    El view es el elemento que nos permite representar todos los objetos visualies como botones, cajas de texto etc.
    View es una clase de la cual representa la contruccion basica de bloques para componentes de la iu.
    El view ocupa un area rectangular en la pantalla, esto se puede apreciar en la pantalla color verde azulado en cualqueir layout
    Todos aqullos botoners, textos, imagenes en la interface son views, osea son objetos que heredan de la clase View

    View Gruop: los view group es una subclase de View, y la cual es la clase base de los layouts, los cuales son los contenedores invisibles en donde
    estan contenidos los eleementos view

    nota: No todos los view heredan de la clase padre, osea que hay view que podemos crear nosotros, osa objetos como botones que creamos nosotros y
    podemos impolementar agregando las librerias al proyecto.



                        SERVICIOS

    Los servicio son componentes que pueden realizar operaciones de larga duracion en segundo plano y que no proporcionan una interfaz de usuario
    Por ejemplo se pueden hacer servicio que manejen las transaccionees de red, musica, etc.
    Los servicio pueden estar ejecutandose a pesar que no este abierta la apliacion directamente.


                        BROADCAST RECEIVERS (TRANSMISORES)

    Estos elementos reaccionan a determinadas circunstancias de la apliacion, por ejemplo, si se ha recibido una llamada, si se ha recibido un mensaje o no
    si se hay insertado una tarjeta o no, los propios datos del telefono, si se tienen activados los datos, si se esta en modo avion, etc.
    Cuando se ha recibido la alerta de que paso una circunstancia especifica se puede programar que se ejecute un codigo en conreto.


                            INTENTS

    Un intent es un objeto de mensajeria que podemos utilizar para solicitar una accion de otro componente de una app.
    Se puede utilizar un intent par: Iniciar un activitie, Iniciar un servicio, o para transmitir una emision


                        CONTENT PROVIDERS (PROVEEDORES DE CONTENIDOS)

    Un content providers administra el acceso a un repositorio central de datos.
    Este content providers nos permite compartir datos entre aplicaciones.
    Por ejemplo, cuando una aplicion te dice si quiere sincronizar tus contactos en la aplicion, pues la informacion la va a obtener de la aplicacion contactos.
    No solo se pasa informacion de una aplicion a la que se este ejecutando, tambien se puede pasr la informacion a otra aparte.


                            WIDGET

    Los widget son vistas de app en miniatura que pueden incorporarse en otras apps ( como la pantalla principal) y recibir actualizaciones periodicas.
    Los widget se utilizan para poder ejecutar funciones de la apliacion sin necesidad de tener que abrirla, no es necesario implementar todas las funciones
    en un solo widget


                        ANIMACIONES Y TRANSICIONES

    Las animaciones se utlizan para poder emplear una experiencia mas fluida en la apliacion, no tan brusca, ya sea en transiciones o simplemente al mostrar alguno elemento

     */
}