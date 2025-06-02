package com.pack01.Services;

import java.util.Map;

import org.hibernate.tool.schema.internal.StandardUserDefinedTypeExporter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.pack01.DTO.Students_Order;
import com.pack01.Repo.StudentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class StudentOrderService 
{
	
	// Take the Repository field :
	@Autowired
	private StudentRepository STOrderRepo;

	// In order to use the StudentOrder for that purpose use Rezorpay keys and
	// secret keys :
	// take the keys form the .properties file :
	// At the time of taking the value form the .properties file use @Value
	// annotation By using {$property_name}
	@Value("${razorpay.key.id}")
	private String rezorPaykey;

	@Value("${razorpay.secret.key}")
	private String rezorPaySecret;

	// for creating the Order in razorpay :
	private RazorpayClient client;

	// write the logic :

	public Students_Order CreateOrder(Students_Order STOrder) throws Exception {
		// taking the JSON format data :
		JSONObject orderReq = new JSONObject();
		orderReq.put("amount", STOrder.getAmount() * 100);// Rupees Amount Converted into Paisa :
		orderReq.put("currency", "INR");
		orderReq.put("receipt", STOrder.getEmail());

		this.client = new RazorpayClient(rezorPaykey, rezorPaySecret);

		// create the Order in Razorpay :
		Order razorPayOrder = client.orders.create(orderReq);

		STOrder.setRazorpayOrderId(razorPayOrder.get("id"));
		STOrder.setOrderstatus(razorPayOrder.get("status"));

		// Using the repository object save the order
		STOrderRepo.save(STOrder);

		return STOrder;

	}

	
	//logic for updating the recored:
	public Students_Order UpdateStudentOrder(Map<String , String> responsePayLoad) {
		String OrderId = responsePayLoad.get("razorpay_order_id");
		
		
		// implementing the repository interface
		Students_Order order = STOrderRepo.findByRazorpayOrderId(OrderId);
		
		 
		   
		// set the status as Payment_completed
		order.setOrderstatus("PAYMENT_COPMPELETED");

		// called the save method for update the Status :
		Students_Order updatedOrder = STOrderRepo.save(order);

		return updatedOrder;

	}
	

}
