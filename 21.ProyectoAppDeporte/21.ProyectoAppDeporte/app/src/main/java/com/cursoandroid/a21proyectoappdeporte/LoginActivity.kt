package com.cursoandroid.a21proyectoappdeporte

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.IntentSender
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider

class LoginActivity : AppCompatActivity() {

    //13. Se agrega un companion object par declarar las variables estaticas
    companion object{
        lateinit var useremail: String
        lateinit var providerSession: String
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }

    //14. Se crean las siguientes variables locales para el uso de firebase authentication

    //15. el email y password le colocamos que no puedan ser de tipo null
    private var email by Delegates.notNull<String>()
    private var password by Delegates.notNull<String>()
    private var password2 by Delegates.notNull<String>()
    //16. variables para guardar los elementos del layout
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etPassword2: EditText
    private lateinit var lyTerms: LinearLayout
    private lateinit var lyPassword: LinearLayout
    //17. Variable para la autenticacion de la base de datos
    private lateinit var mAuth: FirebaseAuth

    //79. Se crean las siguientes variables para el incio de sesion con google
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    //85. Se crea la siguietne variable por parte del login de facebook
    private var callbackManager = CallbackManager.Factory.create();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //18. recuperamos el lyterms y cambiamos su visibilidad
        lyTerms = findViewById(R.id.lyTerms)
        lyTerms.visibility = View.INVISIBLE
        lyPassword = findViewById(R.id.lyPassword)
        lyPassword.visibility = View.GONE

        //19. Recuperamos los elementos del layout
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etPassword2 = findViewById(R.id.etPassword2)
        //20. Creamos una instancia para la autenticacion
        mAuth = FirebaseAuth.getInstance()

