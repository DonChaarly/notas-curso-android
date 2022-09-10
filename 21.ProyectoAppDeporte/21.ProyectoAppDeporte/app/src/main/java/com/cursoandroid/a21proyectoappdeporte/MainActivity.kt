package com.cursoandroid.a21proyectoappdeporte

import android.animation.ObjectAnimator
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.RoundedCorner
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import com.cursoandroid.a21proyectoappdeporte.LoginActivity.Companion.providerSession
import com.cursoandroid.a21proyectoappdeporte.LoginActivity.Companion.useremail
import com.cursoandroid.a21proyectoappdeporte.Utility.animatedViewofFloat
import com.cursoandroid.a21proyectoappdeporte.Utility.animatedViewofInt
import com.cursoandroid.a21proyectoappdeporte.Utility.getFormattedStopWatch
import com.cursoandroid.a21proyectoappdeporte.Utility.getSecFromWatch
import com.cursoandroid.a21proyectoappdeporte.Utility.setHeightConstraintLayout
import com.facebook.login.LoginManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import me.tankery.lib.circularseekbar.CircularSeekBar
import org.w3c.dom.Text

//117. Implementamos la interface ItemSelectedListener para especificar las acciones que se realizaran al interactuar con los elementos del menu
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    //110. Creamos una variable global que nos servira para administra el drawer layout
    private lateinit var drawer: DrawerLayout

    //146. Creamos las variables para administrar los switch
    private lateinit var swIntervalMode: Switch
    private lateinit var swChallenges: Switch
    private lateinit var swVolumes: Switch
    //147. Creamos variables para administrar los datos de distancia e intervalos que introduce el usuario
    private var challengeDistance: Float = 0f
    private var challengeDuration: Int = 0
    //157. Creamos variables para administra los numberPickers
    private lateinit var npChallengeDistance: NumberPicker
    private lateinit var npChallengeDurationHH: NumberPicker
    private lateinit var npChallengeDurationMM: NumberPicker
    private lateinit var npChallengeDurationSS: NumberPicker
    //171. Creamos una variable para administrar el elementos tvChrono
    private lateinit var tvChrono: TextView
    //176. Creamos una variable para administrar el elemento circularSeekBark scbRunWalk
    private lateinit var  csbRunWalk: CircularSeekBar
    //179. creamos variables para administrar el number picker de los intervalos y tambien pra los textos
    private lateinit var npDurationInterval: NumberPicker
    private lateinit var tvRunningTime: TextView
    private lateinit var tvWalkingTime: TextView
    //180 Creamos variables para administrar los circularSeekBar de hasta arriba
    private lateinit var csbChallengeDistance: CircularSeekBar
    private lateinit var csbCurrentDistance: CircularSeekBar
    private lateinit var csbRecordDistance: CircularSeekBar

    private lateinit var csbCurrentAvgSpeed: CircularSeekBar
    private lateinit var csbRecordAvgSpeed: CircularSeekBar

    private lateinit var csbCurrentSpeed: CircularSeekBar
    private lateinit var csbCurrentMaxSpeed: CircularSeekBar
    private lateinit var csbRecordSpeed: CircularSeekBar
    //181. Creamos variables para administrar los TextView de los records debajo de los seekbar
    private lateinit var tvDistanceRecord: TextView
    private lateinit var tvAvgSpeedRecord: TextView
    private lateinit var tvMaxSpeedRecord: TextView
    //192 Creamos variables para administrar el tiempo que toman los intervalos en segundos
    private var  ROUND_INTERVAL = 300
    private var TIME_RUNNING: Int = 0
    //206. Creamos una variable para administrar el constriantLayout del Popup
    private lateinit var clPopupRun: ConstraintLayout
    //209. Creamos variables para administra el ancho de la ventana
    private var widthScreenPixels: Int = 0
    private var heightScreenPixels: Int = 0
    private var widthAnimations: Int = 0
    //210 Creamos una variable mHandler la cual nos permitira ejecutar codigo siempre dependiendo del intervalo que le configuremos
    private var mHandler: Handler? = null
    //211. Creamos variables para administrar el tiempo en segundos y si se ha pulsado el boton de inicio de carrera
    private var  timeInSeconds = 0L
    private var startButtonClicked = false
    //214. Creamos una variable mInterval para administrar el tiempo de intervalos en el mHandler inicialmente 1000 milisegundos
    private var mInterval = 1000
    //244. Se crea una variable para administrar el boton de foto
    private lateinit var fbCamara: FloatingActionButton
    //248. Creamos una variable para llevar cuenta de las rondas
    private var rounds: Int = 1
    //262. Creamos variables de tipo MediaPlayer para administrar el sonido de nuestra app
    private var mpNotify: MediaPlayer? = null
    private var mpHard: MediaPlayer? = null
    private var mpSoft: MediaPlayer? = null
    //263. Creamos variables para administrar los seekBar de las canciones
    private lateinit var sbHardVolume : SeekBar
    private lateinit var sbSoftVolume : SeekBar
    private lateinit var sbNotifyVolume : SeekBar
    //283. Creamos una variable que administre el estado del intervalo
    private var running = true




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initToolBar()
        initObjetcts()
        initNavigationView()

    }

    //127. La funcion onBackPressed gestiona lo que se realiza cuando se da click en el boton de hacia atras el celular
    override fun onBackPressed() {
        //super.onBackPressed()

        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START)
        }else{
            signOut()
        }
    }

    //173. La funcion initStopWatch que colcoara la cadena de 00:00:00 al cronometro
    private fun initStopWatch(){
        tvChrono.text = getString(R.string.init_stop_watch_value)
    }
    //185. Cramos varias funciones init para separar el codigo y manterlo mas ordenado
    private fun initChrono(){
        tvChrono = findViewById(R.id.tvChrono)
        tvChrono.setTextColor(ContextCompat.getColor(this, R.color.white))
        //172. Inicializamos el valor del cronometro con la funcion initStopWatch
        initStopWatch()

        widthScreenPixels = resources.displayMetrics.widthPixels
        heightScreenPixels = resources.displayMetrics.heightPixels
        widthAnimations = widthScreenPixels


        //208. Inicializamos los view del chrono
        val vChronoProgressBar = findViewById<ProgressBar>(R.id.vChronoProgressBar)
        val vRoundProgressBar = findViewById<ProgressBar>(R.id.vRoundProgressBar)
        vChronoProgressBar.progress = 0
        vRoundProgressBar.progress = 0

        //234. Administramos el click sobre el boton Finalizar
        val tvReset = findViewById<TextView>(R.id.tvReset)
        tvReset.setOnClickListener{ resetClicked()}

        //245. Inicializamos el boton de la camara
        fbCamara = findViewById(R.id.fbCamera)
        fbCamara.isVisible = false

    }
    private fun hideLayouts(){
        //131. recuperamos todos los constraint layout y groups
        val clFragmentMap = findViewById<ConstraintLayout>(R.id.clFragmentMap)
        val clIntervalMode = findViewById<ConstraintLayout>(R.id.clIntervalMode)
        val clChallenges = findViewById<ConstraintLayout>(R.id.clChallenges)
        val clVolumes = findViewById<ConstraintLayout>(R.id.clVolumes)
        val gSoftVolumes = findViewById<androidx.constraintlayout.widget.Group>(R.id.gSoftVolumen)
        val gSoftTrack = findViewById<androidx.constraintlayout.widget.Group>(R.id.gSoftTrack)
        //132. Confiuramos los atributos iniciales, colocamos 1 ya que si colocamos 0 es como colocar wrapContent
        setHeightConstraintLayout(clFragmentMap, 1)
        clFragmentMap.translationY = -300f
        setHeightConstraintLayout(clIntervalMode, 1)
        clIntervalMode.translationY = -300f
        setHeightConstraintLayout(clChallenges, 1)
        clChallenges.translationY = -600f
        setHeightConstraintLayout(clVolumes, 1)
        clVolumes.translationY = -300f
        gSoftVolumes.visibility = View.GONE
        gSoftTrack.visibility = View.GONE
    }
    private fun initMetrics(){
        //182. Inicializamos los TextView
        tvDistanceRecord = findViewById(R.id.tvDistanceRecord)
        tvAvgSpeedRecord = findViewById(R.id.tvAvgSpeedRecord)
        tvMaxSpeedRecord = findViewById(R.id.tvMaxSpeedRecord)

        tvDistanceRecord.text = ""
        tvAvgSpeedRecord.text = ""
        tvMaxSpeedRecord.text = ""

        //183. Inicializamos los circularSeekBars
        csbCurrentDistance = findViewById(R.id.csbCurrentDistance)
        csbChallengeDistance = findViewById(R.id.csbChallengeDistance)
        csbRecordDistance = findViewById(R.id.csbRecordDistance)
        csbCurrentAvgSpeed = findViewById(R.id.csbCurrentAvgSpeed)
        csbRecordAvgSpeed = findViewById(R.id.csbRecordAvgSpeed)
        csbCurrentSpeed = findViewById(R.id.csbCurrentSpeed)
        csbCurrentMaxSpeed = findViewById(R.id.csbCurrentMaxSpeed)
        csbRecordSpeed = findViewById(R.id.csbRecordSpeed)

        csbCurrentDistance.progress = 0f
        csbChallengeDistance.progress = 0f
        csbCurrentAvgSpeed.progress = 0f
        csbCurrentSpeed.progress = 0f
        csbCurrentMaxSpeed.progress = 0f
    }
    private fun initSwitchs(){
        //Inicializamos los Switch
        swIntervalMode = findViewById(R.id.swIntervalMode)
        swChallenges = findViewById(R.id.swChallenges)
        swVolumes = findViewById(R.id.swVolumes)
    }
    private fun initIntervalMode(){
        npDurationInterval = findViewById(R.id.npDurationInterval)
        tvRunningTime = findViewById(R.id.tvRunningTime)
        tvWalkingTime = findViewById(R.id.tvWalkingTime)
        csbRunWalk = findViewById(R.id.csbRunWalk)
        csbRunWalk.max = 300f
        csbRunWalk.progress = 150f
        //187. Inicializamos los valores del numberPicker
        npDurationInterval.minValue = 1
        npDurationInterval.maxValue = 60
        npDurationInterval.value = 5
        npDurationInterval.wrapSelectorWheel = true
        //188. Formateamos como se muestran los numeros en el numberPicker
        npDurationInterval.setFormatter(NumberPicker.Formatter { i -> String.format("%02d", i) })
        //189. Con un listener ejecutamos un codigo cada que cambia el valor del number Pikcer
        npDurationInterval.setOnValueChangedListener { numberPicker, oldValue, newVal ->
            //190. Establecemos el maximo del circularSeekBar dependiendo del valor que eligamos en el numberPikcer
            csbRunWalk.max = (newVal * 60).toFloat()
            csbRunWalk.progress = csbRunWalk.max/2

            //191. Obtenemos el formato en string del tiempo que tendremos en el circularSeekBar y se lo colocamos al los TextView que muestran estos timepo
            tvRunningTime.text = getFormattedStopWatch(((newVal * 60 / 2) * 1000).toLong()).subSequence(3, 8)
            tvWalkingTime.text = tvRunningTime.text

            //193. Le establecemos el valor al round_interval
            ROUND_INTERVAL = newVal * 60

        }

        //177. Administramos el comportamiento del circularSeekBar con un setOnSeekBarChangeListener
        csbRunWalk.setOnSeekBarChangeListener(object : CircularSeekBar.OnCircularSeekBarChangeListener{
            override fun onProgressChanged(circularSeekBar: CircularSeekBar?, progress: Float, fromUser: Boolean) {

                if (fromUser){
                    //178. El profesor creo un algoritmo para esto, Nota: Aprender a hacer algoritmos
                    var STEP_UX: Int = 15
                    if (ROUND_INTERVAL > 600) STEP_UX = 60
                    if (ROUND_INTERVAL > 1800) STEP_UX = 300

                    var set: Int = 0
                    var p = progress.toInt()

                    var limit = 60
                    if (ROUND_INTERVAL > 1800) limit = 300

                    if(p%STEP_UX != 0 && progress != csbRunWalk.max){
                        while (p >= limit) p -= limit
                        while (p >= STEP_UX) p -= STEP_UX
                        if (STEP_UX - p > STEP_UX/2) set = -1 * p
                        else set = STEP_UX - p

                        if (csbRunWalk.progress + set > csbRunWalk.max) {
                            csbRunWalk.progress = csbRunWalk.max
                        }
                        else csbRunWalk.progress = csbRunWalk.progress + set
                    }
                }
                //233. Evaluamos si el progressBar esta en cero, si es asi se desabilitan los botones ya que no debe haber intervalos en 0
                if (csbRunWalk.progress == 0f || csbRunWalk.progress == csbRunWalk.max) {
                    manageEnableButtonsRun(false, false)
                }
                else manageEnableButtonsRun(false,true)

                //204. Actualizamos el texto de los TextView que muestra el tiempo
                tvRunningTime.text = getFormattedStopWatch((csbRunWalk.progress.toInt() * 1000).toLong()).subSequence(3, 8)
                tvWalkingTime.text = getFormattedStopWatch(((ROUND_INTERVAL - csbRunWalk.progress.toInt()) * 1000).toLong()).subSequence(3, 8)
                //205. Actualizamos la variable con la que administramos el tiempo
                TIME_RUNNING = getSecFromWatch(tvRunningTime.text.toString())

            }
            override fun onStopTrackingTouch(seekBar: CircularSeekBar?) {
            }
            override fun onStartTrackingTouch(seekBar: CircularSeekBar?) {
            }
        })
    }
    private fun initChallengeMode(){
        //158. Inicializamos los numberPickers
        npChallengeDistance = findViewById(R.id.npChallengeDistance)
        npChallengeDurationHH = findViewById(R.id.npChallengeDurationHH)
        npChallengeDurationMM = findViewById(R.id.npChallengeDurationMM)
        npChallengeDurationSS = findViewById(R.id.npChallengeDurationSS)

        //198. Configuramos los valore del numberPikcer
        npChallengeDistance.minValue = 1
        npChallengeDistance.maxValue = 300
        npChallengeDistance.value = 10
        npChallengeDistance.wrapSelectorWheel = true
        //199. Confiugramos el evento OnValueChange
        npChallengeDistance.setOnValueChangedListener { numberPicker, oldVal, newVal ->
            //200 Le establecemos un nuevo valor a la variable challengeDistance con el valor del np
            challengeDistance = newVal.toFloat()
            //201. Le establecemos nuevos valores al seekBar
            csbChallengeDistance.max = newVal.toFloat()
            csbChallengeDistance.progress = newVal.toFloat()
            challengeDuration = 0
            //201. preguntamos si el objetivo es mas grande que el recordpersonal actual y acutalizamos el maximo valor del currentDistance
            if (csbChallengeDistance.max > csbRecordDistance.max) csbCurrentDistance.max = csbChallengeDistance.max

            //202. Configuramos los valores de los numberPickers de Duracion
            npChallengeDurationHH.minValue = 0
            npChallengeDurationHH.maxValue = 23
            npChallengeDurationHH.value = 1
            npChallengeDurationHH.wrapSelectorWheel = true
            npChallengeDurationHH.setFormatter(NumberPicker.Formatter { i -> String.format("%02d", i) })

            npChallengeDurationMM.minValue = 0
            npChallengeDurationMM.maxValue = 59
            npChallengeDurationMM.value = 0
            npChallengeDurationMM.wrapSelectorWheel = true
            npChallengeDurationMM.setFormatter(NumberPicker.Formatter { i -> String.format("%02d", i) })

            npChallengeDurationSS.minValue = 0
            npChallengeDurationSS.maxValue = 59
            npChallengeDurationSS.value = 0
            npChallengeDurationSS.wrapSelectorWheel = true
            npChallengeDurationSS.setFormatter(NumberPicker.Formatter { i -> String.format("%02d", i) })

            //203. Administramos el comportamiento de los numberPicker para actualizar el valor de la variable challengeDuration con ayuda del metodo getChallengeDuration
            npChallengeDurationHH.setOnValueChangedListener { picker, oldVal, newVal ->
                getChallengeDuration(newVal, npChallengeDurationMM.value, npChallengeDurationSS.value)
            }
            npChallengeDurationMM.setOnValueChangedListener { picker, oldVal, newVal ->
                getChallengeDuration(npChallengeDurationHH.value, newVal, npChallengeDurationSS.value)
            }
            npChallengeDurationSS.setOnValueChangedListener { picker, oldVal, newVal ->
                getChallengeDuration(npChallengeDurationHH.value, npChallengeDurationMM.value, newVal)
            }

        }

    }
    private fun initMusic(){
        //264. Inicializamos los media player
        mpNotify = MediaPlayer.create(this, R.raw.notificacion)
        mpHard = MediaPlayer.create(this, R.raw.music_corriendo)
        mpSoft = MediaPlayer.create(this, R.raw.music_caminando)

        //265. Especificamos que la cancion pueda estar en un loop, que no se pare cuando acabe
        mpSoft?.isLooping = true
        mpHard?.isLooping = true

        mpSoft?.start()
        mpSoft?.pause()
        mpHard?.start()
        mpHard?.pause()

        //266. Inicializamos los seekBar
        sbHardVolume = findViewById(R.id.sbHardVolume)
        sbSoftVolume = findViewById(R.id.sbSoftVolume)
        sbNotifyVolume = findViewById(R.id.sbNotifyVolume)

        setVolumes()
        setProgressTracks()
    }
    private fun hidePopUpRun(){
        //207. Inicializamos el constraintLayout del popup
        clPopupRun = findViewById<ConstraintLayout>(R.id.clPopupRun)
        clPopupRun.translationX = -600f
        clPopupRun.isVisible = false

    }
    //128. La funcion initObjetcts configura el estdo inicial de la vista
    private fun initObjetcts(){
        initChrono()
        hideLayouts()
        initMetrics()
        initSwitchs()
        initIntervalMode()
        initChallengeMode()
        initMusic()
        hidePopUpRun()
    }

    //133. La funcion callShowHideMap se ejecuta cuando se da click sobre el boton de mapa
    fun callShowHideMap(v: View){
        val clFragmentMap = findViewById<ConstraintLayout>(R.id.clFragmentMap)

        //134. Preguntamos la altura que tiene el constraintLayout del mapa
        if (clFragmentMap.height == 1){
            setHeightConstraintLayout(clFragmentMap, 1160)
            ObjectAnimator.ofFloat(clFragmentMap, "translationY", 0f).apply {
                duration = 500
                start()
            }
        }else{
            setHeightConstraintLayout(clFragmentMap, 1)
            clFragmentMap.translationY = -300f
        }
    }

    //167. La funcion inflateInervalMode se ejecuta cuando se da click sobre el switch de intervalos
    fun inflateIntervalMode(v: View){
        //168. Recuperamos todos los elementos que van a ser modificados
        val clIntervalMode = findViewById<ConstraintLayout>(R.id.clIntervalMode)
        val tvRounds = findViewById<TextView>(R.id.tvRounds)
        val gSoftVolume = findViewById<androidx.constraintlayout.widget.Group>(R.id.gSoftVolumen)
        val gSoftTrack = findViewById<androidx.constraintlayout.widget.Group>(R.id.gSoftTrack)

        //169. Preguntamos si el switch se ha checado
        if (swIntervalMode.isChecked){
            //170. Cambiamos el color del switch, la altura del constraintLayout, la transalacion en Y del IntevalMode
            animatedViewofInt(swIntervalMode, "textColor", ContextCompat.getColor(this, R.color.orange), 500)
            setHeightConstraintLayout(clIntervalMode, 0)
            animatedViewofFloat(clIntervalMode, "translationY", 0f, 500)

            //174. Cambiamso los valores del cronometro y las rondas
            animatedViewofFloat(tvChrono, "translationX", -20f, 500)
            tvRounds.setText(R.string.rounds)
            animatedViewofInt(tvRounds, "textColor", ContextCompat.getColor(this, R.color.white), 500)

            //175. Mostramos los grupos de softVolume y Track
            gSoftVolume.visibility = View.VISIBLE
            gSoftTrack.visibility = View.VISIBLE

            //246. Capturamos los el tiempo del intervalo corriendo en nuestra variable timeRunning
            var tvRunningTime = findViewById<TextView>(R.id.tvRunningTime)
            TIME_RUNNING = getSecFromWatch(tvRunningTime.text.toString())

        }else{
            swIntervalMode.setTextColor(ContextCompat.getColor(this, R.color.white))
            setHeightConstraintLayout(clIntervalMode, 1)
            clIntervalMode.translationY = -300f

            //174. Cambiamso los valores del cronometro y las rondas
            animatedViewofFloat(tvChrono, "translationX", 0f, 500)
            tvRounds.setText("")
            tvRounds.setTextColor(ContextCompat.getColor(this, R.color.orange))

            gSoftVolume.visibility = View.GONE
            gSoftTrack.visibility = View.GONE
        }
    }

    //143. La funcion inflateChallenges se ejecuta cuando se da click sobre el switch de Objetivos
    fun inflateChallenges(v: View){
        val clChallenges = findViewById<ConstraintLayout>(R.id.clChallenges)

        //144. Preguntamos si el switch esta activado o no
        if (swChallenges.isChecked){
            //145. Animamos el cambio de color de texto y la altura del constraintLayout
            animatedViewofInt(swChallenges, "textColor", ContextCompat.getColor(this, R.color.orange), 500)
            setHeightConstraintLayout(clChallenges, 0)
            animatedViewofFloat(clChallenges, "translationY", 0f, 500)

        }
        else{
            swChallenges.setTextColor(ContextCompat.getColor(this, R.color.white))
            setHeightConstraintLayout(clChallenges, 1)
            clChallenges.translationY = -600f
            //148. Si el switch de objetivos no esta activado se debe tener valores de 0 en los siguientes variables
            challengeDistance = 0f
            challengeDuration = 0
        }
    }

    //149. La funcion showDuration se ejecuta cuando se da click sobre el boton de Duracion
    fun showDuration(v: View){
        if(timeInSeconds.toInt() == 0) showChallenge("duration")
    }
    //150. La funcion showDistance se ejecuta cuando se da click sobre el boton de Distancia
    fun showDistance(v: View){
        if(timeInSeconds.toInt() == 0) showChallenge("distance")
    }
    //151. La funcion showChallenge cambiara la vista que se ejecuta cuando se da click sobre cualquier boton de los challenge
    private fun showChallenge(option: String){
        //152. Recuperamos los elementos view
        var tvChallengeDuration = findViewById<TextView>(R.id.tvChallengeDuration)
        var tvChallengeDistance = findViewById<TextView>(R.id.tvChallengeDistance)
        var gChallengeDuration = findViewById<androidx.constraintlayout.widget.Group>(R.id.gChallengesDuration)
        var gChallengeDistance = findViewById<androidx.constraintlayout.widget.Group>(R.id.gChallengesDistance)

        //153. Con un when decidimos que elementos modificar
        when(option){
            "duration"->{
                //154. Cambiamos que elementos sera visible
                gChallengeDistance.visibility = View.INVISIBLE
                gChallengeDuration.visibility = View.VISIBLE
                //155. Cambiamos los colore de los botones
                tvChallengeDistance.setTextColor(ContextCompat.getColor(this, R.color.white))
                tvChallengeDistance.setBackgroundColor(ContextCompat.getColor(this, R.color.gray_dark))
                tvChallengeDuration.setTextColor(ContextCompat.getColor(this, R.color.gray_dark))
                tvChallengeDuration.setBackgroundColor(ContextCompat.getColor(this, R.color.orange))

                //160. Si se ha pulsado el reto de duracion, el reto de distancia debe estar en cero
                challengeDistance = 0f
                //161. Mandamos llamar al metodo getChallengeDuration
                getChallengeDuration(npChallengeDurationHH.value, npChallengeDurationMM.value, npChallengeDurationSS.value)

            }
            "distance"->{
                gChallengeDuration.visibility = View.INVISIBLE
                gChallengeDistance.visibility = View.VISIBLE

                tvChallengeDuration.setTextColor(ContextCompat.getColor(this, R.color.white))
                tvChallengeDuration.setBackgroundColor(ContextCompat.getColor(this, R.color.gray_dark))
                tvChallengeDistance.setTextColor(ContextCompat.getColor(this, R.color.gray_dark))
                tvChallengeDistance.setBackgroundColor(ContextCompat.getColor(this, R.color.orange))

                //156. Si se ha pulsado el reto de distancia el reto de duracion debe estar en cero
                challengeDuration = 0
                //159. Recuperamos el valor del numberPicker
                challengeDistance = npChallengeDistance.value.toFloat()

            }
        }
    }

    //162. La funcion getChallengeDuration convierte los valores numberPicker a un formato en string
    private fun getChallengeDuration(hh: Int, mm: Int, ss: Int){
        var hours: String = hh.toString()
        if (hh<10) hours = "0" + hours
        var minutes: String = mm.toString()
        if (mm<10) minutes = "0" + minutes
        var seconds: String = ss.toString()
        if (ss<10) seconds = "0" + seconds

        //163. Obtenemos la duracion en segundos a partir de la funcion getSecFromWatch
        challengeDuration = getSecFromWatch("${hours}:${minutes}:${seconds}")
    }

    //135. La funcion inflateVolumes se ejecuta cuando se da click sobre el switch de volumenes
    fun inflateVolumes(v: View){
        val clVolumes = findViewById<ConstraintLayout>(R.id.clVolumes)
        val switch = v as Switch

        //139. Preguntamos si se ha activado el switch
        if (switch.isChecked){

            //140. Mandamos llamar la funcion animatedViewofInt de la clase Utility que creamos
            animatedViewofInt(switch, "textColor", ContextCompat.getColor(this, R.color.orange), 500)
            //142. Ampliamos el espacio para ver los volumnes y hacemos la animacion con la translationY
            setHeightConstraintLayout(clVolumes, 0)
            animatedViewofFloat(clVolumes, "translationY", 0f, 500)
        }
        else{
            switch.setTextColor(ContextCompat.getColor(this, R.color.white))
            setHeightConstraintLayout(clVolumes, 1)
            clVolumes.translationY = -300f
        }
    }

    //217. La funcion startOrStopButtonClicked se ejcuta cuando se da click sobre el boton de Inicio de carrera
    fun startOrStopButtonClicked(v: View){
        manageRun()
    }
    //218. La funcion manageRun sera la encargada de administrar la carrera
    private fun manageRun(){
        if (timeInSeconds.toInt() == 0){

            fbCamara.isVisible = true

            swIntervalMode.isClickable = false
            npDurationInterval.isEnabled = false
            csbRunWalk.isEnabled = false

            swChallenges.isClickable = false
            npChallengeDistance.isEnabled = false
            npChallengeDurationHH.isEnabled = false
            npChallengeDurationMM.isEnabled = false
            npChallengeDurationSS.isEnabled = false

            tvChrono.setTextColor(ContextCompat.getColor(this, R.color.chrono_running))

            //272. En este punto tenemos que lanzar la musica corriendo
            mpHard?.start()
        }

        //219. Determinamos si se tiene que empezar la carrera o pararla
        if (!startButtonClicked){
            startButtonClicked = true
            //220. LLamamos a la funcion startTime
            startTime()
            //227. Se desavilitan los botones
            manageEnableButtonsRun(false, true)

            //287. Evaluamos el estado de la variable running y lanzamos la pista que corresponda
            if (running) mpHard?.start()
            else mpSoft?.start()
        }
        else{
            startButtonClicked = false
            //224. Llamamos a la funcion stopTime
            stopTime()
            manageEnableButtonsRun(true, true)

            //288. Evaluamos el estado de la variable running y pausamos la pista que corresponda
            if (running) mpHard?.pause()
            else mpSoft?.pause()

        }
    }
    //226. La funcion manageEnableButtonsRun administrar el habilitar o no ciertos botones que no pueden estar habilitados mientras corre el codigo porque daria error de ser apretados
    private fun manageEnableButtonsRun(e_reset: Boolean, e_run: Boolean){
        //228. Recuperamos los elementos que se van a modificar
        val tvReset = findViewById<TextView>(R.id.tvReset)
        val btnStart = findViewById<android.widget.Button>(R.id.btnStart)
        //229. Se desavilitan los botones
        tvReset.setEnabled(e_reset)
        btnStart.setEnabled(e_run)

        //230. Evaluamos si el boton de finalizar esta habilitado
        if(e_reset){
            tvReset.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
            animatedViewofFloat(tvReset, "translationY", 0f, 500)
        }
        else{
            tvReset.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
            animatedViewofFloat(tvReset, "translationY",  150f, 500)
        }

        //231. Evaluamos si el boton de la carrera esta hablitidado y configuramos la aparciencia de este boton
        if (e_run){
            //232. Evaluamos como se encuentra el boton de start
            if (startButtonClicked){
                btnStart.background = getDrawable(R.drawable.circle_background_topause)
                btnStart.setText(R.string.stop)
            }
            else{
                btnStart.background = getDrawable(R.drawable.circle_background_toplay)
                btnStart.setText(R.string.start)
            }
        }
        else btnStart.background = getDrawable(R.drawable.circle_background_todisable)


    }
    //221. La funcion startTime ejecuta el codigo de la variable chronometer
    private fun startTime(){
        //222. Necesitamos inicializar el mHandler obteniendo el objeto Loop
        mHandler = Handler(Looper.getMainLooper())
        //223. Hacemos que corra el chronometer
        chronometer.run()
    }
    //225. La funcion stopTime va a parar el chronometer
    private fun stopTime(){
        mHandler?.removeCallbacks(chronometer)
    }

    //212. Creamos una variable en donde administremos el codigo que se estara ejecutando constantemente y el tiempo de intervalo entre ejecuciones
    private var chronometer: Runnable = object : Runnable {
        override fun run() {
            try {
                //281. Actualizamos el progress de los seekBars de los tracks
                if (mpHard!!.isPlaying){
                    val sbHardTrack = findViewById<SeekBar>(R.id.sbHardTrack)
                    sbHardTrack.progress = mpHard!!.currentPosition
                }
                if (mpSoft!!.isPlaying){
                    val sbSoftTrack = findViewById<SeekBar>(R.id.sbSoftTrack)
                    sbSoftTrack.progress = mpSoft!!.currentPosition
                }

                //282. Actualizamos los textos de los track se musica
                updateTimesTrack(true, true)


                //247. Tenemos que estar verificando constantemente si toca el intervalo de caminar o siguiente ronda
                if (swIntervalMode.isChecked){
                    checkStopRun(timeInSeconds)
                    checkNewRound(timeInSeconds)
                }

                //215. El codigo dentro del try es el que se estara ejecutando cada cierto tiempo
                timeInSeconds += 1
                updateStopWatchView()

            }finally {
                //213. Con el mHandler indicamos el tiempo de retraso o delayed en el que se ejecutar el codigo
                mHandler!!.postDelayed(this, mInterval.toLong())
            }
        }
    }

    //216. La funcion upadteStopWatchView actualiza el valor del TextView del cronometro
    private fun updateStopWatchView(){
        tvChrono.text = getFormattedStopWatch(timeInSeconds * 1000)
    }

    //108. La funcion initToolBar inicializa todas las cuestiones de la ToolBar
    private fun initToolBar(){
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_main)
        //109. Establecemos la toolbar
        setSupportActionBar(toolbar)

        //111. Inicializamos el Drawer
        drawer = findViewById(R.id.drawer_layout)
        //112. Creamo un toggle con el cual configurarermos que en esta activity se ocupe el drawer el cual tendra un toolbar que mostrar un mensaje para abrir y otro para cerrar
        var toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.bar_title, R.string.navigation_drawer_close)
        //113. Agregamos un listener
        drawer.addDrawerListener(toggle)
        //114. Sincronizamos la activacion del menu o el cierre
        toggle.syncState()

    }

    //115. La funcion initNavigationView inicializa los elementos del la Vista de navegacion en donde esta el menu y la cabecera
    private fun initNavigationView(){
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        //116. Establecemos un Listener a los elementos del menu
        navigationView.setNavigationItemSelectedListener(this)

        //118. Creamos un headerView con el cual vamos a 'inflar' la ventana del menu
        var headerView: View = LayoutInflater.from(this).inflate(R.layout.nav_header_main, navigationView, false)
        //119. Borramos y poner nuevamente el headerView para que se actualicen los datos de este
        navigationView.removeHeaderView(headerView)
        navigationView.addHeaderView(headerView)

        //120. Creamos una variable para administra el textView con el objeto headerView
        var tvUser: TextView = headerView.findViewById(R.id.tvUser)
        tvUser.text = useremail

    }

    //54. La funcion callSignOut se ejecuta cuando el usuario da click sobre el boton de cerrar sesion
    fun callSignOut(view: View){
        signOut()
    }

    //55 La funcion signOut Cierra la sesion activa de Firebase y nos redirige a la activityLogin
    private fun signOut(){
        //56. Se crea una instancia de FirebaseAuth para llamar al metodo signOut()
        FirebaseAuth.getInstance().signOut()
        //57. Redirigimos al activity de login
        startActivity(Intent(this, LoginActivity::class.java))

        //96. Verificamos si el usuario entro por parte de facebook para hacer el logOut de facebook
        if(providerSession == "Facebook") LoginManager.getInstance().logOut()
    }

    //122. La funcion onNavigationItemSelected Se ejcuta cuando se pulsa uno de los elementos del menu
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        //123. Hacemos un when para ejecutar el codigo dependiendo del elemento al que se haya hecho click
        when (item.itemId){
            R.id.nav_item_record -> callRecordActvity()
            R.id.nav_item_signout -> signOut()
        }

        //124. Cerramos el drawer o menu hacia el inicio
        drawer.closeDrawer(GravityCompat.START)


        return true

    }

    //125. La funcion callRecordActviity nos redirige a la activity de RecordActvity
    private fun callRecordActvity(){
        val intent = Intent(this, RecordActivity::class.java)
        startActivity(intent)
    }

    //235. La funcion resetClicked Se ejecuta cuando se da click sobre el boton de finalizar
    private fun resetClicked(){
        resetVariablesRun()
        resetTimeView()
        resetInterface()
    }
    //337. La funcion resetVariablesRun resetea el tiempo en segundos colocara el valor al TextView del cronometro
    private fun resetVariablesRun(){
        running = true
        timeInSeconds = 0
        rounds = 1

        challengeDistance = 0f
        challengeDuration = 0

        initStopWatch()
    }
    //236. La funcion resetTimeView resetea los valores de todos los elementos asociados a la carrera
    private fun resetTimeView(){
        manageEnableButtonsRun(false, true)
        //val btnStart = findViewById<android.widget.Button>(R.id.btnStart)
        //btnStart.background = getDrawable(R.drawable.circle_background_toplay)
        tvChrono.setTextColor(ContextCompat.getColor(this, R.color.white))
    }
    //238. La funcion resetInterface coloca los elementos como botones habilitados y listos para una nueva carrera
    private fun resetInterface(){


        //243. Ocultamos el boton de foto
        fbCamara.isVisible = false

        //242. Se devuelven a cero los textView de los circularSeekBar
        val tvCurrentDistance: TextView = findViewById(R.id.tvCurrentDistance)
        val tvCurrentAvgSpeed: TextView = findViewById(R.id.tvCurrentAvgSpeed)
        val tvCurrentSpeed: TextView = findViewById(R.id.tvCurrentSpeed)
        tvCurrentDistance.text = "0.0"
        tvCurrentAvgSpeed.text = "0.0"
        tvCurrentSpeed.text = "0.0"

        tvDistanceRecord.setTextColor(ContextCompat.getColor(this, R.color.gray_dark))
        tvAvgSpeedRecord.setTextColor(ContextCompat.getColor(this, R.color.gray_dark))
        tvMaxSpeedRecord.setTextColor(ContextCompat.getColor(this, R.color.gray_dark))

        //240. Se devuelven los seekBar a la posicion inicial
        csbCurrentDistance.progress = 0f
        csbCurrentAvgSpeed.progress = 0f
        csbCurrentMaxSpeed.progress = 0f
        csbCurrentSpeed.progress = 0f

        //241. Se devuelven los view que actuan como progressbar a su posicion inicial
        val vChronoProgressBar = findViewById<ProgressBar>(R.id.vChronoProgressBar)
        val vRoundProgressBar = findViewById<ProgressBar>(R.id.vRoundProgressBar)
        vChronoProgressBar.progress = 0
        vRoundProgressBar.progress = 0

        //239. Se habilitan los numberPickers y switch
        swIntervalMode.isClickable = true
        npDurationInterval.isEnabled = true
        csbRunWalk.isEnabled = true

        swChallenges.isClickable = true
        npChallengeDistance.isEnabled = true
        npChallengeDurationHH.isEnabled = true
        npChallengeDurationMM.isEnabled = true
        npChallengeDurationSS.isEnabled = true

    }

    //254. Esta funcion actualiza el progreso del progressBar
    private fun updateProgressBarRound(secs: Long){
        var s = secs.toInt()
        //255. Si el tiempo corrido es mayor al tiempo de la ronda, restamos este valor hasta obtener el tiempo en la ronda acutal
        while (s > ROUND_INTERVAL) s -= ROUND_INTERVAL

        var vRoundProgressBar = findViewById<ProgressBar>(R.id.vRoundProgressBar)
        //256. Evaluamos si el tiempo en la ronda actual es mayor al tiempo en el que debemos estar corriendo
        if (s >= TIME_RUNNING ){
            s++
            s -= TIME_RUNNING
            //257. Actualizamos el progreso que debe tener
            vRoundProgressBar.max = ROUND_INTERVAL - TIME_RUNNING
            vRoundProgressBar.progress = s
        }else{
            s++
            vRoundProgressBar.max = TIME_RUNNING
            vRoundProgressBar.progress = s
        }
    }
    //249. La funcion checkStopRun comprueba si ya termino el intervalo de correr
    private fun checkStopRun(secs: Long){
        //250. guardamos en una variabel auxiliar el valor de secs
        var secAux: Long = secs
        //251. Evaluamos si el tiempo corriendo es mayor al tiempo total de la ronda, si es asi, le restamos el tiempo de la ronda hasta que no se pueda mas
        while (secAux.toInt() > ROUND_INTERVAL) secAux -= ROUND_INTERVAL

        //252. Evaluamos si el tiempo que quedo corriendo, es igual al intervalo corriendo, si es asi cambiamos el color del progress bar y lo devolvemos a 0
        if(secAux.toInt() == TIME_RUNNING){
            tvChrono.setTextColor(ContextCompat.getColor(this, R.color.chrono_walking))
            val vRoundProgressBar= findViewById<ProgressBar>(R.id.vRoundProgressBar)
            vRoundProgressBar.progressDrawable = ContextCompat.getDrawable(this, R.drawable.style_progressbar_chronowalking)
            vRoundProgressBar.progress = 1

            //286. Cambiamos el estado de estas variables
            running = false

            //270. En este punto pausamos la cancion corriendo, lanzamos la notificacion y la cancion caminando
            mpHard?.pause()
            notifySound()
            mpSoft?.start()
        }
        //253. Actualizamos el estado del progressBar
        else updateProgressBarRound(secs)
    }
    //258. La funcion CheckNewRound evalua si se ha completado la ronda
    private fun checkNewRound(secs: Long){
        //259. Preguntamos si el tiempo actual es multiplo de tiempo de la ronda y si es mayor a 0
        if (secs.toInt() % ROUND_INTERVAL == 0 && secs.toInt() > 0){
            //260. Actualizamos el texto de las rondas y las rondas
            val tvRounds: TextView = findViewById(R.id.tvRounds)
            rounds ++
            tvRounds.text = "Round $rounds"
            //261. Cambiamos el color del progressbar y el del texto del chronometro
            tvChrono.setTextColor(ContextCompat.getColor(this, R.color.chrono_running))
            val vRoundProgressBar= findViewById<ProgressBar>(R.id.vRoundProgressBar)
            vRoundProgressBar.progressDrawable = ContextCompat.getDrawable(this, R.drawable.style_progressbar_salmon)
            vRoundProgressBar.progress = 1

            //285. Cambiamos el estado de las variables
            running = true
            //271. En este punto pausamos la musica caminado, lanzamos la notificacion y la musica corriendo
            mpSoft?.pause()
            notifySound()
            mpHard?.start()
        }
        else updateProgressBarRound(secs)
    }

    //267. La funcion setVolumes adminsitra el control de los volumenese con los seekbar
    private fun setVolumes(){
        //268. Con un OnSeekBarChangeListener controlamos cuando se cambia el valor del seekbar y ejecutamos algun codigo
        sbHardVolume.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, i: Int, p2: Boolean) {
                //269. Establecemos el volumen que tendra la musica con setVolume, se puede espcificar tanto para el oido derecho como izquierdo
                mpHard?.setVolume(i/100.0f, i/100.0f)
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        sbSoftVolume.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, i: Int, p2: Boolean) {
                //269. Establecemos el volumen que tendra la musica con setVolume, se puede espcificar tanto para el oido derecho como izquierdo
                mpSoft?.setVolume(i/100.0f, i/100.0f)
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        sbNotifyVolume.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, i: Int, p2: Boolean) {
                //269. Establecemos el volumen que tendra la musica con setVolume, se puede espcificar tanto para el oido derecho como izquierdo
                mpNotify?.setVolume(i/100.0f, i/100.0f)
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }
    //269. La funcion notifySound simplemente va a iniciar el sonido de la notificacion
    private fun notifySound(){
        mpNotify?.start()
    }
    //273. La funcion setProgressTracks administra el comportamiento de estos seekbars
    private fun setProgressTracks(){
        val sbHardTrack = findViewById<SeekBar>(R.id.sbHardTrack)
        val sbSoftTrack = findViewById<SeekBar>(R.id.sbSoftTrack)
        //274. Establecemos los maximos de los seekbar dependiendo de la duracion de la cancio
        sbHardTrack.max = mpHard!!.duration
        sbSoftTrack.max = mpSoft!!.duration

        //278. Acutalizamos los textView de los tracks
        updateTimesTrack(true, true)

        //279. Si el usuario cambia el seekbar la musica tendra que reproducirse en este punto
        sbHardTrack.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, i: Int, fromUser: Boolean) {
                if (fromUser){
                    //280. Pausamos la musica, nos vamos al punto que el usuario coloco el seekbar y reanudamos la musica si se ha oprimido el boton de inicio
                    mpHard?.pause()
                    mpHard?.seekTo(i)
                    //289. Evaluamos si se ha hecho click sobre el boton de incio y si toca correr o caminar
                    if (startButtonClicked && running){
                        mpHard?.start()
                    }
                    updateTimesTrack(true, false)
                }
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        sbSoftTrack.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, i: Int, fromUser: Boolean) {
                if (fromUser){
                    mpSoft?.pause()
                    mpSoft?.seekTo(i)
                    //290. Evaluamos si se ha hecho click sobre el boton de incio y si toca caminar o correr
                    if (startButtonClicked && !running){
                        mpSoft?.start()
                    }
                    updateTimesTrack(true, false)
                }
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }

    //275. La funcion updateTimesTrack acutaliza los TextView asociados a los seekBars de tracks
    private fun updateTimesTrack(timesH: Boolean, timesS: Boolean){
        val sbHardTrack = findViewById<SeekBar>(R.id.sbHardTrack)
        val sbSoftTrack = findViewById<SeekBar>(R.id.sbSoftTrack)
        //276. Pregutnamos que TextView queremos actualizar si el de la musica hard o soft
        if(timesH){
            val tvHardPosition = findViewById<TextView>(R.id.tvHardPosition)
            val tvHardRemaining = findViewById<TextView>(R.id.tvHardRemaining)
            //277. Actualizamos los textView dependiendo del progreso del seekBar ya que el progreso de este esta relacionado a los segundos de la cancion
            tvHardPosition.text = getFormattedStopWatch(sbHardTrack.progress.toLong())
            tvHardRemaining.text = "-" + getFormattedStopWatch(mpHard!!.duration.toLong() - sbHardTrack.progress.toLong())
        }
        if(timesS){
            val tvSoftPosition = findViewById<TextView>(R.id.tvSoftPosition)
            val tvSoftRemaining = findViewById<TextView>(R.id.tvSoftRemaining)
            tvSoftPosition.text = getFormattedStopWatch(sbSoftTrack.progress.toLong())
            tvSoftRemaining.text = "-" + getFormattedStopWatch(mpSoft!!.duration.toLong() - sbSoftTrack.progress.toLong())
        }
    }


}