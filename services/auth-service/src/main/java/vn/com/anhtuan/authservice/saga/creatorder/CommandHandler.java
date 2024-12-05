package vn.com.anhtuan.authservice.saga.creatorder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import vn.com.anhTuan.commons.messaging.Command;
import vn.com.anhTuan.commons.messaging.Reply;
import vn.com.anhTuan.commons.saga.aggregate.CreateOrderAggregate;
import vn.com.anhtuan.authservice.service.UserService;

import java.util.function.Function;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CommandHandler {
    private final ObjectMapper objectMapper;

    private final UserService userService;

    @Bean
    public Function<Message<Command<Long, CreateOrderAggregate>>,Message<Reply<Long, CreateOrderAggregate>>> handleDebitBalanceCommand() {
        return message -> {
            Command<Long, CreateOrderAggregate> command = message.getPayload();
            CreateOrderAggregate aggregate = objectMapper.convertValue(message.getPayload(), new TypeReference<>() {});
            try {
                userService.debitBalance(aggregate.getUserId(), aggregate.getTotal());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return MessageBuilder.withPayload(Reply.failure(command.identifier(), aggregate)).build();
            }
            return MessageBuilder.withPayload(Reply.success(command.identifier(), aggregate)).build();
        };
    }



}
