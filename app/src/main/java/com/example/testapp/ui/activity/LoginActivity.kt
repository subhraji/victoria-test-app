package com.example.testapp.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.testapp.MainActivity
import com.example.testapp.R
import com.example.testapp.databinding.ActivityLoginBinding
import com.example.testapp.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_login)

        binding.loginBtn.setOnClickListener {
            val email = binding.emailTxt.text.toString()
            val password = binding.passwordTxt.text.toString()

            if(email == "test@gmail.com" && password == "12345"){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,"Wrong Credential.", Toast.LENGTH_SHORT).show()
            }

        }
    }
}