package in.scalive.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.scalive.entity.Emp;
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
	
	

}
