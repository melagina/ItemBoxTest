package ru.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.test.model.BoxEntity;

import java.util.List;

@Repository
public interface BoxRepository extends JpaRepository<BoxEntity, Integer> {
    List<BoxEntity> findAllByParentBox(BoxEntity parentBox);
}
