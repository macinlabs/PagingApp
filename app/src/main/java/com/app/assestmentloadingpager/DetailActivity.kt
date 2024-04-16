package com.app.assestmentloadingpager

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details)
        val itemId = intent.getIntExtra("itemId", 0)
        val itemTitle = intent.getStringExtra("itemTitle")
        val itemBody = intent.getStringExtra("itemBody")
        findViewById<TextView>(R.id.detailId).text = itemId.toString()
        findViewById<TextView>(R.id.detailTitle).text = itemTitle
        findViewById<TextView>(R.id.detailBody).text = itemBody


    }
}