package com.cursoandroid.a10palettewidget

import android.content.Context
import android.media.Rating
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Thread.sleep
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this

        ///////////////////////         IMAGE VIEW (url)        //////////////////////////////

        //4. Para cargar la imagen de internet a un imageView se tiene que hacer lo sguiente
        var ivEjemplo2 = findViewById<ImageView>(R.id.ivEjemplo2)
        //5. Se guarda la url de la imagen en una variable para despues solamente pasar esta variable
        var imageUrl = "https://cdn.foodandwineespanol.com/2019/02/destacda-hamburguesa.jpg"
        // 5. Se utiliza los siguientes metodos de picasso para cargar una imagen de internet
        Picasso.get().load(imageUrl).into(ivEjemplo2)


        ////////////////////////        WEB VIEW           /////////////////////////////////

        //13. Podemos recuperar el elemento web view con el metodo findViewById
        var webView = findViewById<WebView>(R.id.webView)

        //14. Se necesita configurar la navegacion del sitio web ya que si no se hace esto puede provocar que la webView no funcione bien
        //15. Se crea una variable webSetting en donde se cargaran todos los valores de la configuracion
        //16. Con el metodo getSettings() se obtienen todos los settings que ya se tienen
        var webSettings: WebSettings = webView.getSettings()
        //17. habilitamos el JavaScript ya que por defecto las web utilizan esta tecnologia
        webSettings.javaScriptEnabled = true
        //18. Finalmente establecemos el navegador como cliente
        webView.webViewClient = WebViewClient()

        //19. Para establecer una direccion url al werView se utiliza el metodo loadUrl
        webView.loadUrl("https://www.google.com.mx/?gws_rd=ssl")


        ///////////////////////         VIDEO VIEW          ///////////////////////////////////

        //          Reproduccion Web
        //21. Se recupera un video view de forma normal
        var vvWeb = findViewById<VideoView>(R.id.vvWeb)
        //22. Se debe establecer un controlador de video o mediaControler
        var mcWeb = MediaController(this)
        //23 Vinculamos el mediaController al videoView
        mcWeb.setAnchorView(vvWeb)
        //24. Para establecer la url que hace referencia al video que queremos colocar en este elemento
        vvWeb.setVideoPath("https://jotajotavm.com/img/video.mp4")
        //25. Indicamos que mediaController tiene que utilizar el videoView
        vvWeb.setMediaController(mcWeb)


        //          Reproduccion local
        var vvLocal = findViewById<VideoView>(R.id.vvLocal)
        var mcLocal = MediaController(this)
        mcLocal.setAnchorView(vvLocal)
        //26. Para pasar la ruta del video, esta se puede guardar en una variable
        var path = "android.resource://" + packageName + "/" + R.raw.videoprueba
        //27. Le indicamos la ruta al reproductor de video
        vvLocal.setVideoURI(Uri.parse(path))
        vvLocal.setMediaController(mcLocal)
        //28. Se puede acceder a los metodos para los controles como star video, resume, stop, etc
        //vvLocal.start()


        //////////////////////////          CALENDAR VIEW     //////////////////////////////

        //30. recuperamos el calendar view y el text view para mostrar informacion relacionada a las fechas
        var cvEjemplo = findViewById<CalendarView>(R.id.cvEjemplo)
        var tvFecha = findViewById<TextView>(R.id.tvFecha)

        //31. con el atributo setOnDateClickListener establecemos el codigo que se va a ejecutar cuando se presente este evento
        //32 Se colocan 4 parametros al lambda, el primero sera el objeto calendarView, el segundo el año el tercer el mes, el cuarto el dia
        cvEjemplo.setOnDateChangeListener { cv, year, month, day ->
            //33. Cramos una variable que almacenara la fecha en cadena de texto
            ///34. El mes es manejado como un array asi que se considerea a enero como el mes 0, por ende hay que sumarle un 1 para que se muestre correctamente
            var date = "$day/${month + 1}/$year"
            //35Cambiamos el valor de text del textview
            tvFecha.text = "Fecha seleccionada: $date"
        }

        //36. Para marcar una fecha en el calendario primero
        //37. Se crea una instancia de un calendario
        var calendar = Calendar.getInstance()
        //38. se pasa el valor de la fecha al calendar
        calendar.set(2026, 5, 8)
        //39. Finalmente se establece la fecha al calendar View, se tieen que pasar en milisegundos ya que asi funciona internamente el calendario
        cvEjemplo.date = calendar.timeInMillis

        //40. Si se quiere recorrer un dia el calendario par que nos se muestre como domingo el primer dia
        //41. Se obtiene el primer dia que se tiene actualmente en el calendario
        var d = cvEjemplo.firstDayOfWeek
        //42. Se le establece un nuevo dia al firstDayOfWeek, se coloca el % 7 porque los dias se manejan en porciones de 7
        cvEjemplo.firstDayOfWeek = (d + 1) % 7



        //////////////////////////      PROGRESS BAR         ///////////////////////////////////

        //44. Se recupera un progress bar de forma normal
        var pbDeterminado = findViewById<ProgressBar>(R.id.pbDeterminado)

        //45. Se les puede cambiar sus propiedades como el max del progress bar, el color etc
        pbDeterminado.max = 1000
        pbDeterminado.progress = 0
        pbDeterminado.secondaryProgress = 0

        //47. para gestionar el progreso de una progress bar determinada se hace uso de una co rutina
        //48. En un Globalscope hacemos un launch en donde llamaremos una corutina
        GlobalScope.launch {
            progressManager(pbDeterminado)
        }


        ///////////////////////////         SEEKBAR             //////////////////////////////

        //59 Recuperamos el seek bar con normalidad
        var sbNormal = findViewById<SeekBar>(R.id.sbNormal)
        //60 Para no afectar al hilo normal utilizamos una corutina en donde se va a ejecutar el metodo seekBarManager
        GlobalScope.launch {
            seekbarManager(sbNormal)
        }

        //63 Para administrar quien hizo el cambio en el progreso del seekbar vamos a utilizar la funcion setOnSeekbarChangeListener
        //64 En este metodo necesitamos crear un objeto de tipo SeekBar al cual tambien se le va a llamar el mismo metodo
        sbNormal.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            //65 Al crear este objeto, necesitamos impolementar todos sus metodos, los cuales nos sirven para saber como se ha modificado el progress

            //66 Este metodo se ejecuta cuando el progress cambie
            //69 Este metodo recibe como parametros el objeto SeekBar, el punto de progreso en que nos encontramos y el tercero indica si el cambio lo ha hecho el usuario
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser) Toast.makeText(context, "Tu lo cambiaste", Toast.LENGTH_SHORT).show()
            }

            //67 Este metodo se ejecuta cuando se toca el inicio del tracking
            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            //68 Este metodo se ejecuta cuando el tracking se detiene
            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })


        /////////////////////////           RATING BAR          ////////////////////////////////////

        //71 Se puede recuperar un rating bar de forma normal
        var rbEjemplo = findViewById<RatingBar>(R.id.rbEjemplo)
        var tvRating = findViewById<TextView>(R.id.tvRating)

        //72 Se pueden modificar las propiedades del objeto view RatingBar
        rbEjemplo.rating = 3.5f
        //72 Se tiene tambien el evento setOnRatingBarChangeLister el cual en la lambda nos coloca por defecto tres parametros
        //73 El primer parametro se refiere al RatingBar, el segundo al rating y el tercer indica si el cambio en el rating lo hizo el usuario o el codigo
        rbEjemplo.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            //74 Cambiaremos el texto de acuerdo al numeor de estrellas que se tienen
            tvRating.text = "$rating/${ratingBar.numStars}"
        }


        ////////////////////////           SEARCH VIEW      //////////////////////////////////////

        //76 El serch view se recupera con normalidad
        var svUsers = findViewById<SearchView>(R.id.svUsers)
        var lvUsers = findViewById<ListView>(R.id.lvUsers)

        //77 Se tiene que crear un array que seran las opciones que se muestran en el listView
        var users = arrayOf("Carlos", "Alberto", "Rodrigo", "Altamirano", "Carla", "Carlota", "Rogelio")
        //78 Se debe crear un adaptador para decirle al ListView que array de opciones va a tomar
        var adapterUsers: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, users)
        //79 Se vincula el adapter con el listview
        lvUsers.adapter = adapterUsers

        //80 Podemos utilizar el evento setOnQueryTextListener para indicar un codigo cuando se ejecute este evento
        //81 Ete evento necesita que creemos un objeto tipo SearchView
        svUsers.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            //82 Se implementan sus metodos
            //83 Cuando se hace un submit del texto se ejecutar el siguiente metodo
            override fun onQueryTextSubmit(query: String?): Boolean {
                //84 Verificamos que el array users contiene lo que esta escribiendo el usuario y si si, vamos filtrando con el adapter las opciones que se muestran
                if(users.contains(query)) adapterUsers.filter.filter(query)
                return false
            }
            //85 Este metodo se ejcuta cada que se hace un cambio en el query de busqueda
            override fun onQueryTextChange(query: String?): Boolean {
                //86 Se va a ir filtrando cad vez que se agregue o borre algo
                adapterUsers.filter.filter(query)
                return false
            }

        })

    }


    //49 Se crea la funcion la cual al llamarse como corrutina a pesar de ser llamada en el OnCreate, va a ser llamada una vez el OnCreate termine
    private fun progressManager(pb: ProgressBar){
        //50 Vamos a hacer un bucle while el cual se ejecutar mientras el progrseo de la progressBar sea menor que el maximo de la misma
        while(pb.progress < pb.max){
            //51 Cada Vez que se ejecute un ciclo vamos a dormir el hilo 100 milisegundos, osea 0.1 s
            sleep(100L)
            //52 Se puede aumentar el medidor del progress de dos formas
            //pb.progress += 5
            pb.incrementProgressBy(5)
            //53 El secondaryProgress se le puede dar un mayor avance ya que este es basicamente su funcion
            pb.incrementSecondaryProgressBy(10)
        }
    }

    //61 Se crea la funcion seekBarManager la cual se ejecutar en un hilo secundario par no afectar al primario
    private fun seekbarManager(sb: SeekBar){
        //62 Vamos a estar ejecutando siempre un bucle while, ya que el usuario va a poder regresar el progreso de la seekbar entonces este bucle siempre tiene que estar trabajando
        while (true){
            sleep(100L)
            sb.incrementProgressBy(5)
        }
    }

}