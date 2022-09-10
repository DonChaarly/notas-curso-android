package com.cursoandroid.a15publicidadenunaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity : AppCompatActivity() {

    //16. Se tiene que crear una variable InterstitialAd la cual guardara el anuncio de tipo Intersticial
    private var mInterstitialAd: InterstitialAd? = null
    //27. Con una variable global se puede gestionar la carga o descarga del anuncion Intersticial
    private var unloadedAd = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //3 Se puede agregar un metodo que maneje toodo lo de la publicidad
        initAds()

    }

    //4. La funcion initAds inicializa toodo lo relacionado con la publicidad de tipo Banner
    private fun initAds(){
        MobileAds.initialize(this) {}
        //7. Se puede administrar el codigo del banner con lo siguiente
        //8. Se crea una variable llamada adView la cual guardara el objeto anuncion
        val adView = AdView(this)
        //9. Se establece el tamano del anuncion, en la ayuda de google Admob viene los diferentes tamanos que hay
        adView.adSize = AdSize.BANNER
        //10. Se agrega el id que nos proporciona el google AdMob para nuestra aplicacion
        adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"

        //11 Se recupera el espacio dentor del layout en donde vamos a agregar la publicidad
        var lyAdsBanner = findViewById<LinearLayout>(R.id.lyAdsBanner)
        //12 Se le agrega el objeto publicidad
        lyAdsBanner.addView(adView)

        //13. Se pide a android que nos genere un anuncio
        val adRequest = AdRequest.Builder().build()
        //14. Y finalmente se carga el anuncio
        adView.loadAd(adRequest)



    }

    //15. La funcion getReadyAds carga la publicidad de tipo Intersticial
    private fun getReadyAds(){
        //17. La variable adRequest pide a androir crear el anuncio
        var adRequest = AdRequest.Builder().build()
        //27. Al preparar el anuncion se tiene que indicar que no esta descargado el anuncio
        unloadedAd = false

        //18 Se hace la carga del anuncion y se proporciona el id de nuestra apliacion
        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            //19. Se tiene dos casos, en caso de que el anuncio falle se ejecuta el siguiente codigo
            override fun onAdFailedToLoad(adError: LoadAdError) {
                //20. Se deja en null el objeto interstitial
                mInterstitialAd = null
            }
            //21. En caso de que se cargue el anuncio correctamente se ejecuta el siguiente codigo
            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                //22. Se le pasa al objeto interstitial el anuncion que se creo con la viariable adREquest
                mInterstitialAd = interstitialAd
            }
        })
    }

    //23 La funcion showIntertitial sera la encargada de mostrar el anuncio previamente cargado por getReadyAds
    private fun showIntertitial(){
        //24. Si se cargo correctamente el anuncion, el objeto Interstitial dejara de ser null, en tal caso se mostrara el anuncion con el metodo .show()
        if (mInterstitialAd != null) {

            //30. cuando ya se mostro el anuncion se descarga se indica que ya se ocupo el anuncio cargado
            unloadedAd = true

            //25. Con el fullScreenContentCallback se gestionan eventos relacionados con la visualizacion del Interstitial, si se quiere ir viendo el registo de lo que pasa con nuestro anuncion por medio del LOG
            mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    //Log.d(TAG, 'Ad was dismissed.')
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                    //Log.d(TAG, 'Ad failed to show.')
                }

                override fun onAdShowedFullScreenContent() {
                    //Log.d(TAG, 'Ad showed fullscreen content.')
                    mInterstitialAd = null
                }
            }
            mInterstitialAd?.show(this)
        }
    }

    //25. la siguiente funcion gestiona el evento click del boton cargarAnuncio
    fun cargarAnuncio(v: View){
        //26 se tiene que gestionar la carga del anuncion, no se puede cargar si ya se ha cargado previamente
        if (unloadedAd == true) getReadyAds()

    }

    //28. La siguiente funcion gestiona el evento click del boton mostrarAnuncio
    fun mostrarAnuncio(v: View){
        //29 Se llama a la funcion showIntertitial
        showIntertitial()
    }

}