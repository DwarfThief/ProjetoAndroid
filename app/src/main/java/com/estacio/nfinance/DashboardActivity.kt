package com.estacio.nfinance

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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
    }
}