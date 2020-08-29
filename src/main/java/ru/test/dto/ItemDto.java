package ru.test.dto;

import ru.test.object.Item;

import javax.xml.bind.annotation.XmlAttribute;

public class ItemDto implements Item {

    private Integer id;

    private String color;

    public ItemDto() {
        super();
    }

    public String getColor() {
        return color;
    }

    public Integer getId() {
        return id;
    }

    @XmlAttribute
    public void setColor(String color) {
        this.color = color;
    }

    @XmlAttribute
    public void setId(Integer id) {
        this.id = id;
    }

}
