package com.cursoandroid.a16integrarsistemasdepagos

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit


class CheckoutActivity : AppCompatActivity() {
    companion object {
        //4. La siguiente variable es el tag par el log, es el nombre con el que vamos a buscar en el registro log
        private const val TAG = "CheckoutActivity"
        //5. La siguiente linea correesponde al puerto que configuramos en el archivo server.js (toodo lo anterior al 4242 es lo equivalente al localhost)
        //37. Se coloca la url que nos proporciono herku en la cual tenemos nuestro servidor publico
        private const val BACKEND_URL = "https://heroku-prueba-carlos.herokuapp.com"
    }

    private lateinit var paymentIntentClientSecret: String
    private lateinit var paymentSheet: PaymentSheet

    private lateinit var payButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)




        //6. Se esta recuperando el button el layout, asi que el id tiene que corrsponder
        payButton = findViewById(R.id.pay_button)
        //7. Se tiene un evento onClick Listener que llama a la funcion onPlayClicked
        payButton.setOnClickListener(::onPayClicked)
        payButton.isEnabled = false

        paymentSheet = PaymentSheet(this, ::onPaymentSheetResult)

        //9. La siguiente funcion es la que va a tener preparado que funcinoe el pago
        fetchPaymentIntent()

    }

    private fun fetchPaymentIntent() {
        //10. Se crea la variable url en donde al a direccion de la variable BACKEND_URL se le agrega la ruta create_payment-intent la cual esta definida en la funcion post del archivo server.js
        val url = "$BACKEND_URL/create-payment-intent"

        //11. Se tiene una variable ShoppingCartContent el cual sera el carrito de compra y contendra los articulos que se van a comprar por medio de un archivo JSON

        //24. Se tiene la cantidad que costara nuestro producoto
        val amount = 100.0f
        //25. Se crea un mapa el cual contendra una clave y un valor de cualquier tipo, el cual se encargara de guardar todos los tiems y el tipo de moneda en el que seran cobrados
        val payMap: MutableMap<String, Any> = HashMap()
        //26 Se tiene otro mapa el cual igual contiene una clave y otro valor de cualquier tipo, el cual se encarga de guardar un articulo
        val itemMap: MutableMap<String, Any> = HashMap()
        //27. Se crea una lista la cual contendra todos los articulos itemMap
        val itemList: MutableList<Map<String, Any>> = ArrayList()
        //29. Se crea un tiem
        itemMap["id"] = "photo_suscription"
        itemMap["amount"] = amount
        //30. Se agrega a la lista de items
        itemList.add(itemMap)
        //31. Se Agrega el tipo de moneda y la lista de articulos al payMap, el tipo de moneda tien que coincidir con lo establecido en el server.js
        payMap["currency"] = "usd"
        payMap["items"] = itemList

        //32. Finalmente creamos nuestra variable shoppingCartContent la cual contendra el mapa payMap pero lo convertiremos a JSON (siguiente comentario en archivo server.js)
        val shoppingCartContent = Gson().toJson(payMap)



        //12 aqui los datos se convierten en JSON
        val mediaType = "application/json; charset=utf-8".toMediaType()

        val body = shoppingCartContent.toRequestBody(mediaType)

        //13. Se envia la peticion utilizando la libreria de okhttp
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        //14. Hacemos la llamada
        OkHttpClient()
            .newCall(request)
            .enqueue(object: Callback {
                //15. En caso de error se ejecuta el siguiente codigo
                override fun onFailure(call: Call, e: IOException) {
                    showAlert("Failed to load data", "Error: $e")
                }
                //16. En caso de respuesta se verifica que ha sido exitosa
                override fun onResponse(call: Call, response: Response) {
                    if (!response.isSuccessful) {
                        showAlert("Failed to load page", "Error: $response")
                    } else {
                        val responseData = response.body?.string()
                        val responseJson = responseData?.let { JSONObject(it) } ?: JSONObject()
                        //17. Enviamos el paymentIntentClientSecret y le asignamos los datos que hemos ido recopilando
                        paymentIntentClientSecret = responseJson.getString("clientSecret")
                        //18. finalmente tramitamos al servidor el pago
                        runOnUiThread { payButton.isEnabled = true }
                        Log.i(TAG, "Retrieved PaymentIntent")
                    }
                }
            })
    }

    private fun showAlert(title: String, message: String? = null) {
        runOnUiThread {
            val builder = AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
            builder.setPositiveButton("Ok", null)
            builder.create().show()
        }
    }

    //35. La siguiente funcion manda un mensaje de acuerdo si se realizo el pago o no, cambiamos el Toast por un snackBar ya que este ultimo es mas atractivo (siguiente comentario en archivo server.js)
    private fun showSnackBar(message: String, duration: Int) {
        /*
        runOnUiThread {
            Toast.makeText(this,  message, Toast.LENGTH_LONG).show()
        }
         */
        val mySnackbar = Snackbar.make(findViewById(R.id.lyMain), message, duration)
        mySnackbar.show()
    }

    //8. El evento onPayClicked muestra una ventanita para que el usuario para que introduzca los datos de pago
    private fun onPayClicked(view: View) {
        val configuration = PaymentSheet.Configuration("Example, Inc.")

        // Present Payment Sheet
        paymentSheet.presentWithPaymentIntent(paymentIntentClientSecret, configuration)
    }

    //19. Al tramitar el pago, nos mostrara un resultado
    private fun onPaymentSheetResult(paymentResult: PaymentSheetResult) {
        //20. Dependiendo de la respuesta del pago se ejecuatn las siguientes lineas
        when (paymentResult) {
            is PaymentSheetResult.Completed -> {
                //showToast("Payment complete!")
                showSnackBar("Payment succed", Snackbar.LENGTH_LONG)
            }
            is PaymentSheetResult.Canceled -> {
                Log.i(TAG, "Payment canceled!")
                showSnackBar("Payment canceled. Try again", Snackbar.LENGTH_LONG)
            }
            is PaymentSheetResult.Failed -> {
                //showAlert("Payment failed", paymentResult.error.localizedMessage)
                showSnackBar("Payment failed. Try again", Snackbar.LENGTH_LONG)
            }
        }
    }
}