package com.muhammedturgut.wordboxmobileapp

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.muhammedturgut.wordboxmobileapp.databinding.ActivityAddWordBoxBinding

class AddWordBox : AppCompatActivity() {
    private lateinit var binding: ActivityAddWordBoxBinding
    private lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityAddWordBoxBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database=this.openOrCreateDatabase("Word", MODE_PRIVATE,null)

        binding.editTextWord.setText("")
        binding.editTextTextMean.setText("")
        binding.editTextTextNot.setText("")


    }
    fun btnSave(view: View){

        val word=binding.editTextWord.text.toString()
        val mean=binding.editTextTextMean.text.toString()
        val notum=binding.editTextTextNot.text.toString()

        try {
            database.execSQL("CREATE TABLE IF NOT EXISTS word (id INTEGER PRIMARY KEY,word VARCHAR,mean VARCHAR,notum VARCHAR)")
            val sqlString="INSERT INTO word(word,mean,notum) VALUES (?,?,?)"
            val statement=database.compileStatement(sqlString)
            statement.bindString(1,word)
            statement.bindString(2,mean)
            statement.bindString(3,notum)
            statement.execute()
        }catch (e:Exception){
            e.printStackTrace()
        }
        val intent=Intent(this@AddWordBox,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}