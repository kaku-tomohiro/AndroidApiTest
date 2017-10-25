package com.example.daron.qiitaclient.View

/**
 * Created by daron on 2017/10/22.
 */


import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.daron.qiitaclient.R
import com.example.daron.qiitaclient.bindView
import com.example.daron.qiitaclient.model.Article

/**
 * Created by daron on 2017/10/22.
 */

class ArticleView:FrameLayout{
    constructor(context: Context?): super(context)
    constructor(context: Context?, attrs:AttributeSet):super(context,attrs)
    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context,attrs,defStyleAttr,defStyleRes)
//    val titleTextView: TextView by lazy{
//        findViewById<TextView>(R.id.title_text_view)
//    }
//
//    val profileImageView: ImageView by lazy {
//        findViewById<ImageView>(R.id.profile_image_view)
//    }
//
//    val userNameTextView: TextView by lazy {
//        findViewById<TextView>(R.id.user_name_text_view)
//    }

    val profileImageView: ImageView by bindView<ImageView>(R.id.profile_image_view)
    val titleTextView: TextView by bindView<TextView>(R.id.title_text_view)
    val userNameTextView: TextView by bindView<TextView>(R.id.user_name_text_view)
    init {
        LayoutInflater.from(context).inflate(R.layout.view_article, this)
    }

    fun setArticle(article: Article){
        titleTextView.text = article.title
        userNameTextView.text = article.user.name
        Glide.with(context).load(article.user.profileImageUrl).into(profileImageView)
        profileImageView.setBackgroundColor(Color.RED)
    }
}



