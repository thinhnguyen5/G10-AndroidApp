package fi.oamk.androidapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_changepassword.*

class ChangepasswordActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_changepassword)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        auth = FirebaseAuth.getInstance()

        btn_change_password.setOnClickListener {
            changePassword ()
        }
    }

    private fun changePassword() {

        if (et_current_password.text.isNotEmpty() &&
                et_new_password.text.isNotEmpty() &&
                et_confirm_password.text.isNotEmpty() ) {

            if (et_new_password.text.toString().equals(et_confirm_password.text.toString())) {

                val user = auth.currentUser
                if(user != null && user.email != null) {
                    val credential = EmailAuthProvider
                            .getCredential(user.email!!, et_current_password.text.toString())

                    user?.reauthenticate(credential)
                    ?.addOnCompleteListener {
                        if(it.isSuccessful) {
                        Toast.makeText(this, "Re-Authentication success",
                        Toast.LENGTH_SHORT).show()
                            user!!.updatePassword(et_new_password.text.toString())
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            Toast.makeText(this, "Password changed successfully.", Toast.LENGTH_SHORT).show()
                                            auth.signOut()
                                            startActivity(Intent(this, LoginActivity::class.java))
                                            finish()
                                        }
                                    }
                        }else{
                            Toast.makeText(this, "Re-Authentication failed",
                                    Toast.LENGTH_SHORT).show()
                        }
                    }

                }else{
                    startActivity(Intent(this,LoginActivity::class.java))
                    finish()
                }

            }else {
                Toast.makeText(this,"Password mismactching.",Toast.LENGTH_SHORT).show()
            }

        }else{
            Toast.makeText(this,"Please enter all the fields.",Toast.LENGTH_SHORT).show()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}