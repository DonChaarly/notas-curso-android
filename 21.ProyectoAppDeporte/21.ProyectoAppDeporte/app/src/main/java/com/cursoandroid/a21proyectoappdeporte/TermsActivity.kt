package com.cursoandroid.a21proyectoappdeporte

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TermsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms)

        //48. Se crea una variable toolbar para colocar el titulo del activity
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_terms)
        setSupportActionBar(toolbar)
        toolbar.title = getString(R.string.bar_title_terms)




    }
}