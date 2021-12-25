package kitchenpos.tobe.orders.dto.ordertable;

public class OrderTableChangeEmptyRequest {

    private final boolean empty;

    public OrderTableChangeEmptyRequest(final boolean empty) {
        this.empty = empty;
    }

    public boolean isEmpty() {
        return empty;
    }
}
