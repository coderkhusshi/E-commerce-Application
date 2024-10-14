package com.fd.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "foodImage")
public class ImageFood {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private String type;
	
	private long foodId;
	
	@Lob
	private byte[] data;
	
	public ImageFood(String name, String type, byte[] data, long foodId){
		this.name = name;
		this.type = type;
		this.data = data;
		this.foodId = foodId;
	}
}
