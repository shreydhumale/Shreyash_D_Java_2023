package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService	{
	
	@Autowired
	private StudentRepository repository;
	
	@Override
	public Student saveStudent(Student student) {
		return repository.save(student);
	}

	@Override
	public List<Student> getAllStudentsList() {
		return repository.findAll();
	}

	@Override
	public Student getStudentyById(int id) {
		Optional<Student> optional = repository.findById(id);
		return optional.get();
	}

	@Override
	public void deleteStudentById(int id) {
		repository.delete(getStudentyById(id));
	}

	@Override
	public void updateStudent(Student student) {
		repository.save(student);
	}
	

	 
}
