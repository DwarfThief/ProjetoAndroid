package com.estacio.nfinance

import ApiService
import Transaction
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.estacio.nfinance.models.api.ListTransactionResponse
import com.estacio.nfinance.models.api.RegisterResponse
import retrofit2.Call
import retrofit2.Response

class DashboardActivity : AppCompatActivity() {
    inner class TransactionAdapter(private val transactions: List<Transaction>) :
        RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

        inner class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val name: TextView = itemView.findViewById(R.id.spent_title)
            val description: TextView = itemView.findViewById(R.id.spent_description)
            val amount: TextView = itemView.findViewById(R.id.spent_value)
            val date: TextView = itemView.findViewById(R.id.spent_date)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_transaction, parent, false)
            return TransactionViewHolder(view)
        }

        override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
            val transaction = transactions[position]
            holder.name.text = transaction.name
            holder.amount.text = "R$ ${transaction.amount}"
            holder.description.text = transaction.description
            holder.date.text = transaction.date
        }

        override fun getItemCount(): Int = transactions.size
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
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
        val userId = "6743211137dbc7da9dc596a4"

        val retrofitClient = RetrofitService.getRetrofitInstance("http://3.145.171.244:3002/")
        val endpoint = retrofitClient.create(ApiService::class.java)

        endpoint.getTransactionsByType(userId).enqueue(object : retrofit2.Callback<ListTransactionResponse> {
            override fun onResponse(
                call: Call<ListTransactionResponse>,
                response: Response<ListTransactionResponse>
            ) {
                if (response.isSuccessful) {
                    val transactions = response.body()?.data ?: emptyList()

                    // Configurar o RecyclerView
                    val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
                    recyclerView.layoutManager = LinearLayoutManager(this@DashboardActivity)
                    recyclerView.adapter = TransactionAdapter(transactions)
                } else {
                    Toast.makeText(
                        this@DashboardActivity,
                        "Falha na requisição: ${response.body().toString()}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<ListTransactionResponse>, t: Throwable) {
                Toast.makeText(this@DashboardActivity, "Falha na requisição: ${t.message}",
                    Toast.LENGTH_LONG).show()
            }
        })
    }
}