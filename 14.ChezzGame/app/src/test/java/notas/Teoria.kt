package notas

class Teoria {

    /*
                    METODOLOGIA DE TRABAJO

    Es importante seguir una metodologia de trabajo para no solamente escribir codigo a lo bestia
    Ya que de esta forma nos evitamos cometer errores y emepzar por las cosas mas importantes
    hasta llegar a optimizar el codigo y mejorar la Experiencia de usuario (UX)

    Para esto es utili crear un file tipo txt para escribir la ruta que se va a serguir
    en donde se puede colocar los paso que se deben seguir
    Los errores que pueden surgir
    Las mejoras de UX que se pueden hacer
    Las funcionalidades que se nos ocurran


                    OBTENER EL TAMANO DE LA PANTALLA DEL USUARIO

    Para obtener la patnalla del usuario se tiene que hacer lo que se hace a partir del comentario 33



                    LLEVAR EL TIEMPO DE LA PARTIDA (Comentario 140)
    Para llevar el tiempo se necesita crear una variable de tipo Runable la cual es ejecutable en la cual
    Se incluye un bloque try Catch en el cual se va a ir aumentando el tiempo mientras que una variable
    de tipo Handler va a hacer que el try catch se ejecute cada cierto tiempo


                    OBTENER UN VIEW POR MEDIO DEL TAG
    Para objetner un view por medio del tag al momento de ejecutar el findViewById en vez de colocar el R.id....
    SE coloca un resources.getidentifier("nombre del tag", "id", packageName)


                    PEDIR PERMISOS

    Hay veces que nuesrra aplicacion necesita pedir permiso de cualqueir cosa, para esto en el kodigo
    kotlin para que al usuario le aparezca la ventana de pedir permisos necesitamos colocar lo del comentario 155
    Tambien es necesario color en el manifest estos permisos

----------------------¡¡¡IMPORTANTE!!! Hacer una captura de pantalla (toodo a aprtir del comentario 157)-----------------------------

    Para hacer una captura de pantalla se necesita pedir los permisos READ_EXTERNAR_STORAGE Y WRITE_EXTERNAL_STORAGE
    Despues de esto es necesario incluir la liberria ScreenCapture la cual hara la captura de pantalla
    Y esta captura se guarda en una variable bitmap


                        INTENTS (Comentario 247 y 260)
    Los intents nos permiten redirigirnos a otras pantallas
    aparte de esto se pueden agregar datos que quremos mandar desde la pantalla de origne a la pantalla que nos estamos redirigiendo


                        SHARED PREFERENCES (Comentario 268)
    Las sharedPreferences son datos que podemos guardar dentro del propio dispositivo de la aplicacion y que se queda guardado aun cerrando la aplicacion


                        CREAR UN NUEVO ICONO PARA LA APLICACION

    Para crear un nuevo icono:
    1. En la carpeta de drawable podemos damos new -> Image Asset
    2. En icon Type se tiene que seleccionar Launcher icons
    3. Se seleciona la imagen que quremos que tena el icono
    4. Se cambia tambien el Backgroun layer
    5. Al hace el icono se van a reemplazar los archivos por el icono anterior
    6. Se pueden presentar algunos errores por archivos duplicados etc.
    7. Para solucionar los errores se puede crear nuevamente el icono para que se reemplacen correctamente los archivos necesarios


                            HACER QUE LA APLICACON NO PUEDA ROTAR LA PANTALLA
    Para hacer esto se puede agregar la siguiente linea al manifest
    android:screenOrientation="portrait"


                            HACER QUE EL CICLO DE VIDA NO SE REINICIE CUANDO SE ROTA LA PANTALLA
    Para hacer esto se puede agregar la siguiente linea al manifest
    android:configChanges="screenSize|orientation"



                        AGREGAR SONIDOS A LA APLICACION

    Para agregar sonidos, es necesario verificar que estos sonidos no tengan derechos de auto o que nosotros tengamos esos derechos
    La pagina: FreeAudioLibrary cuenta con sonidos libres de derechos

    Se agrega un android Resource Directory de tipo raw par agregar los sonidos de la aplicacion
    Pasamos a esta carpeta los sonidos que queramos agregar
    Se tiene que crear variables de tipo MediaPlayer dentro del codigo para manejar los audios (comentario





     */
}