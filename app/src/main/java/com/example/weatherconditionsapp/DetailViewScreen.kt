package com.example.weatherconditionsapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class DetailViewScreen : AppCompatActivity() {

    lateinit var txtDetailedReport: TextView
    lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_view_screen)
        // Bind layout elements using findViewById
        txtDetailedReport = findViewById(R.id.txtDetailedReport)
        btnBack = findViewById(R.id.btnBack)

        val days = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        val intMinTemps = intArrayOf(12, 15, 10, 14, 11, 10, 10)  // Added placeholder inputs for Wed-Fri
        val intMaxTemps = intArrayOf(25, 29, 22, 21, 19, 18, 16)  // Added placeholder inputs for Wed-Fri
        val strConditions = arrayOf("Sunny", "Sunny", "Cloudy", "Windy", "Rainy", "Raining", "Cold")
                // Retrieve the parallel arrays passed forward from Screen 2
        val receivedMins = intent.getIntArrayExtra("MIN_TEMPS")
        val receivedMaxs = intent.getIntArrayExtra("MAX_TEMPS")
        val receivedConditions = intent.getStringArrayExtra("CONDITIONS")

        // Create a StringBuilder to compile the multi-line text report
        val finalReport = StringBuilder()

        // Ensure data exists before running the loop to prevent crashes
        if (receivedMins != null && receivedMaxs != null && receivedConditions != null) {

            // The textbook loop using index 'i' to match data across all parallel arrays
            for (i in 0 until 7) {
                finalReport.append("Day: ${days[i]}\n")
                finalReport.append("Minimum Temperature: ${receivedMins[i]}°C\n")
                finalReport.append("Maximum Temperature: ${receivedMaxs[i]}°C\n")

                // If a condition is null or empty, display "Not Recorded"
                val conditionText = if (receivedConditions[i].isNullOrEmpty()) "Not Recorded" else receivedConditions[i]
                finalReport.append("Weather Condition: ${conditionText}\n")

                // Add a visual dividing line between days for clean layout design
                finalReport.append("----------------------------------------\n\n")
            }
        }

        // Set the compiled text into the TextView to display to the user
        txtDetailedReport.text = finalReport.toString()

        // Navigation Button: Closes Screen 3 and smoothly goes back to Screen 2
        btnBack.setOnClickListener {
            finish()
        }
    }
}
