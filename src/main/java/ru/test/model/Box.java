package ru.test.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name = "box")
public class Box {
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(targetEntity = Box.class, cascade=CascadeType.PERSIST)
	@JoinColumn(name = "parent_box_id", nullable=true)
	private Box parentBox;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "parentBox", cascade = CascadeType.ALL)
	private List<Item> item;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "parentBox", cascade = CascadeType.ALL)
	private List<Box> box;
	
	public Box() {
		super();
	}

	public Integer getId() {
		return id;
	}
	
	@XmlAttribute
	public void setId(Integer id) {
		this.id = id;
	}

	public List<Item> getItem() {
		return item;
	}

	@XmlElement(name = "Item", nillable = true)
	public void setItem(List<Item> item) {
		this.item = item;
	}
	

	public List<Box> getBox() {
		return box;
	}
	@XmlElement(name = "Box", nillable = true)
	public void setBox(List<Box> box) {
		this.box = box;
	}

	public Box getParentBox() {
		return parentBox;
	}

	public void setParentBox(Box parentBox) {
		this.parentBox = parentBox;
	}

	@Override
	public String toString() {
		return "Box [id=" + id + ", parentBoxId="  +  "]";
	}
	
}
