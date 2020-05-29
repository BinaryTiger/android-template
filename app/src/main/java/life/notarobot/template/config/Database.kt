package life.notarobot.template.config

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(

)
abstract class Database : RoomDatabase() {
//    abstract fun dao(): Dao

    // ref: https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/
    companion object {
        @Volatile
        private var INSTANCE: life.notarobot.template.config.Database? = null

        fun getDatabase(context: Context): life.notarobot.template.config.Database {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        life.notarobot.template.config.Database::class.java,
                        config[DATABASE_NAME_KEY] as String
                    )
                        // TODO remove this after version hit 1.0 (release to real user)
                        .fallbackToDestructiveMigration()
                        .build()

                INSTANCE = instance

                return instance
            }
        }
    }
}