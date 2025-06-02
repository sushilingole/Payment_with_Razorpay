package com.pack01.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pack01.DTO.Students_Order;



public interface StudentRepository extends JpaRepository<Students_Order, Integer>
{
	//create the abstract method for update (status) by finding  Orderid:
	 public Students_Order findByRazorpayOrderId(String razorpayOrderId);
	 
} 


