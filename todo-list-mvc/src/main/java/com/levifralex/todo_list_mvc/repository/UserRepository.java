package com.levifralex.todo_list_mvc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.levifralex.todo_list_mvc.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	public Optional<UserEntity> findByName(String name);
    public Optional<UserEntity> findByEmail(String email);

}
