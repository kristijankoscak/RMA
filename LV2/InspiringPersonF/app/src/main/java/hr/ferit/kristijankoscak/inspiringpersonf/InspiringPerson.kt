package hr.ferit.kristijankoscak.inspiringpersonf

data class InspiringPerson(
    var id: Int = 0,
    val image:String,
    val birthDate: String,
    val deathDate: String,
    val description:String,
    val quote: MutableList<String>
) {}