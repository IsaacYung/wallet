package controllers

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing

fun Application.wallet() {
    routing {
        route("/wallet") {
            get {
                call.respond(mapOf("Hello" to "Wallet!"))
            }
        }
    }
}