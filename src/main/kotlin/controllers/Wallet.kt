package controllers

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing
import mu.KotlinLogging

fun Application.wallet() {

    val logger = KotlinLogging.logger {}

    routing {
        route("/wallet") {
            get {
                logger.info { "Vai q tá" }
                call.respond(mapOf("Hello" to "Wallet!"))
            }
        }
    }
}