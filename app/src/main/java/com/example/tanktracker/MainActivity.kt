package com.example.tanktracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var trackerAdapter: TrackerAdapter

    //private val ADD_TANK_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trackerAdapter = TrackerAdapter(mutableListOf())

        rvTankList.adapter = trackerAdapter
        rvTankList.layoutManager = LinearLayoutManager(this)

        btnNewTank.setOnClickListener {
            val intent = Intent(this, AddTank::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                val jName = data?.getStringExtra("TANK_NAME")
                val jSize = data?.getIntExtra("TANK_SIZE", 1)
                var name = ""
                var size = 0
                jName?.let {
                    name = it
                }
                jSize?.let {
                    size = it
                }
                val tank = Tracker(name, size, 50, 50)
                trackerAdapter.addTank(tank)
            }
        }
    }
}