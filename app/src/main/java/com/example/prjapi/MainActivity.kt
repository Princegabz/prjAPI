package com.example.prjapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import com.google.gson.Gson
import java.net.URL
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val executor = Executors.newSingleThreadExecutor()

        executor.execute {
            val url = URL("https://wordapidata.000webhostapp.com/Dis.php")
            val json = url.readText()
            val userList = Gson().fromJson(json, Array<User>::class.java).toList()

            // Assuming User class represents the structure of your JSON data
            val arrName = ArrayList<String>()
            val arrSurname = ArrayList<String>()
            val arrPlanID = ArrayList<String>()
            val arrAmount = ArrayList<String>()

            // Logging
            Handler(Looper.getMainLooper()).post {
                Log.d("MainActivity", "Fetched JSON: $json")
                Log.d("MainActivity", "Parsed User List: $userList")

            // Loop through userList to populate arrays
            for (user in userList) {
                arrName.add(user.Name)
                arrSurname.add(user.Surname)
                arrPlanID.add(user.PlanID)
                arrAmount.add(user.Amount)
            }


            // UI Elements Initialization
            val nameTextView = findViewById(R.id.txtName) as TextView
            val surnameTextView = findViewById(R.id.txtSurname) as TextView
            val planIDTextView = findViewById(R.id.txtPlanID) as TextView
            val amountTextView = findViewById(R.id.txtAmount) as TextView
            val btnSearch = findViewById(R.id.btnSearch) as Button

            // Spinner Population
            val spinner = findViewById<Spinner>(R.id.spinnerNameList)
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrName)
            spinner.adapter = adapter

            // Button Click Listener
            btnSearch.setOnClickListener {
                val selectedName = spinner.selectedItem.toString()
                val selectedIndex = arrName.indexOf(selectedName)

                if (selectedIndex != -1) {
                    nameTextView.text = "Name: ${arrName[selectedIndex]}"
                    surnameTextView.text = "Surname: ${arrSurname[selectedIndex]}"
                    planIDTextView.text = "Plan ID: ${arrPlanID[selectedIndex]}"
                    amountTextView.text = "Amount: ${arrAmount[selectedIndex]}"
                }
            }


            }
        }
    }
}
