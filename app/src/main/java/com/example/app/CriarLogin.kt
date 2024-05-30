package com.example.app

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app.databinding.ActivityCriarLoginBinding
import com.google.firebase.auth.FirebaseAuth

class CriarLogin : AppCompatActivity() {

    private lateinit var tela  :ActivityCriarLoginBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        tela = ActivityCriarLoginBinding.inflate(layoutInflater)
        setContentView(tela.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tela.btncadastrar.setOnClickListener {
            val nome = tela.nomeInput.text.toString()
            val email = tela.emailInput.text.toString()
            val senha = tela.senhaInput.text.toString()

            if(email.trim().isEmpty() || senha.trim().isEmpty() || nome.trim().isEmpty()){
                Toast.makeText(this , "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }else{
                auth = FirebaseAuth.getInstance()

                auth.createUserWithEmailAndPassword(email , senha)
                    .addOnCompleteListener {
                        Toast.makeText(this , "Usuário cadastrado com sucesso" , Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener{
                        Toast.makeText(this ,  "erro ao cadastrar" , Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}