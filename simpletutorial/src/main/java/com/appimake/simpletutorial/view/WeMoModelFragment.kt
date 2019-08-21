package com.appimake.simpletutorial.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appimake.simpletutorial.R
import com.appimake.simpletutorial.Tutorial
import com.appimake.simpletutorial.models.WeMoStyleModel
import kotlinx.android.synthetic.main.fragment_wemo_style.view.*


class WeMoModelFragment  : androidx.fragment.app.Fragment() {
    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_wemo_style, container, false)
        val obj = Tutorial.pageList[arguments?.getInt(ARG_SECTION_NUMBER)!!]

        when (obj) {
            is WeMoStyleModel -> wemoModel(obj)
        }

        return rootView
    }

    private fun wemoModel(obj: WeMoStyleModel) {
        rootView.wemo_image.apply {
            if (obj.img is Drawable) {
                this.setImageDrawable(obj.img)
            }
        }

        rootView.wemo_header.apply {
            text = obj.headerText
            obj.custom.let {
                typeface = it.fontTypeFace
                textSize = it.fontSize
                setTextColor(it.fontColor)
            }
        }

        rootView.wemo_header_container.apply {
            rootView.wemo_header.visibility = View.VISIBLE
            obj.headerView?.let {
                rootView.wemo_header.visibility = View.GONE
                if(it.parent != null){
                    (it.parent as ViewGroup).removeAllViews()
                }
                this.addView(it)
            }
        }

        rootView.wemo_footer.apply {
            text = obj.footerText
            obj.custom.let {
                typeface = it.fontTypeFace
                textSize = it.fontSize
                setTextColor(it.fontColor)
            }
        }
        rootView.wemo_footer_container.apply {
            obj.bottomBackground.let {
                when(it){
                    is Int -> setBackgroundColor(it)
                    is Drawable -> background = it
                }
            }
        }
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(sectionNumber: Int): WeMoModelFragment {
            val fragment = WeMoModelFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }
}