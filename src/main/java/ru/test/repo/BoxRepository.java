package ru.test.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.test.model.BoxEntity;

@Repository
public interface BoxRepository extends JpaRepository<BoxEntity, Integer> {
	public List<BoxEntity> findAllByParentBox(BoxEntity parentBox);
}
