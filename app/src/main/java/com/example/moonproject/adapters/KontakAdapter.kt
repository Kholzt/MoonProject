package com.example.moonproject.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moonproject.Models.KontakModel
import com.example.moonproject.R


class KontakAdapter(private val mahasiswaList: List<KontakModel>) : RecyclerView.Adapter<KontakAdapter.MahasiswaViewHolder>() {

    // ViewHolder class to hold item views
    class MahasiswaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.name)
        val phoneTextView: TextView = itemView.findViewById(R.id.phone)
    }

    // Create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MahasiswaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.kontak_item, parent, false)
        return MahasiswaViewHolder(view)
    }

    // Bind data to the views
    override fun onBindViewHolder(holder: MahasiswaViewHolder, position: Int) {
        val mahasiswa = mahasiswaList[position]
        holder.nameTextView.text = mahasiswa.name
        holder.phoneTextView.text = mahasiswa.phone.toString()
    }

    // Return the size of the dataset
    override fun getItemCount(): Int {
        return mahasiswaList.size
    }
}
