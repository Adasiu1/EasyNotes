package com.example.easynote

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.easynote.Models.Note
import com.example.easynote.databinding.ActivityAddNoteBinding
import java.text.SimpleDateFormat
import java.util.Date

class AddNote : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding

    private lateinit var note: Note
    private lateinit var old_note : Note
    var isUpdate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            old_note = intent.getSerializableExtra("current_note") as Note
            binding.etTitle.setText(old_note.title)
            isUpdate = true
        } catch (e: Exception) {
            e.printStackTrace()
        }

        binding.imgCheck.setOnClickListener {
            val title = binding.etTitle.text.toString().trim()
            if (title.isNotEmpty()) {
                val formatter = SimpleDateFormat("EEE, d MMM yyyy HH:mm a")
                note = if (isUpdate) {
                    Note(old_note.id, title, "", formatter.format(Date()))
                } else {
                    Note(null, title, "", formatter.format(Date()))
                }
                val intent = Intent()
                intent.putExtra("note", note)
                setResult(Activity.RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this@AddNote, "You can't leave it blank", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }

        binding.imgBackArrow.setOnClickListener {
            onBackPressed()
        }
    }
}
