package com.example.helloworld;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.Key;

/**
 * Created by eliana.moran on 5/24/2016.
 */
@Entity
public class Student {
    @Id
    private Long fileNumber;
    private String name;
    private String surname;
    private String career;

    public Student(){}

    public Student(Long fileNumber, String name, String surname, String career) {
        this.name = name;
        this.surname = surname;
        this.fileNumber = fileNumber;
        this.career = career;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(Long fileNumber) {
        this.fileNumber = fileNumber;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }



    @Override
    public String toString() {
        return  ", fileNumber=" + fileNumber +
                "Student{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", career='" + career + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (fileNumber != null ? !fileNumber.equals(student.fileNumber) : student.fileNumber != null) return false;
        if (name != null ? !name.equals(student.name) : student.name != null) return false;
        if (surname != null ? !surname.equals(student.surname) : student.surname != null) return false;
        return career != null ? career.equals(student.career) : student.career == null;

    }

    @Override
    public int hashCode() {
        int result = fileNumber != null ? fileNumber.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (career != null ? career.hashCode() : 0);
        return result;
    }
}
