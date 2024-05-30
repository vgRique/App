package com.example.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app.databinding.ActivityTelaHomeBinding
import com.google.firebase.auth.FirebaseAuth

class TelaHome : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    private lateinit var tela : ActivityTelaHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        tela = ActivityTelaHomeBinding.inflate(layoutInflater)
        setContentView(tela.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tela.signout.setOnClickListener {
            auth = FirebaseAuth.getInstance()

            auth.signOut()
            telaMain()

        }
    }
    private fun telaMain(){
        val telaMain = Intent(this , MainActivity::class.java)
        startActivity(telaMain)
        finish()
    }
}
