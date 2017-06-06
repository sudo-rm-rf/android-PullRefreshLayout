package com.baoyz.pullrefreshlayout.sample

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.baoyz.widget.PullRefreshLayout

class NestRecyclerViewActivity : Activity() {

  lateinit var layout: PullRefreshLayout

  override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_nest_recycler_view)


    val recyclerView = findViewById(R.id.nestedrecyclerView) as RecyclerView
    recyclerView.layoutManager = LinearLayoutManager(this)
    val array = Array(50,{i -> "recycler " + i })
    recyclerView.adapter = ArrayAdapter(this, array)

    layout = findViewById(R.id.swipeRefreshLayout) as PullRefreshLayout

    layout.setTargetView(recyclerView)
    layout.setOnRefreshListener {
      layout.postDelayed(
          { layout.setRefreshing(false) }, 4000)
    }
  }

  internal class ArrayAdapter(private val mContext: Context,
      private val mArray: Array<String>) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
      return ViewHolder(View.inflate(viewGroup.context, android.R.layout.simple_list_item_1, null))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
      viewHolder.mTextView.text = mArray[i]
    }

    override fun getItemCount(): Int {
      return mArray.size
    }
  }

  internal class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var mTextView: TextView = itemView as TextView

  }


  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.demo, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    val id = item.itemId

    when (id) {
      R.id.action_material -> {
        layout.setRefreshStyle(PullRefreshLayout.STYLE_MATERIAL)
        return true
      }
      R.id.action_circles -> {
        layout.setRefreshStyle(PullRefreshLayout.STYLE_CIRCLES)
        return true
      }
      R.id.action_water_drop -> {
        layout.setRefreshStyle(PullRefreshLayout.STYLE_WATER_DROP)
        return true
      }
      R.id.action_ring -> {
        layout.setRefreshStyle(PullRefreshLayout.STYLE_RING)
        return true
      }
      R.id.action_smartisan -> {
        layout.setRefreshStyle(PullRefreshLayout.STYLE_SMARTISAN)
        return true
      }
    }

    return super.onOptionsItemSelected(item)
  }
}
