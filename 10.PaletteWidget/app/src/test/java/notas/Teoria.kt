package notas

class Teoria {

    /*
                                PALETTE WIDGETS

    Los widgets que tenemos en el layout no son los mismos que se pueden colocar en la pantalla de inicio
    del celular, estos son diferentes

    Estos widgets se utiilzan para representar una accion como el pregreso de la musica, la barra de scroll, etc


                                IMAGE VIEW

    Este widget se utiliza para agregar alguna imagen a la aplciacion

        Resoluciones de imagenes
    Ya que la aplicacion puede cargarse en diferentes dispositivos en donde no se sabe el tamaño de pantalla
    que se tendra. Por esto conviene tener la imagen en diferentes resoluciones para que automaticamente
    la apliacion carge la que coreesponde
    Par esto se hace uso del pluggin Android Drawable Importer, el cual nos permite cargar una imagen y
    crar distintos archivos con distintas resoluciones
    Para esto se carga la imagen dando new, batch Drawable import

        Convertir imagenes a webP
    WebP es un formato de imagenes que mantiene una calidad buena no tanta pero con un tamano mucho mas reducido
    Para esto se hace un click derecho sobre el archivo de la imagen a convertir y se selecciona la opcion convert to WebP

        imagenes desde url
    Para poder mostra una imagen de internet se tieen que primero:
    Se debe dar permisos a la app para cargar datos de internet configurando el archivo AndroidManifest.xml
    Se debe configurar el gradle (module) las dependencias correspondientes
    Se carga la imagen de url desde el codigo haciendo uso de la libreria Picasso


                                SHAPEABLE IMAGE VIEW

    Este es un tipo de image view el cual tiene la caracteristica que las imagenes que se cargen, se les puede dar
    una diferente forma
    Para aplicar una forma se puede crear un archivo de estilos en donde se especifique la forma del item


                                    WEB VIEW
    Este elemento nos permite mostrar una pagina web sin necesidad de abrir una aplicacion de explorador web
    Sino que solo en la pantalla de la aplicacion puede mostrarse este tipo de ventanas

    Para poder cargar un sitio web en la aplicacion se le debe dar permisos a la aplicacion para utilizar el
    internet como se hizo en la imageView


                                VIDEO VIEW

    Los video view Se utilizan para mostrar un video dentro de la aplicacion
    Como las imagenes, se puede mostrar un video directamente cargado desde el propio proyecto o puede ser
    cargado haciendo referencia al url de un vieo en internet

    No se recomienda cargar video desde la aplicacion ya que esto hara que la aplicacion pese mas al tener
    que descargar el video junto con la aplicacion.

    Para el archivo de video se recomienda crear una carpeta propia en res de tipo raw
    Solo se admiten video mp3 o mp4

    Si se quiere cargar un video de youtube es un poco mas complicado ver:
    https://dev4phones.wordpress.com/2020/05/29/reproducir-un-video-de-youtube-dentro-de-una-app-android-usando-kotlin/


                         CALENDAR VIEW

    El calendar view es un tipo de widget en donde podemos visualizar un calendario en la pantalla del activity


                            PROGRESS BAR

    Este elemento nos ayuda a representar una avance o el tiempo que se ha transcurrido mediante una barra la cual
    puede ser circular o horizontal

    Para ir mostrando el avance en tiempo real de un progress bar es necesario utilizar una corrutina



                            CORRUTINAS

    Las corrutinas son hilos de codigo que estaran ejecutandose aparte del hilo prinipal
    Para poder utilizar las corrutinas se necesita colocar la siguiente dependencia en el gradle (Module):
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")


                            SEEKBAR

    Los seekbar son como los progress bar en el sentido que sirven para representar un avance dentro del rango de la barra
    Sin embargo con estos desde la parte del usuario seremos capaces de modificar la colocacion del avance, por ejemlo
    cuando queremos reproducir una cancion y queremos regresar un poco, eso no lo podriamos hacer con un proresss bar

    Se pueden crear muchas cosas con los seek bar, en la siguiente pagina hay algunos ejemplos:
    https://android2d.blogspot.com/2021/06/android-seekbar-intervals.htmlhttps://android2d.blogspot.com/2021/06/android-seekbar-intervals.html


                            RATING BAR

    Este elemento se utiilza para representar una valoracion, en su forma por default se tiene un numero de estrellas
    las cuales pueden ser modificadas.
    El rating bar hereda del progress Bar por ello hay algunas cuantas similitudes


                            SEARCH VIEW

    Este elemento sirve para representar una barra de busqueda en donde el usuaruio coloca alguna palabra y el search view
    puede brindarle una serie de palabras de ayuda que concuerden con su busqueda
    Esto recuerda un poco al AutoCompleteTextView


                                LIST VIEW

    Este elemento sirve para mostrar una lista de textos


                            HORIZONTAL DIVIDER Y VERTICAL DIVIDER

    Estos view son realmente el view padre, simplemente representan una linea o cuadrado en la pantalla
    asi sin mas, se ocupa para pues dibujar una linea y asi dar una separacion mas marcada de algunos objetos


     */
}