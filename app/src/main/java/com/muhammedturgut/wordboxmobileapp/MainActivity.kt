package com.muhammedturgut.wordboxmobileapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Window
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.muhammedturgut.wordboxmobileapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var wordBoxList:ArrayList<WordBox>
    private lateinit var wordAdapter: WordAdapter
    private lateinit var   sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        wordBoxList= ArrayList<WordBox>()
        wordAdapter= WordAdapter(wordBoxList)
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        binding.recyclerView.adapter=wordAdapter

        //Ekranın alt ve üst tarafını gri leştirdik

        val window: Window? =this@MainActivity.window
        window?.statusBarColor= ContextCompat.getColor(this@MainActivity,R.color.blue)

        //SharedPreferences -XML-Key-Value (simple data storage method)
        sharedPref=getSharedPreferences("com.muhammedturgut.verisaklama", Context.MODE_PRIVATE)
        val sayac=sharedPref.getInt("sayac",0)
        if(sayac==-1){
            binding.textView.text="Sayac: "
        }
        else{
            binding.textView.text="Sayac: ${sayac}"
        }


        try {
            val database=this.openOrCreateDatabase("Word", MODE_PRIVATE,null)
            val cursor =database.rawQuery("SELECT * FROM word",null)

            val wordIx=cursor.getColumnIndex("word")
            val meanIx=cursor.getColumnIndex("mean")
            val notIx=cursor.getColumnIndex("notum")
            val idIx=cursor.getColumnIndex("id")

            while (cursor.moveToNext()){
                val word=cursor.getString(wordIx)
                val mean=cursor.getString(meanIx)
                val not=cursor.getString(notIx)
                val id=cursor.getInt(idIx)
                val wordbox=WordBox(word,mean,not,id)
                wordBoxList.add(wordbox)
            }
            wordAdapter.notifyDataSetChanged()
            cursor.close()
        }catch (e:Exception){
            e.printStackTrace()
        }

        binding.floatingActionButton.setOnClickListener {

            val intent=Intent(this@MainActivity,AddWordBox::class.java)
            startActivity(intent)
            finish()

        }



    }
}