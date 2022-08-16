package com.byyakke.study


import android.os.Parcelable
import android.util.Log
import kotlinx.parcelize.Parcelize
import java.sql.Connection
import java.sql.DriverManager


data class StudyItem(var description: String, var numberOfLikes: Int) {

    private val studyItems  = mutableListOf<StudyItem>()
    private lateinit var  connection: Connection

    fun getResult(): MutableList<StudyItem> {
         val  jdbcUrl = "jdbc:postgresql://localhost:5432/study"
         val connection = DriverManager.getConnection(jdbcUrl, "postgres", "yarno")

        val query = connection.prepareStatement("select * from \"Item\" order by \"nOL\"")
        val result = query.executeQuery()


        while(result.next()) {

            val description = result.getString("description")
            val numberOfLikes = result.getInt("nOL")

            studyItems.add(StudyItem(description, numberOfLikes))
        }
        connection.close()
        return studyItems
    }

    fun writeToDatabase(desc: String) {
        Class.forName("org.postgresql.Driver")
        val  jdbcUrl = "jdbc:postgresql://10.0.0.2:5432/study"
        connection = DriverManager.getConnection(jdbcUrl, "postgres", "yarno")

        val query = connection.prepareStatement("insert into \"item\"(\"description\") " +
                "values (${desc})")
        query.executeQuery()
        connection.close()
        //
    }
}