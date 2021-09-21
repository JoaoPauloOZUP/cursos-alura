package br.com.zupacademy

import com.google.protobuf.Any
import com.google.rpc.Code
import io.grpc.Status
import io.grpc.protobuf.StatusProto
import io.grpc.stub.StreamObserver
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
import java.lang.IllegalArgumentException
import kotlin.random.Random

@Singleton
class FretesGrpcServer : FretesServiceGrpc.FretesServiceImplBase() {

    private val logger = LoggerFactory.getLogger(FretesGrpcServer::class.java)

    override fun calculaFrete(request: CalculaFreteRequest?, responseObserver: StreamObserver<CalculaFreteResponse>?) {
        logger.info("Calculando frete para: $request")

        val cep = request!!.cep

        if(cep == null || cep.isBlank()) {
            val e = Status.INVALID_ARGUMENT
                .withDescription("CEP deve ser informado")
                .asRuntimeException()

            responseObserver?.onError(e)
        }

        if(!cep!!.matches("[0-9]{5}-[0-9]{3}".toRegex())) {
            val e = Status.ALREADY_EXISTS
                .withDescription("Formato errado")
                .augmentDescription("Esperado: 00000-000")
                .asRuntimeException()

            responseObserver?.onError(e)
        }

        var valor = 0.0
        try {
            valor = Random.nextDouble(from = 0.0, until = 140.0)

            if(valor > 100.0) {
                throw IllegalArgumentException("Erro inesperado ao calcular frete")
            }
        } catch (e: Exception) {
//            responseObserver?.onError(
//                Status.INTERNAL
//                    .withDescription(e.message)
//                    .withCause(e)
//                    .asRuntimeException()
//            )

//            throw StatusRuntimeException(Status.INTERNAL
//                .withDescription("erro interno inesperado")
//                .withCause(e))
        }

        if(cep.endsWith("333")) {
            // Status pacote google
            val statusProto = com.google.rpc.Status.newBuilder()
                .setCode(Code.PERMISSION_DENIED_VALUE)
                .setMessage("Usuario não pode acessar o recurso")
                .addDetails(Any.pack(ErroDetails.newBuilder()
                    .setCode(301)
                    .setMessage("Token expirado")
                    .build()))
                .build()

            val e = StatusProto.toStatusRuntimeException(statusProto)

            responseObserver?.onError(e)
        }

        val response = CalculaFreteResponse.newBuilder()
            .setCep(request!!.cep)
            .setValor(valor.toString())
            .build()

        responseObserver!!.onNext(response)
        responseObserver.onCompleted()
    }
}