package ru.test.model;

import java.lang.annotation.Annotation;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import ru.test.object.Item;

@Entity
@Table(name = "item")
public class ItemEntity implements Item {
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private  Integer id;

	@Column(name = "color")
	private  String color;
	
	@JsonProperty("box")
	@ManyToOne(targetEntity = BoxEntity.class, cascade=CascadeType.PERSIST)
	@JoinColumn(name = "parent_box_id", nullable=true)
	private BoxEntity parentBox;
	
	public ItemEntity() { super(); }

	public String getColor() {
		return color;
	}

	public Integer getId() {
		return id;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public BoxEntity getParentBox() {
		return parentBox;
	}

	public void setParentBox(BoxEntity parentBox) {
		this.parentBox = parentBox;
	}

	public Class<? extends Annotation> annotationType() {
		// TODO Auto-generated method stub
		return null;
	}

	public String name() {
		// TODO Auto-generated method stub
		return null;
	}


}
