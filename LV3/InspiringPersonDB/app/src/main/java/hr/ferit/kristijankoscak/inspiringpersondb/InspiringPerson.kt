package hr.ferit.kristijankoscak.inspiringpersondb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persons")
data class InspiringPerson (
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "birthDate") val birthDate: String,
    @ColumnInfo(name = "deathDate") val deathDate: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "quote") val quote: String
)
