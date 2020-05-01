package hr.ferit.kristijankoscak.inspiringpersonf

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_add_person.*
import kotlinx.android.synthetic.main.fragment_add_person.view.btnAddPerson


class AddPersonFragment : Fragment() , FragmentInteractionListener{
    private lateinit var rootView: View
    private lateinit var etImageLink: EditText
    private lateinit var etBirthDate: EditText
    private lateinit var etDeathDate: EditText
    private lateinit var etDesc: EditText
    private lateinit var etQuote: EditText
    private var identifier:Int = 0

    private lateinit var mMainActivity: MainActivity

    override fun submit(text: String?) {
        var id :Int = text!!.toInt()
        this.identifier = id
        setInputs(id)
        btnAddPerson.setText("Update")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mMainActivity = activity as MainActivity
        mMainActivity.setAddListener(this)
    }

    companion object {
        fun newInstance(): AddPersonFragment {
            return AddPersonFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_add_person, container, false);
        setUpUi()
        return rootView;
    }
    private fun setUpUi() {
        initializeElements()
        rootView.btnAddPerson.setOnClickListener{ addPerson() }
    }
    private fun initializeElements(){
        etImageLink = rootView.findViewById(R.id.etImageLink)
        etBirthDate = rootView.findViewById(R.id.etBirthDate)
        etDeathDate = rootView.findViewById(R.id.etDeathDate)
        etDesc = rootView.findViewById(R.id.etDesc)
        etQuote = rootView.findViewById(R.id.etQuote)
    }
    private fun addPerson(){
        var person : InspiringPerson = InspiringPerson(
            0,
            etImageLink.text.toString(),
            etBirthDate.text.toString(),
            etDeathDate.text.toString(),
            etDesc.text.toString(),
            etQuote.text.toString().split(",").toMutableList()
        )
        if(checkInputs() && btnAddPerson.text == "Add") {
            proceedAdd(person)
        }
        if(checkInputs() && btnAddPerson.text == "Update"){
            proceedUpdate(person)
        }
        else{
            Toast.makeText(activity, "One of input is empty! Try again.", Toast.LENGTH_LONG).show()
        }
    }
    private fun proceedAdd(person:InspiringPerson){
        person.id = PersonRepository.persons.last().id + 1
        PersonRepository.add(person)
        resetInputs()
        mMainActivity.addPerson("Person added!")
    }
    private fun proceedUpdate(person:InspiringPerson){
        person.id = this.identifier
        PersonRepository.replace(person)
        mMainActivity.addPerson("Person updated!")
        resetInputs()
        btnAddPerson.setText("Add")
    }
    private fun resetInputs() {
        etImageLink.text.clear()
        etBirthDate.text.clear()
        etDeathDate.text.clear()
        etDesc.text.clear()
        etQuote.text.clear()
    }
    private fun checkInputs():Boolean {
        var state = true;
        if(etImageLink.text.isEmpty()||etBirthDate.text.isEmpty()||etDeathDate.text.isEmpty()||etDesc.text.isEmpty()||etQuote.text.isEmpty()){
            state = false;
        }
        return state
    }

    private fun setInputs(id:Int){
        etImageLink.setText(PersonRepository.get(id)!!.image)
        etBirthDate.setText(PersonRepository.get(id)!!.birthDate)
        etDeathDate.setText(PersonRepository.get(id)!!.deathDate)
        etDesc.setText(PersonRepository.get(id)!!.description)
        var quotes = PersonRepository.get(id)!!.quote.toString().replace("[","").replace("]","")
        etQuote.setText(quotes)
    }
}