package com.faruk.mlkitsample.data

import android.media.Image
import java.util.ArrayList

/**
 * Created by farukhossain on 2019/05/20.
 */
class AdapterData {

    private var title: String? = null
    private var description: String? = null
    private var icon: Int? = null;


    fun setTitle(title: String) {
        this.title = title
    }

    fun getTitle(): String? {
        return title
    }

    fun setDescription(description: String) {
        this.description = description
    }

    fun getDescription(): String? {
        return description
    }

    fun setIcon(icon: Int) {
        this.icon = icon
    }

    fun getIcon(): Int? {
        return icon
    }

}