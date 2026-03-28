package in.scalive.controller;

import java.net.http.HttpClient;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import in.scalive.entity.Emp;
import in.scalive.exception.EmpAlreadyExistsException;
import in.scalive.exception.ErrorResponse;
import in.scalive.exception.NoSuchEmpExistsException;
import in.scalive.service.EmpService;

@RestController
@RequestMapping("api/emp")
public class EmpController {
	
	private EmpService serv;
	
	public EmpController(EmpService serv) {
		this.serv = serv;
	}
	
	@PostMapping("/add")
	public String addEmp(@RequestBody Emp empToAdd) {
	   return serv.addEmp(empToAdd);
	}
	
	@GetMapping("/{empId}")
	public Emp getEmp(@PathVariable("empId")Integer empId) {
		return serv.getEmp(empId);
	}
	
	@PutMapping("/update/{empId}")
	public String updateEmp(@RequestBody Emp updatedEmp, @PathVariable("empId")Integer empId) {
		return serv.updateEmp(updatedEmp, empId);
	}
	
	@ExceptionHandler(value = NoSuchEmpExistsException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleNoSuchEmpExistsException(NoSuchEmpExistsException ex) {
		ErrorResponse response = new ErrorResponse();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage(ex.getMessage());
		return response;
	}
	
	@ExceptionHandler(value = EmpAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErrorResponse handleEmpAlreadyExistsException(EmpAlreadyExistsException ex) {
		ErrorResponse response = new ErrorResponse();
		response.setStatusCode(HttpStatus.CONFLICT.value());
		response.setMessage(ex.getMessage());
		return response;
	}

}
