package com.levifralex.todo_list_mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.levifralex.todo_list_mvc.entity.TodoEntity;

import jakarta.transaction.Transactional;

@Transactional
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

	@Query(value = "select t from Todo t where t.state=1")
	List<TodoEntity> findAllActives();

	@Modifying
	@Query(value = "update tbl_todo set state=0 where id=:id", nativeQuery = true)
	void deleteLogic(@Param("id") Long id);

}
