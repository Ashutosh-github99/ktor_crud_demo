package curd.com.example.plugins

import curd.com.example.routing.customerRouting
import curd.com.example.routing.getOrderRoute
import curd.com.example.routing.listOrdersRoute
import curd.com.example.routing.totalizeOrderRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        customerRouting()

        listOrdersRoute()
        getOrderRoute()
        totalizeOrderRoute()
    }
}
