package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.dao.SalaryDao;
import com.example.demo.model.Employee;
import com.example.demo.model.Salary;

@Controller
@RequestMapping("/salaries")
public class SalaryPage {

    @Autowired
    private EmployeeDao empDao;
    @Autowired
    private SalaryDao slrDao;

    @GetMapping
    public String listSalaries(@RequestParam(required = false) Integer month, Model model) {
        List<Salary> salaries;
        if (month != null) {
            // Logika untuk mengambil data Salary berdasarkan bulan
            salaries = slrDao.findByMonth(month);
        } else {
            salaries = slrDao.findAll();
        }
        model.addAttribute("salaries", salaries);
        return "salaries";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("salary", new Salary());
        model.addAttribute("employees", empDao.findAll());
        model.addAttribute("judulForm", "Add Salary");
        return "formSalaries"; // Ganti dengan path ke template Thymeleaf Anda
    }

    @PostMapping("/save")
    public String saveSalary(@ModelAttribute Salary salary, @RequestParam Integer employeeId) {
        Employee employee = empDao.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + employeeId));
        salary.setEmployee(employee);
        slrDao.save(salary);
        return "redirect:/salaries";
    }

    @GetMapping("/edit")
    public String ShowEditForm(@RequestParam Integer id, Model model) {
        Salary salary = slrDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("salary", salary);
        model.addAttribute("employees", empDao.findAll());
        model.addAttribute("judulForm", "Edit Salary");
        return "formSalaries";

    }

}