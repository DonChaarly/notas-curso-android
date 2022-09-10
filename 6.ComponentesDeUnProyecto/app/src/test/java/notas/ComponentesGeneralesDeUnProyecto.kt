package notas

class ComponentesGeneralesDeUnProyecto {

    /*
                DIRECTORIOS

    Los directorios son todos las carpetas que conforman el proyecto android las cuales tamien
    se pueden visualizar desde la carpeta de archivos de windows

                GRADLE

    El gradle indica como esta configurado el proyecto en cuestion de las herramientas que se utilizara
    y el modulo que se este programando
    Los archivos mas importantes dentro dele gradle son los llamados build.gradle
        build.gradle(Pruject:...): Este gradel indica la configuracion que tendra el proyecto, indica
                                    que repositorios se utilizan y que dependencias
                                    Se colocan todos los elementos que estan en el proyecto, ya que
                                    este se encarga de verificar que no haya de mas
        build.gradle(Module:....): Los modulos tambien indican diferentes configuarciones del proyecto
                                    Pero de este se pueden crear mas de uno, cada uno con sus caracteristicas
                                    propias, de manera independiente


                        CARPETAS DE UN PROYECTO

    MANIFEEST: Esta carpeta contiene el archivo AndroidManifest el cual es un archivo de configuracion
               El cual tiene que ver mas con la parte de vista como el icono de la aplicacion, el titulo,
               el activity que se la lanzara primero, los permisos que pedira al usuario como camara, microfono, etc

    JAVA: Esta carpeta contiene a su vez tres carpetas donde la primera contien todos los archivos del proyecto como
          actvities, clases, interfaces etc.
          Se tiene una segunda y tercera carpeta androidTest y test, en androidTest se hacen pruebas intrumentales
          y en test solo pruebas locales, para ver como funciona la aplciacion en diferentes circunstancias como en
          diferentes dispositivos, versiones de android, etc

          En los activities se encuentra el icono de una palomita el cual indica si el archivo se encuentra sin errores
          esto tambien se puede configurar

    JAVA (Generated): Este carpeta es generada automaticamente por el ide al moento de lanzar la aplicacion
    RES: En esta carpeta se encuentran todos los recursos graficos y de texto que pueden ser usados en la aplicacion
         Dentro de esta carpeta se encuentran diferentes tipos de otras carpetas:
         drawable: Dentro de esta carpeta se encuentran archivos xml o bien todas aquellas imaegnes que se utilizan en la aplicacion.
                   es para recursos generales que no dependerán de la resolución del dispositivo, sirve para colocar todas las
                   imágenes que vaya a utilizar nuestra aplicación.
         layout: En esta carpeta se encuentran los archivos xml que seran la representacion visual de de los archivos
                 Los archivos de esta carpeta se pueden ver de diferentes formas, solo el codigo, solo la represaentacion visual, o los dos juntos
         mipmap: Esta carpeta tambien contiene imagenes sin embargo,se utiliza cuando quieres cargar recursos dependiendo de la resolución
                 del dispositivo, como por ejemplo el icono de la app
         values: En esta carpeta se encuentran archivos de strings para la configuracion de varios idiomas, ademas se tienen
                 los archivos donde se indican los colores de la apliacion, estilos, etc. En general valores que no son del mismo tipo
                 En la carpeta de themes se puede especificar diferentes colores dependiendo del tema como claro y oscuro

         A su vez se pueden crear nuevas carpetas como para agrupar menus, musica, atributos, etc
         los recursos deben contener letras minusculas, no se aceptan letras mayusculas en el nombre del recurso



                            Agregar una nueva carpeta en la carpeta de recusros

    Dentro de la carpeta de res, se da click drecho, new y Android Resource Directory
    Aparece una ventana en la que se pueden especificar distintos valores como el nombre del directorio, el tipo de este directorio
    Dentro de esta carpeta se pueden agregar todos los archivos que queramos y que sean del tipo de archivo que se espera



                            Paginas interesantes en donde se pueden encontrar recursos

    Google fonts: En esta pagina se pueden encontrar fuentes gratuitas por parte de google
                  Al momento de descargar una de estas fuentes se descargan a su vez una serie de archivos para varias variaciones de la mimsa fuente
                  NO es necesario agragrlas todas al proyecto, de hecho no se deberia hacer si no se necesita, se agrega solo la que nos sirve
    Material Design icons: Esta pagina nos brinda una serie muy amplia de iconos para nuestra aplicacion
                           Para descargar alguno atributo lo mas conveniente es descargar la opcion de Vector Drawable ya que este es el codigo que
                           se utiliza para crear el icono, y este puede ser modificado.





     */

}