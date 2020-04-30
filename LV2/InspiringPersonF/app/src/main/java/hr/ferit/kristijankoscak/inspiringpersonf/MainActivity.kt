package hr.ferit.kristijankoscak.inspiringpersonf

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity()  {
    private  var fragmentListener : FragmentInteractionListener? = null
    private  var fragmentListener2 : FragmentInteractionListener? = null
    private  var listenerText:String? = null

    fun setListener(listener:FragmentInteractionListener){
        this.fragmentListener = listener
        if(listenerText=="Person added!"){
            fragmentListener!!.submit(listenerText)
        }
    }
    fun setListener2(listener:FragmentInteractionListener){
        this.fragmentListener2 = listener
        if(listenerText=="Person edit!"){
            fragmentListener2!!.submit(listenerText)
        }
    }
    fun addPerson(text:String?){
        if(fragmentListener == null){
            this.listenerText = text
            return
        }
        fragmentListener!!.submit(text)
        viewPager.currentItem = 0
    }
    fun editPerson(text:String?){
        if(fragmentListener2 == null){
            this.listenerText = text
            return
        }
        fragmentListener2!!.submit(text)
        viewPager.currentItem = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpUi()
    }

    private fun setUpUi() {
        viewPager.adapter = FragmentAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }
}
