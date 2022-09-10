package com.cursoandroid.a21proyectoappdeporte

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat

class RecordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)


        //127. Recuperamos el toolbar
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_record)
        //128. establcemos el toolbar
        setSupportActionBar(toolbar)
        //129. Podemos cambiar el texto que se muestra en el toolbar
        toolbar.title = getString(R.string.bar_title_record)
        //130. Podemos cambiar los colores tanto de la frase como del fondo
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.gray_dark))
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.gray_light))

        //131. La siguiente propiedad permite al boton poder desplazarse al home
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    //135. La funcion onSupportNavigateUp Se ejecuta cuando el usario da click en el boton para atras que se muestra
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    //132. La funcion OnCreateOptionsMenu inicializa el menu que se va a utilizar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //133. Inflamos el layout order_records_by el cual cargamos al menu
        menuInflater.inflate(R.menu.order_records_by, menu)

        //134. No ncecesitamos que se ejecute el codigo del padre
        return true//super.onCreateOptionsMenu(menu)
    }

    //136. La funcion onOptionsItemSelected administra el codigo que se ejecutara cuando se haga click sobre algun elemento del menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.orderbay_date -> {
                if (item.title == getString(R.string.orderbay_dateZA)){
                    item.title = getString(R.string.orderbay_dateAZ)
                }else{
                    item.title = getString(R.string.orderbay_dateZA)
                }
                return true
            }
            R.id.orderbay_duration -> {
                if (item.title == getString(R.string.orderbay_durationZA)){
                    item.title = getString(R.string.orderbay_durationAZ)
                }else{
                    item.title = getString(R.string.orderbay_durationZA)
                }
                return true
            }
            R.id.orderbay_distance -> {
                if (item.title == getString(R.string.orderbay_distanceZA)){
                    item.title = getString(R.string.orderbay_distanceAZ)
                }else{
                    item.title = getString(R.string.orderbay_distanceZA)
                }
                return true
            }
            R.id.orderbay_avgspeed -> {
                if (item.title == getString(R.string.orderbay_avgspeedZA)){
                    item.title = getString(R.string.orderbay_avgspeedAZ)
                }else{
                    item.title = getString(R.string.orderbay_avgspeedZA)
                }
                return true
            }
            R.id.orderbay_maxspeed -> {
                if (item.title == getString(R.string.orderbay_maxspeedZA)){
                    item.title = getString(R.string.orderbay_maxspeedAZ)
                }else{
                    item.title = getString(R.string.orderbay_maxspeedZA)
                }
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }


    //137. La funcion callHome se ejecuta cuando hacemos click sobre el floatBottom y nos redirige a la pagina principal
    fun callHome(v: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}