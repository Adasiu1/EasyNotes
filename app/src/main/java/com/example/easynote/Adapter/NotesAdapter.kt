package com.example.easynote.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.SearchEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsAnimationControlListener
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.easynote.Models.Note
import com.example.easynote.R
import kotlin.random.Random

@Suppress("UNUSED_EXPRESSION")
class NotesAdapter(private val context: Context, val listener: NotesClickListener) : RecyclerView.Adapter<NotesAdapter.NoteViewModel>() {

    private val NotesList = ArrayList<Note>()
    private val FullList = ArrayList<Note>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewModel {
        return NoteViewModel(
            LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewModel, position: Int) {
        val currentNote = NotesList[position]
        holder.title.text = currentNote.title
        holder.title.isSelected = true

        holder.notes_layout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(), null))

        holder.notes_layout.setOnClickListener {
            listener.onItemClicked(NotesList[holder.adapterPosition])
        }
        holder.notes_layout.setOnClickListener {
            listener.onLongItemClicked(NotesList[holder.adapterPosition], holder.notes_layout)
            true
        }


    }

    override fun getItemCount(): Int {
        return NotesList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(NewList: List<Note>){
        FullList.clear()
        FullList.addAll(NewList)
        NotesList.clear()
        NotesList.addAll(FullList)
        notifyDataSetChanged()

    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(search: String){

        NotesList.clear()

        for(item in FullList){
            if (item.title?.lowercase()?.contains(search.lowercase()) == true || item.note?.lowercase()?.contains(search.lowercase()) == true){
                NotesList.add(item)
            }
        }
        notifyDataSetChanged()
    }

    fun randomColor(): Int{
        val list = ArrayList<Int>()
        list.add(R.color.NoteColor1)
        list.add(R.color.NoteColor2)
        list.add(R.color.NoteColor3)
        list.add(R.color.NoteColor4)
        list.add(R.color.NoteColor5)
        list.add(R.color.NoteColor6)
        list.add(R.color.NoteColor7)
        val seed = System.currentTimeMillis().toInt()
        val randx = Random(seed).nextInt(list.size)
        return list[randx]
    }


    inner class NoteViewModel(itemView: View) : RecyclerView.ViewHolder(itemView){
        val notes_layout = itemView.findViewById<CardView>(R.id.card_layout)
        val title = itemView.findViewById<TextView>(R.id.tv_title)

    }

    interface NotesClickListener{
        fun onItemClicked(note: Note)
        fun onLongItemClicked(note:Note, cardView: CardView)
    }
}