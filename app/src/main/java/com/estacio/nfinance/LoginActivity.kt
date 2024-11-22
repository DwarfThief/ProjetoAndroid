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
import com.estacio.nfinance.models.api.LoginResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        // Configuração de padding para a janela
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obter o TextView pelo ID
        val textCadastro = findViewById<TextView>(R.id.text_cadastro)

        // Definir o listener para o clique
        textCadastro.setOnClickListener {
            // Iniciar a atividade de cadastro
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Obter o botão pelo ID
        val btnLogin = findViewById<Button>(R.id.btn_login)

        // Alterar o destino para MainPageActivity
        btnLogin.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val loginRequest = LoginRequest(email = findViewById<TextView>(R.id.edit_email).toString(), password = findViewById<TextView>(R.id.edit_senha).toString())

                try {
                    val response = RetrofitInstance.api.postLogin(loginRequest).execute()
                    if (response.isSuccessful) {
                        // Iniciar a atividade de cadastro
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@LoginActivity, "E-mail ou senha errado",
                            Toast.LENGTH_LONG).show();
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@LoginActivity, "E-mail ou senha errado",
                        Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}