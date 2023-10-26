package com.example.trimapps

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trimapps.databinding.LoginActivityBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        emailFocusListener()
        passwordFocusListener()
    }

    private fun emailFocusListener()
    {
        binding.etEmail.setOnFocusChangeListener { _, focused ->
            if (!focused)
            {
        binding.tilEmail.helperText = validEmail()
    }
}
}
        private fun validEmail(): String?
        {
        val emailText = binding.etEmail.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches())
        {
            return "Email kurang lengkap"
    }
        return null
    }

    private fun passwordFocusListener()
    {
        binding.etKataSandi.setOnFocusChangeListener{ _, focused ->
            if (!focused)
            {
                binding.tilKataSandi.helperText = validPassword()
            }
        }
    }
    private fun validPassword(): String?
    {
        val passwordText = binding.etKataSandi.text.toString()
        if (passwordText.length < 8)
        {
            return "Kata sandi minimal 8 karakter"
        }
        if (!passwordText.matches(".*[A-Z].*".toRegex()))
        {
            return "Kata sandi harus mengandung 1 huruf kapital"
        }
        if (!passwordText.matches(".*[a-z].*".toRegex()))
        {
            return "Kata sandi harus mengandung 1 huruf kecil"
        }
        if (!passwordText.matches(".*[@#\$%^&+=].*".toRegex()))
        {
            return "Kata sandi harus mengandung 1 spesial karakter (@#\$%^&+=)"
        }
        return null
    }
}