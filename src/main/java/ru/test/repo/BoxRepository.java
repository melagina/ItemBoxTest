package ru.test.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.test.model.Box;

@Repository
public interface BoxRepository extends JpaRepository<Box, Integer> {
	public List<Box> findAllByParentBox(Box parentBox);
}
