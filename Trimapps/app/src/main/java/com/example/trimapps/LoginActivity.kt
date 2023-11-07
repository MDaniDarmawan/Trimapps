package com.example.trimapps

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trimapps.MainActivity
import com.example.trimapps.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inisialisasi elemen UI
        emailEditText = findViewById(R.id.et_email)
        passwordEditText = findViewById(R.id.et_kata_sandi)
        loginButton = findViewById(R.id.button_masuk)
        registerTextView = findViewById(R.id.daftar)

        // Tambahkan event listener untuk tombol login
        loginButton.setOnClickListener(View.OnClickListener {
            if (emailEditText.text.toString() == "user" && passwordEditText.text.toString() == "1234") {
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
            }
        })
        registerTextView.setOnClickListener {
            // Arahkan ke halaman pendaftaran
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
