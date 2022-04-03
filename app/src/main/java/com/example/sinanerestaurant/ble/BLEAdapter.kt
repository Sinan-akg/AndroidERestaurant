package com.example.sinanerestaurant.ble

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.bluetooth.le.ScanResult
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.sinanerestaurant.R

class BLEAdapter(val data: ArrayList<ScanResult>, val result: (BluetoothDevice) -> Unit) :
    RecyclerView.Adapter<BLEAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var adresse: TextView = view.findViewById(R.id.mac)
        var name: TextView = view.findViewById(R.id.nom)
        var rssi: TextView = view.findViewById(R.id.rssi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val bleView =
            LayoutInflater.from(parent.context).inflate(R.layout.cell_ble_scan, parent, false)
        return MyViewHolder(bleView)
    }

    @SuppressLint("MissingPermission")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.i("XXX", "onBindViewHolder")
        val appareil = data[position]
        holder.adresse.text = appareil.device.address
        holder.name.text = appareil.device.name
        holder.rssi.text = appareil.rssi.toString()

        holder.itemView.setOnClickListener {
            result(appareil.device)
        }
    }

    fun addElement(scanResult : ScanResult) {
        val indexOfResult = data.indexOfFirst{
            it.device.address == scanResult.device.address
        }
        if (indexOfResult != -1) {
            data[indexOfResult] = scanResult
            notifyItemInserted(indexOfResult)
        }
        else{
            data.add(scanResult)
         //   notifyItemInserted()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


}


