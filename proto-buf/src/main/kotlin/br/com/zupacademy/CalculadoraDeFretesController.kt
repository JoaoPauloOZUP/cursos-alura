package br.com.zupacademy


import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.grpc.protobuf.StatusProto
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.exceptions.HttpStatusException
import jakarta.inject.Inject

@Controller("/fretes")
class CalculadoraDeFretesController {

    @Inject
    private lateinit var grpcClient: FretesServiceGrpc.FretesServiceBlockingStub

    @Get
    fun calcula(@QueryValue cep: String): HttpResponse<Any> {
        val request = CalculaFreteRequest.newBuilder()
            .setCep("13054-061")
            .build()

        val response: CalculaFreteResponse?
        try {
            response = grpcClient.calculaFrete(request)
        } catch (e: StatusRuntimeException) {
            val statusCode = e.status.code
            val description = e.status.description

            if(statusCode == Status.Code.INVALID_ARGUMENT) {
                throw HttpStatusException(HttpStatus.BAD_REQUEST, description)
            }

            if(statusCode == Status.Code.PERMISSION_DENIED) {
                val statusProto = StatusProto.fromThrowable(e)

                if(statusCode == null) {
                    throw HttpStatusException(HttpStatus.FORBIDDEN, description)
                }

                val anyDetails = statusProto?.detailsList?.get(0)
                val errorDetails = anyDetails?.unpack(ErroDetails::class.java)

                throw HttpStatusException(HttpStatus.FORBIDDEN, "${errorDetails?.code}: ${errorDetails?.message}")

            }

            throw HttpStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }


        return HttpResponse.ok(response.valor)
    }
}