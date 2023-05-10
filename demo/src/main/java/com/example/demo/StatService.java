package com.example.demo;

import com.example.demo.schema.StatServiceGrpc;
import com.example.demo.schema.*;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatService extends StatServiceGrpc.StatServiceImplBase {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StatRepository statRepository;

    @KafkaListener(id="statService",topics = "stat")
    public void listen(StatDTO statDTO){
        logger.info("received: "+statDTO.getMessage());
        statRepository.save(statDTO);
    }

    @Override
    public void getStatById(getStatByIdParams getStatByIdParams, StreamObserver<com.example.demo.schema.StatDTO> responseObserver){
        StatDTO reply = statRepository.getStatDTOById(getStatByIdParams.getId());
        com.example.demo.schema.StatDTO dto = com.example.demo.schema.StatDTO.newBuilder().setId(reply.getId()).setMessage(reply.getMessage()).setTimestamp(Long.valueOf(reply.getCreatedDate().getTime())).setStatus(reply.isStatus()).build();
        responseObserver.onNext(dto);
        responseObserver.onCompleted();
    }
    @Override
    public void getAllStat(com.example.demo.schema.nullParams request,
                           io.grpc.stub.StreamObserver<com.example.demo.schema.StatDTO> responseObserver) {
        List<StatDTO> reply = statRepository.findAll();
        for (StatDTO replyDto:reply
             ) {
            com.example.demo.schema.StatDTO dto = com.example.demo.schema.StatDTO.newBuilder().setId(replyDto.getId()).setMessage(replyDto.getMessage()).setTimestamp(Long.valueOf(replyDto.getCreatedDate().getTime())).setStatus(replyDto.isStatus()).build();
            responseObserver.onNext(dto);
        }
        responseObserver.onCompleted();
    }
}
