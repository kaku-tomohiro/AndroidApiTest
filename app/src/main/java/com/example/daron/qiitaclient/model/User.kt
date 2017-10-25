package com.example.daron.qiitaclient.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by daron on 2017/10/22.
 */


data class User(val id: String,
                val name: String,
                val profileImageUrl: String
): Parcelable{
    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<User> = object: Parcelable.Creator<User>{
            override fun createFromParcel(source: Parcel?): User = source.run {
                User(this!!.readString(), this!!.readString(), this!!.readString())
            }

            override fun newArray(size: Int): Array<User?> {
                return arrayOfNulls<User>(size)
            }
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest.run {
            this!!.writeString(id)
            this!!.writeString(name)
            this!!.writeString(profileImageUrl)
        }
    }
}