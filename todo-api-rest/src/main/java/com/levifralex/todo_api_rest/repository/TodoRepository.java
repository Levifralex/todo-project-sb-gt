package com.levifralex.todo_api_rest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.levifralex.todo_api_rest.entity.TodoEntity;

import jakarta.transaction.Transactional;

@Transactional
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

	@Modifying
	@Query(value = "update tbl_todo set state=0 where id=:id", nativeQuery = true)
	void deleteLogic(@Param("id") Long id);
	

	@Query(value = "select t from Todo t where t.state=1")
	Page<TodoEntity> findAllPaging(Pageable pageable);

}
