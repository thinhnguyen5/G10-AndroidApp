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
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
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

        auth = FirebaseAuth.getInstance();

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

//             Firebase Authentication to create a user with email and password
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "Registration Successfully!", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                    }
                    else {
                        Toast.makeText(this, "Registration Fail!", Toast.LENGTH_LONG).show()
                    }
                }
        }

        tvLogin = findViewById(R.id.tv_login)
        tvLogin.setOnClickListener {
//            Toast.makeText(this, "Login", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, LoginActivity ::class.java))
        }

    }

    //Check user is Logined or not
    override fun onStart() {
        super.onStart()
        val user = auth.currentUser;
        if (user != null) {
            startActivity(Intent(this, MainActivity::class.java))
        }
        else {
            Log.e("user status: ", "empty!");
        }
    }
}