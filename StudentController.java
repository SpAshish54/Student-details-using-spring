package controller;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import model.Student;

@Controller
//@RequestMapping("/details")
public class StudentController {
	private List<Student> students = new ArrayList<>();
	
	@GetMapping("/details")
	public String rank(Model model) {
		Collections.sort(students);
		model.addAttribute("students" , students);
		model.addAttribute("new_Student", new Student());
		return "details";
	}
	
	@PostMapping("/add")
	public String addStudent(@ModelAttribute Student student) {
		students.add(student);
		return "redirect:/details";
	}
	
	@GetMapping("/delete/{index}" )
	public String deleteStudent(@PathVariable int index) {
		students.remove(index);
		return "redirect:/details";
	}
	
	@GetMapping("/change/{index}")
	public String editStudent(@PathVariable int index, Model model) {
		Student student = students.get(index);
		model.addAttribute("student", student);
		model.addAttribute("studentIndex",index);
		return "change";
	}
	
	@PostMapping("/update/{index}")
	public String updateStudent(@PathVariable int index, @ModelAttribute Student updatedStudent) {
		students.set(index, updatedStudent);
		return "redirect:/details";
	}
}
