package fi.oamk.androidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.math.log

class RegisterActivity : AppCompatActivity() {

    private lateinit var edUsername: EditText
    private lateinit var edEmail: EditText
    private lateinit var edPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var tvLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        edUsername = findViewById(R.id.username_editText_register)
        edEmail = findViewById(R.id.email_editText_register)
        edPassword = findViewById(R.id.password_editText_register)

        btnRegister = findViewById(R.id.btn_register)
        btnRegister.setOnClickListener {
            val username = edUsername.text.toString();
            val email = edEmail.text.toString();
            val password = edPassword.text.toString();

            Toast.makeText(this, "Username: " + username, Toast.LENGTH_LONG).show()
            Toast.makeText(this, "Email: " + email, Toast.LENGTH_LONG).show()
            Toast.makeText(this, "Password: " + password, Toast.LENGTH_LONG).show()
        }

        tvLogin = findViewById(R.id.tv_login)
        tvLogin.setOnClickListener{
            Toast.makeText(this, "Login", Toast.LENGTH_LONG).show()
        }

    }
}