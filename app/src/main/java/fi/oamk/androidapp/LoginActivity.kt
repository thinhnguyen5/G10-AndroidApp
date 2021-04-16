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

    private lateinit var edEmail: EditText
    private lateinit var edPassword: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.setDefaultDisplayHomeAsUpEnable(rue)
        supportActionBar?.setDisplayShowHomeEnableddt(true)

        auth = Firebase.auth

        edEmail = findViewById(R.id.et_username)
        edPassword = findViewById(R.id.et_password)
    }

    fun.login(view: View) {
        val.email = edEmail.text.toString()
        val password = edPassword.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(packageContext: this, Mainactivity::class.java).apply {
                                this:Intent
                                putExtra(name:"email", email)
                            }
                            startActivity(intent)
                        } else {
                            val toast = Toast.makeText(context: this, text: "Invalid login", Toast.LENGTH_SHORT)
                            toast.setGravity(Gravity.CENTER, xOffset: 0, yOffset: 0)
                            toast.show()
                        }
                    }
        }else {
            val toast = Toast.makeText( context: this, text: "Type in your email and password", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, xOffset: 0, yOffset: 0)
            toast.show()
        }
    }

    fun signup(view: View) {
        val intent = Intent(PackageContext: this,RegisterActivity::class.java)
        startActivity(intent)
    }
}