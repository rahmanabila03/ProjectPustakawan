package com.rahma.projectpustakawan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.rahma.projectpustakawan.adapter.PustakawanAdapter
import com.rahma.projectpustakawan.database.DBPustakawan
import com.rahma.projectpustakawan.database.Pustakawan
import com.rahma.projectpustakawan.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var adapter : PustakawanAdapter
    private lateinit var database : DBPustakawan

    private lateinit var binding: ActivityMainBinding

    private val db by lazy { DBPustakawan.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //STEP 5 LANJUTAN DARI PUSTAKAWAN ADAPTER
        adapter= PustakawanAdapter(arrayListOf(),
            object : PustakawanAdapter.OnAdapterListener{
                override fun onEdit(pustakawan: Pustakawan) {
                    TODO("Not yet implemented")
                }

                override fun onDelete(pustakawan: Pustakawan) {
                    //STEP 7 MEMANGGIL FUN / PERINTAH U HAPUS DATA
                    HapusData(pustakawan)
                }

            }
            )

        binding.listData.adapter = adapter
        binding.listData.layoutManager = LinearLayoutManager(applicationContext,VERTICAL,false)
        binding.listData.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))

        binding.btnActivityTambah.setOnClickListener {
            startActivity(
                Intent(this, input_data::class.java)
            )
        }
    }
    //STEP 6 MEMBUAT FUN / PERINTAH U HAPUS DATA
    private fun HapusData(pustakawan: Pustakawan) {
        CoroutineScope(Dispatchers.IO).launch {
            db.pustakawanDao().deleteData(pustakawan)
            finish()
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()

        tampilSemuaData()
    }
    fun tampilSemuaData(){
        binding.listData.layoutManager = LinearLayoutManager(this)
            CoroutineScope(Dispatchers.IO).launch {
                val data = db.pustakawanDao().getAll()
                adapter.setData(data)
                withContext(Dispatchers.Main){
                    adapter.notifyDataSetChanged() // adapternya ikut yg privat var 
                }
            }
        }

}
