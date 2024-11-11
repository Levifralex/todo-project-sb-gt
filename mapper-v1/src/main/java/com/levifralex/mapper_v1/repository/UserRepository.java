package com.levifralex.mapper_v1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.levifralex.mapper_v1.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	public Optional<UserEntity> findByName(String name);
    public Optional<UserEntity> findByEmail(String email);

}