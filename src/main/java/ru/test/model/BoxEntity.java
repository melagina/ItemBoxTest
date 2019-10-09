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

import ru.test.object.Box;

@Entity
@Table(name = "box")
public class BoxEntity implements Box {
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(targetEntity = BoxEntity.class, cascade=CascadeType.PERSIST)
	@JoinColumn(name = "parent_box_id", nullable=true)
	private BoxEntity parentBox;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "parentBox", cascade = CascadeType.ALL)
	private List<ItemEntity> item;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "parentBox", cascade = CascadeType.ALL)
	private List<BoxEntity> box;
	
	public BoxEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public List<ItemEntity> getItem() {
		return item;
	}

	public void setItem(List<ItemEntity> item) {
		this.item = item;
	}
	

	public List<BoxEntity> getBox() {
		return box;
	}
	public void setBox(List<BoxEntity> box) {
		this.box = box;
	}

	public BoxEntity getParentBox() {
		return parentBox;
	}

	public void setParentBox(BoxEntity parentBox) {
		this.parentBox = parentBox;
	}

	
}
