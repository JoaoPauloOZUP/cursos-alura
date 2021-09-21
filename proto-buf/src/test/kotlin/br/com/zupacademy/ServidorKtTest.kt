package br.com.zupacademy

import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@MicronautTest
internal class ServidorKtTest(
    @Inject val grpcClient: FretesServiceGrpc.FretesServiceBlockingStub?
) {

    @Test
    fun `deve retornar erro caso final do cep seja 333`(){
        val response = grpcClient?.calculaFrete(CalculaFreteRequest.newBuilder().setCep("13054-000").build())

        assertNotNull(response)
        assertTrue(response?.valor != null)
    }
}

@Factory
class FactoryTest {
    @Singleton
    fun blockingStub(@GrpcChannel("grpc-server") channel: ManagedChannel): FretesServiceGrpc.FretesServiceBlockingStub? {
        return FretesServiceGrpc.newBlockingStub(channel)
    }
}