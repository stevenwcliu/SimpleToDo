package com.example.simpletodo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 *  A bridge that tells the recyclerview how to display the data we give it
 *  data: a list of tasks(string)
 *  take the data and render it inside the recycler view item by item
 */

class TaskItemAdapter(val listOfItems: List<String>,
                      val longClickListener: OnLongClickListener) :
    RecyclerView.Adapter<TaskItemAdapter.ViewHolder>()  {

    interface OnLongClickListener{
        fun onItemLongClicked(position: Int)
    }

    // Usually involves inflating a layout from XML and returning the holder
    // inflate a specific layout for each item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    // Involves populating data into the item through holder
    // what it does: take whatever in the listOfItems and use that to populate the layout inside viewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // Get the data model based on positions
        // Get the specific item that we care abt from the listOfItems
        val item = listOfItems.get(position)

        //
        holder.textView.text = item
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access

    // in another word: grab references to the views we need
    // so that we can populate data into those views
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // store references to elements in our layout view
        val textView: TextView // declare the textView

        init { // set it in the init block by using findViewById
            textView = itemView.findViewById(android.R.id.text1)

            itemView.setOnLongClickListener{
//                Log.i("Steven", "Long clicked on item" + adapterPosition)
                longClickListener.onItemLongClicked(adapterPosition)
                true
            }
        }
    }

    // give the data it needs to display
}