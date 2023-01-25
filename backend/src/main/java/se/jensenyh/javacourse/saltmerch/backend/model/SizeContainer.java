package se.jensenyh.javacourse.saltmerch.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class SizeContainer
{
    @JsonProperty("size") public String size;
    @JsonProperty("stock") public int stock;

    public SizeContainer() {
    }

    public SizeContainer(String size, int stock) {
        this.size = size;
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SizeContainer that = (SizeContainer) o;
        return stock == that.stock && Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, stock);
    }
}
