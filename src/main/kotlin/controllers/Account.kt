package controllers

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing

fun Application.account() {
    routing {
        route("/account") {
            get {
                call.respond(mapOf("Hello" to "Account!"))
            }
        }
    }
}