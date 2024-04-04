package curd.com.example.routing

import curd.com.example.models.Customer
import curd.com.example.models.customerStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.customerRouting(){

    route("/customer"){
        get {
            if (customerStorage.isNotEmpty()){
                call.respond(customerStorage)
            }else{
                call.respondText("No customers found", status = HttpStatusCode.OK)
            }
        }
        get("{id?}"){
            val id = call.parameters["id"] ?: return@get call.respondText ( "missing id" ,status = HttpStatusCode.BadRequest)

            val customer = customerStorage.find{it.id == id} ?: return@get call.respondText ( "id :$id not found" ,status = HttpStatusCode.NotFound)

            call.respond(customer)
        }
        post {
            val customer = call.receive<Customer>()
            customerStorage.add(customer)
            call.respondText("new customer added" , status = HttpStatusCode.Created)
        }
        delete ("{id?}"){
            val id = call.parameters["id"] ?: return@delete call.respondText("no customer of this id" ,status = HttpStatusCode.BadRequest)
            if (customerStorage.removeIf { it.id == id }){
                call.respondText("id : $id deleted successfully", status = HttpStatusCode.Accepted)
            }else{
                call.respondText("not found", status = HttpStatusCode.NotFound)
            }




        }
    }
}