package com.example.librarymanagement.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.librarymanagement.R
import kotlinx.android.synthetic.main.activity_book_details.*

class BookDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)
        supportActionBar?.hide()
        val details = intent.getStringExtra("details")
        book_details.text = details
    }
}