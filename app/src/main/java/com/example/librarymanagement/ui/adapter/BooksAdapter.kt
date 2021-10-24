package com.example.librarymanagement.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.librarymanagement.R
import com.example.librarymanagement.ui.BookDetails
import com.example.librarymanagement.ui.modelClass.Books
import kotlinx.android.synthetic.main.books_viewholder.view.*

class BooksAdapter(val context:Context,val list:List<Books>): RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    inner class BooksViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.books_viewholder, parent, false)
        return BooksViewHolder(view)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val data = list[position]
        holder.itemView.apply {
            book_title.text = data.title
            book_genre.text = data.genre
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(context, BookDetails::class.java)
            intent.putExtra("details",list[position].details)
            context.startActivity(intent)
        }
    }



    override fun getItemCount(): Int {
       return list.size
    }
}