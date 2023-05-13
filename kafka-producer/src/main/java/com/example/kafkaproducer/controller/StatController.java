package com.example.kafkaproducer.controller;

import com.example.kafkaproducer.schema.*;
import com.example.kafkaproducer.schema.ListStat;
import com.example.kafkaproducer.schema.StatDTO;
import com.example.kafkaproducer.schema.StatServiceGrpc;
import com.example.kafkaproducer.schema.getStatByIdParams;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.internal.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/stat")
public class StatController {

//    private ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",5555).usePlaintext().build();

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @GetMapping(value = "/getStatById",produces = MediaType.APPLICATION_JSON_VALUE)
    public StatDTO getStatById(@RequestParam Long id){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",5555).usePlaintext().build();
        StatServiceGrpc.StatServiceBlockingStub stub = StatServiceGrpc.newBlockingStub(channel);
        com.example.kafkaproducer.schema.StatDTO result = stub.getStatById(getStatByIdParams.newBuilder().setId(id).build());
        channel.shutdown();
        return result;
    }
    @GetMapping(value = "/getAllStat",produces = MediaType.APPLICATION_JSON_VALUE)
    public ListStat getAllStat(){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",5555).usePlaintext().build();
        StatServiceGrpc.StatServiceBlockingStub stub = StatServiceGrpc.newBlockingStub(channel);
        ListStat result = stub.getAllStat(Empty.newBuilder().build());
        channel.shutdown();
        return result;
    }

    @PostMapping(value = "/create")
    public com.example.kafkaproducer.schema.StatDTO createStat(@RequestBody com.example.kafkaproducer.schema.StatDTO stat) {
        kafkaTemplate.send("statistic", stat);
        return stat;
    }
}
