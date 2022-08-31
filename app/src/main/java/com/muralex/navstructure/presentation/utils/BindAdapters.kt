package com.muralex.navstructure.presentation.utils

import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.muralex.navstructure.R
import com.muralex.navstructure.app.utils.gone
import com.muralex.navstructure.app.utils.visible
import timber.log.Timber

@BindingAdapter("setHomeListItemImage")
fun ImageView.setHomeListItemImage(imageUrl: String?) {

    if (imageUrl.isNullOrEmpty()) visibility = View.GONE
    else {
        visibility = View.VISIBLE
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(12))

        Glide.with(context)
            .load(imageUrl)
            //.placeholder(ColorDrawable(ContextCompat.getColor(context, R.color.colorImagePlaceholder)))
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(requestOptions)
            .into(this)
    }
}

@BindingAdapter("setListItemImage")
fun ImageView.setListItemImage(imageUrl: String?) {

    if (imageUrl.isNullOrEmpty()) visibility = View.GONE
    else {
        visibility = View.VISIBLE
//        var requestOptions = RequestOptions()
//        requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(12))

        val requestOptions = RequestOptions().transform(CenterCrop(), CircleCrop())

        Glide.with(context)
            .load(imageUrl)
            //.placeholder(ColorDrawable(ContextCompat.getColor(context, R.color.colorImagePlaceholder)))
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(requestOptions)
            .into(this)
    }
}

@BindingAdapter("setArticleSource")
fun ImageView.setArticleSource(imageUrl: String?) {

    if (imageUrl.isNullOrEmpty()) visibility = View.GONE
    else {
        visibility = View.VISIBLE
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(12))

        Glide.with(context)
            .load( imageUrl)
           // .placeholder(ColorDrawable(ContextCompat.getColor(context, R.color.colorImagePlaceholder)))
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(requestOptions)
            .into(this)
    }
}


@BindingAdapter("detailBtnVisibility")
fun View.detailBtnVisibility(navId: String?) {
    if (navId.isNullOrEmpty()) gone()
    else visible()
}