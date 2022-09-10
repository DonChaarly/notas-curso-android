package notas

class Teoria {
    /*
                FORMAS DE PAGOS EN APLIACIONES

    Existen diferentes formas de pagos como pagos con tarjetas, pagos en oxxo, con paypal, etc.
    Para agregar una forma de pago, podemos administrar los datos de los usuarios nosotros pero el
    riesgo es muy grande, por ello es mejor utilizar una pasarela
    Una pasarela es un servicio que se encarga de administrar las formas de pagos
    En este caso se utilizara la pasarela de STRIPE para pagos con tarjeta

    Documentacion completa:
    https://stripe.dev/stripe-android/
    Versiones de stripe:
    https://github.com/stripe/stripe-android/releases
    Guia de implementacion de pago con stripe:
    https://stripe.com/docs/payments/quickstart


    STRIPE esta muy bien documentado, tiene su canal de youtube y en su pagina explican muy bien como
    implementar stripe para distintos tipos de aplicaciones ya sea web android etc


                IMPLEMENTACION DEL CODIGO DE STRIPE

    Stripe ya nos proporciona el codigo necesario para implementar el servicio de pagos
    En su pagina debe serleccinarse la plataforma, el front-end y el back-end
    Se tiene que crear un activity para esto, el codigo que nos da viene con el activity nombrado como  CheckoutActivity
    se puede cambiar este nombre pero mejor dejarlo ya que es llamado desde otra partes del codigo

                        NODE.JS COMO SERVIDOR

    Node.js nos provee un servicio para utilizar un servidor
    Para utilizar a node.js como servidor en tu computadora primero hay que intalar node.js, para nuestro sistema operativo
    esta es una guia muy sencilla para node.js: https://medium.com/@adnanrahic/hello-world-app-with-node-js-and-express-c1eb7cfa8a30


                        PROGRAMAR SERVIDOR (Ver video de Configuracion de pagos con node.js de este tieam para mas informacion)

    1. Creamos una carpeta en el explorador de windows (la llamaremos en este caso stripe-payment)
    2. Con la consola de comandos nos dirigimos a esa carpeta
    3. Ya en la ruta de la carpeta ejecutamos el comando: npm init
    4. Damos enter a toodo hasta que sale (index.js)
    5. Podemos renombrar ese archivo colocando simplemente el nombre nuevo con .js ( en este caos la documentacion de stripe nos recomienda que lo llamemos server.js)
    6. Le damos a toodo lo demas enter
    7. Ahora ejecutamos el comando: npm install express --save
    8. Ejecutamos el comando: npm install --save stripe ( Esto es por parte de stripe)
    9. Despues de esto aparecen 3 archivos en la carpeta que creamos al incio
    10. En la carpeta que creamos, ahora creamos un archivo llamado server.js (puede ser inicialmente un archivo de texto .txt)
    11. Dentro de este archivo copiamos toodo el codigo que nos da stripe
    12. El la linea donde se declara la constante stripe tenemos un codigo de prueba que nos da stripe, cuando nos registremos en stripe ya tendremos nuestro propio codigo ( El registro es un poco complicado)
    13. Una vez se tiene el codigo copiado, se puede modificar de acuerdo a lo que necesitemos
    14. Para lanzar el archivo podemos escribir en el sistema de comandos: node server.js

                    PROGRAMACION DEL CODIGO PARA USO DEL SERVIDOR (LOCAL)
    1. Se implementaron las liberias, los sdks de stripe en el gradle (module)
    2. Se creo el activity CheckoutActivity (ese nombre nos recomienda stripe)
    3. Se copio y pego el codigo que nos da stripe para el archivo checkoutActivity (Este puede ser modificado de acuerdo a lo que necesitemos, de hecho fue modificado)
    4. Se copio y pego el codigo que nos da stripe para el archivo MyApp.kt (Solamente la parte del paymentConfiguration
    5. Se agregaron los permisos al manifest para utilizar el internet

    EXTRA:
    Se tuvo que agregar el siguiente archivo para permitir peticiondes con HTTP
    1. Se agrego la carpeta xml y dentro el archivo network_security_config.xml
    2. Se modfico el manifesetm, se agergo la etiqueta networkSecurityConfig


                        PROBAR EL PAGO DE STRIPE

    Para hacer pruebas del codigo de pago, Stripe nos proporciona tarjetas de pruebas para hacer pagos
    tiene diversar tarjetas, una que no tiene dinero, otra que si etc.

    En el siguiente enlace se muestra las tarjetas de prueba que nos da stripe: https://stripe.com/docs/payments/without-card-authentication#android-test


                        HACER EL SERVIDOR PUBLICO Y ACCESIBLE DESDE OTROS LADOS

    1. Se descargo y creo una cuenta de heroku
    2. Se descargo git y se reinicio la computadora
    3. Se siguio la guia de heroku: https://devcenter.heroku.com/articles/deploying-nodejs
    4. En el simbolo del sistema en la carpeta donde se encuentra el servidor de stripe (server.js), se consulto la version de node.js que estamos utilizando con el comando npm -v
    5. Se copio y pego lo siguiente en el packeg.json (En la carpeta stripe-payment) Especificando la version de node.js donde dice node
        ,
        "engines": {
        "node": "6"
        }
    6. Se modifico el server.js para no especificar un puerto estatico sino un puerto dinamico
        const PORT = process.env.PORT || 5001;
        app.set('port', process.env.PORT)
        app.listen(PORT, () => console.Log("Node server listening on port ${PORT}!"))
    7.En el simbolo del sistema se colocaron los siguientes comandos en la carpeta del proyecto
    8. npm i -g heroku (Causo problemas debido a que no tenia la utlima version de node.js instalada, para actualizar node.js, como administrador en PowerShell corri el comando npm-windows-upgrade)
    9. heroku local web
    10. Control + C (Para cerrar la ejecucion anterior)
    11.git init
    12.git add .
    13.git commit -m "HEROKUprueba" (Se coloca el titulo de la apliacion que queramos darle) (Se tuvo que establecer un correo y un usario primero, aparecia como ahi mismo)
    14.heroku login
    15.heroku create heroku-prueba-carlos (El nombre heroku-prueba-carlos es el nombre que le colocamos al proyecto, puede cambiar)
    16.git push heroku master
    17.Despues del ultimo comando copiamos la direccion que nos dio heroku en donde se encuentra nuestro servidor publico (https://heroku-prueba-carlos.herokuapp.com/)
    18.heroku open
    19.Se cambio la url establecida en el archivo CheckoutActivity.kt por la nueva que nos dio heroku






     */
}