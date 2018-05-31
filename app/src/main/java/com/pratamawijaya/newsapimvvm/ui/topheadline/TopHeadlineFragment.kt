package com.pratamawijaya.newsapimvvm.ui.topheadline

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.ajalt.timberkt.d
import com.github.ajalt.timberkt.e

import com.pratamawijaya.newsapimvvm.R
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class TopHeadlineFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    companion object {
        fun newInstance() = TopHeadlineFragment()
    }

    private lateinit var viewModel: TopHeadlineViewModel

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.top_headline_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TopHeadlineViewModel::class.java)

        observeViewModel()

        savedInstanceState?.let {
            viewModel.restoreTopHeadlines()
        } ?: viewModel.updateTopHeadlines()
    }

    private fun observeViewModel() {
        viewModel.topHeadlineState.observe(this, stateObserver)
    }

    // state observer, switching for show data or show error
    private val stateObserver = Observer<TopHeadlineState> { state ->
        when (state) {
        // show data
            is DefaultState -> {
                state.data.map {
                    d { "title ${it.title}" }
                }
            }
        // show error
            is ErrorState -> {
                e { "error ${state.errorMessage}" }
                Toast.makeText(activity, "error ${state.errorMessage}", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
