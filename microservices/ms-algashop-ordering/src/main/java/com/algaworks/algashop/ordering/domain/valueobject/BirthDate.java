package com.algaworks.algashop.ordering.domain.valueobject;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public record BirthDate(LocalDate value) {


    public BirthDate {
        Objects.requireNonNull(value);
        if (value.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Birth date cannot be in the future");
        }
    }

    public Integer age() {
        int years = Period.between(this.value, LocalDate.now()).getYears();
        return years;
    }


    @Override
    public String toString() {
        return value.toString();
    }
}
