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
@Table(name = "restaurantImage")
public class ImageRestaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private String type;
	
	private long restaurantId;
	
	@Lob
	private byte[] data;
	
	public ImageRestaurant(String name, String type, byte[] data, long restuarantId){
		this.name = name;
		this.type = type;
		this.data = data;
		this.restaurantId = restuarantId;
	}
}
