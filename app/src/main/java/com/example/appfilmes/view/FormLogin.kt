package com.example.appfilmes.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.appfilmes.R
import com.example.appfilmes.databinding.ActivityFormLoginBinding
import com.google.firebase.auth.FirebaseAuth

class FormLogin : AppCompatActivity() {

    private lateinit var binding: ActivityFormLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        window.statusBarColor = Color.parseColor("#FF000000")

        binding.subscribe.setOnClickListener {
            val intent = Intent(this, FormRegister::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                binding.emailContainer.helperText = getString(R.string.empty)
                binding.emailContainer.helperText = getString(R.string.empty)
                binding.emailContainer.boxStrokeColor = Color.parseColor("#646464")
                binding.passwordContainer.helperText = getString(R.string.empty)
                binding.passwordContainer.helperText = getString(R.string.empty)
                binding.passwordContainer.boxStrokeColor = Color.parseColor("#646464")
                autentication(email, password)
            } else if (email.isEmpty()) {
                binding.emailContainer.helperText = "Preencha o e-mail!"
            } else if (password.isEmpty()) {
                binding.passwordContainer.helperText = "Preencha a senha!"

            }
        }
    }

    private fun autentication(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { autentication ->
                if (autentication.isSuccessful) {
                    Toast.makeText(this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show()
                    navigation()
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Erro ao efetuar o login!", Toast.LENGTH_SHORT).show()
            }
    }

    private fun navigation() {
        val intent = Intent(this, MainScreen::class.java)
        startActivity(intent)
        finish()
    }
    override fun onStart(){
        super.onStart()

        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser != null){
            navigation()
        }

    }
}

