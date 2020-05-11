package hr.ferit.kristijankoscak.inspiringpersondb

object PersonRepository {
    val persons: MutableList<InspiringPerson>
    init{
        persons = retreivePersons()
    }
    private fun retreivePersons():MutableList<InspiringPerson>{
        return mutableListOf(
            InspiringPerson(1,"https://upload.wikimedia.org/wikipedia/commons/c/ce/Bill_Gates_in_WEF%2C_2007.jpg","28/10/1955","-",
                "Born and raised in Seattle, Washington, Gates co-founded Microsoft with childhood friend Paul Allen in 1975, in Albuquerque.",
                "Don’t compare yourself with anyone in this world..If you do, You are insulting yourself.;Quote2.;Quote3.;Quote4"),
            InspiringPerson(2,"https://upload.wikimedia.org/wikipedia/commons/thumb/d/dc/Steve_Jobs_Headshot_2010-CROP_%28cropped_2%29.jpg/619px-Steve_Jobs_Headshot_2010-CROP_%28cropped_2%29.jpg",
                "24/2/1955","5/10/2011","Steven Paul Jobs was an American business magnate, industrial designer, investor, and media proprietor. ",
                "Your time is limited, so don’t waste it living someone else’s life.;Quote2;Quote3."),
            InspiringPerson(3,"https://upload.wikimedia.org/wikipedia/commons/thumb/2/23/Rimac_%28102%29.jpg/757px-Rimac_%28102%29.jpg", "12/2/1988",
                "-","Mate Rimac is a Croatian innovator, entrepreneur, and founder of the Croatian car company Rimac Automobili in 2009. "
                ,"I'm the best.;Quote.;Quote2")
        )
    }
    fun remove(id: Int) = persons.removeAll{ person -> person.id == id }
    fun get(id: Int): InspiringPerson? = persons.find { person -> person.id == id }
    fun add(person: InspiringPerson) = persons.add(person)
    fun replace(person:InspiringPerson) {
        var index = persons.indexOf(get(person.id))
        persons[index] = person
    }
}