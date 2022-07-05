package kitchenpos.order.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import kitchenpos.order.domain.Order;
import kitchenpos.order.domain.OrderLineItems;
import kitchenpos.order.domain.OrderStatus;
import kitchenpos.table.domain.OrderTable;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class OrderRequest {
    private Long id;
    private Long orderTableId;
    private String orderStatus;
    private LocalDateTime orderedTime;
    private List<OrderLineItemRequest> orderLineItems;

    public Long getId() {
        return id;
    }

    public Long getOrderTableId() {
        return orderTableId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public LocalDateTime getOrderedTime() {
        return orderedTime;
    }

    public List<OrderLineItemRequest> getOrderLineItems() {
        return orderLineItems;
    }

    private static Long setOrderTableIdFromOrder(Order order) {
        Long orderTableId = null;
        OrderTable orderTable = order.getOrderTable();
        if (orderTable != null) {
            orderTableId = order.getOrderTable().getId();
        }
        return orderTableId;
    }
    private static String setOrderStatusNameFromOrder(Order order) {
        String orderStatusName = null;
        OrderStatus orderStatus = order.getOrderStatus();
        if (orderStatus != null) {
            orderStatusName = orderStatus.name();
        }
        return orderStatusName;
    }

    private static List<OrderLineItemRequest> setOrderLineItemsRequestsFromOrder(Order order) {
        OrderLineItems orderLineItems = order.getOrderLineItems();
        List<OrderLineItemRequest> orderLineItemRequests = null;
        if (orderLineItems != null) {
            orderLineItemRequests = orderLineItems.getOrderLineItems()
                    .stream()
                    .map(OrderLineItemRequest::of)
                    .collect(Collectors.toList());
        }
        return orderLineItemRequests;
    }

    public static OrderRequest of(Order order){
        Long orderTableId = setOrderTableIdFromOrder(order);
        String orderStatusName = setOrderStatusNameFromOrder(order);
        List<OrderLineItemRequest> orderLineItemRequests = setOrderLineItemsRequestsFromOrder(order);

        return OrderRequest.builder()
                .id(order.getId())
                .orderTableId(orderTableId)
                .orderStatus(orderStatusName)
                .orderedTime(order.getOrderedTime())
                .orderLineItems(orderLineItemRequests)
                .build();
    }
}
