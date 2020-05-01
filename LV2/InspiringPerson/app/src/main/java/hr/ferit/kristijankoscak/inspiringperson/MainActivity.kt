package hr.ferit.kristijankoscak.inspiringperson

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpUi()
    }

    private fun setUpUi() {
        iconPersonList.setOnClickListener{ showPersons() }
        btnAddPerson.setOnClickListener{ addPerson()}
    }

    private fun showPersons(){
        val displayIntent = Intent(this,InspiringPersonActivity::class.java)
        startActivity(displayIntent)
    }
    private fun addPerson(){
        if(checkInputs()) {
            PersonRepository.add(
                InspiringPerson(
                    PersonRepository.persons.size + 1,
                    etImageLink.text.toString(),
                    etBirthDate.text.toString(),
                    etDeathDate.text.toString(),
                    etDesc.text.toString(),
                    etQuote.text.toString().split(",").toMutableList()
                )
            )
            resetInputs()
            Toast.makeText(applicationContext, "Person added!", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(applicationContext, "One of input is empty! Try again.", Toast.LENGTH_LONG).show()
        }
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

}
