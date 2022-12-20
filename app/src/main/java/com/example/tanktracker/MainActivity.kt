package com.example.tanktracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var tankAdapter: TankAdapter

    private val ADD_TANK_REQUEST = 1
    private val VIEW_TANK_REQUEST = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tankAdapter = TankAdapter(mutableListOf()) { position -> onListItemClick(position) }

        rvTankList.adapter = tankAdapter
        rvTankList.layoutManager = LinearLayoutManager(this)

        btnNewTank.setOnClickListener {
            val intent = Intent(this, AddTank::class.java)
            startActivityForResult(intent, ADD_TANK_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_TANK_REQUEST) {
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
                val tank = Tank(name, size, 50, 50)
                tankAdapter.addTank(tank)
            }
        }
        else if (requestCode == VIEW_TANK_REQUEST) {
            //tankAdapter.notifyDataSetChanged()
        }
    }

    private fun onListItemClick(position: Int) {
        val intent = Intent(this@MainActivity, TankDisplay::class.java)
        startActivityForResult(intent, VIEW_TANK_REQUEST)
//        tankAdapter.editTank(position)
//        tankAdapter.notifyDataSetChanged()
    }
}