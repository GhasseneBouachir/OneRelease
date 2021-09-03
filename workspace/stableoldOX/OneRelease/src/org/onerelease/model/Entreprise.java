package org.onerelease.model;


import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.model.*;

@Entity
@View(members="name ; managers ; employees")
public class Entreprise extends Identifiable {

    @Column
    private String name;


    @OneToMany( mappedBy="entrepriseAsManager", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @CollectionView(value="ManagerView")
    private Collection<Manager> managers;

    @CollectionView("Employee")
    @OneToMany(mappedBy="entreprise", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private Collection<Employee> employees;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Manager> getManagers() {
        return managers;
    }

    public void setManagers(Collection<Manager> managers) {
        this.managers = managers;
    }

    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }





}