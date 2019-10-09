package ru.test.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ru.test.object.Storage;

@XmlRootElement(name = "Storage")
public class StorageDto implements Storage {
	
	private List<BoxDto> box;
	private List<ItemDto> item;

	public List<BoxDto> getBox() {
		return box;
	}
	public List<ItemDto> getItem() {
		return item;
	}

	@XmlElement(name =  "Box", nillable = true)
	public void setBox(List<BoxDto> box) {
		this.box = box;
	}

	@XmlElement(name =  "Item", nillable = true)
	public void setItem(List<ItemDto> item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "Storage [Box=" + box + "]";
	}
	
	
//	@XmlElement(name =  "Box")
//	private List<Box> box;
//	public List<Item> item;
//	public List<Box> getBox() {
//		return box;
//	}
//	public List<Item> getItem() {
//		return item;
//	}
//	
//	public void setBox(List<Box> box) {
//		this.box = box;
//	}
//	public void setItem(List<Item> item) {
//		this.item = item;
//	}
//	@Override
//	public String toString() {
//		return "Storage [box=" + box + ", item=" + item + "]";
//	}
	
	
}
