package ru.test.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.test.model.BoxEntity;
import ru.test.model.ItemEntity;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {
	public List<ItemEntity> findAllByParentBoxAndColor(BoxEntity parentBox, String color);
}
