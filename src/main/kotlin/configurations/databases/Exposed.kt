package configurations.databases

import org.jetbrains.exposed.sql.Database

class Exposed {

    companion object {
        fun connect() {
            Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")
        }
    }

}