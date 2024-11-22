package com.estacio.nfinance

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.estacio.nfinance.models.api.LoginRequest
import com.estacio.nfinance.models.api.RegisterRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obter o botão pelo ID
        val btnLogin = findViewById<Button>(R.id.btn_login)

        // Alterar o destino para MainPageActivity
        btnLogin.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val registerRequest = RegisterRequest(
                    name = findViewById<TextView>(R.id.edit_name).toString(),
                    email = findViewById<TextView>(R.id.edit_email).toString(),
                    password = findViewById<TextView>(R.id.edit_senha).toString(),
                    passwordVerify = findViewById<TextView>(R.id.confirm_senha).toString()
                )

                try {
                    val response = RetrofitInstance.api.postRegister(registerRequest).execute()
                    if (response.isSuccessful) {
                        // Iniciar a atividade de cadastro
                        val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@RegisterActivity, "Dados inválidos ou e-mail já cadastrado",
                            Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@RegisterActivity, "Dados inválidos ou e-mail já cadastrado",
                        Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}