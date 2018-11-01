package com.pratamawijaya.newsapimvvm.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import com.pratamawijaya.newsapimvvm.R
import com.pratamawijaya.newsapimvvm.ui.entity.event.SearchArticleEvent
import com.pratamawijaya.newsapimvvm.ui.topheadline.TopHeadlineFragment
import org.greenrobot.eventbus.EventBus

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, TopHeadlineFragment.newInstance())
                    .commitNow()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                EventBus.getDefault().post(SearchArticleEvent(query))
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
        return true
    }

}
