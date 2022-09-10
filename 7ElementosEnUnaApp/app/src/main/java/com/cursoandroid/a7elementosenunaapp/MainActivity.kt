package com.cursoandroid.a7elementosenunaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle

class MainActivity : AppCompatActivity() {

    //1. Este metodo es el primero que se lanza
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //2. En este metodo el activitie apenas esta preparandose
    override fun onStart() {
        super.onStart()
    }

    //3. En este metodo ya se puede visualizar el contenido del activitie en el IU y se puede interactuar con la apliacion
    override fun onResume() {
        super.onResume()
    }

    //4. Este metodo es llamado cada vez que se cambia de aplicacion sin cerrarla completamente
    // Se vuelve al metodo onResume si es que se vuelve a abrir la aplacion.
    override fun onPause() {
        super.onPause()
    }

    //5. Este metodo es llamado una vez que ha pasado mucho tiempo en el metodo onResume
    // Se vuelve al metodo onResume si es que se vuelve a abrir la aplacion.
    override fun onStop() {
        super.onStop()
    }

    //6. Este metodo se ejecuta cuando se cierra la aplicacion o se ha pasado mucho tiempo en el metodo onStop
    override fun onDestroy() {
        super.onDestroy()
    }
}