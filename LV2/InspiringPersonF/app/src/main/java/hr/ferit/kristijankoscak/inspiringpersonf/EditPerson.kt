package hr.ferit.kristijankoscak.inspiringpersonf


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit_person.*

class EditPerson : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_person)
        var id:String = intent.getStringExtra("id")
        setInputs(id)
        btnUpdatePerson.setOnClickListener{updatePerson(id)}
    }

    private fun setInputs(id:String){
        val id = id.toInt()
        var person: InspiringPerson = PersonRepository.get(id)!!
        etImageLinkEdit.setText(person.image)
        etBirthDateEdit.setText(person.birthDate)
        etDeathDateEdit.setText(person.deathDate)
        etDescEdit.setText(person.description)
        etQuoteEdit.setText(person.quote)
    }
    private fun updatePerson(id:String){
        if(checkInputs()){
            val idPerson = id.toInt()
            val person = InspiringPerson(
                idPerson,etImageLinkEdit.text.toString(),etBirthDateEdit.text.toString(),etDeathDateEdit.text.toString(),
                etDescEdit.text.toString(),etQuoteEdit.text.toString()
            )
            PersonRepository.replace(person)
            val intent: Intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        else{
            Toast.makeText(this, "One of input is empty! Try again.", Toast.LENGTH_LONG).show()
        }
    }
    private fun checkInputs():Boolean {
        var state = true;
        if(etImageLinkEdit.text.isEmpty()||etBirthDateEdit.text.isEmpty()||etDeathDateEdit.text.isEmpty()||etDescEdit.text.isEmpty()||etQuoteEdit.text.isEmpty()){
            state = false;
        }
        return state
    }
}
