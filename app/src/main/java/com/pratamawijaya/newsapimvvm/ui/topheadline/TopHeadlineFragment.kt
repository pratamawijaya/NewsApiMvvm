package com.pratamawijaya.newsapimvvm.ui.topheadline

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.pratamawijaya.newsapimvvm.R
import dagger.android.support.AndroidSupportInjection

class TopHeadlineFragment : Fragment() {

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
        viewModel = ViewModelProviders.of(this).get(TopHeadlineViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
