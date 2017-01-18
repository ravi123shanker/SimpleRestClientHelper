package com.ravidev.restclient;

/**
 * Created by ryadav3 on 12/7/2016.
 */

public class Employee {
    private int salary;
    private String department;
    private int age;
    private String name;

    /**
     *
     * @return
     * The salary
     */
    public int getSalary() {
        return salary;
    }

    /**
     *
     * @param salary
     * The salary
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     *
     * @return
     * The department
     */
    public String getDepartment() {
        return department;
    }

    /**
     *
     * @param department
     * The department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     *
     * @return
     * The age
     */
    public int getAge() {
        return age;
    }

    /**
     *
     * @param age
     * The age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return "Employee: "+name+", "+age+", "+salary+", "+department;
    }
}
