package com.example.librarymanagement.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.librarymanagement.R
import com.example.librarymanagement.ui.messages.EMPTY
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        // Firebase Authentication
        auth = FirebaseAuth.getInstance()

        //intent to login
        create_account.setOnClickListener {
            loginToSignup()
        }

        //sigInUser with Firebase
        login_user.setOnClickListener {
            signInFields()
            loginToBooks()
        }
    }

    private fun signInFields(){

        val email = login_email.text.toString()
        val password = login_password.text.toString()

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(this, "Field is Empty", Toast.LENGTH_SHORT).show()
        }
        else{
            signInUser(email, password)
        }
    }

    private fun signInUser(email:String, password:String){

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { user->
            if (user.isSuccessful){
                val intent = Intent(this, BooksActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, EMPTY, Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun loginToSignup(){
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }

    private fun loginToBooks(){

        val intent = Intent(this, BooksActivity::class.java)
        startActivity(intent)
    }
}