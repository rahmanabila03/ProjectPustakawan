package com.rahma.projectpustakawan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rahma.projectpustakawan.database.DBPustakawan
import com.rahma.projectpustakawan.database.Pustakawan
import com.rahma.projectpustakawan.databinding.ActivityInputDataBinding

class input_data : AppCompatActivity() {

    private lateinit var binding: ActivityInputDataBinding
    private lateinit var database : DBPustakawan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = DBPustakawan.getInstance(applicationContext)

        binding.btnSimpan.setOnClickListener{
            if (binding.txtNamaPustakawan.text.isNotEmpty()&&
                    binding.statusPustakawan.text.isNotEmpty()&&
                    binding.txtTanggalRegistrasi.text.isNotEmpty()&&
                    binding.txtIDPustakawan.text.isNotEmpty()){
                database.pustakawanDao().insertData(Pustakawan(
                    0,
                    binding.txtNamaPustakawan.text.toString(),
                    binding.statusPustakawan.text.toString(),
                    binding.txtTanggalRegistrasi.text.toString()

                ))
                binding.txtNamaPustakawan.setText("")
                binding.statusPustakawan.setText("")
                binding.txtTanggalRegistrasi.setText("")
                binding.txtIDPustakawan.setText("")

                startActivity(
                    Intent(this,MainActivity::class.java)
                )
            }else{
                Toast.makeText(applicationContext,"silahkan isi semua data terlebih dahulu",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}