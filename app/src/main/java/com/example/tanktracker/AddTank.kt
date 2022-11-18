package com.example.tanktracker

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_tank.*

class AddTank : AppCompatActivity() {

    //private lateinit var tankAdapter: TankAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_tank)

        //tankAdapter = TankAdapter(mutableListOf()) { position -> onListItemClick(position) }

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "hello"

        btnAddTank.setOnClickListener {
            val tankName = etTankName.text.toString()
            val tankSizeStr = etTankSize.text.toString()
            val tankSize = if (tankSizeStr != "") {
                tankSizeStr.toInt()
            } else {
                0
            }
            val returnIntent = Intent()
            returnIntent.putExtra("TANK_NAME",tankName)
            returnIntent.putExtra("TANK_SIZE",tankSize)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}