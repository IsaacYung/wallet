import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.jackson.jackson
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun Application.wallet() {
    routing {
        route("/") {
            get {
                call.respond(mapOf("Hello" to "World!"))
            }
        }
    }
}

fun Application.account() {
    routing {
        route("/") {
            get {
                call.respond(mapOf("Hello" to "World!"))
            }
        }
    }
}

fun Application.module() {
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    wallet()
    account()
}

fun main() {
    embeddedServer(Netty, 8080, module = Application::module).start()
}