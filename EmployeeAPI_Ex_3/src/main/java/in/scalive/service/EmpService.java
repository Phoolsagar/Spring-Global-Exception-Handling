package in.scalive.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import in.scalive.entity.Emp;
import in.scalive.exception.EmpAlreadyExistsException;
import in.scalive.exception.NoSuchEmpExistsException;
import in.scalive.repository.EmpRepository;

@Service
public class EmpService {

	private EmpRepository repo;

	public EmpService(EmpRepository repo) {
		this.repo = repo;
	}

	public String addEmp(Emp e) {
		Emp existingEmp = repo.findByEname(e.getEname()).orElse(null);
		if (existingEmp != null) {
			throw new EmpAlreadyExistsException("emp with name:" + e.getEname() + " already exists");
		}
		repo.save(e);
		return "Emp saved successfully!";
	}

	public Emp getEmp(Integer id) {
		Emp existingEmp = repo.findById(id).orElse(null);
		if (existingEmp == null) {
			throw new NoSuchEmpExistsException("emp with id:" + id + " does not exists");
		}

		return existingEmp;
	}

	public String updateEmp(Emp updatedEmp, Integer id) {
		Emp existingEmp = repo.findById(id).orElse(null);
		if (existingEmp == null) {
			throw new NoSuchEmpExistsException("emp with id:" + id + " does not exists");
		}

		String newname = updatedEmp.getEname();
		if (newname != null && !newname.equals(existingEmp.getEname())) {
			Optional<Emp> optEmp = repo.findByEname(newname);
			if (optEmp.isPresent()) {
				throw new EmpAlreadyExistsException("emp with name:" + newname + " already exists");
			}

			existingEmp.setEname(newname);

		}

		if (updatedEmp.getSal() != null) {
			existingEmp.setSal(updatedEmp.getSal());
		}

		repo.save(existingEmp);
		return "Emp updated successfully!";
	}
}
