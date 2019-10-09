package ru.test.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import ru.test.object.Box;

public class BoxDto implements Box {
	
	private Integer id;
	
	private List<ItemDto> item;
	
	private List<BoxDto> box;
	
	public BoxDto() {
		super();
	}

	public Integer getId() {
		return id;
	}
	
	
	
	public BoxDto(Integer id, List<ItemDto> item, List<BoxDto> box) {
		super();
		this.id = id;
		this.item = item;
		this.box = box;
	}

	@XmlAttribute
	public void setId(Integer id) {
		this.id = id;
	}

	public List<ItemDto> getItem() {
		return item;
	}

	@XmlElement(name = "Item", nillable = true)
	public void setItem(List<ItemDto> item) {
		this.item = item;
	}
	

	public List<BoxDto> getBox() {
		return box;
	}
	
	@XmlElement(name = "Box", nillable = true)
	public void setBox(List<BoxDto> box) {
		this.box = box;
	}

	@Override
	public String toString() {
		return "Box [id=" + id + ", parentBoxId="  +  "]";
	}
	
}
