package hr.ferit.kristijankoscak.inspiringperson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_inspiring_person.*

class InspiringPersonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inspiring_person)
        setUpUi()
    }
    private fun setUpUi(){
        personDisplay.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        personDisplay.itemAnimator = DefaultItemAnimator()
        personDisplay.addItemDecoration(DividerItemDecoration(this,RecyclerView.VERTICAL))
        displayData()
    }
    private fun displayData() {
        val personListener = object: PersonInteractionListener{
            override fun onShowDetails(id: Int) {
                val person = PersonRepository.get(id)
                Toast.makeText(applicationContext, person!!.quote.toString(), Toast.LENGTH_SHORT).show()
            }
        }
        personDisplay.adapter = PersonAdapter(PersonRepository.persons,personListener)
    }
}
