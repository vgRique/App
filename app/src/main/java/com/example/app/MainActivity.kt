package com.example.app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    private lateinit var tela : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        tela = ActivityMainBinding.inflate(layoutInflater)
        setContentView(tela.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tela.btn.setOnClickListener {
            val email = tela.email.text.toString()
            val senha = tela.senha.text.toString()

            if(email.trim().isEmpty() || senha.trim().isEmpty()){
                Toast.makeText(this , "Preencha todos os campos" , Toast.LENGTH_SHORT).show()
            }else{
                auth = FirebaseAuth.getInstance()

                auth.signInWithEmailAndPassword(email , senha)
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            telaHome()
                        }
                    }.addOnFailureListener{
                        Toast.makeText(this , "Erro ao fazer login" , Toast.LENGTH_SHORT).show()
                    }


            }

        }
        tela.reset.setOnClickListener {
            telaReset()
        }
        tela.cadastrar.setOnClickListener {
            telaCadastrar()
        }
    }
    private fun telaHome(){
        val telaHome = Intent(this , TelaHome::class.java)
        startActivity(telaHome)
        finish()
    }

    private fun telaReset(){
        val telaReset = Intent(this ,  Reset::class.java)
        startActivity(telaReset)
        finish()
    }
    private fun telaCadastrar(){
        val telaCadastrar = Intent(this , CriarLogin::class.java)
        startActivity(telaCadastrar)
        finish()
    }
}