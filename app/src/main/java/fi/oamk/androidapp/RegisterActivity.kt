package fi.oamk.androidapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var edUsername: EditText
    private lateinit var edEmail: EditText
    private lateinit var edPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var tvLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth

        edUsername = findViewById(R.id.username_editText_register)
        edEmail = findViewById(R.id.email_editText_register)
        edPassword = findViewById(R.id.password_editText_register)

        btnRegister = findViewById(R.id.btn_register)
        btnRegister.setOnClickListener {
            val username = edUsername.text.toString().trim();
            val email = edEmail.text.toString().trim();
            val password = edPassword.text.toString().trim();

            if (username.isEmpty()) {
                edUsername.setError("Enter your Username");
                edUsername.requestFocus();
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                edEmail.setError("Enter your Email");
                edEmail.requestFocus();
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                edEmail.setError("Please provide valid email");
                edEmail.requestFocus();
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                edPassword.setError("Enter your password");
                edPassword.requestFocus();
                return@setOnClickListener
            }
            if (password.length < 6) {
                edPassword.setError("Password must be more than 6 letters");
                edPassword.requestFocus();
                return@setOnClickListener;
            }
//
//            // Firebase Authentication to create a user with email and password
////            auth.createUserWithEmailAndPassword(email, password)
////                .addOnCompleteListener{
////                    if (!it.isSuccessful) {
////                        Toast.makeText(this, "create Fail", Toast.LENGTH_LONG).show()
////                        return@addOnCompleteListener
////                    }
////                    else {
////                        // else if successful
////                        val user = auth.currentUser
////                        Toast.makeText(this, "Successfully created user with uid: " + user.uid, Toast.LENGTH_LONG).show()
//////                        Log.d("RegisterActivity", "Successfully created user with uid: ${it.result.user.uid}")
////                    }
////                }
        }

        tvLogin = findViewById(R.id.tv_login)
        tvLogin.setOnClickListener {
//            Toast.makeText(this, "Login", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, MainActivity ::class.java))
        }

    }
}