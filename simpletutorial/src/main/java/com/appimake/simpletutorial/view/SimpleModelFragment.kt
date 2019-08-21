package com.appimake.simpletutorial.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appimake.simpletutorial.R
import com.appimake.simpletutorial.Tutorial
import com.appimake.simpletutorial.models.SimpleModel
import kotlinx.android.synthetic.main.fragment_simple_tutorial.view.*

class SimpleModelFragment : androidx.fragment.app.Fragment() {
    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_simple_tutorial, container, false)
        val obj = Tutorial.pageList[arguments?.getInt(ARG_SECTION_NUMBER)!!]

        when (obj) {
            is SimpleModel -> simpleModel(obj)
        }

        return rootView
    }

    private fun simpleModel(obj: SimpleModel) {
        rootView.section_image.apply {
            if (obj.img is Drawable) {
                this.setImageDrawable(obj.img)
            }
        }
        rootView.section_title.apply {
            text = obj.title
            obj.custom.let {
                typeface = it.fontTypeFaceForTitle
                textSize = it.fontSizeTitle
            }
        }
        rootView.section_desc.apply {
            text = obj.desc
            obj.custom.let {
                typeface = it.fontTypeFaceForDesc
                textSize = it.fontSizeDesc
            }
        }
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(sectionNumber: Int): SimpleModelFragment {
            val fragment = SimpleModelFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }
}