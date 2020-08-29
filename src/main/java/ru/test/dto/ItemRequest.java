package ru.test.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;
import ru.test.object.Item;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemRequest implements Item {

    private final String color;

    @JsonValue
    private final int parentBoxId;

    public ItemRequest(String color, int parentBoxId) {
        super();
        this.color = color;
        this.parentBoxId = parentBoxId;
    }

    public String getColor() {
        return color;
    }

    public int getParentBoxId() {
        return parentBoxId;
    }

}
