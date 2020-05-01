package hr.ferit.kristijankoscak.inspiringpersonf

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity()  {
    private  var showListener : FragmentInteractionListener? = null
    private  var addListener : FragmentInteractionListener? = null
    private  var listenerText:String? = null

    fun setShowListener(listener:FragmentInteractionListener){
        this.showListener = listener
        if(listenerText=="Person added!"){
            showListener!!.submit(listenerText)
        }
    }
    fun setAddListener(listener:FragmentInteractionListener){
        this.addListener = listener
        if(listenerText=="Person updated!"){
            addListener!!.submit(listenerText)
        }
    }
    fun addPerson(text:String?){
        if(showListener == null){
            this.listenerText = text
            return
        }
        showListener!!.submit(text)
        viewPager.currentItem = 0
    }
    fun editPerson(text:String?){
        if(addListener == null){
            this.listenerText = text
            return
        }
        addListener!!.submit(text)
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
