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
    private lateinit var usernameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var githubUsernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button
    private lateinit var loginTextView: TextView

    private lateinit var mBinding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private val usersReference = database.getReference("users")


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


        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        // Tambahkan event listener untuk tombol register
        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val email = emailEditText.text.toString()
            val githubUsername = githubUsernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            data class User(
                val username: String,
                val usergithub: String,

                )

            val user = User(username, githubUsername)
            if (username.isNotEmpty() && password.isNotEmpty() && githubUsername.isNotEmpty() && email.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
                            val userId = auth.currentUser?.uid
                            if (userId != null) {
                                usersReference.child(userId).setValue(user)
                            }
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this,
                                "Registrasi Gagal: " + task.exception?.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Harap isi semua kolom yang ada", Toast.LENGTH_SHORT).show()
            }
            loginTextView.setOnClickListener {
                // Arahkan ke halaman pendaftaran
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
}







