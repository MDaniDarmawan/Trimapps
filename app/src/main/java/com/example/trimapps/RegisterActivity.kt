package com.example.trimapps

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trimapps.databinding.ActivityRegisterBinding
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.auth.User

class RegisterActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityRegisterBinding
    private  lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.masuk.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.buttonDaftar.setOnClickListener{
            val user = binding.etNamaPengguna.text.toString()
            val email = binding.etEmail.text.toString()
            val github = binding.etGithub.text.toString()
            val pass = binding.etKataSandi.text.toString()


            if (user.isNotEmpty() && email.isNotEmpty() && github.isNotEmpty() && pass.isNotEmpty()){

                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener{
                    if (it.isSuccessful){
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, it.exception.toString() , Toast.LENGTH_SHORT).show()
                    }
                }

            }else{
                Toast.makeText(this, "Kolom harus diisi semua !!!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}



