package hr.ferit.kristijankoscak.inspiringpersondb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_person.view.*


class PersonAdapter(persons: MutableList<InspiringPerson>,personListener:PersonInteractionListener): RecyclerView.Adapter<PersonHolder>() {

    private val persons: MutableList<InspiringPerson>
    private val personListener: PersonInteractionListener
    init {
        this.persons = mutableListOf()
        this.persons.addAll(persons)
        this.personListener = personListener
    }
    fun refreshData(persons: MutableList<InspiringPerson>) {
        this.persons.clear()
        this.persons.addAll(persons)
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int = persons.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder {
        val personView = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        val personHolder = PersonHolder(personView)
        return personHolder
    }
    override fun onBindViewHolder(holder: PersonHolder, position: Int) {
        val person = persons[position]
        holder.bind(person,personListener)
    }
}
class PersonHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    fun bind(person:InspiringPerson,personListener: PersonInteractionListener){
        itemView.birth.text = person.birthDate
        itemView.died.text = person.deathDate
        itemView.desc.text = person.description
        Picasso.get()
            .load(person.image)
            .fit()
            .placeholder(R.drawable.person_icon)
            .error(R.drawable.person_icon)
            .into(itemView.personImage)
        itemView.personImage.setOnClickListener{personListener.onShowDetails(person.id)}
        itemView.desc.setOnClickListener{personListener.removePerson(person.id)}
        itemView.desc.setOnLongClickListener {personListener.updatePerson(person.id); true}
    }
}