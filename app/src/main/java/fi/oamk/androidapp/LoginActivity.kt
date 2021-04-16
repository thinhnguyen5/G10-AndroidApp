package fi.oamk.androidapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var edEmail: EditText
    private lateinit var edPassword: EditText
//    private lateinit var btnLogin: Button

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        auth = Firebase.auth

        edEmail = findViewById(R.id.et_username)
        edPassword = findViewById(R.id.et_password)
    }

    fun login(view: View) {
        val email = edEmail.text.toString()
        val password = edPassword.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) {task ->
                        if(task.isSuccessful) {
                            val intent = Intent(this,MainActivity::class.java).apply {
                                putExtra("email",email)
                          }
                            startActivity(intent)
                        }else{
                            val toast = Toast.makeText(this, "Invalid login", Toast.LENGTH_SHORT)
                            toast.setGravity(Gravity.CENTER,0,0)
                            toast.show()
                        }
                    }
        }else {
            val toast = Toast.makeText(this, "Type in your email and password", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER,0,0)
            toast.show()
        }
    }

//    override fun onSupportNavigateUp(): Boolean {
//        onBackPressed()
//        return super.onSupportNavigateUp()
//
//    }
    fun signup(view: View) {
        val intent = Intent(this,RegisterActivity::class.java)
        startActivity(intent)
    }
}