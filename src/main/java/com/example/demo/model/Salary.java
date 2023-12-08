package com.example.demo.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "salary") // nama tabel
public class Salary {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int amount; // Jumlah gaji
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date paymentDate; // Tanggal pembayaran
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee; // Referensi ke Employee

    public Salary(int amount, Date paymentDate, Employee employee) {
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.employee = employee;
    }

    public Salary() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
