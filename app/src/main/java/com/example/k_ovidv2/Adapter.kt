package com.example.k_ovidv2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

/**
 * @author CHOI
 * @email vviian.2@gmail.com
 * @created 2021-09-13
 * @desc
 */
class Adapter : BaseAdapter() {

    var list = ArrayList<ViewItem>()

    override fun getCount(): Int {
        return list.size
    }

    override fun getItemId(position: Int) : Long {
        return position.toLong()
    }

    override fun getItem(position: Int): Any {
        return list.get(position)
    }

    fun getSize(): Int {
        return list.size
    }

    fun addItem(storeName : String, start : String, end : String, lunchst : String, lunchend : String, add : String, tel : String) {
        var item = ViewItem(storeName, start,end, lunchst, lunchend, add, tel )
        list.add(item)

        println("in ListViewAdapter --> listSize : "+list.size)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var context = parent?.getContext()
        var convertV = convertView

        if (convertView == null) {
            val systemService =
                    context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertV = systemService.inflate(R.layout.listview,parent,false)
        }

        var storeName = convertV?.findViewById(R.id.text1) as TextView
        var start = convertV?.findViewById(R.id.text2) as TextView

        val listViewItem = list[position]

        storeName.text = listViewItem.storeName
        start.text = listViewItem.start

        return convertV

    }

}