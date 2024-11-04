package com.levifralex.todo_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.levifralex.todo_backend.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	public Optional<UserEntity> findByName(String name);
    public Optional<UserEntity> findByEmail(String email);

}
