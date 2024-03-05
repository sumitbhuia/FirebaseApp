package com.sumitbhuia.firebaseapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue

/*

   - To get SHA code
        - Open terminal , left bottom
        - type "gradlew signingReport"
        - cmd+return

   - Then register project
   - download google-services.json
   - copy paste the file to Project level -> app (paste here)

   - Copy paste the dependencies
   - Sync gradle

 */

class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val textView : TextView = findViewById(R.id.textView)
        // Connecting to real time reference
        // https://fir-kotlin-f51eb-default-rtdb.firebaseio.com/
        database = Firebase.database.reference

        // Writing value to Firebase
       // database.child("Price").setValue("$6999")

        // Reading data from firebase
//        val postListener = object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val gold_price = snapshot.value
//
//                textView.text = gold_price.toString()
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Getting Post failed, log a message
//                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
//            }
//
//        }

        // Creating custom objects for firebase
        val user1 = User("Sumit","123456789")
        database.child("UserList").setValue(user1)

        // Reading custom objects from firebase
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val u1 = snapshot.getValue<User>()
                textView.text = u1.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }

        // This is important to make the transaction happen
        database.child("UserList").addValueEventListener(listener)
    }
}