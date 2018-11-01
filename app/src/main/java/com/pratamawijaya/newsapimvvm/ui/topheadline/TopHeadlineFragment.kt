package com.pratamawijaya.newsapimvvm.ui.topheadline

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.ajalt.timberkt.d
import com.github.ajalt.timberkt.e
import com.pratamawijaya.newsapimvvm.R
import com.pratamawijaya.newsapimvvm.shared.toGone
import com.pratamawijaya.newsapimvvm.shared.toVisible
import com.pratamawijaya.newsapimvvm.ui.entity.event.SearchArticleEvent
import com.pratamawijaya.newsapimvvm.ui.topheadline.rvitem.ArticleItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.top_headline_fragment.loading
import kotlinx.android.synthetic.main.top_headline_fragment.rvTopHeadline
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.koin.android.ext.android.inject

class TopHeadlineFragment : Fragment() {

    private val groupAdapter = GroupAdapter<ViewHolder>()

    companion object {
        fun newInstance() = TopHeadlineFragment()
    }

    private val viewModel: TopHeadlineViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.top_headline_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observeViewModel()

        savedInstanceState?.let {
            viewModel.restoreTopHeadlines()
        } ?: viewModel.updateTopHeadlines()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvTopHeadline.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = groupAdapter
        }

    }

    @Subscribe
    fun onSearchArticleSubmitted(event: SearchArticleEvent) {
        viewModel.searchArticle(event.query)
    }

    override fun onStart() {
        super.onStart()
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    private fun observeViewModel() {
        viewModel.topHeadlineState.observe(this, stateObserver)
    }

    // state observer, switching for show data or show error
    private val stateObserver = Observer<TopHeadlineState> { state ->
        when (state) {

            is ArticleLoadedState -> {
                rvTopHeadline.toVisible()
                loading.toGone()
                state.articles.map {
                    d { "title ${it.title}" }

                    Section().apply {
                        add(ArticleItem(it))
                        groupAdapter.add(this)
                    }
                }
            }

            is LoadingState -> {
                // show Loading
                loading.toVisible()
                rvTopHeadline.toGone()
            }

            // show error
            is ErrorState -> {
                e { "error ${state.errorMessage}" }
                Toast.makeText(activity, "error ${state.errorMessage}", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
