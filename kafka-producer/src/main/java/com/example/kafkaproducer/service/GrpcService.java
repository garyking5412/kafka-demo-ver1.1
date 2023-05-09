package com.example.kafkaproducer.service;

import com.example.kafkaproducer.schema.AccountServiceGrpc;
import com.example.kafkaproducer.schema.ListAccount;
import com.example.kafkaproducer.schema.nullParams;
import io.grpc.stub.StreamObserver;
import io.grpc.stub.annotations.GrpcGenerated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@GrpcGenerated
@Service
public class GrpcService extends AccountServiceGrpc.AccountServiceImplBase {
    @Override
    public void getAllAccount(nullParams request, StreamObserver<ListAccount> responseObserver) {
        super.getAllAccount(request, responseObserver);
    }
}
