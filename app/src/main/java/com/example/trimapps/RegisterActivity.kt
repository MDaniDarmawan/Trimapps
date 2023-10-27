package com.example.trimapps

import android.content.Intent
import android.os.Bundle
import android.text.Selection
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trimapps.Database.User
import com.example.trimapps.Database.UserRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var usergithubEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var registerButton: Button
    private lateinit var loginText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        val database = UserRoomDatabase.getDatabase(applicationContext)
        val userDao = database.UserDao()

        usernameEditText = findViewById(R.id.til_nama_pengguna)
        passwordEditText = findViewById(R.id.til_kata_sandi)
        usergithubEditText = findViewById(R.id.til_github)
        emailEditText = findViewById(R.id.til_email)
        registerButton = findViewById(R.id.button_daftar)
        loginText = findViewById(R.id.masuk)

        loginText.makeLinks(
            Pair("Masuk sekarang!", View.OnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }))

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val usergithub = usergithubEditText.text.toString()
            val email = emailEditText.text.toString()


            if (username.isNotEmpty() && password.isNotEmpty() && usergithub.isNotEmpty() && email.isNotEmpty()) {
                // Check if the username is already in use
                CoroutineScope(Dispatchers.IO).launch {
                    val existingUser = userDao.getUserByUsername(username)
                    if (existingUser == null) {
                        // User doesn't exist, proceed with registration
                        val newUser = User(userApp = username, password = password, userGithub = usergithub, email = email)
                        userDao.insert(newUser)
                        runOnUiThread {
                            Toast.makeText(this@RegisterActivity, "Data berhasil disimpan, silahkan kembali", Toast.LENGTH_SHORT).show()
                        }
                        // Optionally, you can navigate to the login page or perform other actions.
                    } else {
                        // Username already in use, show an error message.
                        runOnUiThread {
                            Toast.makeText(this@RegisterActivity, "Username telah digunakan", Toast.LENGTH_SHORT).show()
                        }
                        // You may want to use runOnUiThread or LiveData to update the UI.
                    }
                }
            } else {
                Toast.makeText(this, "Harap isi semua kolom yang ada", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

private fun TextView.makeLinks(vararg links: Pair<String, View.OnClickListener>) {
    val spannableString = SpannableString(this.text)
    var startIndexOfLink = -1
    for (link in links) {
        val clickableSpan = object : ClickableSpan() {
            override fun updateDrawState(textPaint: TextPaint) {
                // use this to change the link color
                textPaint.color = textPaint.linkColor
                // toggle below value to enable/disable
                // the underline shown below the clickable text
                textPaint.isUnderlineText = true
            }

            override fun onClick(view: View) {
                Selection.setSelection((view as TextView).text as Spannable, 0)
                view.invalidate()
                link.second.onClick(view)
            }
        }
        startIndexOfLink = this.text.toString().indexOf(link.first, startIndexOfLink + 1)
//      if(startIndexOfLink == -1) continue // todo if you want to verify your texts contains links text
        spannableString.setSpan(
            clickableSpan, startIndexOfLink, startIndexOfLink + link.first.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    this.movementMethod =
        LinkMovementMethod.getInstance() // without LinkMovementMethod, link can not click
    this.setText(spannableString, TextView.BufferType.SPANNABLE)
}
