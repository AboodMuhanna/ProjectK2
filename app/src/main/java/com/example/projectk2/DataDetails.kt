package com.example.projectk2

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectk2.Adapter.MyAdapter
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DataDetails : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataArrayList: ArrayList<Data>
    private lateinit var myAdapter: MyAdapter
    var db = Firebase.firestore



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_details)
        recyclerView = findViewById(R.id.rvDetails)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        dataArrayList = arrayListOf()
        myAdapter = MyAdapter(dataArrayList)
        recyclerView.adapter = myAdapter
        eventChangeListener()

    }

    private fun eventChangeListener() {
        db = FirebaseFirestore.getInstance()
        db.collection("Details").
                addSnapshotListener(object :EventListener<QuerySnapshot>{
                    @SuppressLint("NotifyDataSetChanged")
                    override fun onEvent(
                        value: QuerySnapshot?,
                        error: FirebaseFirestoreException?
                    ) {
                        if (error != null) {
                            Log.e("Firestore Error", error.message.toString())
                            return
                        }
                        for (dc : DocumentChange in value?.documentChanges!!){
                            if(dc.type == DocumentChange.Type.ADDED){
                                dataArrayList.add(dc.document.toObject(Data::class.java))
                            }
                        }
                        myAdapter.notifyDataSetChanged()
                    }
                })
    }
}