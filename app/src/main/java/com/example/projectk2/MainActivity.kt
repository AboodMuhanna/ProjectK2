package com.example.projectk2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.projectk2.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    val db = Firebase.firestore
    private lateinit var binding: ActivityMainBinding
    private val progressDialog by lazy { CustomProgressDialog(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Sbutton.setOnClickListener {
            progressDialog.start("Please Wait...")
            val name = binding.edName.text.toString()
            val number = binding.edNumber.text.toString()
            val address = binding.edAddress.text.toString()

            // Create a new user with a first and last name
            val details = hashMapOf(
                "name" to name,
                "number" to number,
                "address" to address
            )

            // Add a new document with a generated ID
            db.collection("Details")
                .add(details)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(applicationContext,"${documentReference.id}", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(applicationContext,"$e", Toast.LENGTH_LONG).show()
                }

            Handler(Looper.getMainLooper()).postDelayed({
                // Dismiss progress bar after 4 seconds
                progressDialog.stop()
            }, 6000)
        }

        binding.itemButton.setOnClickListener {
            val intent = Intent(this, DataDetails::class.java)
            startActivity(intent)

        }

    }
}