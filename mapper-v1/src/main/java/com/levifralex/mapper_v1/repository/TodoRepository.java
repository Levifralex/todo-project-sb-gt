package com.levifralex.mapper_v1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.levifralex.mapper_v1.entity.TodoEntity;

import jakarta.transaction.Transactional;

@Transactional
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

	@Query(value = "select t from Todo t where t.state=1 ORDER BY t.timestamp DESC")
	List<TodoEntity> findAllActives();

	@Modifying
	@Query(value = "update tbl_todo set state=0 where id=:id", nativeQuery = true)
	void deleteLogic(@Param("id") Long id);

	@Query("select t from Todo t where upper(t.description) like upper(:description) and t.state=1")
	List<TodoEntity> findByLikeDescription(@Param("description") String description);

}
