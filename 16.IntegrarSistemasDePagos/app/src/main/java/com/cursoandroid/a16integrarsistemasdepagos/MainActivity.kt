package com.cursoandroid.a16integrarsistemasdepagos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.stripe.android.PaymentConfiguration

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun launchPaymentCard(v: View){
        callPayment()
    }

    //21 El codigo que nos da stripe en el archivo MyApp Tiene que ejecutarse antes que toodo el codigo de CheckoutActivity
    private fun  callPayment(){
        //22. La siguiente variable guarda la clave de pago ( La utilizada es de pruebas, para obtener una debemos registrarnos en stripe)
        var keyStripePayment = "pk_test_wk6O7Cc5k3McBIG2Hut2irGs"
        PaymentConfiguration.init (applicationContext, keyStripePayment)

        //23. Para llamar a otro activity se hace lo siguiente
        val intent = Intent(this, CheckoutActivity::class.java)
        startActivity(intent)
    }



}