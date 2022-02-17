package com.example.galleryapplication.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import android.os.Parcel
import android.os.Parcelable.Creator

class Images : Parcelable {
    @SerializedName("total")
    @Expose
    var total: Int

    @SerializedName("totalHits")
    @Expose
    var totalHits: Int

    @SerializedName("hits")
    @Expose
    var hits: List<Hit>? = null

    protected constructor(`in`: Parcel) {
        total = `in`.readInt()
        totalHits = `in`.readInt()
    }

    constructor(total: Int, totalHits: Int, hits: List<Hit>?) {
        this.total = total
        this.totalHits = totalHits
        this.hits = hits
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeInt(total)
        parcel.writeInt(totalHits)
    }

    companion object CREATOR : Creator<Images> {
        override fun createFromParcel(parcel: Parcel): Images {
            return Images(parcel)
        }

        override fun newArray(size: Int): Array<Images?> {
            return arrayOfNulls(size)
        }
    }
}