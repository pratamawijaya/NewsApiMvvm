package com.pratamawijaya.newsapimvvm.ui.topheadline.rvitem

import com.pratamawijaya.newsapimvvm.R
import com.pratamawijaya.newsapimvvm.di.module.GlideApp
import com.pratamawijaya.newsapimvvm.domain.Article
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_article.view.imgArticle
import kotlinx.android.synthetic.main.item_article.view.tvDesc
import kotlinx.android.synthetic.main.item_article.view.tvPublishedAt
import kotlinx.android.synthetic.main.item_article.view.tvSource
import kotlinx.android.synthetic.main.item_article.view.tvTitle

interface ArticleItemListener {
    fun onArticleSelected(article: Article)
}

class ArticleItem(private val article: Article,
                  private val listener: ArticleItemListener) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.tvTitle.text = article.title
        viewHolder.itemView.tvDesc.text = article.description
        viewHolder.itemView.tvSource.text = article.source.name
        viewHolder.itemView.tvPublishedAt.text = article.publishedAt

        viewHolder.itemView.setOnClickListener { listener.onArticleSelected(article) }

        if (article.urlToImage.isNotBlank()) {
            GlideApp.with(viewHolder.itemView.context)
                    .load(article.urlToImage)
                    .into(viewHolder.itemView.imgArticle)
        }
    }

    override fun getLayout(): Int = R.layout.item_article
}