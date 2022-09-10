package notas

class Proceso {

    /*

    AGREGANDO RECURSOS
    Se agregaron los colores a utilizar en la carpeta res -> values -> colors
    Se creo una nueva carpeta de recuros llamada raw en donde se agregaron todas las pistas de musica y sonidos
    Se agregaron las imagenes que nos tendran mas de una resolucion a la carpeta de drawable
    Se agrego el icono le la apliacion en mipmap, sustituyendo el anterior
    Se agregaron las imagenes con diferentes resoluciones con ayuda del pluggin Android Drawable Importer, en la carpeta mipmap

    IMPLEMENTANDO LA BASE DE DATOS.
    En la pagina de firebase, iniciamos sesion con nuestr cuenta
    Creamos un nuevo proyecto en la pagina de firebase y seguimos los pasos
        Le damos un nombre a nuestra base de datos
        Seleccionamos si utilizar analytics o no (se recomienda que si)
    Una vez creado, se nos despliega una ventana y en grande el texo "Comienza por agregar...."
    Seleccionamos la plataforma en donde vamos a implementar la base de datos.
    Seguimos los pasos
        Colocamos el nombre del paquete : com.cursoandroid.a21proyectoappdeporte
        Coloamos nombre del proyecto
        registramos la aplicacion y nos genera un archivo el cual debemos copiar y pegar donde nos indique
    Se nos muestran las lineas que debemos colocar en el gradle.

    CREACION DE BASE DE DATOS
    En la pestana de compilacion - firestore database, seleccoinamos crear base de datos.
    Nos da dos opciones una modo produccion y otra opcion de prueba. la cual se borra despues de 30 dias
    Ya creada la bases de datos en la pestana de regalas, cambiamos a True la linea: allow read, write: if True;
    Ahora si empezamos a crear las secciones de nuestra base de datos
    Creamos documentos como: lebelsBike -> level_1 -> name: turtle
    (IMPORTANTE: un documento padre como lebelsBike debe tener siempre un documento hijo coo level_1,
        debe tener algo, sino esta seccion se borrara automaticamente
        por ello es importante crear a cada padre un documento x el cual nuca se borre)


    CREACION DEL LAYOUT DE LOGIN
    Se creo un activity nuevo llamado LoginActivity
    Se modficia el activity_login.xml
    Se modifico el archivo themes.xml para hacer que se quite el action bar y el titulo, a parte de modificar el color del statusBar

    
    IMPLEMENATACION DE AUTHENTICATION DE FIREBASE
    En la pagina de firebase en nuestra apliacion, creamos un nuevo metodo de acceso, en este caso sera con correo electronico
    Se implementan las librerias en el gradle module
    En el LoginActivity se crearon tanto las variables para el tema de la authentication de firebase y los usarios.
    Se modifica el codigo del LoginActivity

    ACTIVITY DE TERMINOS Y CONDICIONES DE USO
    Se creo la activity TermsActivity donde se coloco los textos de condiciones de uso
    Se modifico el el codigo que se coloque un toolbar en este activity que diga terminos y condiciones de uso

    VALIDADOR DE EMAILS
    Se creo la clase validateEmail.kt

    IMPLEMENTACION DE LOGIN CON CUENTA DE GOOGLE
    Agregamos como metodo de inicio de sesion a googel en firebase en nuestro proyecto
    Se debe indicar una huella digital al proyecto en la cunta de firebase, esto se encuentr en la parte de configuracion del proyecto
    Se obtiene la huella digital del proyecto, tenemos que abrir el archivo gradlew desde la terminal,
        par esto podemos ir a la vista Project que es donde se puede ver este archivo y le damos open in -> Terminal
        y ejecutamos este comando: gradlew signingReport
    Copiamos el SHA1 y lo pegamos donde nos pide la huella digital
    Se agrean las librerias para iniciar sesion con google
    copíamos el codigo que nos proporciona google en nuestro metodo para iniciar sesion con google

    IMPLEMENTACION DE LOGIN CON CUENTA FACEBOOK
    Iniciamos sesion o creamos cuenta en FacebookDevelopers: https://developers.facebook.com/
    Creamos nuetro proyecto en facebook developers
    Agregamos como metodo de inicio de sesion a facebook en firebase en nuestro proyecto
        nos va a perdir dos datos los cules se encuentran en nuestro proyecto en facebook developers en configuracion y basica
    En nuestro proyecto en facebook Developers damos click en agregar producto y seleccionamos inicio de sesion con Facebook, escogemos android
    Agregamos el repositorio de mavenCentral en el gradle Project
    Agreamos la implementacion del sdk de facebook en el gradle module
    Copiamos el nombre del paquete y la activity donde nos pide facebook developers
    Para generar el codigo hash
        Se tuvo que descargar la biblioteca OPENSSL para windows, ya descargada y descomprimida,
        se copio y pego dentro de la carpeta jdk1.8.0_301 de java en archivos del programa
        (realmente se puede dejar donde sea pero para tenerla donde deberia de estar la pegamos ahi)
        Se abrio el simbolo del sistema y nos dirigimos a la carpeta de java en donde esta la herramienta keytool
        Para utilizar el comando que marca facebook developers es necesario configurar una variable de entorno para que
        con la cadena PATH_TO_OPENSSL_LIBRARY se obtenga en realidad el path en donde se encuentra el archivo openssl
        Esto ultimo no es realment necesario pero si no lo hacemos en vez de colocar ese string se cambia por el path real
    Seguimos los demas pasos que nos dice facebook developers
    Copiamos la URI de nuetra aplicacion la cual se encuentra en la consola firebase de nuestro proyecto en el metodo de inicio de sesion de facebook
        y lo pegamos en nuestro proyecto en facebook developers en configuracion en URI de redireccionamiento de OAuth válidos
    Agregamos las librerias necesarias en el gradle module
    Implementamos el codigo necesario de facebook en la actvitiy login basandonos en la documentacion de google (A partir del comentario 85)
    Provamos el inicio de sesion utilizando uno de los usuarios de prueba que nos da FacebookDevelopers en la parte de roles


    CREACION DE MENUS
    Se creo el layout app_bar_main
    Se creo un Android Resource Directory de tipo menu dentro de res
    Se creo el un resource File llamado acivity_main_drawer dentro de la carpeta menu para el menu que sera el drawer(el que tenga la estrucutra del menu)
    Se agregaron los layout del toolbar y el menu dentor del layoutMain con la etiquta include y NavigationView
    Se creo el resource File nav_header_main el cual contiene el layout de navegacion principal
    Se creo el activity RecordActivity
    Se creo un layout order_records_bay en la carpeta de menus

    VIDA A LAYOUTS
    Se creo la clase objetct Utility






     */


}