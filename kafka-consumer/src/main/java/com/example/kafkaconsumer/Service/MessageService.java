package com.example.kafkaconsumer.Service;
import com.example.kafkaconsumer.model.MessageDTO;
import com.example.kafkaconsumer.repository.MessageRepository;
import com.example.kafkaconsumer.schema.MessServiceGrpc;
import io.grpc.stub.annotations.GrpcGenerated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@GrpcGenerated
public class MessageService extends MessServiceGrpc.MessServiceImplBase {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmailService emailService;

    @Autowired
    private MessageRepository messageRepository;

    @KafkaListener(id="console-consumer-19584",topics = "noti")
    public void listen(MessageDTO messageDTO){
        logger.info("received: " + messageDTO.getTo());
        emailService.sendEmail(messageDTO);
    }

    @Override
    public void getAllMess(com.google.protobuf.Empty request,
                           io.grpc.stub.StreamObserver<com.example.kafkaconsumer.schema.MessageDTO> responseObserver){
        List<MessageDTO> dtoList = messageRepository.findAll();
        for (MessageDTO dto:dtoList
             ) {
            com.example.kafkaconsumer.schema.MessageDTO reply = com.example.kafkaconsumer.schema.MessageDTO.newBuilder().setId(dto.getId()).setContent(dto.getContent()).setTo(dto.getTo()).setToName(dto.getToName()).build();
            responseObserver.onNext(reply);
        }
        responseObserver.onCompleted();
    }
    @Override
    public void getMessageById(com.example.kafkaconsumer.schema.getMessageByIdParams request,
                               io.grpc.stub.StreamObserver<com.example.kafkaconsumer.schema.MessageDTO> responseObserver){
        MessageDTO dto = messageRepository.getMessageDTOById(request.getId());
        com.example.kafkaconsumer.schema.MessageDTO reply = com.example.kafkaconsumer.schema.MessageDTO.newBuilder().setId(dto.getId()).setContent(dto.getContent()).setTo(dto.getTo()).setToName(dto.getToName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
