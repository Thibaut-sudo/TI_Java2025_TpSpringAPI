package be.bstorm.demospringapi.api.models.security.forms;

import jakarta.validation.constraints.NotNull;

public class OrderForm {

    @NotNull
    private Double totalAmount;

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}