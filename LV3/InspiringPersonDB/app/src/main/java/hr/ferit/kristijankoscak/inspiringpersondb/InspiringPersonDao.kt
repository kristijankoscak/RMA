package hr.ferit.kristijankoscak.inspiringpersondb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface InspiringPersonDao{
    @Insert
    fun insert(person:InspiringPerson);
    @Delete
    fun delete(person:InspiringPerson);
    @Query("SELECT * FROM persons")
    fun getAll(): MutableList<InspiringPerson>;
    @Query("SELECT * FROM persons WHERE id = :id")
    fun getByID(id: Int): InspiringPerson
    @Query("DELETE FROM persons")
    fun emptyDB()
    @Query("UPDATE persons SET image= :image,birthDate =:birthDate,deathDate=:deathDate,description=:description,quote=:quotes WHERE id= :id")
    fun updatePerson(id:Int,image:String,birthDate:String,deathDate:String,description:String,quotes:String)
}
