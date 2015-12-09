package org.baeldung.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.baeldung.thymeleaf.model.Student;

/**
 * Handles requests for the student model.
 * 
 */
@Controller
public class StudentController {

    @RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
    public String saveStudent(@Valid @ModelAttribute Student student, BindingResult errors, Model model) {
        if (!errors.hasErrors()) {
            // get mock objects
            List<Student> students = buildStudents();
            // add current student
            students.add(student);
            model.addAttribute("students", students);
        }
        return ((errors.hasErrors()) ? "addStudent" : "listStudents");
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.GET)
    public String addStudent(Model model) {
        model.addAttribute("student", new Student());
        return "addStudent";
    }

    @RequestMapping(value = "/listStudents", method = RequestMethod.GET)
    public String listStudent(Model model) {

        model.addAttribute("students", buildStudents());

        return "listStudents";
    }

    private List<Student> buildStudents() {
        List<Student> students = new ArrayList<Student>();

        Student student1 = new Student();
        student1.setId(1001);
        student1.setName("John Smith");
        students.add(student1);

        Student student2 = new Student();
        student2.setId(1002);
        student2.setName("Jane Williams");
        students.add(student2);

        return students;
    }
}
