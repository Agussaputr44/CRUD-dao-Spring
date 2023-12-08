package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.model.Employee;


@Controller
@RequestMapping("/employers")
public class EmployersPage {


    @Autowired
    private EmployeeDao empDao;

    @GetMapping
    public String index(Model model){
        model.addAttribute("emps", empDao.findAll());
        model.addAttribute("emp", new Employee());
        return "employers";
    }
    
    @GetMapping("/new")
    public String newForm(Model model){
        model.addAttribute("emp", new Employee());
        model.addAttribute("judulForm", "Add New Employee");
        model.addAttribute("mode", "add");
        return "formEmployers";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Employee data){
        empDao.save(data);
        return "redirect:/employers";
    }

    @GetMapping("/edit")
    public String editForm(Model model, @RequestParam("id") Integer id, Employee employee){
        Optional<Employee> existingEmpOpt = empDao.findById(id);
            model.addAttribute("mode", "add");

        if (existingEmpOpt.isPresent()) {
            
            Employee existingEmp = existingEmpOpt.get(); // Mengambil data Empoyee jika ada
            model.addAttribute("emp", existingEmp);
            model.addAttribute("judulForm", "Update New Employee");
        } 
        return "formEmployers";
    }

    @GetMapping("/delete")
    public String deleteEmploye(@RequestParam("id") Integer id){
        empDao.deleteById(id);
        return "redirect:/employers";
    }
}
