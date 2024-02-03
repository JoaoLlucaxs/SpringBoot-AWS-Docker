package com.essentials;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {
    private static final AtomicLong atomic =new AtomicLong();

    
    // Sairá assim localhost:8080/greeting?name=Joao; se não por padrão será World
    @RequestMapping(value="/sum/{numberOne}/{numberTwo}", method=RequestMethod.GET)
    public Double sum(@PathVariable(value = "numberOne") String numberOne, 
    		@PathVariable(value = "numberTwo") String numberTwo) throws Exception{
    	
    	if(!isNumeric(numberOne) ||!isNumeric(numberOne)) {
    		throw new Exception();
    	}
    	
        return convertToNumber(numberOne) + convertToNumber(numberTwo);
    }


	private Double convertToNumber(String strNumber) {
		if(strNumber == null) {
			return 0D;
		}
		String number=strNumber.replaceAll(",", "."); // mesmo se inserir 8,9 sairá 8.9
		return null;
	}


	private boolean isNumeric(String numeric) {
		// TODO Auto-generated method stub
		return false;
	}
}
