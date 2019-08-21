package com.appimake.simpletutorial

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.appimake.simpletutorial.models.CardImageWithDescModel
import com.appimake.simpletutorial.models.SimpleModel
import com.appimake.simpletutorial.models.WeMoStyleModel
import com.appimake.simpletutorial.util.dpToPx
import com.appimake.simpletutorial.view.CardImageWithDescFragment
import com.appimake.simpletutorial.view.SimpleModelFragment
import com.appimake.simpletutorial.view.WeMoModelFragment
import kotlinx.android.synthetic.main.activity_simple_tutorial.*
import kotlinx.android.synthetic.main.activity_simple_tutorial.view.*
import kotlinx.android.synthetic.main.fragment_simple_tutorial.view.*


class SimpleTutorial : AppCompatActivity() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_tutorial)

        setOption()

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        container.adapter = mSectionsPagerAdapter

        setIndicator(0)

        container.addOnPageChangeListener(object : androidx.viewpager.widget.ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                setIndicator(position)

                if(Tutorial.pageList.size -1 == position){
                    container_close.visibility =  View.VISIBLE
                    container_cv_close.visibility =  View.VISIBLE
                    container_skip.visibility =  View.INVISIBLE
                    Tutorial.closeView?.let {
                        container_cn_close.visibility =  View.INVISIBLE
                    }
                } else {
                    container_skip.visibility =  View.VISIBLE
                    container_close.visibility =  View.INVISIBLE
                    container_cv_close.visibility =  View.INVISIBLE
                    container_cn_close.visibility =  View.VISIBLE
                }
            }
        })

        container_close.setOnClickListener {
            finish()
        }

        container_skip.setOnClickListener {
            finish()
        }
    }

    private fun setOption(){

        header_cn.apply {
            Tutorial.barTitle?.let {
                this.visibility = View.VISIBLE
                this.header_cn_title.text = it

            }

            Tutorial.barHexColor?.let {
                this.visibility = View.VISIBLE
                this.setBackgroundColor(Color.parseColor(it))
                this@SimpleTutorial.tutorial_cn.setBackgroundColor(Color.parseColor(it))
            }
        }

        main_content.apply {
            Tutorial.background?.let {

                when(it){
                    is Int -> setBackgroundColor(it)
                    is Drawable -> background = it
                }
            }
        }

        container_cv_close.apply {
            Tutorial.closeView?.let {
                it.setOnClickListener { this@SimpleTutorial.finish() }
                container_cv_close.removeAllViews()
                container_cv_close.addView(it)
            }
        }

        container_skip.apply {
            Tutorial.skipText?.let {
                text = it
            }

            Tutorial.fontTypeFace?.let {
                typeface = it
            }

            Tutorial.fontSize.let {
                textSize = it
            }
            setTextColor(Tutorial.fontColor)
        }

        container_close.apply {
            Tutorial.closeText?.let {
                text = it
            }

            Tutorial.fontTypeFace?.let {
                typeface = it
            }

            Tutorial.fontSize.let {
                textSize = it
            }
            setTextColor(Tutorial.fontColor)
        }
    }

    private fun setIndicator(position: Int) {
        container_indicator.removeAllViews()

        Tutorial.pageList.forEachIndexed { index, _ ->
            val drawable = GradientDrawable()

            drawable.setColor(if (index == position) Color.parseColor(Tutorial.indicatorSelectHex) else Color.parseColor(Tutorial.indicatorDeselectHex))
            drawable.shape = GradientDrawable.OVAL
            drawable.setSize(dpToPx(6f).toInt(), dpToPx(6f).toInt())

            val iv = ImageView(this)
            iv.setImageDrawable(drawable)
            val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            lp.marginStart = dpToPx(4f).toInt()
            lp.marginEnd = dpToPx(4f).toInt()
            iv.layoutParams = lp

            container_indicator.addView(iv)
        }
    }

    inner class SectionsPagerAdapter(fm: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): androidx.fragment.app.Fragment {
            when(Tutorial.pageList.get(position)){
                is WeMoStyleModel -> return WeMoModelFragment.newInstance(position)
                is CardImageWithDescModel -> return CardImageWithDescFragment.newInstance(position)
                else -> return SimpleModelFragment.newInstance(position)
            }
        }

        override fun getCount(): Int {
            return Tutorial.pageList.size
        }
    }
}
