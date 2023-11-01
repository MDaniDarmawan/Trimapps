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

class RegisterActivity : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var githubUsernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button
    private lateinit var loginTextView: TextView

    private lateinit var mBinding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inisialisasi elemen UI
        usernameEditText = findViewById(R.id.et_nama_pengguna)
        emailEditText = findViewById(R.id.et_email)
        githubUsernameEditText = findViewById(R.id.et_github)
        passwordEditText = findViewById(R.id.et_kata_sandi)
        registerButton = findViewById(R.id.button_daftar)
        loginTextView = findViewById(R.id.masuk)

        // Tambahkan event listener untuk tombol register
        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val email = emailEditText.text.toString()
            val githubUsername = githubUsernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (username.isNotEmpty() && email.isNotEmpty() && githubUsername.isNotEmpty() && password.isNotEmpty()) {
                Toast.makeText(this, "Pendaftaran Berhasil!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Pendaftaran Gagal!", Toast.LENGTH_SHORT).show()
            }

            // Lakukan validasi data jika diperlukan

            // Lakukan operasi pendaftaran atau penyimpanan data ke server atau database
        }
        loginTextView.setOnClickListener {
            // Arahkan ke halaman pendaftaran
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
