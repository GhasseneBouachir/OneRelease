package org.onerelease.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.model.*;


@Views({@View(members="name, managerId"), @View(name="Employee", members="name"),
        @View(name="ManagerView", members="name, managerId;"
                + " Employees[ employees ] "

        		)})


@Entity
public class Employee extends Identifiable {
    @Column
    private String name;

    @Column
    private String managerId;

    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    private Employee manager;

    @CollectionView("Employee")
     @OneToMany(mappedBy="manager", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
//    @OneToMany(mappedBy="manager", fetch=FetchType.LAZY)
    private Collection<Employee> employees;


    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Entreprise entreprise;
    
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Entreprise entrepriseAsManager;    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

	public Entreprise getEntrepriseAsManager() {
		return entrepriseAsManager;
	}

	public void setEntrepriseAsManager(Entreprise entrepriseAsManager) {
		this.entrepriseAsManager = entrepriseAsManager;
	}


}