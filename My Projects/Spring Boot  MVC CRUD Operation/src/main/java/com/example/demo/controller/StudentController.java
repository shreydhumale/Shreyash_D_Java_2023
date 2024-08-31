package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    //registration form
    @GetMapping("/register")
    public String registerStudent(Model model) {
        model.addAttribute("student", new Student());
        return "register-student";
    }

    //Create
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student, RedirectAttributes redirectAttributes) {
        studentService.saveStudent(student);
        int id = student.getSid();
        String message = "Student with id: " + id + " is saved successfully.";
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/getAllStudents";
    }

    //Read
    @GetMapping("/getAllStudents")
    public String getAllStudents(Model model, @RequestParam(value = "message", required = false) String message) {
        List<Student> students = studentService.getAllStudentsList();
        model.addAttribute("list", students);
        model.addAttribute("message", message);
        return "student-list";
    }

    //Edit
    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable int id, Model model) {
        Student student = studentService.getStudentyById(id);
        model.addAttribute("student", student);
        return "edit-student";
    }

    //Update
    @PostMapping("/update")
    public String updateStudent(@ModelAttribute Student student, RedirectAttributes redirectAttributes) {
        studentService.saveStudent(student);
        String message = "Student with id: " + student.getSid() + " has been updated successfully.";
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/getAllStudents";
    }

    //Delete
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id, RedirectAttributes redirectAttributes) {
        studentService.deleteStudentById(id);
        String message = "Student with id: " + id + " has been deleted successfully.";
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/getAllStudents";
    }
}
