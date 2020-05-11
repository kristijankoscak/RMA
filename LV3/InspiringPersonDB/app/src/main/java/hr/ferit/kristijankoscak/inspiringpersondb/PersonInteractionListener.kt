package hr.ferit.kristijankoscak.inspiringpersondb

interface PersonInteractionListener {
    fun onShowDetails(id: Int)
    fun removePerson(id:Int)
    fun updatePerson(id:Int)
}