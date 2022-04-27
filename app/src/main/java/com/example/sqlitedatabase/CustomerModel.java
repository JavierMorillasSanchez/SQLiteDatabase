package com.example.sqlitedatabase;

public class CustomerModel {

    private int id;
    private String name;
    private int age;
    private boolean isActive;

    //constructors
    public CustomerModel(int id, String name, int age, boolean isActive) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isActive = isActive;
    }
    public CustomerModel() {
    }

    //getters
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public boolean isActive() {
        return isActive;
    }


    //setters
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setActive(boolean active) {
        isActive = active;
    }

    //toString to print the contents of the customer object
    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isActive=" + isActive +
                '}';
    }


}
