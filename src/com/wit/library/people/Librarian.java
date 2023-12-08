package com.wit.library.people;

import java.util.Objects;

public class Librarian extends Person {
    private String staffId;
    private String password;

    public Librarian(String staffId, String name,String password) {
        super(staffId, name);
        this.staffId = staffId;
        this.password=password;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "staffId='" + staffId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Librarian librarian = (Librarian) o;
        return Objects.equals(staffId, librarian.staffId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(staffId);
    }
}