package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Student;

public interface StudentService {
	
    public Student saveStudent(Student student);
    public List<Student> getAllStudentsList();
    public Student getStudentyById(int id);
    public void deleteStudentById(int id);
    public void updateStudent(Student student);

}
