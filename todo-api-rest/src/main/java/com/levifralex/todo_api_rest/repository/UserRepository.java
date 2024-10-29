package com.levifralex.todo_api_rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.levifralex.todo_api_rest.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	public Optional<UserEntity> findByName(String name);
    public Optional<UserEntity> findByEmail(String email);

}
