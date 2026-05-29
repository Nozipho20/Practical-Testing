package com.example.weatherconditionsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainScreen : AppCompatActivity() {
    val days = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    val intMinTemps = intArrayOf(12, 15, 10, 14, 11, 10, 10)
    val intMaxTemps = intArrayOf(25, 29, 22, 21, 19, 18, 16)
    val strConditions = arrayOf("Sunny", "Sunny", "Cloudy", "Windy", "Rainy", "Raining", "Cold")

    var intCurrentDayIndex = 0

    // Plain lateinit var declarations (No private modifiers)
    lateinit var edtMinTemp: EditText
    lateinit var edtMaxTemp: EditText
    lateinit var edtCondition: EditText
    lateinit var btnSaveDay: Button
    lateinit var btnCalculateAverage: Button
    lateinit var btnClearData: Button
    lateinit var btnGoToDetails: Button
    lateinit var txtAverageResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_screen)
        edtMinTemp = findViewById(R.id.edtMinTemp)
        edtMaxTemp = findViewById(R.id.edtMaxTemp)
        edtCondition = findViewById(R.id.edtCondition)
        btnSaveDay = findViewById(R.id.btnSaveDay)
        btnCalculateAverage = findViewById(R.id.btnCalculateAverage)
        btnClearData = findViewById(R.id.btnClearData)
        btnGoToDetails = findViewById(R.id.btnGoToDetails)
        txtAverageResult = findViewById(R.id.txtAverageResult)

        // BUTTON 1: Save data into our parallel arrays day-by-day
        btnSaveDay.setOnClickListener {
            if (intCurrentDayIndex < 7) {
                val strMinInput = edtMinTemp.text.toString()
                val strMaxInput = edtMaxTemp.text.toString()
                val strConditionInput = edtCondition.text.toString()

                // Error Handling: Validate that the fields are not left empty
                if (strMinInput.isEmpty() || strMaxInput.isEmpty() || strConditionInput.isEmpty()) {
                    Toast.makeText(this, "Error: Please enter data for all fields!", Toast.LENGTH_SHORT).show()
                } else {
                    // Store the data into the arrays at the matching index positions
                    intMinTemps[intCurrentDayIndex] = strMinInput.toInt()
                    intMaxTemps[intCurrentDayIndex] = strMaxInput.toInt()
                    strConditions[intCurrentDayIndex] = strConditionInput

                    Toast.makeText(this, "${days[intCurrentDayIndex]} data saved!", Toast.LENGTH_SHORT).show()

                    // Move index forward to the next day
                    intCurrentDayIndex++

                    // Clear the input boxes so the user can type the next day's info comfortably
                    edtMinTemp.text.clear()
                    edtMaxTemp.text.clear()
                    edtCondition.text.clear()
                }
            } else {
                Toast.makeText(this, "All 7 days of data have been recorded!", Toast.LENGTH_SHORT).show()
            }
        }

        // BUTTON 2: Use a loop to calculate the average temperature of the week
        btnCalculateAverage.setOnClickListener {
            var intTotalTempSum = 0
            var intRecordedDaysCount = 0

            // The loop checks every position in the parallel arrays
            for (i in 0 until 7) {
                // Ensure we only calculate days where data was actually entered
                if (intMinTemps[i] != 0 || intMaxTemps[i] != 0) {
                    val intDayAverage = (intMinTemps[i] + intMaxTemps[i]) / 2
                    intTotalTempSum += intDayAverage
                    intRecordedDaysCount++
                }
            }

            // Display calculated result or show error message if no entries exist
            if (intRecordedDaysCount > 0) {
                val intOverallAverage = intTotalTempSum / intRecordedDaysCount
                txtAverageResult.text = "Weekly Average Temperature: ${intOverallAverage}°C"
            } else {
                txtAverageResult.text = "No data entered yet. Cannot calculate average."
            }
        }

        // BUTTON 3: Clear all input data so the user can re-input if they made a mistake
        btnClearData.setOnClickListener {
            intCurrentDayIndex = 0 // Reset day tracker back to Monday

            for (i in 0 until 7) {
                intMinTemps[i] = 0
                intMaxTemps[i] = 0
                strConditions[i] = ""
            }

            txtAverageResult.text = "Weekly Average Temperature: --"
            Toast.makeText(this, "All data cleared! Start again from Monday.", Toast.LENGTH_SHORT).show()
        }

        // BUTTON 4: Navigate to the Detailed View Screen (Screen 3) and pass data along
        btnGoToDetails.setOnClickListener {
            val intent = Intent(this, DetailViewScreen::class.java)

            // Passing the parallel arrays to the next activity via intent extras
            intent.putExtra("MIN_TEMPS", intMinTemps)
            intent.putExtra("MAX_TEMPS", intMaxTemps)
            intent.putExtra("CONDITIONS", strConditions)

            startActivity(intent)
        }
    }
}

