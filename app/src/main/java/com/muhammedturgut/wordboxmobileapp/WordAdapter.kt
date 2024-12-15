package com.muhammedturgut.wordboxmobileapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muhammedturgut.wordboxmobileapp.databinding.RecyclerRowBinding

class WordAdapter(val wordList:ArrayList<WordBox>):RecyclerView.Adapter<WordAdapter.WordHolder>(){
    class WordHolder(val binding:RecyclerRowBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordHolder {
        val binding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return WordHolder(binding)
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    override fun onBindViewHolder(holder: WordHolder, position: Int) {
      holder.binding.RecyclerViewWord.text=wordList.get(position).word
        holder.binding.RecyclerViewMean.text=wordList.get(position).mean
        holder.binding.recyclerViewNot.text=wordList.get(position).not

        holder.itemView.setOnClickListener {

            val intent=Intent(holder.itemView.context,AddWordBox::class.java)
            intent.putExtra("id",wordList.get(position).id)
            holder.itemView.context.startActivity(intent)

        }
    }
}