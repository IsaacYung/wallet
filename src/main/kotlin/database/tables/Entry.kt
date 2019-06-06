package database.tables

import org.jetbrains.exposed.sql.Table

object Entry : Table() {
    val id = integer("id").autoIncrement().primaryKey()
}