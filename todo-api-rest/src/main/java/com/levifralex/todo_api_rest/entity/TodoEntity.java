package com.levifralex.todo_api_rest.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Todo")
@Table(name = "tbl_todo")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TodoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String description;

	@CreationTimestamp
	private Timestamp timestamp;

	@Column(columnDefinition = "TINYINT default 1")
	private int state;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_user_id",
            foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES tbl_user(id) ON DELETE CASCADE"))
    @JsonIgnore
    UserEntity user;

}
