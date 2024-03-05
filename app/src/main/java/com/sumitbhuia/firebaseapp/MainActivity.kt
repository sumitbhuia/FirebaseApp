package com.sumitbhuia.firebaseapp

import android.os.Bundle
import android.util.Log
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
import com.google.firebase.firestore.firestore

/*
HOW TO IMPLEMENT FIREBASE ?
   - To get SHA code
        - Open terminal , left bottom
        - type "gradlew signingReport"
        - cmd+return

   - Then register project
   - download google-services.json
   - copy paste the file to Project level -> app (paste here)

   - Copy paste the dependencies
   - Sync gradle


   IN THIS PROJECT WE IMPLEMENTED REAL- TIME DATABASE

   FIRESTORE
   - This is also a real time database , that is faster easier and requires less code .
   Hierarchy
   ---------
   Collection
        ↪️ Documents
                ↪️ Data

    Example :
    User:
        user1
            first : "Sumit"
            last : "Bhuia"
            born : "2002"
        user2
            first : "Bhavya"
            last : "Jaiswal"
            born : "2004"

 */

class MainActivity : AppCompatActivity() {

    // This is for RTD (Real-time Database)
    //private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*
// This section implements RTD , including custom objects

        val textView : TextView = findViewById(R.id.textView)
        // Connecting to real time reference
        // https://fir-kotlin-f51eb-default-rtdb.firebaseio.com/
        database = Firebase.database.reference

        // Writing value to Firebase
        database.child("Price").setValue("$6999")

        // Reading data from firebase
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val gold_price = snapshot.value

                textView.text = gold_price.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                // Getting Post failed, log a message
                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }

        }

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

 */



        val textView : TextView = findViewById(R.id.textView)

        // Access a Cloud Firestore instance from your Activity
        val db = Firebase.firestore

        // Creating a collection of users
        val users_collection = db.collection("User")

        //Creating documents
        val user1 = hashMapOf(
            "first" to "Sumit",
            "last" to "Bhuia",
            "born" to "2002"
        )
        val user2 = hashMapOf(
            "first" to "Bhavya",
            "last" to "Jaiswal",
            "born" to "2004"
        )
        // Connecting documents to collection
        users_collection.document("user1").set(user1)
        users_collection.document("user2").set(user2)

       /* // Updating the data in document
        db.collection("User")
            .document("user1")
            .update("born", "TWO THOUSAND TWO")

        */
       /* // Deleting the document
        db.collection("User")
            .document("user1")
            .delete()

        */
        /*        //Receiving specific documents from firestore
        val docRef = db.collection("User").document("user1")
        //Getting specific data from document
        docRef.get().addOnSuccessListener(){ document ->
            if (document != null){
                textView.text = "${document.data?.get("first")}"
            }

        }

 */
        /*// Getting all documents from a collection
        var allDocuments: String = ""
        db.collection("User").get().addOnSuccessListener{ result->
            for ( document in result){
                //textView.text = "${document.data}"
                Log.i("TAGY","${document.data}")
                allDocuments += "${document.data} "+"\n"
                allDocuments += "\n"
            }
            textView.text = ""+allDocuments
        }

 */
    }
}