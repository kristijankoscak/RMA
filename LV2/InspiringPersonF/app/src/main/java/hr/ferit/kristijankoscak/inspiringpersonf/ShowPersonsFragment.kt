package hr.ferit.kristijankoscak.inspiringpersonf

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_show_persons.view.*
import java.util.*


class ShowPersonsFragment : Fragment() ,FragmentInteractionListener{
    private lateinit var rootView: View
    private lateinit var personDisplay: RecyclerView

    private lateinit var mMainActivity: MainActivity

    override fun onActivityCreated(savedState: Bundle?) {
        super.onActivityCreated(savedState)
        mMainActivity = activity as MainActivity
        mMainActivity.setShowListener(this)
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
        initializeElements()
        displayData()
    }
    private fun initializeElements(){
        personDisplay = rootView.personDisplay
        personDisplay.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL,false)
        personDisplay.itemAnimator = DefaultItemAnimator()
        personDisplay.addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))
    }
    private fun displayData() {
        val personListener = object: PersonInteractionListener{
            override fun onShowDetails(id: Int) {
                val person = PersonRepository.get(id)
                val randomNumber : Int = Random().nextInt(person!!.quote.size)
                Toast.makeText(activity, person!!.quote[randomNumber], Toast.LENGTH_SHORT).show()
            }
            override fun removePerson(id: Int) {
                PersonRepository.remove(id)
                (personDisplay.adapter as PersonAdapter).refreshData(PersonRepository.persons)
            }

            override fun updatePerson(id: Int) {
                mMainActivity.editPerson(id.toString())
            }
        }
        personDisplay.adapter = PersonAdapter(PersonRepository.persons,personListener)
    }
}