package com.rahma.projectpustakawan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rahma.projectpustakawan.R
import com.rahma.projectpustakawan.database.Pustakawan
                                                        //step 3 membuat variabel listener yg extend ke interface
class PustakawanAdapter(val list: ArrayList<Pustakawan>, var listener : OnAdapterListener): RecyclerView.Adapter<PustakawanAdapter.ViewHolder>() {
    class ViewHolder (view: View):RecyclerView.ViewHolder(view){
        var namaPustakawan = itemView.findViewById<TextView>(R.id.txt_nama_pustakawan)
        var IDPustakawan = itemView.findViewById<TextView>(R.id.id_pustakawan)
        var statusPustakawan = itemView.findViewById<TextView>(R.id.statusPustakawan)
        var tanggalRegistrasi = itemView.findViewById<TextView>(R.id.txt_tanggal_registrasi)
        //step 1 membuat variabel tombol edit & delete
        var hapus = itemView.findViewById<ImageView>(R.id.imgDelete)
        var edit = itemView.findViewById<ImageView>(R.id.imgEdit)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_pustakawan,parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.namaPustakawan.text = list [position].namaPustakawan
        holder.statusPustakawan.text = list [position].statusPustakawan
        holder.tanggalRegistrasi.text = list [position].tanggal_registrasi
        holder.IDPustakawan.text = list [position].idPustakawan.toString()

        //step 4
        holder.edit.setOnClickListener{
            listener.onEdit(list[position])
        }
        holder.hapus.setOnClickListener{
            listener.onDelete(list[position])
        }
    }
    fun setData (newList: List<Pustakawan>){
        list.clear()
        list.addAll(newList)
    }
    override fun getItemCount(): Int {
        return list.size
    }
    // step 2 membuat click recycle
    interface OnAdapterListener{
        fun onEdit(pustakawan: Pustakawan)//AGAR MENGARAH KE DAO EDITNYA / HAPUS
        fun onDelete(pustakawan: Pustakawan)
    }
}