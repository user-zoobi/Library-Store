package com.example.librarymanagement.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.librarymanagement.R
import com.example.librarymanagement.ui.adapter.BooksAdapter
import com.example.librarymanagement.ui.messages.EMPTY
import com.example.librarymanagement.ui.modelClass.Books
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_books.*

class BooksActivity : AppCompatActivity() {

    private lateinit var list: ArrayList<Books>
    private lateinit var auth: FirebaseAuth
    private lateinit var dbRef: FirebaseDatabase
    private lateinit var adapter: BooksAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)
        supportActionBar?.hide()

        // Firebase Authentication
        auth = FirebaseAuth.getInstance()
        dbRef = FirebaseDatabase.getInstance()
        list = ArrayList()
        adapter = BooksAdapter(this, list)
        recyclerView()
        booksList()

    }

    private fun booksList() {

        dbRef.getReference().child("books").addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for (x in snapshot.children) {
                    val values = x.getValue(Books::class.java)
                        list.add(values!!)

                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    private fun recyclerView() {

        booksRecyclerView.adapter = adapter
        booksRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}
