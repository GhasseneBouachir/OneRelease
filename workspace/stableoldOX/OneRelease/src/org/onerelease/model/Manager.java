package org.onerelease.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@Views({@View(members="name, managerId"), @View(name="Employee", members="name"),
    @View(name="ManagerView", members="name, managerId;"
            + " Employees[ employees ] "

    		)})


@Entity
public class Manager extends Employee {

}
