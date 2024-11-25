package com.estacio.nfinance

import ApiService
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.estacio.nfinance.models.api.ListTransactionResponse
import com.estacio.nfinance.models.api.RegisterResponse
import retrofit2.Call
import retrofit2.Response

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referencia a View com id "spent"
        val spentView = findViewById<View>(R.id.spent)

        // Adiciona o OnClickListener
        spentView.setOnClickListener {
            // Cria o Intent para ir para a EditActivity
            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent) // Inicia a EditActivity
        }

        // Referencie a View com o ID "create"
        val createButton = findViewById<View>(R.id.create)

        // Adicione o OnClickListener
        createButton.setOnClickListener {
            // Crie um Intent para abrir a CreateActivity
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent) // Inicia a nova Activity
        }

        // TODO: Criar função para pegar o Id do user
        val userId = "66909ad31211260fe9c2a6bd"
        val typeTransaction = 0

        val retrofitClient = RetrofitService.getRetrofitInstance("http://localhost:3001/")
        val endpoint = retrofitClient.create(ApiService::class.java)

        endpoint.getTransactionsByType(userId,typeTransaction).enqueue(object : retrofit2.Callback<ListTransactionResponse> {
            override fun onResponse(
                call: Call<ListTransactionResponse>,
                response: Response<ListTransactionResponse>
            ) {
                if (response.isSuccessful) {
                    // TODO: Alimentar o recyclerview
                } else {
                    Toast.makeText(this@DashboardActivity, "Falha na requisição: ${response.body().toString()}",
                        Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ListTransactionResponse>, t: Throwable) {
                Toast.makeText(this@DashboardActivity, "Falha na requisição: ${t.message}",
                    Toast.LENGTH_LONG).show()
            }
        })
    }
}