package com.cursoandroid.a14chezzgame



import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.SharedPreferences

import android.graphics.Bitmap
import android.graphics.Point
import android.media.MediaPlayer
import android.media.MediaScannerConnection
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.TypedValue
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.test.runner.screenshot.ScreenCapture
import androidx.test.runner.screenshot.Screenshot.capture
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.stripe.android.PaymentConfiguration
import java.io.File
import java.io.FileOutputStream

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    //50. Creamos unas variables x y y para ir guardando las posiciones en donde se coloque el caballo
    private var cellSelected_x = 0
    private var cellSelected_y = 0
    //54. Se crea un Array de IntArrays el cual representara el tablero de ajedres
    private lateinit var board: Array<IntArray>
    //69. La variable options almacenara el numero de opciones que tenemos disponibles para mover el caballo
    private var options = 0
    //90. Creamos las variables nameColor_black y white
    private var nameColorBlack = "black_cell"
    private var nameColorWhite = "white_cell"
    //92. Creamos una variable llamada moves la cual getionara el numero de movimientos que nos quedan hasta terminar el juego la cual empezara en 64 ya que es el numero de casillas del tablero
    private var moves = 64
    //99. Creamos una variable para administrar la cantidad de movimientos que se necesitan para mostrar un nuevo bonus
    private var movesRequired = 0
    //108. Creamos una variable la cual va a administrar el numero de bonus que se tienen
    private var bonus = 0
    //111. Creamos una variable la cual va a indicar el ancho que tendra el bonus dependiendo de la pantalla que tenga el usuario
    private var widht_bonus = 0
    //113. Se crea una variable la cual indica cuantos movimientos tiene cada nivel
    private var levelMoves = 0
    //121. Se Crea una variable llamada checkMovement la cual va a indicar si se tiene que checar el movimeinto o no
    private var checkMovement = true
    //137. Se crea una variable mHandler la cual nos ayudara a llevar el coronometro
    private var mHandler: Handler? = null
    //143. Se crea una variable llamada tiemeInSeconds la cual llevara la cuenta de los segundos transcurridos
    private var timeInSeconds = 0L
    //150. La variable gaming nos dira si estamos jugando o no
    private var gaming = true
    //158. La variable bitMap nos ayudara guardar imagnees
    private var bitmap: Bitmap? = null
    //171. Creamos la variable stirng_share la cual guardara el mensaje que quremos mostrar junto con la captura de pantalla
    private var string_share = ""
    //172. Se crea una variable llamada level el cual va a ir guardando el nivel en el que nos encontremos
    private var level = 1
    //182. Se crea una variable que nos indica si se avanza de nivel o no
    private var nextLevel = false
    //183. Se crea una variable la cual va a ir guardando el numero de vidas con las que cuenta el usuario
    private var lives = 1
    //211. Se tiene que crear una variable InterstitialAd la cual guardara el anuncio de tipo Intersticial
    private var mInterstitialAd: InterstitialAd? = null
    //222. Con una variable global se puede gestionar la carga o descarga del anuncion Intersticial
    private var unloadedAd = true
    //272. Creamos una variable de tipo sharedReferences para recuperar los datos guardados en este apartado
    private lateinit var sharedPreferences: SharedPreferences
    //273. Tambien nos creamos un editor con el cual editar las sharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    //276 Creamos una variable premium en donde se guardara si el usuario es premium o no
    private var premium: Boolean = false
    //280. Creamos unas variables para guardar la seleccion de color de las celdas
    private var optionBlack = R.drawable.option_black
    private var optionWhite = R.drawable.option_white
    //285. Cree una variable llamad primeraVez para gestionar el momento en que se haga premium el usuario
    private var primeraVez = 0
    //286. Cree una variable llamad resume para gestionar cuando se haga el onResume y de esta manera no mostrar el banner muchas veces
    private var resume = 0
    //287. Creamos una variable LASTLEVEL la cual define el ultimo nivel disponible que tendran los usuario
    private var LASTLEVEL = 9
    //291. Se crean variables de tipo MediaPlayer para manejar los audios de la aplicacion
    private lateinit var mpMovement : MediaPlayer
    private lateinit var mpBonus: MediaPlayer
    private lateinit var mpGameOver: MediaPlayer



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //292. Se llama a la funcin initSound
        initSound()

        //26. Se llama a la funcion initScreenGame()
        initScreemGame()

        //274. Llamamos a la funcion initPreferences
        initPreferences()


        checkPremium()
        startGame()


    }

    //275. La funcion initPreferences inicializara las variables sharedPreferences y editor
    private fun initPreferences(){
        sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    //269. Cuando nos regresemos a esta activity no se ejecutara la funcion onCreate sino la onResume
    override fun onResume() {
        super.onResume()
        resume++

        if (primeraVez == 1) {
            ++primeraVez
            //270. Revisamos si el usuario es premium
            checkPremium()
            //128. LLamamos a la funcion start game
            startGame()
        }

    }

    //293. La funcion initSound inicializamos las variables de tipo MediaPlayer
    private fun initSound(){
        mpMovement = MediaPlayer.create(this, R.raw.paso)
        //294. Con la propiedad isLopping se puede configurar que el sonido este constantemente reproduciendo o solamente se reproduzca una vez y ya
        mpMovement.isLooping = false

        mpBonus = MediaPlayer.create(this, R.raw.bonus)
        mpBonus.isLooping = false

        mpGameOver = MediaPlayer.create(this, R.raw.fin)
        mpGameOver.setLooping(false)
    }

    //26 la funcion initScreenGame va a inicializar la pantalla haciendo que el tablero sea cuadrado en todos los dispositivos y se quite la visibilidad del lyMessage
    private fun initScreemGame(){
        //27. Esta funcion llama a su vez a las funciones setSizeBoard y hide_message con valor false para que en este punto no inicialiece el juego esta funcion
        setSizeBoard()
        hideMessage(false)
    }
    //28. La funcion setSizeboard hace que el tablero sea cuadrado dependiendo de la pantalla en la que este
    private fun setSizeBoard(){
        //31. Creamos una variable de tipo ImageView para ir guardando cada elementos que vamos recorriendo
        var iv: ImageView

        //33. Ceramos una variable en donde guardaremos la pantalla del dispositivo
        val display = windowManager.defaultDisplay
        //34. Obtenemos el tamano de la pantalla con las siguientes tres lineas
        val size = Point()
        display.getSize(size)
        val width = size.x
        //35. Convertimos el tamano a dp
        var width_dp = (width / resources.displayMetrics.density)
        //36. Restamos los margenes que se han especificado al TableLayout
        var lateralMarginsDP = 0
        val width_cell = (width_dp - lateralMarginsDP)/8
        val heigth_cell = width_cell

        //112. Se le asigna al ancho de la barra bonus el doble de la celda del tablero
        widht_bonus = 2 * width_cell.toInt() -6

        //30. Se recorre cada una de las celdas del tablero para cambiar su with y height
        for (i in 0..7){
            for (j in 0..7){
                /*32. Para saber de recuperar el ImageView utilizamos el metodo findviewById
                      y para saber el id del view utilizamos el metodo getIdentifier de resources
                      Pasamos como parametro es tag, el tipo  y de donde obtendra el tipo*/
                iv = findViewById(resources.getIdentifier("c$i$j", "id", packageName))

                //37. Definimos el tamano del ancho y alto de las celdas
                var height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, heigth_cell, resources.displayMetrics).toInt()
                var width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width_cell, resources.displayMetrics).toInt()

                //38. Enviamos la altura y anchula a la variable iv
                iv.setLayoutParams(TableRow.LayoutParams(width, height))
            }
        }

    }
    //29. La funcon hide_message hace que el linearlayout lyMessage cambie su visibilidad
    private fun hideMessage(start: Boolean){
        var lyMessage = findViewById<LinearLayout>(R.id.lyMessage)
        lyMessage.visibility = View.INVISIBLE

        //179. Se inicializa el juego si es que start es true
        if (start) startGame()

    }
    //200. La funcion initAds() inicializa la publicidad y en especifico la publicidad de tipo banner
    private fun initAds(){
        //201. Se inicializa la publicidad en este contexto
        MobileAds.initialize(this) {}
        //202. Se puede administrar el codigo del banner con lo siguiente
        //203. Se crea una variable llamada adView la cual guardara el objeto anuncion
        val adView = AdView(this)
        //204. Se establece el tamano del anuncion, en la ayuda de google Admob viene los diferentes tamanos que hay
        adView.adSize = AdSize.BANNER
        //205. Se agrega el id que nos proporciona el google AdMob para nuestra aplicacion
        adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"

        //206 Se recupera el espacio dentro del layout en donde vamos a agregar la publicidad
        var lyAdsBanner = findViewById<LinearLayout>(R.id.lyAdsBanner)
        //207 Se le agrega el objeto publicidad
        lyAdsBanner.addView(adView)

        //208. Se pide a android que nos genere un anuncio
        val adRequest = AdRequest.Builder().build()
        //209. Y finalmente se carga el anuncio
        adView.loadAd(adRequest)
    }
    //210. La funcion getReadyAds carga la publicidad de tipo Intersticial
    private fun getReadyAds(){
        //212. La variable adRequest pide a androir crear el anuncio
        var adRequest = AdRequest.Builder().build()
        //27. Al preparar el anuncion se tiene que indicar que no esta descargado el anuncio
        unloadedAd = false

        //213 Se hace la carga del anuncion y se proporciona el id de nuestra apliacion
        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            //214. Se tiene dos casos, en caso de que el anuncio falle se ejecuta el siguiente codigo
            override fun onAdFailedToLoad(adError: LoadAdError) {
                //215. Se deja en null el objeto interstitial
                mInterstitialAd = null
            }
            //216. En caso de que se cargue el anuncio correctamente se ejecuta el siguiente codigo
            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                //217. Se le pasa al objeto interstitial el anuncion que se creo con la viariable adREquest
                mInterstitialAd = interstitialAd
            }
        })
    }
    //244. La funcion launchPayment se ejecuta cuando se hace click sobre el anuncio para volverse premium
    fun launchPaymentCard(v: View){
        callPayment()
    }
    //245 El codigo que nos da stripe en el archivo MyApp Tiene que ejecutarse antes que toodo el codigo de CheckoutActivity
    private fun  callPayment(){
        //246. La siguiente variable guarda la clave de pago ( La utilizada es de pruebas, para obtener una debemos registrarnos en stripe)
        var keyStripePayment = "pk_test_wk6O7Cc5k3McBIG2Hut2irGs"
        PaymentConfiguration.init (applicationContext, keyStripePayment)

        //247. Para llamar a otro activity se hace lo siguiente
        val intent = Intent(this, CheckoutActivity::class.java)
        //258. Al intent le pasamos como parametro el nivel en el que se encuentra el usuario, colocamos level como la clave con la que vamos a recuperar la informacion
        intent.putExtra("level", level)

        startActivity(intent)

    }
    //271. La funcion checkPremium revisa en la sharedPreferences si es que el usuario es premium
    private fun checkPremium(){
        //277. Recuperamos la informacion del sharedPreferences, en caso de obtener un null se recupera solamente false
        premium = sharedPreferences.getBoolean("PREMIUM", false)

        //278. EN caso de que no sea premium se inicializan los anuncios
        if (premium){
            primeraVez ++
            //288. Para los usuarios premium establecemos otro lastlevel mas alto
            LASTLEVEL = 14
            level = sharedPreferences.getInt("LEVEL", 1)
            //279. En caso de ser premium modificamos los aspectos visuales de la apliacion para los usurios premium
            var lyPremium = findViewById<LinearLayout>(R.id.lyPremium)
            lyPremium.removeAllViews()
            var lyAdsBanner = findViewById<LinearLayout>(R.id.lyAdsBanner)
            lyAdsBanner.removeAllViews()
            var svGame = findViewById<ScrollView>(R.id.svGame)
            svGame.setPadding( 0, 0, 0, 0)

            var tvLiveData = findViewById<TextView>(R.id.tvLiveData)
            tvLiveData.background = getDrawable(R.drawable.bg_data_bottom_contrast_premium)
            var tvLiveTittle = findViewById<TextView>(R.id.tvLivesTitle)
            tvLiveTittle.background = getDrawable(R.drawable.bg_data_top_contrast_premium)

            var vNewBonus = findViewById<View>(R.id.vNewBonus)
            vNewBonus.setBackgroundColor(ContextCompat.getColor(this,
                resources.getIdentifier("contrast_data_premium", "color", packageName)))

            nameColorBlack = "black_cell_premium"
            nameColorWhite = "white_cell_premium"

            optionBlack = R.drawable.option_black_premium
            optionWhite = R.drawable.option_white_premium

        }
        //199. Se llama al metodo initAds()
        else if (resume == 0 )initAds()

    }

    //218 La funcion showIntertitial sera la encargada de mostrar el anuncio previamente cargado por getReadyAds
    private fun showIntertitial(){
        //219. Si se cargo correctamente el anuncion, el objeto Interstitial dejara de ser null, en tal caso se mostrara el anuncion con el metodo .show()
        if (mInterstitialAd != null) {

            //30. cuando ya se mostro el anuncion se descarga se indica que ya se ocupo el anuncio cargado
            unloadedAd = true

            //220. Con el fullScreenContentCallback se gestionan eventos relacionados con la visualizacion del Interstitial, si se quiere ir viendo el registo de lo que pasa con nuestro anuncion por medio del LOG
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

    //177. La funcion launchAction se ejecuta cuando se cumple el evento del tvAction
    fun launchAction(v: View){
        //291. Comprobamos si es el ultimo nivel y si es premiun o no
        if (level > LASTLEVEL && !premium) callPayment()
        //178. Se oculta el mensaje de gameOver o You Win y se le coloca un true par que lance nuevamente el juego
        hideMessage(true)
    }

    //152. La funcion launchShareGame llama a la funcion shere game que es la que se encrga de comporatir una camptura de pantall
    fun launchShareGame(v: View){
        //153. Se llama a la funcion shareGame()
        shareGame()
    }
    //154. La funcion shareGame toma una captura de pantalla y la puede compartir
    private fun shareGame(){
        //155. Necesitamos pedir permisos para leer y guardar informacion en el dispositivo
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)

        //157. Creamos una variable la cual nos ayudara a hacer la captura de pantalla, y le asociamos una vista la cual sera el activity entero
        var ssc: ScreenCapture = capture(this)
        //159. Le guardamos la captura al bitmap
        bitmap = ssc.bitmap

        //160 Verificamos que la captura no tuvo errores
        if (bitmap != null){
            //161 Para guardar la captura en el archivo establecemos un nombre a la captura con la cual se va a guardar, le vamos a colocar el nombre de la fecha ya que este no se repetira
            var idGame = SimpleDateFormat("yyyy/MM/DD").format(Date())
            //162. REemplazamos los siguientes caracteres ya que peuden causar problemas
            idGame.replace(":", "")
            idGame.replace("/", "")

            //163. Establecemos una ruta a la imagen con la funcion saveImage
            val path = saveImage(bitmap, "${idGame}.jpg")
            //164. Tambien vamos a crear un URI(identificador de recurso unico)
            val bmpUri = Uri.parse(path)

            //165. Se crea un Intent el cual sera de tipo ActionSend para enviar
            val shareIntent = Intent(Intent.ACTION_SEND)
            //166. Hacemos una nueva tarea
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            //167. Indicamos que es lo que estamos enviando
            shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri)
            //168. Hacemos referencia a una frase con la que queremos enviarla
            shareIntent.putExtra(Intent.EXTRA_TEXT, string_share)
            //169. Establecemso el tipo de archivo
            shareIntent.type = "image/png"

            //173. Cramos otro intent el cual se encargara de dar a elegir al usaurio que aplicacion querie para compartir
            var finalSahreIntent = Intent.createChooser(shareIntent, "Select the app you want to share the game to")
            //174. Hacemos una nueva tarea
            finalSahreIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            //175. Lanzamos finalmente el intent
            this.startActivity(finalSahreIntent)
        }

    }
    //176. la funcion saveImage nos ayuda a guardar los datos de una imagen en una archivo, no se expica ya que es muy funcional se puede encotrar facilmente
    private fun saveImage(bitmap: Bitmap?, fileName: String): String?{
        if (bitmap == null)
            return null

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q){
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/Screenshots")
            }

            var uri = this.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            if (uri != null){
                this.contentResolver.openOutputStream(uri).use {
                    if (it == null)
                        return@use

                    bitmap.compress(Bitmap.CompressFormat.PNG, 85, it)
                    it.flush()
                    it.close()

                    //add pic to galery
                    MediaScannerConnection.scanFile(this, arrayOf(uri.toString()), null, null)
                }
            }
            return uri.toString()
        }

        val filePath = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES + "/Screenshots"
        ).absolutePath

        var dir = File(filePath)
        if (!dir.exists()) dir.mkdirs()
        val file = File(dir, fileName)
        val fOut = FileOutputStream(file)

        bitmap.compress(Bitmap.CompressFormat.PNG, 85, fOut)

        fOut.flush()
        fOut.close()

        //add pic to galery
        MediaScannerConnection.scanFile(this, arrayOf(file.toString()), null, null)
        return filePath

    }


    //59. Creamos el metodo para el evento Onclick llamado checkCellClicked
    fun checkCellClicked(v: View){
        //60. Recuperamos el tag del view que nos pasaron
        var name = v.tag.toString()
        //61. Guardamos en una varibale la fila del x
        var x = name.substring(1, 2).toInt()
        var y = name.substring(2,3).toInt()

        //62. Llamamos a la funcion checkCell pasandole x y y
        checkCell(x, y)
    }
    //62. La funcion checkCell verifica que podamos movernos a la casilla seleccionada
    private fun checkCell(x: Int, y: Int){
        //63. Creamos las variables que guardaran la diferencia del movimiento seleccionado y el anterior
        var dif_x = x - cellSelected_x
        var dif_y = y - cellSelected_y

        //64. Cramos una variable para guardar la decision y la inicializamos en true por si no hay que checar el movimiento
        var checkTrue = true

        //122. Comprobamos si se tienen que checar el movmiento
        if (checkMovement){
            // El checkTrue lo dejamos en fales al iniciio por si ninguna de las siguientes comprobaciones es correcta
            checkTrue = false
            if (dif_x == 1 && dif_y == 2) checkTrue = true // right -top long
            if (dif_x == 1 && dif_y == -2) checkTrue = true // right -bottom long
            if (dif_x == 2 && dif_y == 1) checkTrue = true // right long - top
            if (dif_x == 2 && dif_y == -1) checkTrue = true // right long - bottom
            if (dif_x == -1 && dif_y == 2) checkTrue = true // left - top Long
            if (dif_x == -1 && dif_y == -2) checkTrue = true // left - bottom Long
            if (dif_x == -2 && dif_y == 1) checkTrue = true // left long - top
            if (dif_x == -2 && dif_y == -1) checkTrue = true // left long - bottom
        }else{
            //123. Comprobamos si la casilla no esta ocupada, en tal caso podemos movernos a cualquier casilla no ocupada
            if (board[x][y] != 1){
                bonus--
                //124. Restamos un bonus y lo mostramos por pantalla
                var tvBonusData = findViewById<TextView>(R.id.tvBonusData)
                tvBonusData.text = " +  $bonus"
                if (bonus == 0) tvBonusData.text = ""
            }
        }


        //65. Tambien verificamos que la casilla seleccionada no este ya marcada en el array board
        if (board[x][y] == 1) checkTrue = false

        //66. Si checkTrue es verdadero ahora si llamaremos la funcio selectCell
        if(checkTrue) selectCell(x, y)

    }
    //43. La funcion selectCell pinta el caballor donde se le indique y marca la casilla con el color selected_cell
    private fun selectCell(x: Int, y: Int){
        //93. Le restamos una unidad a moves
        moves--
        //94. mostramos en el Textview de Moves la cantidad de movimientos que nos queda
        var tvMovesNumber: TextView = findViewById(R.id.tvMovesNumber)
        tvMovesNumber.text = moves.toString()

        //109. Se llama a la funcino growProgressBarBonus la cual va a modificar el progreso del la barra view
        growProgressBarBonus()

        //107. Vamos a compronbar que en la casilla del array se tenga un 2, en tal caso se va a aumentar la variable bonus y se muestra enla pantalla el bonus
        if (board[x][y] == 2){
            bonus++
            var tvBonusData = findViewById<TextView>(R.id.tvBonusData)
            tvBonusData.text = " + $bonus"
            //295. Si se a agrrado un bonus, se repoduce el sonido del bonus
            mpBonus.start()
        }
        else{
            //296. En caso contrario solamente se ha hecho un movimiento por ello se repoduce el sonido de movimiento
            mpMovement.start()
        }

        //58. Coloamos el valor 1 en la valor del array que le corresponde a la casilla x, y
        board[x][y] = 1

        //52. Antes de pintar la celda con el color selected_cell, pintamos la anterior celda con el color previus_cell
        paintHorseCell(cellSelected_x, cellSelected_y, "previus_cell")

        //53. Les cambiamos el valor a las variables cellSelected
        cellSelected_x = x
        cellSelected_y = y

        //84. Llamamos a la funcion clearOptions para quitar la marca de las posiciones posibles que se marcaron en el movieminto anterior
        clearOptions(x, y)

        //44. Mandamos llamar la funcion paintHorseCell la cual recibe una posicion y un color
        paintHorseCell(x, y, "selected_cell")

        //124. Colocamos el checkMovement nuevamente en true una vez se ha ocupado el bonus
        checkMovement = true

        //67. Llamamos a la funcion checkOptions la cual verifica las posiciones posibles en el punto en el que nos encontramos y las pintara
        checkOptions(x, y)

        //95. Comprobamos constantemente si la cantidad de movimientos es mayor que 0
        if (moves > 0){
            //96. Llamamos a la funcion checkNewBonus la cual colocara un bonus en el tablero si es que toca
            checkNewBonus()
            //97. Llamamos tambien a la funcion game Over si es que se quedo sin opciones para moverse
            checkGameOver()
        }else{
            //98. En caso contrario llamamos a la funcion showMessage para que muestre el mesaje de que gano el juego
            var youWin = resources.getString(R.string.youWin)
            var nextLevel = resources.getString(R.string.nextLevel)
            showMessage(youWin, nextLevel, false)
        }

    }


    //56. La funcion resetBoard hara que el array board se inicialice en 0`s
    private fun resetBoard(){
        /*57. Los valores dentro del array representaran lo siguiente
                0 = esta libre
                1 = casilla marcada
                2 = Es bonus
                9 = Es una opcion del movimiento actual
         */
        board = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0)
        )
    }
    //131. La funcion clearBoard Quita todos los caballos que haya en el tablero y regresa a su color normal las casillas
    private fun clearBoard(){
        var iv: ImageView

        //132 Guardamos loc colores blanco y negro
        var colorBlack = ContextCompat.getColor(this, resources.getIdentifier(nameColorBlack, "color", packageName))
        var colorWhite = ContextCompat.getColor(this, resources.getIdentifier(nameColorWhite, "color", packageName))
        //133 Recorremos el tablero para ir colocando sus colores
        for (i in 0..7){
            for (j in 0..7){
                iv = findViewById(resources.getIdentifier("c$i$j", "id", packageName))
                //134Para quitar la imagen del caballor
                iv.setImageResource(0)
                if (checkColorCell(i, j) == "black") iv.setBackgroundColor(colorBlack)
                else iv.setBackgroundColor(colorWhite)
            }
        }

    }
    //40 La funcion setfirstPosition coloca el el horse en una posicion aleatoria
    private fun setFirstPosition(){
        //41 Para generar los indices aleatorios hacemos lo siguiente
        var x: Int = 0
        var y: Int = 0

        //194. Para evitar colocar un caballo en una casiila ya ocupada se hace lo siguiente
        var firstPosition = false
        while (firstPosition == false){
            x = (0..7).random()
            y = (0..7).random()
            if (board[x][y] == 0) firstPosition = true
            checkOptions(x, y)
            if (options == 0) firstPosition = false

        }

        //51. Le pasamos los valores de x y y a las variables cellSeleted, ya que cada que cambie la posicion se les debe pasar
        cellSelected_x = x
        cellSelected_y = y

        //42. Mandamos a llamar a la funcion selectCell y le pasamos los valores x e y
        selectCell(x, y)

    }


    //184. La funcion setLevel verifica si la variable nextLevel es true en tal caso aumenta el numero de vidas y las muestra y en caso contrario resta las vidas
    private fun setLevel(){
        if (nextLevel){
            level++
            if (premium){
                editor.apply {
                    putInt("LEVEL", level)
                }.apply()
            }
            setLives()
        }else{
            //282. Si el usuario no es premium si se hacen la siguietes comprobaciones
            if(!premium){
                lives--
                if (lives < 1){
                    level = 1
                    lives = 1
                }
            }
        }
    }
    private fun setLives(){
        when(level){
            1 -> lives = 1
            2 -> lives = 4
            3 -> lives = 3
            4 -> lives = 3
            5 -> lives = 4
            6 -> lives = 3
            7 -> lives = 5
            8 -> lives = 3
            9 -> lives = 4
            10 -> lives = 5
            11 -> lives = 5
            12 -> lives = 3
            13 -> lives = 4
        }
        //281. Si el usuario es premium se le establecen muchas vidas
        if (premium) lives = 9999999
    }
    //185. La funcion setLevelParameters establece los parametros del nivel
    private fun setLevelParameters(){
        //186. Se muestra al usuario los parametros
        var tvLiveData = findViewById<TextView>(R.id.tvLiveData)
        tvLiveData.text = lives.toString()
        //283. Si el usuario es premium se le muestran vidas infinitas al usuario
        if (premium) tvLiveData.text = "∞"

        var tvLevelNumber = findViewById<TextView>(R.id.tvLevelNumber)
        tvLevelNumber.text = level.toString()


        bonus = 0
        var tvBonusData = findViewById<TextView>(R.id.tvBonusData)
        tvBonusData.text = ""

        //187.LLamamamos a la funcion setLevelMoves para establcer el numero de moviemitnos de acuerdo al nivel
        setLevelMoves()
        moves = levelMoves

        //187. Lo mismo con los los movientos requeridos en el nivel para el bonus
        movesRequired = setMovesRequired()

    }
    //189 La funcion setLevelMoves indica cuantos movimientos tiene el jugador en el nivel en el que se encuentra
    private fun setLevelMoves(){
        //190. Simplemente se llama a un when el cual establece el numero de movientos dependiendo el nivel
        when(level){
            1 -> levelMoves = 64
            2 -> levelMoves = 56
            3 -> levelMoves = 32
            4 -> levelMoves = 16
            5 -> levelMoves = 48
            6 -> levelMoves = 59
            7 -> levelMoves = 28
            8 -> levelMoves = 52
            9 -> levelMoves = 44
            10 -> levelMoves = 49
            11 -> levelMoves = 51
            12 -> levelMoves = 48
            13 -> levelMoves = 49
        }
    }
    //191. La funcion setMovesRequired tambien indica el numero de movimientos requeridos para el bonus
    private fun setMovesRequired(): Int{
        var movesRequired = 0
        when(level){
            1 -> movesRequired = 8
            2 -> movesRequired = 10
            3 -> movesRequired = 12
            4 -> movesRequired = 10
            5 -> movesRequired = 10
            6 -> movesRequired = 12
            7 -> movesRequired = 5
            8 -> movesRequired = 7
            9 -> movesRequired = 9
            10 -> movesRequired = 8
            11 -> movesRequired = 1000
            12 -> movesRequired = 5
            13 -> movesRequired = 5
        }
        return movesRequired

    }
    //192. La funcion setBoardLevel pintara el tablero diferente dependiendo del nivel en el que nos encontremos
    private fun setBoardLevel(){
        when(level){
            2 -> paintLevel_2()
            3 -> paintLevel_3()
            4 -> paintLevel_4()
            5 -> paintLevel_5()
            6 -> paintLevel_6()
            7 -> paintLevel_7()
            8 -> paintLevel_8()
            9 -> paintLevel_9()
            10 -> paintLevel_10()
            11 -> paintLevel_11()
            12 -> paintLevel_12()
            13 -> paintLevel_13()
        }
    }
    //193. La funcion paint_columns pinta toda una columa con caballos
    private fun paint_Column(column: Int){
        for (i in 0..7){
            board[column][i] = 1
            paintHorseCell(column, i, "previus_cell")

        }
    }
    //194. La funcion paint_Row pinta toda una fila con caballos
    private fun paint_Row(row: Int){
        for (i in 0..7){
            board[i][row] = 1
            paintHorseCell(i, row, "previus_cell")
        }
    }
    //195. La funcion paint_Diagonal pinta parte de una diagonal con caballos
    private fun paint_Diagonal(diagonal: Int){
        var x = diagonal -1
        var y = diagonal -1
        while (x < 7 && y < 7){
            x ++
            y ++
            board[x][y] = 1
            paintHorseCell(x, y, "previus_cell")
        }
    }
    //196. Las funciones de los niveles mezclando las funciones anteriores para pintar varias celdas con caballos
    private fun paintLevel_2(){
        paint_Column(6)
    }
    private fun paintLevel_3(){
        for (i in 0..7){
            for (j in 4..7){
                board[i][j] = 1
                paintHorseCell(i, j, "previus_cell")
            }
        }
    }
    private fun paintLevel_4(){
        paintLevel_3(); paintLevel_5()
    }
    private fun paintLevel_5(){
        for (i in 0..3){
            for (j in 0..3){
                board[i][j] = 1
                paintHorseCell(i, j, "previus_cell")
            }
        }
    }
    private fun paintLevel_6(){
        paint_Diagonal(3)
    }
    private fun paintLevel_7(){
        paintLevel_3(); paintLevel_2()
    }
    private fun paintLevel_8(){
        paint_Diagonal(3); paint_Diagonal(4)
        paint_Column(3)
    }
    private fun paintLevel_9(){
        paintLevel_5(); paint_Column(2)
    }
    private fun paintLevel_10(){
        paint_Row(3); paint_Column(3)
    }
    private fun paintLevel_11(){
        paint_Diagonal(3); paint_Column( 1)
    }
    private fun paintLevel_12(){
        paint_Row(3); paint_Row( 6)
    }
    private fun paintLevel_13(){
        paint_Row(4); paint_Column(4)
    }

    //100. La funcion  checkSucessfulEnd verifica si es momento de colocar un nuevo bonus y de ser asi lo coloca
    private fun checkNewBonus(){
        //101. Verificamos si la cantidad de moviemintos es multiplo de movesRequired
        if (moves % movesRequired == 0){
            //102. Generamos unas coordenadas aleatorias
            var bonusCell_x = 0
            var bonusCell_y = 0
            //103. Verificamos constantemente si las coordenadas estan disponibles
            var bonusCell = false
            while (bonusCell == false){
                bonusCell_x = (0..7).random()
                bonusCell_y = (0..7).random()
                if (board[bonusCell_x][bonusCell_y] == 0) bonusCell = true
            }
            //104. Cambiamos el valor de la casilla a 2 en el array
            board[bonusCell_x][bonusCell_y] = 2
            //105. Colocamos el dibujo del bonus en el tablero con la funcion paintBonusCell
            paintBonusCell(bonusCell_x, bonusCell_y)
        }
    }
    //106. La funcion paintBonusCell dibuja un bonus en la posicion indicada
    private fun paintBonusCell(x: Int, y: Int){
        var iv: ImageView = findViewById(resources.getIdentifier("c$x$y", "id", packageName))
        iv.setImageResource(R.drawable.benefit2)
    }
    //110. La funcion growProgressBarBonus aumenta el tamano de la barra hecha con un view que indica el progreso para el siguiente view
    private fun growProgressBarBonus(){
        var moves_done = levelMoves - moves
        var bonus_done = moves_done / movesRequired
        var moves_rest = movesRequired * (bonus_done)
        var bonus_grow = moves_done - moves_rest
        var widthBonus = ((widht_bonus/movesRequired) * bonus_grow).toFloat()

        var v = findViewById<View>(R.id.vNewBonus)

        var heigh = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8f, resources.displayMetrics).toInt()
        var width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, widthBonus, resources.displayMetrics).toInt()
        v.setLayoutParams(TableRow.LayoutParams(width, heigh))
    }


    //85. La funcion clearOptions recorre las posiciones del array board para buscar las casillas que estan marcadas con un 9 y desmarcarlas como posibles
    private fun clearOptions(x: Int, y: Int){
        for (i in 0..7){
            for (j in 0..7){
                if (board[i][j] == 9 || board[i][j] == 2){
                    //86. Debolvemos la casilla del array a 0 quitandole el 9 a menos de que sea 2 en tal caso se queda igual
                    if (board[i][j] == 9) board[i][j] = 0
                    //87. Llamamos a la funcion clearOption para que este devuelva el color original a la casilla
                    clearOption(i, j)
                }
            }
        }
    }
    //88. La funcion clearoption devuelve la casilla a su color original
    private fun clearOption(x: Int, y: Int){
        var iv: ImageView = findViewById(resources.getIdentifier("c$x$y", "id", packageName))
        //89 Verificamos si la casilla es blanca o negra con ayuda de la funcion checkColorCell
        if (checkColorCell(x, y) == "black") {
            iv.setBackgroundColor(ContextCompat.getColor(this, resources.getIdentifier(nameColorBlack, "color", packageName)))
        }else{
            iv.setBackgroundColor(ContextCompat.getColor(this, resources.getIdentifier(nameColorWhite, "color", packageName)))
        }
        //91. Si la casilla esta marcada con un 1 significa que ya esta ocupada asi que esta la pintamos con previus cell para que no se borre
        if (board[x][y] == 1){
            iv.setBackgroundColor(ContextCompat.getColor(this, resources.getIdentifier("previus_cell", "color", packageName)))
        }

    }
    //125. La funcion paintAllOptions pintara todas las casillas disponibles con el color de disponible
    private fun paintAllOptoins(){
        //126. Se recorre el array para saber que valor tienen
        for (i in 0..7){
            for (j in 0..7){
                if (board[i][j] != 1){
                    //127. llamamos al a fucnion paintOption para colocarle el color a la casilla de disponible y cambiamos el valor en el array de la casilla
                    paintOption(i, j)
                    if (board[i][j] == 0) board[i][j] = 9
                }
            }
        }
    }
    //79. La funcion paintOptions pinta la casilla disponible con un color diferente dependiendo si es blanca o negra la casilla
    private fun paintOption(x: Int, y: Int){
        //80. Recuperamos el ImageView correspondiente a la posicion
        var iv: ImageView = findViewById(resources.getIdentifier("c$x$y", "id", packageName))
        //81. Verificamos si la casilla es blanca o negra llamando a la funcion checkColorCell
        if (checkColorCell(x, y) == "black") iv.setBackgroundResource(optionBlack)
        else iv.setBackgroundResource(optionWhite)
    }


    //114. La funicon checkGameOver mostrara los mensajes Game over y tray again en el Linear layout que tienen la propiedad de visibility
    private fun checkGameOver(){
        var gameOver = resources.getString(R.string.gameOver)
        var tryAgain = resources.getString(R.string.tryAgain)
        //115 La funcion verifica si ya no se tienen mas movimientos y si no hay algun bonus
        if (options == 0){
            //116. si tampoco se tienen bonus, se llama a la funcion showMessage
            if (bonus > 0) {
                checkMovement = false
                paintAllOptoins()
            }
            else{
                showMessage(gameOver, tryAgain, true)
            }
        }

    }
    //117. La funcion showMessage mostrara el LinearLayout que tiene la propiedad de visibility
    private fun showMessage(title: String, action: String, gameOver: Boolean, endGame: Boolean = false){
        //149. Cambiamos el valro de gaming a false
        gaming = false
        nextLevel = !gameOver

        //118. Cambiamos la visibilidada de layout
        var lyMessage = findViewById<LinearLayout>(R.id.lyMessage)
        lyMessage.visibility = View.VISIBLE

        //119 Recuperamos los textView que corresponden a los mensajes dentor para cambiar su text
        var tvTitleMessage = findViewById<TextView>(R.id.tvTitleMessage)
        tvTitleMessage.text = title

        var tvtimeData = findViewById<TextView>(R.id.tvTimeData)

        //120. Preguntamos si es game over en tal caso cambiara el mensjae del score
        var score = ""
        if (gameOver){
            //297. Se reproduce el sonido del gameOver
            mpGameOver.start()

            //223. Se llama a la funcion showIntertitial
            //283. El anuncion se muestra solamente si no es premium
            if (!premium) showIntertitial()

            score = "Score: " + (levelMoves - moves) + "/" + levelMoves
            //170. Le establecemso la siguiente frase al string_share al cual se le puede incluir un strin de un link (pequno truquito ahi ee)
            string_share = "This game makes me sick!!! " + score + ") http://google.com"
        }else{
            score = tvtimeData.text.toString()
            //171. le establecemos otro mensaje en caso de perder
            string_share = "Let's go!!! New challenge completed. Level: $level (" + score + ") http://google.com"
        }
        if (endGame) score = ""

        var tvScoreMessage = findViewById<TextView>(R.id.tvScoreMessage)
        tvScoreMessage.text = score

        var tvAction = findViewById<TextView>(R.id.tvAction)
        tvAction.text = action
    }


    //68. La funcion checkOptions verifica las casillas disponibles en la posicion dada
    private fun checkOptions(x: Int, y: Int){
        //70. Iniciamos la variable options en 0 y a medida que tengamos una casilla disponible esta ira aumentando
        options = 0


        //71. Comprobamos cada uno de los moviemintos con la funcion checkMove() ya sea una posicion arriba y dos a la derecha etc...
        checkMove(x, y, 1, 2) //check move right - top long
        checkMove(x, y, 1, -2) //check move right - bottom long
        checkMove(x, y, 2, 1) //check move right long - top
        checkMove(x, y, 2, -1) //check move right long - bottom
        checkMove(x, y, -1, 2) //check move left - top long
        checkMove(x, y, -1, -2) //check move left - bottom long
        checkMove(x, y, -2, 1) //check move left long - top
        checkMove(x, y, -2, -1) //check move left long - bottom

        //Le pasamos el contador de options al TextView que muestra este numero
        var tvOptionsData: TextView = findViewById(R.id.tvOptionsData)
        tvOptionsData.text = options.toString()
    }
    //72. La funcion checkMove comprueba el movimiento dado en x y y con respecto a la posicion x y y que se tiene
    private fun checkMove(x: Int, y: Int, mov_x: Int, mov_y: Int){
        //73. Calculamos la siguiente casilla dependiendo del movimiento dado
        var option_x = x + mov_x
        var option_y = y + mov_y

        //74. Comprobamos que la posicion este dentro del tablero
        //todo ver si el codigo option_x in 0..7 se puede
        if (option_x < 8 && option_x >= 0 && option_y < 8 && option_y >= 0){
            //75. Verificamos que la posicion seleccionada en el tablero esta libre
            if (board[option_x][option_y] == 0 || board[option_x][option_y] == 2 ){
                //76 Aumentamos el valor de options
                options++
                //77 Pintamos la casilla para que se vea que esta disponible
                paintOption(option_x, option_y)
                //78. Le damos un nuevo valor a la casilla dentro del array
                if(board[option_x][option_y] == 0) board[option_x][option_y] = 9
            }
        }
    }
    //82. La funcion checkOptionCell regresa un string diciendo de que tipo de color es la casilla
    private fun checkColorCell(x: Int, y: Int): String{
        var color: String
        var blackColumn_x = arrayOf(0, 2, 4, 6)
        var blackRow_x = arrayOf(1, 3, 5, 7)
        //83. comprobamos si la casilla es negra de dos formas, donde x pertenece a la columna y y a la fila o al reves
        if((blackColumn_x.contains(x) && blackColumn_x.contains(y)) || (blackRow_x.contains(y) && blackRow_x.contains(x))){
            color = "black"
        }else color = "white"

        return color
    }


    //45. La funcion paintHorseCell pinta la celda que se le indique del color que se le indique
    private fun paintHorseCell(x: Int, y: Int, color: String){
        //46. Recuperamos el imageview utilizando el getIdentifier
        var iv: ImageView = findViewById(resources.getIdentifier("c$x$y", "id", packageName))
        //47. Le cambiamos el backgroundColor a la casilla
        //48. Al no tratarse de un archivo y de tener el nombre de la variable en un string, se utiliza lo siguiente
        iv.setBackgroundColor(ContextCompat.getColor(this,resources.getIdentifier(color, "color", packageName)))
        //49. Se puede establecer la ImageRerource con el siguiente metodo
        iv.setImageResource(R.drawable.horse)
    }


    //138. La funcion resetTime reiniciara el cronometro
    private fun resetTime(){
        mHandler?.removeCallbacks(chronometer)
        timeInSeconds = 0
    }
    //139. La funcion startTime iniciara el cronometro
    private fun startTime(){
        mHandler = Handler(Looper.getMainLooper())
        chronometer.run()
    }
    //140. Es necesario crear una variable de tipo Runnable para llevar la cuenta del tiempo
    private var chronometer: Runnable = object: Runnable{
        //141. Sobreescribimos la funcion run ya que esta variable es un ejecutable
        override fun run() {
            //142 Dentro de un try catch vamos a ir llevando la cuenta del tiempo y el intervalo de los segundos
            try {
                if (gaming){
                    timeInSeconds++
                }
                //144. Llamamos a la funcion updateStopWhatchView()
                updateStopWhatchView(timeInSeconds)
            }finally {
                //145. marcamos el intervalo de tiempo en el que se va a ejecutar este try el cual siempre va a estar en ejecucion
                mHandler!!.postDelayed(this, 1000L)
            }
        }
    }
    //146. La funcion updateStopWhatchView muestra al usuario el tiempo en la pantalla
    private fun updateStopWhatchView(timeInSeconds: Long){
        //147. Llamamos a la funcion getFormattedStopWhatch
        val millisenconds = getFormattedStopWhatch(timeInSeconds * 1000)
        //148. Cambiamos el valor del textView correspondiente
        var tvTimeData= findViewById<TextView>(R.id.tvTimeData)
        tvTimeData.text = millisenconds

    }
    //147. La funcion getFormattedStopWhatch regresa el tiempo en formato de reloj
    private fun getFormattedStopWhatch(ms: Long): String{
        var milliseconds = ms
        val minutes = TimeUnit.MILLISECONDS.toMinutes((milliseconds))
        milliseconds -= TimeUnit.MINUTES.toMillis(minutes)
        var seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)

        return "${if (minutes < 10) "0" else "" }$minutes:" +
                "${if (seconds < 10) "0" else ""}$seconds"
    }


    //129. La funcion startGame va a inicializar el resetear el array, limpiar el tablero y colocar la primera posicion
    private fun startGame(){
        //221 se tiene que gestionar la carga del anuncion, no se puede cargar si ya se ha cargado previamente
        //284. La carga de anuncios tambien dependeran de si el usuario es premium
        if (unloadedAd == true && !premium) getReadyAds()
        //180. Se establece el nivel del juego
        setLevel()

        level = 12

        //289. Comprobamos que el nivel en el que se encuentra el usuario no sea mayor al nivel accesible
        if (level >= LASTLEVEL ){
            var beatenGame = resources.getString(R.string.beatenGame)
            var waitLevels = resources.getString(R.string.waitLevels)
            var moreLevels = resources.getString(R.string.moreLevels)
            var getPremium = resources.getString(R.string.getPremium)
            //290. Dependiendo si es premium o no se mostrara un mensaje u otro
            if (premium) showMessage(beatenGame, waitLevels, false, true)
            else showMessage(moreLevels, getPremium, false, true)
        }else{
            //181. Se establecen los parametros del juego
            setLevelParameters()
            //55. mandamos llamar la funcion resetBoard()
            resetBoard()
            //130. Llamamos a la fucnion clearBoard()
            clearBoard()
            //182. Se establece el tablero de acuerdo al nivel
            setBoardLevel()
            //39. Se llama a la funcion setFirstPosition()
            setFirstPosition()

            //135. Llamamos a la funcion resetTime
            resetTime()
            //136. Llamamos al afuncion startTime
            startTime()
            //151. Cambiamos el valor de gaming a true
            gaming = true
        }
    }








}