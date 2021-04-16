package fi.oamk.androidapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var edUsername: EditText
    private lateinit var edPassword: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        edUsername = findViewById(R.id.et_username)
        edPassword = findViewById(R.id.et_password)

        btnLogin = findViewById(R.id.btn_login)
        btnLogin.setOnClickListener {
            val username = edUsername.text.toString().trim();
            val password = edPassword.text.toString().trim();

            if (username.isEmpty()) {
                edUsername.setError("Enter your Username");
                edUsername.requestFocus();
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
        }
    }

    fun signup(view: View) {
        val intent = Intent(this,RegisterActivity::class.java)
        startActivity(intent)
    }
}