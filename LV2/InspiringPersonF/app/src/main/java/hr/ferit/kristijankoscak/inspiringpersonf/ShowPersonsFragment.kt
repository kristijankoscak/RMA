package hr.ferit.kristijankoscak.inspiringpersonf

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_edit_person.*
import kotlinx.android.synthetic.main.activity_edit_person.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_add_person.*
import kotlinx.android.synthetic.main.fragment_add_person.view.*
import kotlinx.android.synthetic.main.fragment_show_persons.view.*


class ShowPersonsFragment : Fragment() ,FragmentInteractionListener{
    private lateinit var rootView: View
    private lateinit var personDisplay: RecyclerView

    private lateinit var mMainActivity: MainActivity

    override fun onActivityCreated(savedState: Bundle?) {
        super.onActivityCreated(savedState)
        mMainActivity = activity as MainActivity
        mMainActivity.setListener(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mMainActivity = context as MainActivity
    }

    override fun submit(text:String?){
        displayData()
    }

    companion object {
        fun newInstance(): ShowPersonsFragment{
            return ShowPersonsFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_show_persons, container, false);
        setUpUi()
        return rootView;
    }

    private fun setUpUi(){
        personDisplay = rootView.personDisplay
        personDisplay.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL,false)
        personDisplay.itemAnimator = DefaultItemAnimator()
        personDisplay.addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))
        displayData()
    }
    private fun displayData() {
        val personListener = object: PersonInteractionListener{
            override fun onShowDetails(id: Int) {
                val person = PersonRepository.get(id)
                Toast.makeText(activity, person!!.quote.toString(), Toast.LENGTH_SHORT).show()
            }
            override fun removePerson(id: Int) {
                PersonRepository.remove(id)
                (personDisplay.adapter as PersonAdapter).refreshData(PersonRepository.persons)
            }

            override fun updatePerson(id: Int) {
                val person = PersonRepository.get(id)
                mMainActivity.editPerson(id.toString())



                /*val id :String = id.toString()
                val intent:Intent = Intent(activity,EditPerson::class.java)
                intent.putExtra("id", id)
                startActivity(intent)*/
            }
        }
        personDisplay.adapter = PersonAdapter(PersonRepository.persons,personListener)
    }
}