        //58. Llamamos a la funcion manageButtonLogin() y la llamamos cada que se modifique el campo de email o password
        manageButtonLogin()
        etEmail.doOnTextChanged { text, start, before, count -> manageButtonLogin()  }
        etPassword.doOnTextChanged { text, start, before, count -> manageButtonLogin()  }
        initOneTapSignin()

    }

    //38. Sobreescribimos el metodo onStart para que cuadno se ejecute este metodo al abrir la apliccion nos recupera los datos del usuario de la base de datos y no tenga que iniciar sesion otra vez
    override fun onStart() {
        super.onStart()

        //39. Creamos una instancia de FirebaseAuth para guardar los datos del usuario actual
        var currentUser = FirebaseAuth.getInstance().currentUser
        //40. llamamos al metodo goHome y le pasamos los datos del usuario con ayuda de nuestra variable currentUser
        if (currentUser != null) goHome(currentUser.email.toString(), currentUser.providerId)

        val currentUserGoogle = auth.currentUser
        if (currentUserGoogle != null) goHome(currentUserGoogle.email.toString(), currentUserGoogle.providerId)


    }

    //41. Sobreescribimos el metodo onBackPressed el cual se ejecuta cada vez que se da click al boton hacia atras del celular
    override fun onBackPressed() {
        //42. No llamamos al metodo super porque queremos cambiar el comportamiento del boton para no tener el fallo de
        // si hicimos un logout y oprimrimos este boton nos lleve otra vez al activitymain lo cual nos daria un fallo, sino que nos lleve a la ventan de inicio del celular

        //43. Creamos un objeto Intent que nos redirija a la ventan de inicio del celular
        val startMain = Intent(Intent.ACTION_MAIN)
        //44. Agregamos informacoin como la categoria y una bander
        startMain.addCategory(Intent.CATEGORY_HOME)
        startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        //45. Lanzamos el intent
        startActivity(startMain)
    }

    //59. La funcion manageButtonLogin evaluara los campos de email y password para habilitar o no el boton login
    private fun manageButtonLogin(){
        val btnLogin = findViewById<AppCompatButton>(R.id.btnLogin)
        email = etEmail.text.toString()
        password = etPassword.text.toString()
        //60. Hacemos la comprobacion de si el email esta bien escrito o no
        if(TextUtils.isEmpty(password) || ValidateEmail.isEmail(email) == false){
            btnLogin.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
            btnLogin.isEnabled = false
        }
        else{
            btnLogin.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
            btnLogin.isEnabled = true
        }


    }

    //21. La funcion login se ejecuta cuando se hace click sobre el boton btLogin
    fun login(view: View){
        loginUser()
    }
    //22. La funcion loginUser es la que realmente contendra el codigo de login, esto para poder hacer privada esta funcion
    private fun loginUser(){
        //23. recuperamos los datos del email y constrasena que introdujo el usuario
        email = etEmail.text.toString()
        password = etPassword.text.toString()

        //24. Con nuestra variable mAuth llamamos al metodo de inicio de sesion con email y password
        //25. Tambien llamamso el metodo .addCompleteListener el cual nos da informacion de si se ejecuto correctamente el metodo o no, y se ejeucta el codigo que coloquemos
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                //26. En task se tiene si el inicio de sesion se ha hecho con exito o no
                if(task.isSuccessful) goHome(email, "email")
                else {
                    if(lyTerms.visibility == View.INVISIBLE){
                        lyTerms.visibility = View.VISIBLE
                        lyPassword.visibility = View.VISIBLE
                    }
                    else {
                        var cbAcept = findViewById<CheckBox>(R.id.cbAcept)
                        if(cbAcept.isChecked) register()
                    }
                }
            }
    }

    //27. La funcion goHome con un intent nos redirigimos a la pantalla donde se muestran los terminos y condiciones
    private fun goHome(email: String, provider: String){

        //28. Como nos interesa saber como inicio sesion el usuario ya sea con email, con google o facebook, esta informacon la guardamos en las variables estaticas
        useremail = email
        providerSession = provider

        //29. Nos redirigimos a otra activity con un intent
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }

    //30. La funcion registrer nos ayuda a registrar un usuario y escribirlo enla base de datos
    private fun register(){
        //31. Recuperamos el email y los password que nos dio el usuario
        email = etEmail.text.toString()
        password = etPassword.text.toString()
        password2 = etPassword2.text.toString()

        //63. Evaluamos si la contrasenas son las mismas, en caso de que no sean iguales se le muestra un mensaje al usuario
        if (password == password2){
            //32. Creamos una instancia de FirebaseAuth para llamar el metodo createUserWithEmailAndPassword y de esta forma registar a nuestro usuario
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    //33. Preguntamos con el objeto it del lambda si el registro fue exitoso
                    if (it.isSuccessful){

                        //34. Creamos una objeto fecha para guardar la fecha de registro
                        var dateRegister = SimpleDateFormat("dd/MM/yyyy").format(Date())
                        //35. Creamos una instancia de FirebaseFirestore que sera la que nos permite acceder a la base de datos
                        var dbRegister = FirebaseFirestore.getInstance()
                        //36. Accedemos a las colecciones donde indicamos el nombre de la coleccion y con el metodo .document creamos un nuevo documento
                        //37. Pasamos dentro de un hasMap los datos del usuario
                        dbRegister.collection("users").document(email).set(hashMapOf(
                            "user" to email,
                            "dateResiter" to dateRegister
                        ))

                        goHome(email, "email")
                    }
                    else Toast.makeText(this, "Error, algo ha salido mal :(", Toast.LENGTH_SHORT).show()
                }
        }
        else Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
    }

    //46. La funcion goTerms se ejecuta cuando se hace click en terimnos y condicines de uso y nos redirige a otra activity en donde esta esta informacion
    fun goTerms(view: View){
        val intent = Intent(this, TermsActivity::class.java)
        startActivity(intent)
    }
    //47. La funcion forgotPassword se ejecuta cuando se hace click sobre Olvidaste la contrasena?
    fun forgotPassword(view: View){
        //49. Se puede tanto mandar a una nueva activity como simplemente mandar un correo de restablecimiento de contrasena cuando se ejecute este metodo
        //startActivity(Intent(this, TermsActivity::class.java))
        resetPassword()
    }
    //50. La funcion resetPassword sera la encargada de enviar un correo de restablecimiento de contrasena
    private fun resetPassword(){
        var email = etEmail.text.toString()
        //51. Con la libreria TextUtils preguntamos si el email esta vacio
        if (!TextUtils.isEmpty(email)){
            //52. Con la varaible mAuth mandamos el correo de restablecimiento de contrasena
            mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    //53. Preguntamos si el envio de correo se ejecuto correctamente
                    if (task.isSuccessful) Toast.makeText(this, "Email enviadoa $email", Toast.LENGTH_SHORT ).show()
                    else Toast.makeText(this, "No se encontro el usuario con este correo", Toast.LENGTH_SHORT).show()
                }
        }
        else Toast.makeText(this, "Indica un email", Toast.LENGTH_SHORT).show()
    }

    //64. La funcion showPassword se ejecuta cada que se hace click sobre el boton del ojo de visibilidad de Password
    fun callShowPassword(view: View){
        showPassword(view)
    }
    //65. La funcion showPassword evalua si la contrasena se esta mostrando o no y lo cambia
    private fun showPassword(view: View){
        //66. Recuperamos el tipo de entrada del EditText pasword
        val password = etPassword.inputType
        val password2 = etPassword2.inputType

        //67. Casteamos el objeto view recuperado por un ImageButton para poder utilizar metodo y atributos de este objeto que no tiene el objeto view
        val ibEye = view as ImageButton

        //68. Evaluamos que boton fue el que se oprimio
        if (view.id == R.id.ibEye1){
            //69. Evaluamos si el passwor es de tipo TYPE_TEXT_VARIATION_PASSWORD el cual se corresponde con el numero 129
            if (password == 129){
                //70. Cambiamos la imagen del boton
                ibEye.setImageResource(R.drawable.ic_visibility)
                //71. Cambiamos el tipo de entrada del EditText de YPE_TEXT_VARIATION_PASSWORD a YPE_TEXT_VARIATION_VISIBLE_PASSWORD el cual se corresponde el numero 128
                etPassword.inputType = 128
            }else{
                findViewById<ImageButton>(R.id.ibEye1).setImageResource(R.drawable.ic_visibility_off)
                etPassword.inputType = 129
            }
        }
        if (view.id == R.id.ibEye2){
            if (password2 == 129){
                findViewById<ImageButton>(R.id.ibEye2).setImageResource(R.drawable.ic_visibility)
                etPassword2.inputType = 128
            }else{
                ibEye.setImageResource(R.drawable.ic_visibility_off)
                etPassword2.inputType = 129
            }
        }

    }

    //75. La funcion initOneTapSignIn configura la funcion de inicio de sesion en un toque de google
    private fun initOneTapSignin(){

        //76. Se configura el GoogleSignInOption llamando al builder el cual obtiene el email y construye el cliente
        //El error de la cadena string siempre se mostrar ya que no seta incluida en el archivo de string pero no pasa nada, esta se saca de una libreria de google
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        //77. Una vez guardado el cliente, se cierra la sesino para que cuando se oprimera nuevamente el boton de inicio de sesion con google vuelva a preguntar que cuenta elegir
        googleSignInClient.signOut()

        //78. Inicializamos el objeto auth
        auth = Firebase.auth

    }

    //72. La funcion callSignInGoogle se ejecuta cuando se hace click en el boton de google
    fun callSignInGoogle(view: View){
        signInGoogle()
    }
    //73. La funcion signInGoogle() Ejecuta el codigo necesario para registrar un usuario con su cuenta de google
    private fun signInGoogle(){
        val lyTerms = findViewById<LinearLayout>(R.id.lyTerms)

        if (lyTerms.visibility != View.VISIBLE){
            lyTerms.visibility = View.VISIBLE
        }else {
            val cbAccept = findViewById<CheckBox>(R.id.cbAcept)
            if (cbAccept.isChecked){
                //74.Se llama al intent de inicio de sesion de google
                val signInIntent = googleSignInClient.signInIntent
                startActivityForResult(signInIntent, RC_SIGN_IN)
            }
        }
    }

    //80.El metodo firebaseAuthWithGoogle obtiene las credenciales y llama al metodo goHome en caso de que todo salga bien
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser

                    if (user != null) {
                        val email = user.email.toString()
                        // Registramos al usuario tambien en nuestra base de datos en firestore
                        var dateRegister = SimpleDateFormat("dd/MM/yyyy").format(Date())
                        var dbRegister = FirebaseFirestore.getInstance()
                        dbRegister.collection("users").document(email).set(hashMapOf(
                            "user" to email,
                            "dateResiter" to dateRegister
                        ))

                        goHome(email, user.providerId)
                    }else showError("Facebook")
                } else {
                    // If sign in fails, display a message to the user.
                    showError("Google")
                }
            }
    }

    //86. La funcion callSignInFacecook se ejecuta cada vez que se hace click sobre el boton de inicio de sesion de facebook
    fun callSignInFacebook(view: View){
        signInFacebook()
    }
    //87. La funcion signInFacebook ejecuta el codigo necesario para registrar un usuario con facebook
    private fun signInFacebook(){

        val lyTerms = findViewById<LinearLayout>(R.id.lyTerms)

        if (lyTerms.visibility != View.VISIBLE){
            lyTerms.visibility = View.VISIBLE
        }else {
            val cbAccept = findViewById<CheckBox>(R.id.cbAcept)
            if (cbAccept.isChecked){
                //88. Se copia y pega el codigo proporcionado por google para el inicio de sesion de facebook

                //89. Obtenemos una instancia de un LoginManager para administrar el inicio de sesion y poder recuperar el email
                // no utilizamos las lineas que implementan un buttonFAcebookLogin ya que no estamos utilizando estos tipos de botones en el xml
                LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))
                LoginManager.getInstance().registerCallback(callbackManager, object :
                    FacebookCallback<LoginResult> {
                    //91. Se tiene diferentes metodos dependiendo de si el inicio de sesion se hizo correctamente o no
                    override fun onSuccess(result: LoginResult) {
                        //92. Con el objeto result obtenemos tanto el token como las credenciales y hacemos el registro
                        //93. Vamos a utilizar un let (esto se ve en la seccion de kotlin avanzado
                        result.let{
                            val token = it.accessToken
                            val credential = FacebookAuthProvider.getCredential(token.token)
                            mAuth.signInWithCredential(credential).addOnCompleteListener {
                                if (it.isSuccessful){
                                    email = it.result.user?.email.toString()
                                    // Registramos al usuario tambien en nuestra base de datos en firestore
                                    var dateRegister = SimpleDateFormat("dd/MM/yyyy").format(Date())
                                    var dbRegister = FirebaseFirestore.getInstance()
                                    dbRegister.collection("users").document(email).set(hashMapOf(
                                        "user" to email,
                                        "dateResiter" to dateRegister
                                    ))
                                    goHome(email,"Facebook")
                                }else{
                                    showError("Facebook")
                                }
                            }
                        }
                        //handleFacebookAccessToken(loginResult.accessToken)
                    }

                    override fun onCancel() {
                    }

                    override fun onError(error: FacebookException) {
                        //95. Se muestra el error
                        showError("Facebook")
                    }
                })
            }
        }

    }

    //94. La funcion showError sera solamente un Toast elcual se utiliza para mostrar el error que se tuvo
    private fun showError(provider: String){
        Toast.makeText(this, "Error de la conexion con $provider", Toast.LENGTH_SHORT).show()
    }



    //81. El metodo onActivityResult se sobreescribe para agregar lo propio de google
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //90. Se hace una llamada al callback manager antes de la llamada al super, el cual nos redirigira al Login Manager del metodo anterior.
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                showError("Google")
            }
        }

    }









}