package com.application.payment.service.impl;

import com.application.payment.client.AddressClient;
import com.application.payment.client.CartItemClient;
import com.application.payment.client.OrderClient;
import com.application.payment.client.UserClient;
import com.application.payment.dto.AddressDto;
import com.application.payment.dto.CartItemDto;
import com.application.payment.dto.OrderDto;
import com.application.payment.dto.UserDto;
import com.application.payment.model.PaymentAttempt;
import com.application.payment.model.PaymentOrder;
import com.application.payment.repository.PaymentAttemptRepository;
import com.application.payment.repository.PaymentOrderRepository;
import com.application.payment.security.TokenProvider;
import com.application.payment.service.PaymentAttemptService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentAttemptServiceImpl implements PaymentAttemptService {

    private final PaymentAttemptRepository paymentAttemptRepository;
    private final PaymentOrderRepository paymentOrderRepository;
    private final TokenProvider tokenProvider;
    private final AddressClient addressClient;
    private final CartItemClient cartItemClient;
    private final OrderClient orderClient;

    @Override
    @Transactional
    public PaymentOrder placeOrder(PaymentOrder purchase, HttpServletRequest httpServletRequest) {
        PaymentOrder paymentOrder = new PaymentOrder();

        UserDto userDto = tokenProvider.extractUser(httpServletRequest);
        String token = "Bearer " + tokenProvider.getToken(httpServletRequest);

        AddressDto address = addressClient.getAddressByUserId(token, httpServletRequest);
        List<OrderDto> orderDtoList = orderClient.getOrder(token, httpServletRequest);

        OrderDto order = new OrderDto();
        if (!orderDtoList.isEmpty()) {
            order = orderDtoList.get(orderDtoList.size() - 1);
        }

        List<CartItemDto> cartItemDtos = cartItemClient.getCartItemByUserId(token);

        String orderTrackingNumber = generateOrderTrackingNumber();
        paymentOrder.setOrderTrackingNumber(orderTrackingNumber);
        paymentOrder.setCustomer(userDto);
        paymentOrder.setShippingAddress(address);
        paymentOrder.setOrder(order);
        paymentOrder.setOrderItems(cartItemDtos);
        paymentOrder.setPaymentAttempts(getByPayment(paymentOrder));

        return paymentOrder;

    }

    @Override
    public PaymentOrder getPaymentOrder(Long userId) {
        return paymentOrderRepository.findPaymentOrderByCustomerId(userId);
    }

    @Override
    public PaymentOrder getPaymentOrder(Long purchaseId, Long userId) {
        return paymentOrderRepository.findPaymentOrderByCustomerIdAndId(purchaseId, userId);
    }

    @Override
    public List<PaymentAttempt> getByPayment(PaymentOrder paymentOrder) {
        return paymentAttemptRepository.findByPaymentOrderId(paymentOrder.getId());
    }

    @Override
    public PaymentAttempt createPaymentAttempt(Long userId, boolean success) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        PaymentAttempt paymentAttemptNew = new PaymentAttempt();
        paymentAttemptNew.setPaymentOrder(getPaymentOrder(userId));
        paymentAttemptNew.setDatetime(dateFormat.format(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())));
        if (success) {
            paymentAttemptNew.setStatus("success");
        } else {
            paymentAttemptNew.setStatus("unsuccessfully");
        }
        paymentAttemptRepository.save(paymentAttemptNew);

        return paymentAttemptNew;
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }

}
