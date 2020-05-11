package hr.ferit.kristijankoscak.inspiringpersondb

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = arrayOf(InspiringPerson::class))
abstract class InspiringPersonDatabase : RoomDatabase() {
    abstract fun inspiringpersonDao(): InspiringPersonDao
    companion object {
        private const val NAME = "inspiringperson_database"
        private var INSTANCE: InspiringPersonDatabase? = null
        fun getInstance(): InspiringPersonDatabase {
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    ContextSaver.ApplicationContext,
                    InspiringPersonDatabase::class.java,
                    NAME)
                    .allowMainThreadQueries() // Not for real apps!
                    .build()
            }
            return INSTANCE as InspiringPersonDatabase
        }
    }
}