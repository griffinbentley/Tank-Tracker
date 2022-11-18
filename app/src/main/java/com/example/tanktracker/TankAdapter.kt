package com.example.tanktracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_tank.view.*

class TankAdapter (
    private val tanks: MutableList<Tank>,
    private val onItemClicked: (position: Int) -> Unit
) : RecyclerView.Adapter<TankAdapter.TrackerViewHolder>() {

    class TrackerViewHolder(itemView: View, private val onItemClicked: (position: Int) -> Unit) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v:View) {
            val position = adapterPosition
            onItemClicked(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackerViewHolder {
        return TrackerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_tank, parent, false), onItemClicked)
    }

    private fun intToDate(date: Int) : String {
        var result = ""
        result += (date / 10000).toString() + "/"
        result += (date.mod(10000) / 100).toString() + "/"
        result += (date.mod(10000).mod(100)).toString()
        return result
    }

    fun addTank(tank: Tank) {
        tanks.add(tank)
        notifyItemInserted(tanks.size - 1)
    }

    override fun onBindViewHolder(holder: TrackerViewHolder, position: Int) {
        val curTank = tanks[position]
        holder.itemView.apply {
            // set tank name on home screen
            val nameText: String
            if (curTank.name != "") {
                nameText = curTank.name
            } else {
                nameText = "Unnamed Tank"
                tvTankName.textSize = 18F
            }
            tvTankName.text = nameText
            // set tank size on home screen
            val sizeText = if (curTank.size != 0) {
                curTank.size.toString() + " gal"
            } else {
                "Unspecified"
            }
            tvTankSize.text = sizeText
            // set dates on home screen
            val maintenanceText = if (curTank.last != 0 && curTank.next != 0) {
                "Last Maintained " + intToDate(curTank.last) + "\nNext Maintenance " + intToDate(curTank.next)
            } else if (curTank.last != 0) {
                "Last Maintained " + intToDate(curTank.last)
            } else if (curTank.next != 0) {
                "Next Maintenance " + intToDate(curTank.next)
            } else {
                ""
            }
            tvLastMainText.text = maintenanceText
        }
    }

    override fun getItemCount() : Int {
        return tanks.size
    }
}