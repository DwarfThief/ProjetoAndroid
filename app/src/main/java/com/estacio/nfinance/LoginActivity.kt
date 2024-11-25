package com.estacio.nfinance

import ApiService
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
import com.estacio.nfinance.models.api.RegisterResponse
import retrofit2.Call
import retrofit2.Response

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

            // Referência ao botão de login
            val btnLogin = findViewById<Button>(R.id.btn_login)

            // Configurar o clique do botão
            btnLogin.setOnClickListener {
                // Criar uma intent para abrir a DashboardActivity
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
            }


//            val emailTextInput = findViewById<TextView>(R.id.edit_email).text.toString()
//            val passwordTextInput = findViewById<TextView>(R.id.edit_senha).text.toString()
//
//            val loginRequest = LoginRequest(email = emailTextInput, password = passwordTextInput)
//            val retrofitClient = RetrofitService.getRetrofitInstance("http://3.145.171.244:3002/")
//
//            val endpoint = retrofitClient.create(ApiService::class.java)
//            endpoint.postLogin(loginRequest).enqueue(object : retrofit2.Callback<LoginResponse> {
//                override fun onResponse(
//                    call: Call<LoginResponse>,
//                    response: Response<LoginResponse>
//                ) {
//                    if(response.isSuccessful) {
//                        val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
//                        startActivity(intent)
//                    }else {
//                        Toast.makeText(this@LoginActivity, "E-mail ou senha errado",
//                            Toast.LENGTH_LONG).show()
//                    }
//                    println(response)
//                }
//
//                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                    Toast.makeText(this@LoginActivity, "Falha na requisição: ${t.message}",
//                        Toast.LENGTH_LONG).show()
//                }
//            })
        }
    }
}