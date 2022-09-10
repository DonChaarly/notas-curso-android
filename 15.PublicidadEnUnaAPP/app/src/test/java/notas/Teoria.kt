package notas

class Teoria {

    /*
                    PUBLICIDAD EN APLICACIONES

    Hay diferentes formas de agregar publicidad, pero siempre se tiene que pensar en el publico objetivo y
    el tipo de aplicacion que se esta lanzando
    La aplicacion puede ser destinada a niños los cuales no cuentan con mucho dinero,
    si es reciente la aplicacion puede incluso no tener anuncios esperando que se viralice la aplicacion
    y despues se agregue poco a poco la publicidad


                        CUENTAS NECESARIAS

    GOOGLE ADS
    Es necesario contar primero con una cuenta de googleAds la cual tiene que estar aprobada no puede estar baneada ni nada
    porque de esta forma se restringen la cuenta y por lo tanto no puedes agregar publicidad

    GOOGLE ADMOB
    Con google AdMob se puede llevar control de la publicidad y aplicaciones que se tienen

    enlace de google de como agregar publicidad:
    https://developers.google.com/admob/android/quick-start?hl=es#import_the_mobile_ads_sdk


                    CONFIGURACION DE LA APICACION

    1.- Se tiene que agregar la siguiente libreria al build.gradle (module):
    dependencies {
        implementation 'com.google.android.gms:play-services-ads:20.6.0'
    }

    2.- En el Manifest se tiene que agregar lo siguiente
    <manifest>
    <application>
        <!-- Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713 -->
        <meta-data
            android:name="com.google.android.gms.ads.APPLICATION_ID"
            android:value="ca-app-pub-xxxxxxxxxxxxxxxx~yyyyyyyyyy"/>
    </application>
    </manifest>
    El app ID es independiente de cada aplicacion, esa te la dan al registrar la aplicacion en el google admob
    Si se quiere probar los anuncios para ver que toodo este en ordne entonces se puede utilizar el  id que viene de ejemplo

    3.- Se tiene que agregar la siguiente linea al onCreate del activity donde se vaya a mostrar la publicidad (comentario 3)
    MobileAds.initialize(this) {}


                        FORMATOS DE ANUNCIO
    Se tienen 4 tipos de formatos de anuncio
    Banner: Este tipo de anuncion es el tipico que se tiene en la parte de abajo o arriba de la pantalla en una parte pequeña y que
    siempre se ve
    Intersticial: Este tipo de anuncion es de pantalla completa, se recomienda colocarlo durante las pausas naturales de la aplicacion
    como cuando se pierde en un juego cuando se cambia de pantalla etc
    Nativo: Este tipo de anuncios son anuncios personalizados que se ajustan a la apariencia de la apliacion
    Bonificado: Este tipo de anuncios son los que el usuario decide si verlos o no, y si el usuario los ve, recibe algun tipo de
    bonificacion dentro de la apliacion

        BANNER:
    Se tiene dos maneras de agregar un banner
    Se puede solamente agregar lo siguiente al layout.xml para que se muestre el banner
    <com.google.android.gms.ads.AdView
      xmlns:ads="http://schemas.android.com/apk/res-auto"
      android:id="@+id/adView"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:layout_centerHorizontal="true"
      android:layout_alignParentBottom="true"
      ads:adSize="BANNER"
      ads:adUnitId="ca-app-pub-3940256099942544/6300978111">
  </com.google.android.gms.ads.AdView>

  O tambien se puede reservar un espacio dentro del layout para administrar el banner desde el codigo
  Se tiene que agregar lo que se tiene a partir del comentario 7


        INTERSTICIAL
  Para este tipo de anuncio se tiene que llamar primero a la funcion de carga el cual se encarga de
  preparar el anuncio
  Despues de esto se ejecuta otra funcion para lanzar el anuncio
  Hay que tener cuidado en siempre cargar un anuncio cuando no se haya cargado previamente un anuncio que no se ha mostrado
  Ya que si se cargan un anuncio y nuevamente se vuelve a cargar otro anuncio sin mostrar el previo
  se produce un error

  A partir del comentario 15 se tiene como configurar el anuncion


                    OBTENER IDs PROPIOS DE PUBLICIDAD

  Para esto tenemos que ir a Google AdMob en la parte de aplicaciones seleccionamos añadir una aplicacion,
  Se siguen los pasos que pide
  Despues google tiene que testear la aplicacion y aprobarla, esto tarda unos cuantos dias

  Dentro de la seccion de la aplicacion agregada se tiene una parte que dice agregar bloques de anuncios, en esta parte elegimos
  cual queremos agregar y con esto obtenemos un Id para utilizar




     */
}