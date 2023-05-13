package com.example.demo;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
public class GrpcServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(5555).addService(new StatService()).build();
        server.start();
        System.out.println(server.getPort());
        server.awaitTermination();
    }
}
