package com.example.daron.qiitaclient.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by daron on 2017/10/22.
 */
data class Article(val id: String,
                   val title: String,
                   val url: String,
                   val user: User
): Parcelable{
    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Article> = object : Parcelable.Creator<Article>{
            override fun createFromParcel(source: Parcel?): Article = source.run {
                Article(this!!.readString(), this!!.readString(), this!!.readString(), readParcelable(Article::class.java.classLoader))
            }

            override fun newArray(size: Int): Array<Article?> {
                return arrayOfNulls<Article>(size)
            }
        }
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest.run {
            this!!.writeString(id)
            this!!.writeString(title)
            this!!.writeString(url)
            this!!.writeParcelable(user, flags)
        }
    }
}