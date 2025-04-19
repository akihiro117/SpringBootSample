package com.sample.demo.layerd.domain;

public record PersonName(String firstName, String lastName) {
    public String getJpFullName() {
        return lastName + " " + firstName;
    }

    public String getEnFullName() {
        return firstName + " " + lastName;
    }
}
