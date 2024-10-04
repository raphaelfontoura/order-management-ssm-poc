package com.github.raphaelfontoura.order_management_ssm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class OrderService {
    
    private StateMachine<OrderStates, OrderEvents> stateMachine;

    @Autowired
    private StateMachineFactory<OrderStates, OrderEvents> stateMachineFactory;

    public void newOrder() {
        initOrderSaga();
        validateOrder();
    }

    private void validateOrder() {
        System.out.println("Validating order...");
        stateMachine.sendEvent(Mono.just(
            MessageBuilder.withPayload(OrderEvents.VALIDATE).build()
        )).subscribe(result -> System.out.println(result.getResultType()));
        System.out.println("Final state: " + stateMachine.getState().getId());
    }

    public void payOrder() {
        System.out.println("Paying order...");
        stateMachine.sendEvent(Mono.just(
            MessageBuilder.withPayload(OrderEvents.PAY).build()
        )).subscribe(result -> System.out.println(result.getResultType()));
        System.out.println("Final state: " + stateMachine.getState().getId());
    }

    public void shipOrder() {
        System.out.println("Shipping order...");
        stateMachine.sendEvent(Mono.just(
            MessageBuilder.withPayload(OrderEvents.SHIP).build()
        )).subscribe(result -> System.out.println(result.getResultType()));
        System.out.println("Final state: " + stateMachine.getState().getId());
    }

    public void completeOrder() {
        System.out.println("Completing order...");
        stateMachine.sendEvent(Mono.just(
            MessageBuilder.withPayload(OrderEvents.COMPLETE).build()
        )).subscribe(result -> System.out.println(result.getResultType()));
        System.out.println("Final state: " + stateMachine.getState().getId());
        
        stopOrderSaga();
    }

    private void stopOrderSaga() {
        System.out.println("Stopping saga...");
        stateMachine.stopReactively().subscribe();
    }

    private void initOrderSaga() {
        System.out.println("Initializing order saga...");
        stateMachine = stateMachineFactory.getStateMachine();
        stateMachine.startReactively().subscribe();
        System.out.println("Final state: " + stateMachine.getState().getId());
    }
}
