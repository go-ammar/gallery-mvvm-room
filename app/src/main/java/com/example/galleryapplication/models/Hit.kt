package com.example.galleryapplication.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import android.os.Parcel
import android.os.Parcelable.Creator
import androidx.room.Entity

@Entity
class Hit : Parcelable {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("pageURL")
    @Expose
    var pageURL: String?

    @SerializedName("type")
    @Expose
    var type: String?

    @SerializedName("tags")
    @Expose
    var tags: String?

    @SerializedName("previewURL")
    @Expose
    var previewURL: String?

    @SerializedName("previewWidth")
    @Expose
    var previewWidth: Int? = null

    @SerializedName("previewHeight")
    @Expose
    var previewHeight: Int? = null

    @SerializedName("webformatURL")
    @Expose
    var webformatURL: String?

    @SerializedName("webformatWidth")
    @Expose
    var webformatWidth: Int? = null

    @SerializedName("webformatHeight")
    @Expose
    var webformatHeight: Int? = null

    @SerializedName("largeImageURL")
    @Expose
    var largeImageURL: String?

    @SerializedName("imageWidth")
    @Expose
    var imageWidth: Int? = null

    @SerializedName("imageHeight")
    @Expose
    var imageHeight: Int? = null

    @SerializedName("imageSize")
    @Expose
    var imageSize: Int? = null

    @SerializedName("views")
    @Expose
    var views: Int? = null

    @SerializedName("downloads")
    @Expose
    var downloads: Int? = null

    @SerializedName("collections")
    @Expose
    var collections: Int? = null

    @SerializedName("likes")
    @Expose
    var likes: Int? = null

    @SerializedName("comments")
    @Expose
    var comments: Int? = null

    @SerializedName("user_id")
    @Expose
    var userId: Int? = null

    @SerializedName("user")
    @Expose
    var user: String?

    @SerializedName("userImageURL")
    @Expose
    var userImageURL: String?

    constructor(
        id: Int?,
        pageURL: String?,
        type: String?,
        tags: String?,
        previewURL: String?,
        previewWidth: Int?,
        previewHeight: Int?,
        webformatURL: String?,
        webformatWidth: Int?,
        webformatHeight: Int?,
        largeImageURL: String?,
        imageWidth: Int?,
        imageHeight: Int?,
        imageSize: Int?,
        views: Int?,
        downloads: Int?,
        collections: Int?,
        likes: Int?,
        comments: Int?,
        userId: Int?,
        user: String?,
        userImageURL: String?
    ) {
        this.id = id
        this.pageURL = pageURL
        this.type = type
        this.tags = tags
        this.previewURL = previewURL
        this.previewWidth = previewWidth
        this.previewHeight = previewHeight
        this.webformatURL = webformatURL
        this.webformatWidth = webformatWidth
        this.webformatHeight = webformatHeight
        this.largeImageURL = largeImageURL
        this.imageWidth = imageWidth
        this.imageHeight = imageHeight
        this.imageSize = imageSize
        this.views = views
        this.downloads = downloads
        this.collections = collections
        this.likes = likes
        this.comments = comments
        this.userId = userId
        this.user = user
        this.userImageURL = userImageURL
    }

