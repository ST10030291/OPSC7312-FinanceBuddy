package com.example.opsc7312_budgetbuddy.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.opsc7312_budgetbuddy.R
import com.example.opsc7312_budgetbuddy.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.LoginBtn.setOnClickListener{
            val email =  binding.emailInput.text.toString()
            val password =  binding.passwordInput.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty())
            {
                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if(task.isSuccessful)
                        {
                            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        else
                        {
                            Toast.makeText(this, "Login Unsuccessful", Toast.LENGTH_SHORT).show()

                        }
                    }

            }
            else
            {
                Toast.makeText(this, "Enter email and password", Toast.LENGTH_SHORT).show()

            }
        }

        binding.registerLink.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }
}