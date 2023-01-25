package se.jensenyh.javacourse.saltmerch.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ColorVariant
{
    @JsonProperty("colorName") public String colorName;
    @JsonProperty("images") public List<String> images;
    @JsonProperty("sizes") public List<SizeContainer> sizes;

    public ColorVariant() {
        this.images = new ArrayList<>();
        this.sizes = new ArrayList<>();
    }
    public ColorVariant(String colorName, List<String> images, List<SizeContainer> sizes) {
        this.colorName = colorName;
        this.images = images;
        this.sizes = sizes;
    }

    // NOTE: you can leave this method as it is; it's used in ProductRepository.java
    public void setImagesFromCSV(String csv) throws Exception
    {
        images = new ArrayList<>(Arrays.asList(csv.split(",")));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColorVariant that = (ColorVariant) o;
        return Objects.equals(colorName, that.colorName) && Objects.equals(images, that.images) && Objects.equals(sizes, that.sizes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(colorName, images, sizes);
    }
}
