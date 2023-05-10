package com.example.kafkaproducer.controller;

import com.example.kafkaproducer.schema.StatDTO;
import com.example.kafkaproducer.schema.StatServiceGrpc;
import com.example.kafkaproducer.schema.getStatByIdParams;
import com.example.kafkaproducer.schema.nullParams;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stat")
public class StatController {

    private ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",5555).usePlaintext().build();

    @GetMapping(value = "/getStatById")
    public StatDTO getStatById(@RequestParam Long id){
        StatServiceGrpc.StatServiceBlockingStub stub = StatServiceGrpc.newBlockingStub(channel);
        com.example.kafkaproducer.schema.StatDTO result = stub.getStatById(getStatByIdParams.newBuilder().setId(id).build());
        channel.shutdown();
        return result;
    }
    @GetMapping(value = "/getAllStat")
    public List<StatDTO> getStatById(){
        StatServiceGrpc.StatServiceBlockingStub stub = StatServiceGrpc.newBlockingStub(channel);
        List<StatDTO> result = (List<StatDTO>) stub.getAllStat(Empty.newBuilder().build());
        channel.shutdown();
        return result;
    }
}
