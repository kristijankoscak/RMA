package hr.ferit.kristijankoscak.inspiringpersonf

interface PersonInteractionListener {
    fun onShowDetails(id: Int)
    fun removePerson(id:Int)
    fun updatePerson(id:Int)
}