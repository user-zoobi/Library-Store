package com.example.librarymanagement.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.librarymanagement.R
import com.example.librarymanagement.ui.messages.EMPTY
import com.example.librarymanagement.ui.messages.INCORRECT
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var dbRef: FirebaseDatabase
    private val entry = HashMap<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        supportActionBar?.hide()

        // Firebase Authentication
        auth = FirebaseAuth.getInstance()
        dbRef = FirebaseDatabase.getInstance()

        //Firebase RealtimeDatabase


        signup_user.setOnClickListener {
            signupUser()
        }

    }

    private fun signupUser(){

        val email = signup_email.text.toString()
        val password = signup_password.text.toString()
        val name = signup_password.text.toString()

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)|| TextUtils.isEmpty(name)){
            Toast.makeText(this, EMPTY, Toast.LENGTH_SHORT).show()
        }
        else{
            createUser(name, email, password)
        }

        //Firebase RealTime Database
        entry.put("name", name)
        entry.put("email", email)
        entry.put("password", password)
        FirebaseDatabase.getInstance().getReference().child("users").setValue(entry)

    }

    private fun createUser(name:String, email:String, password:String){


        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { user->
            if (user.isSuccessful){
                signUpToLogin()
            }
            else{
                Toast.makeText(this, INCORRECT, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signUpToLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

}