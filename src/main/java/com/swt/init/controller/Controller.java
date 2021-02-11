package com.swt.init.controller;

import com.swt.init.model.Details;
import com.swt.init.service.Scrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

	@Autowired
	private Scrapper scrapper;
	


	
	@GetMapping(value = "/get-details")
    public List<Details> getProofOfDelivery(@RequestParam(value = "city") String city,
											@RequestParam(value = "store") String store)
	{
		//Ahmedabad BODAKDEV
        return scrapper.run(city,store);
    }
	

}
