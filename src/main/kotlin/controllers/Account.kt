package controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.util.StdDateFormat
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.javafaker.Faker
import domains.Person
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing
import messaging.Producer
import org.apache.kafka.clients.producer.ProducerRecord

fun Application.account() {
    routing {
        route("/account") {
            get {
                val faker = Faker()
                val fakePerson = Person(
                    firstName = faker.name().firstName(),
                    lastName = faker.name().lastName(),
                    birthDate = faker.date().birthday()
                )

                val jsonMapper = ObjectMapper().apply {
                    registerKotlinModule()
                    disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    setDateFormat(StdDateFormat())
                }

                val fakePersonJson = jsonMapper.writeValueAsString(fakePerson)
                val producer = Producer().createProducer("zookeeper:2181")

                val futureResult = producer.send(ProducerRecord("carro", fakePersonJson))
                futureResult.get()

                call.respond(mapOf(
                    "Person" to fakePerson,
                    "Future" to futureResult.get()
                ))
            }
        }
    }
}