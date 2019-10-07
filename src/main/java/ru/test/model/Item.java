package ru.test.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "item")
public class Item {
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private  Integer id;

	@Column(name = "color")
	private  String color;
	
	@JsonProperty("box")
	@ManyToOne(targetEntity = Box.class, cascade=CascadeType.PERSIST)
	@JoinColumn(name = "parent_box_id", nullable=true)
	private Box parentBox;
	
	public Item() { super(); }

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

	public Box getParentBox() {
		return parentBox;
	}

	public void setParentBox(Box parentBox) {
		this.parentBox = parentBox;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", color=" + color + ", parentBox="  + "]";
	}

}
