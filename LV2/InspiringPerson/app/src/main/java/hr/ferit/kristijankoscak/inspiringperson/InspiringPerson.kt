package hr.ferit.kristijankoscak.inspiringperson

data class InspiringPerson(
  val id: Int = 0,
  val image:String,
  val birthDate: String,
  val deathDate: String,
  val description:String,
  val quote: MutableList<String>
) {}