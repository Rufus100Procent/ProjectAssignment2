package se.jensenyh.javacourse.saltmerch.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class CartItem {
    @JsonProperty("productId") public int productId;
    @JsonProperty("title") public String title;
    @JsonProperty("color") public String color;
    @JsonProperty("size") public String size;
    @JsonProperty("previewImage") public String previewImage;
    @JsonProperty("quantity") public int quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem item = (CartItem) o;
        return productId == item.productId && quantity == item.quantity && Objects.equals(title, item.title) && Objects.equals(color, item.color) && Objects.equals(size, item.size) && Objects.equals(previewImage, item.previewImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, title, color, size, previewImage, quantity);
    }

    public CartItem() {
    }

    public CartItem(int productId, String title, String color, String size, String previewImage) {
        this.productId = productId;
        this.title = title;
        this.color = color;
        this.size = size;
        this.previewImage = previewImage;
    }

    public CartItem(int productId, String title, String color, String size, String previewImage, int quantity) {
        this.productId = productId;
        this.title = title;
        this.color = color;
        this.size = size;
        this.previewImage = previewImage;
        this.quantity = quantity;
    }
}