package fi.oamk.androidapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var edEmail: EditText
    private lateinit var edPassword: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        edEmail = findViewById(R.id.et_username)
        edPassword = findViewById(R.id.et_password)

        btnLogin = findViewById(R.id.btn_login)
        btnLogin.setOnClickListener {
            val email = edEmail.text.toString().trim();
            val password = edPassword.text.toString().trim();
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
        }

 }
    fun signup(view: View) {
        val intent = Intent(this,RegisterActivity::class.java)
        startActivity(intent)
    }
}