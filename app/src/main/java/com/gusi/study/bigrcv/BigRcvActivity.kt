package com.gusi.study.bigrcv

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.blankj.utilcode.util.Utils
import com.gusi.study.R
import com.gusi.study.base.BaseActivity
import com.gusi.study.utils.ColorUtils
import kotlinx.android.synthetic.main.activity_big_rcv.*

class BigRcvActivity : BaseActivity() {
    override fun getLayout() = R.layout.activity_big_rcv
    override fun initView() {
        super.initView()
        initToolBar(mToolbar, true, "BigRcv")

        rcv_big.layoutManager = LinearLayoutManager(this@BigRcvActivity)
        rcv_big.setHasFixedSize(true)
        rcv_big.adapter = BigAdapter(initData())

        rcv_big1.layoutManager = LinearLayoutManager(this@BigRcvActivity)
        rcv_big1.setHasFixedSize(true)
        rcv_big1.adapter = BigAdapter(initData())
    }

    private fun initData(): List<BigItem> {
        val list = ArrayList<BigItem>(20)
//        val randomColor = RandomColor()
        (1..20).mapTo(list) {
            BigItem("name:" + it, ColorUtils.getRandomColor(), "name:" + (it + 1), ColorUtils.getRandomColor(),
                    "name:" + (it + 2), ColorUtils.getRandomColor())
        }
        return list
    }


}

class BigAdapter(list: List<BigItem>) : RecyclerView.Adapter<BigAdapter.ViewHolder>() {
    private var mList: List<BigItem> = list
    private val mInflater = LayoutInflater.from(Utils.getApp())

    override fun getItemCount() = mList.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_big_rcv, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bigItem = mList[position]
        holder.tv1.text = bigItem.name
        holder.tv1.setBackgroundColor(bigItem.color)
        holder.tv2.text = bigItem.name1
        holder.tv2.setBackgroundColor(bigItem.color1)
        holder.tv3.text = bigItem.name2
        holder.tv3.setBackgroundColor(bigItem.color2)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv1: TextView = itemView.findViewById(R.id.tv_1)
        var tv2: TextView = itemView.findViewById(R.id.tv_2)
        var tv3: TextView = itemView.findViewById(R.id.tv_3)
    }
}

data class BigItem(val name: String, val color: Int, val name1: String, val color1: Int, val name2: String, val color2: Int)