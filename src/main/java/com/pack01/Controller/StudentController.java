package com.pack01.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pack01.DTO.Students_Order;
import com.pack01.Services.StudentOrderService;

@Controller
public class StudentController 
{ 
	@Autowired
	private StudentOrderService service ;
	
	
	@GetMapping(value = "/")
	public String init() 
	{
		return "index";
	}
	
	//Saving the Order into database and for Razorpay :
	@PostMapping(value="/create-order" , produces= "application/json")
	@ResponseBody
	public ResponseEntity<Students_Order> CreateOrder(@RequestBody  Students_Order STOrderData) throws Exception
	{
		Students_Order createdOrder = service.CreateOrder(STOrderData);
		return new ResponseEntity<Students_Order>(createdOrder , HttpStatus.CREATED);
	}
	
	//for updating the status :
	
	
	@PostMapping("/handle-payment-callback")
	public String handlePaymentCallback(@RequestParam Map<String, String> responsePayLoad) 
	{
		//after payment whatever the response it will printing just that response printing on the console : 
		System.out.println(responsePayLoad);
		
		 service.UpdateStudentOrder(responsePayLoad);
		
		return "success";
	}
	
	
	
}
