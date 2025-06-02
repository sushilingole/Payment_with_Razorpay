package com.pack01.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "student_order")
public class Students_Order 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderid;
	
	
	private String name ;
	private String email;
	private String phono;
	private String cource;
	private Integer amount;
	private String orderstatus;
	
	@Column(name = "razorpay_order_id") // Specify the correct column name
	private String razorpayOrderId;
	
	
	
	
	
}
