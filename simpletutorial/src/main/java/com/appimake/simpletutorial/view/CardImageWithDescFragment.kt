package com.appimake.simpletutorial.view

import android.app.Activity
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appimake.simpletutorial.R
import com.appimake.simpletutorial.Tutorial
import com.appimake.simpletutorial.intf.ITutorialCallback
import com.appimake.simpletutorial.models.CardImageWithDescModel
import com.appimake.simpletutorial.models.ImageModel
import com.appimake.simpletutorial.util.getBitmapFromBase64Link
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_card_image_with_desc.view.*

class CardImageWithDescFragment : androidx.fragment.app.Fragment() {
    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_card_image_with_desc, container, false)
        val obj = Tutorial.pageList[arguments?.getInt(ARG_SECTION_NUMBER)!!]

        when (obj) {
            is CardImageWithDescModel -> CardImageWithDescModel(obj)
        }

        return rootView
    }

    private fun CardImageWithDescModel(obj: CardImageWithDescModel) {

        rootView.fragment_card_image_with_desc_image.apply {
            when (obj.image) {
                is String -> Glide.with(this).load(obj.image).into(this)
                is ImageModel -> {
                    when (obj.image.type) {
                        "base64URL" -> {
                            obj.image.value.toString().getBitmapFromBase64Link(object : ITutorialCallback {
                                override fun updated(tag: String, code: Int, data: Any) {
                                    if (code == 200 && data is Bitmap) {
                                        (context as Activity).runOnUiThread {
                                            this@apply.setImageBitmap(data)
                                        }
                                    }
                                }
                            })
                        }
                    }
                }
            }
        }

        rootView.fragment_card_image_with_desc_title.apply {
            text = obj.title
        }

        rootView.fragment_card_image_with_desc_sub_title.apply {
            text = obj.subTitle
        }

        rootView.fragment_card_image_with_desc_desc.apply {
            text = obj.desc
        }
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(sectionNumber: Int): CardImageWithDescFragment {
            val fragment = CardImageWithDescFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }
}