    private constructor(`in`: Parcel) {
        id = if (`in`.readByte().toInt() == 0) {
            null
        } else {
            `in`.readInt()
        }
        pageURL = `in`.readString()
        type = `in`.readString()
        tags = `in`.readString()
        previewURL = `in`.readString()
        previewWidth = if (`in`.readByte().toInt() == 0) {
            null
        } else {
            `in`.readInt()
        }
        previewHeight = if (`in`.readByte().toInt() == 0) {
            null
        } else {
            `in`.readInt()
        }
        webformatURL = `in`.readString()
        webformatWidth = if (`in`.readByte().toInt() == 0) {
            null
        } else {
            `in`.readInt()
        }
        webformatHeight = if (`in`.readByte().toInt() == 0) {
            null
        } else {
            `in`.readInt()
        }
        largeImageURL = `in`.readString()
        imageWidth = if (`in`.readByte().toInt() == 0) {
            null
        } else {
            `in`.readInt()
        }
        imageHeight = if (`in`.readByte().toInt() == 0) {
            null
        } else {
            `in`.readInt()
        }
        imageSize = if (`in`.readByte().toInt() == 0) {
            null
        } else {
            `in`.readInt()
        }
        views = if (`in`.readByte().toInt() == 0) {
            null
        } else {
            `in`.readInt()
        }
        downloads = if (`in`.readByte().toInt() == 0) {
            null
        } else {
            `in`.readInt()
        }
        collections = if (`in`.readByte().toInt() == 0) {
            null
        } else {
            `in`.readInt()
        }
        likes = if (`in`.readByte().toInt() == 0) {
            null
        } else {
            `in`.readInt()
        }
        comments = if (`in`.readByte().toInt() == 0) {
            null
        } else {
            `in`.readInt()
        }
        userId = if (`in`.readByte().toInt() == 0) {
            null
        } else {
            `in`.readInt()
        }
        user = `in`.readString()
        userImageURL = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        if (id == null) {
            parcel.writeByte(0.toByte())
        } else {
            parcel.writeByte(1.toByte())
            parcel.writeInt(id!!)
        }
        parcel.writeString(pageURL)
        parcel.writeString(type)
        parcel.writeString(tags)
        parcel.writeString(previewURL)
        if (previewWidth == null) {
            parcel.writeByte(0.toByte())
        } else {
            parcel.writeByte(1.toByte())
            parcel.writeInt(previewWidth!!)
        }
        if (previewHeight == null) {
            parcel.writeByte(0.toByte())
        } else {
            parcel.writeByte(1.toByte())
            parcel.writeInt(previewHeight!!)
        }
        parcel.writeString(webformatURL)
        if (webformatWidth == null) {
            parcel.writeByte(0.toByte())
        } else {
            parcel.writeByte(1.toByte())
            parcel.writeInt(webformatWidth!!)
        }
        if (webformatHeight == null) {
            parcel.writeByte(0.toByte())
        } else {
            parcel.writeByte(1.toByte())
            parcel.writeInt(webformatHeight!!)
        }
        parcel.writeString(largeImageURL)
        if (imageWidth == null) {
            parcel.writeByte(0.toByte())
        } else {
            parcel.writeByte(1.toByte())
            parcel.writeInt(imageWidth!!)
        }
        if (imageHeight == null) {
            parcel.writeByte(0.toByte())
        } else {
            parcel.writeByte(1.toByte())
            parcel.writeInt(imageHeight!!)
        }
        if (imageSize == null) {
            parcel.writeByte(0.toByte())
        } else {
            parcel.writeByte(1.toByte())
            parcel.writeInt(imageSize!!)
        }
        if (views == null) {
            parcel.writeByte(0.toByte())
        } else {
            parcel.writeByte(1.toByte())
            parcel.writeInt(views!!)
        }
        if (downloads == null) {
            parcel.writeByte(0.toByte())
        } else {
            parcel.writeByte(1.toByte())
            parcel.writeInt(downloads!!)
        }
        if (collections == null) {
            parcel.writeByte(0.toByte())
        } else {
            parcel.writeByte(1.toByte())
            parcel.writeInt(collections!!)
        }
        if (likes == null) {
            parcel.writeByte(0.toByte())
        } else {
            parcel.writeByte(1.toByte())
            parcel.writeInt(likes!!)
        }
        if (comments == null) {
            parcel.writeByte(0.toByte())
        } else {
            parcel.writeByte(1.toByte())
            parcel.writeInt(comments!!)
        }
        if (userId == null) {
            parcel.writeByte(0.toByte())
        } else {
            parcel.writeByte(1.toByte())
            parcel.writeInt(userId!!)
        }
        parcel.writeString(user)
        parcel.writeString(userImageURL)
    }


    companion object CREATOR : Creator<Hit> {
        override fun createFromParcel(parcel: Parcel): Hit {
            return Hit(parcel)
        }

        override fun newArray(size: Int): Array<Hit?> {
            return arrayOfNulls(size)
        }
    }
}