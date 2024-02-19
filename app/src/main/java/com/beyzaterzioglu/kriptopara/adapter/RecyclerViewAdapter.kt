package com.beyzaterzioglu.kriptopara.adapter

import android.graphics.Color
import android.net.sip.SipSession
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beyzaterzioglu.kriptopara.R
import com.beyzaterzioglu.kriptopara.databinding.RowLayoutBinding
import com.beyzaterzioglu.kriptopara.model.CryptoModel




class RecyclerViewAdapter(private val cryptoList: ArrayList<CryptoModel>,private val listener:Listener) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {

    interface Listener{
        fun onItemClick(cryptoModel: CryptoModel)
    }
    private val colors: Array<String> = arrayOf(
        "#800080", // Mor
        "#9370DB", // Eflatunumsu Mor
        "#DA70D6", // Orkide Rengi
        "#FF00FF", // Fuşya
        "#8A2BE2", // Menekşe Rengi
        "#E6E6FA", // Lavanta
        "#4B0082", // Patlıcan Moru
        "#9966CC", // Ametist Rengi
        "#C8A2C8", // Lila
        // Mavi Tonları
        "#0000FF", // Mavi
        "#0000CD", // Orta Mavi
        "#4169E1", // Orta Mavi (Parlak)
        "#6495ED", // Mavi (Suni)
        "#1E90FF", // Gökyüzü Mavisi
        "#4682B4", // Çelik Mavisi
        "#87CEEB", // Gök Mavisi
        "#87CEFA", // Gökyüzü Mavisi
        "#00BFFF", // Orta Mavi (Parlak)
        "#1E90FF", // Deniz Mavisi
        // Pembe Tonları
        "#FFC0CB", // Pembe
        "#FF69B4", // Gül Kurusu
        "#FF1493", // Derin Pembe
        "#DB7093", // Soluk Pembe
        "#FFB6C1", // Açık Pembe
        "#FFA07A", // Ateş Pembe
        "#FA8072", // Somon
        "#FF7F50", // Pembe Turuncu
        "#FF6347", // Pembe (Parlak)
        "#FF4500"  // Şeftali
    )


    inner class RowHolder(private val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cryptoModel: CryptoModel,colors:Array<String>,position: Int,listener: Listener) {

            itemView.setOnClickListener{
                listener.onItemClick(cryptoModel)
            }
            itemView.setBackgroundColor(Color.parseColor(colors[position%29]))
            binding.textName.text = cryptoModel.currency
            binding.textPrice.text = cryptoModel.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val binding = RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RowHolder(binding)
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {

        holder.bind(cryptoList[position],colors,position,listener)
    }
}