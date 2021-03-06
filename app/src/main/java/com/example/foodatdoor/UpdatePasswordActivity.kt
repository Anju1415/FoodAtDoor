package com.example.foodatdoor

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
/** to update password if user want **/
class UpdatePasswordActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var passwordEt: EditText
    private lateinit var changePasswordBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_password)

        auth = FirebaseAuth.getInstance()

        passwordEt = findViewById(R.id.password_edt_text)

        changePasswordBtn = findViewById(R.id.reset_pass_btn)


        changePasswordBtn.setOnClickListener {
            var password: String = passwordEt.text.toString()
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show()
            } else {
                if (password.length < 6) {
                    Toast.makeText(
                        this,
                        "Password too short! , enter minimum 6 characters",
                        Toast.LENGTH_LONG
                    ).show()
                }
                auth.currentUser?.updatePassword(password)
                    ?.addOnCompleteListener(this, OnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Password changes successfully", Toast.LENGTH_LONG)
                                .show()
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "password not changed", Toast.LENGTH_LONG)
                                .show()
                        }
                    })
            }
        }
        setToolBar()
    }

    private fun setToolBar(){
        supportActionBar?.title="Update Password"
        supportActionBar?.setHomeButtonEnabled(true)//enables the button on the tool bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)//displays the icon on the button
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_arrow)//change icon to custom
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            android.R.id.home->{
                super.onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}