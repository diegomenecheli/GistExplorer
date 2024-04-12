package com.menecheli.gistexplorer.domain.gist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.menecheli.gistexplorer.domain.gist.model.Gist
import com.menecheli.gistexplorer.utils.FilesConverter
import com.menecheli.gistexplorer.utils.GistOwnerConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [Gist::class], version = 1, exportSchema = false)
@TypeConverters(GistOwnerConverter::class, FilesConverter::class)
abstract class GistsDataBase : RoomDatabase() {

    abstract fun getGistsDao(): GistsDao

    companion object {
        @Volatile
        private var instance: GistsDataBase? = null
        private val Lock = Any()

        fun getInstance(context: Context): GistsDataBase =
            instance ?: synchronized(Lock) {
                instance ?: createdDatabase(context).also { instance = it }
            }

        private fun createdDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                GistsDataBase::class.java,
                "gist7_db.db"
            )
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        GlobalScope.launch(Dispatchers.Main) {
                            //todo
                        }
                    }
                })
                .build()
    }
}
