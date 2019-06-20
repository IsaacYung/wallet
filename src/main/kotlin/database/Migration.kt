package database

import configurations.databases.Exposed
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object Cities : IntIdTable() {
    val name = varchar("name", 50)
}

fun migration() {

    Exposed.connect()

    transaction {
        // print sql to std-out
        addLogger(StdOutSqlLogger)

        SchemaUtils.create(Cities)

        // insert new city. SQL: INSERT INTO configurations.databases.Cities (name) VALUES ('St. Petersburg')
        val stPeteId = Cities.insert { cities ->
            cities[name] = "St. Petersburg"
        } get Cities.id

        // 'select *' SQL: SELECT configurations.databases.Cities.id, configurations.databases.Cities.name FROM configurations.databases.Cities
        println("configurations.databases.Cities: ${Cities.selectAll()}")
    }
}