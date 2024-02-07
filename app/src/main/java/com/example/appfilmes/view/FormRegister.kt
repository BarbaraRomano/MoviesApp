package com.example.appfilmes.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.appfilmes.R
import com.example.appfilmes.databinding.ActivityFormCadastroBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class FormRegister : AppCompatActivity() {

    private lateinit var binding: ActivityFormCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        window.statusBarColor = Color.parseColor("#FFFFFF")
        binding.emailRegister.requestFocus()

        binding.btnGo.setOnClickListener {
            val emailRegister = binding.emailRegister.text.toString()

            when {
                emailRegister.isEmpty() -> {
                    binding.emailRegisterContainer.helperText = getString(R.string.emailRequired)
                    binding.emailRegisterContainer.boxStrokeColor = Color.parseColor("#FF0000")
                }

                emailRegister.isNotEmpty() -> {
                    binding.emailRegisterContainer.helperText = getString(R.string.empty)
                    binding.passwordRegister.requestFocus()
                    binding.passwordRegisterContainer.visibility = View.VISIBLE
                    binding.btnGo.visibility = View.GONE
                    binding.btnKeep.visibility = View.VISIBLE
                    binding.containerHeader.visibility = View.VISIBLE
                    binding.txtTitle.setText(R.string.title)
                    binding.txtDescription.setText(R.string.description)
                }
            }
        }
        binding.btnKeep.setOnClickListener {
            val emailRegister = binding.emailRegister.text.toString()
            val passwordRegister = binding.passwordRegister.text.toString()

            if (emailRegister.isNotEmpty() && passwordRegister.isNotEmpty()) {
                register(emailRegister, passwordRegister)
                binding.emailRegisterContainer.boxStrokeColor = Color.parseColor("#646464")
                binding.passwordRegisterContainer.boxStrokeColor = Color.parseColor("#646464")
                binding.emailRegisterContainer.helperText = getString(R.string.empty)
                binding.passwordRegisterContainer.helperText = getString(R.string.empty)
            } else if (passwordRegister.isEmpty()) {
                binding.passwordRegisterContainer.helperText = getString(R.string.passwordRequired)
                binding.passwordRegisterContainer.boxStrokeColor = Color.parseColor("#FF0000")
                binding.emailRegisterContainer.boxStrokeColor = Color.parseColor("#646464")
            } else if (emailRegister.isEmpty()) {
                binding.emailRegisterContainer.helperText = getString(R.string.emailRequired)
                binding.emailRegisterContainer.boxStrokeColor = Color.parseColor("#FF0000")
                binding.passwordRegisterContainer.boxStrokeColor = Color.parseColor("#646464")
            }
        }
        binding.txtLogin.setOnClickListener {
            val intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
            finish()

        }
    }

    private fun register(email: String, password: String) {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { register ->
                if (register.isSuccessful) {
                    Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT)
                        .show()
                    binding.emailRegisterContainer.boxStrokeColor = Color.parseColor("#646464")
                    binding.passwordRegisterContainer.boxStrokeColor = Color.parseColor("#646464")
                    binding.emailRegisterContainer.helperText = getString(R.string.empty)
                    binding.passwordRegisterContainer.helperText = getString(R.string.empty)
                }
            }.addOnFailureListener {
                val error = it

                when {
                    error is FirebaseAuthWeakPasswordException -> {
                        binding.passwordRegisterContainer.helperText =
                            "Digite uma senha com no minimo 6 caracteres."

                        binding.passwordRegisterContainer.boxStrokeColor =
                            Color.parseColor("#FF0000")

                    }

                    error is FirebaseAuthUserCollisionException -> {
                        binding.emailRegisterContainer.helperText =
                            "Conta já cadastrada!"

                        binding.emailRegisterContainer.boxStrokeColor =
                            Color.parseColor("#FF0000")

                    }

                    error is FirebaseNetworkException -> {
                        binding.passwordRegisterContainer.helperText =
                            "Sem conexão com a internet!"

                        binding.emailRegisterContainer.boxStrokeColor =
                            Color.parseColor("#FF0000")

                    }

                    else -> {
                        Toast.makeText(this, "Erro inesperado!", Toast.LENGTH_SHORT)
                            .show()
                    }

                }
            }
    }
}
