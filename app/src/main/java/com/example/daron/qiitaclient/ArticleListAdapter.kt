package com.example.daron.qiitaclient

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.daron.qiitaclient.View.ArticleView
import com.example.daron.qiitaclient.model.Article

/**
 * Created by daron on 2017/10/22.
 */


class ArticleListAdapter(private val context: Context): BaseAdapter(){
    var articles: List<Article> = emptyList()

    override fun getCount(): Int {
        return articles.size
    }

    override fun getItem(position: Int): Any{
        return articles[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return (convertView as? ArticleView) ?: ArticleView(context).apply {
            setArticle((articles[position]))
        }
    }

}