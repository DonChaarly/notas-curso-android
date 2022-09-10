package com.cursoandroid.a21proyectoappdeporte

import android.animation.ObjectAnimator
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import java.sql.Time
import java.util.concurrent.TimeUnit

object Utility {

    //129. La funcion setHeightConstraintLayout cambiara la altura del constraintLayout
    fun setHeightConstraintLayout(cl: ConstraintLayout, value: Int){
        //130. Creamos un objeto params para administrar los parametros del constriant
        val params: ConstraintLayout.LayoutParams = cl.layoutParams as ConstraintLayout.LayoutParams
        params.height = value
        cl.layoutParams = params
    }

    //136. La funcion animatedViewofInt ejecuta la animacion sobre el atributo que indiquemos
    fun animatedViewofInt(v: View, attr: String, value: Int, time: Long){
        /*137. Con un objectAnimator podemos realizar una animacion
                   Seleccionamos el metodo ofInt ya que vamos a cambiar un atributo que recibe un entero
                   Se coloca el target o View, el atributo que vamos a cambiar y el valor que queremos que tome
                   Con apply aplicamos el cambio y establecemos la duracion en milisegundos
             */
        ObjectAnimator.ofInt(v, attr, value).apply {
            duration = time
            start()
        }
    }
    //138. La funcion animatedViewofFloat ejecuta la animacion sobre el atributo que indiquemos
    fun animatedViewofFloat(v: View, attr: String, value: Float, time: Long){
        ObjectAnimator.ofFloat(v, attr, value).apply {
            duration = 500
            start()
        }
    }

    //164. La funcion getSecFromWatch devuelve el tiempo en segundos a partir del tiempo pasado como strng
    fun getSecFromWatch(watch: String): Int{

        //165. Por si recibimos un formato del tipo 00:00 lo transformamos a un formato de tipo 00:00:00
        var secs = 0
        var w: String = watch
        if (w.length == 5) w = "00:" + w

        //166. Vamos sumandole a la variable secs lo correspondiente en segundos de string que tenemos
        secs += w.subSequence(0, 2).toString().toInt() * 3600
        secs += w.subSequence(3, 5).toString().toInt() * 60
        secs += w.subSequence(6, 8).toString().toInt()

        return secs
    }

    //194. La funcion getFormattedStopWatch devuelve un straing con el formato de reloj 00:00:00 a partir de un tiempo en milisegundos
    fun getFormattedStopWatch(ms: Long): String{
        var milliseconds = ms
        //195. Convertimos a horas los milisegundos
        val hours = TimeUnit.MILLISECONDS.toHours((milliseconds))
        //196. Restamos las horas a nuestro tiempo inicial
        milliseconds -= TimeUnit.HOURS.toMillis((hours))
        val minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
        milliseconds -= TimeUnit.MINUTES.toMillis(minutes)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)

        //197. Regresamos el string con el formato
        return "${if (hours<10) "0" else ""}${hours}:" +
                "${if (minutes<10) "0" else ""}${minutes}:" +
                "${if (seconds<10) "0" else ""}${seconds}"
    }


}