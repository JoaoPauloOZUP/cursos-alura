package br.com.zupacademy

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import jakarta.inject.Singleton

@Factory
class GrpcClienteFactory {

    @Singleton
    fun fretesClientsStubs(@GrpcChannel("fretes") channel: ManagedChannel ): FretesServiceGrpc.FretesServiceBlockingStub? {
        return FretesServiceGrpc.newBlockingStub(channel)
    }
}