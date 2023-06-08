package com.ApiExamen.ApiExamen.Entity.Builder;
import com.ApiExamen.ApiExamen.Entity.Student;
;

public class StudentBuilder implements Builder<Student>{

    String firstname;
    String lastname;


    public StudentBuilder setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public StudentBuilder setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }


    @Override
    public  Student build() {
        return new Student(this.firstname,this.lastname);
    }

}
