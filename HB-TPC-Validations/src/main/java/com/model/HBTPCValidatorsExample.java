package com.model;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class HBTPCValidatorsExample{

public static void main(String[] args) {
Transaction tr=null;
ValidatorFactory validatorfactory= Validation.buildDefaultValidatorFactory();
Validator validator=validatorfactory.getValidator();
Person p1= new Person("Ruba","4th cross, anna nagar , chennai");
Customer p2=new Customer("JeeviShobi","shobi@gmail.com","9294996298","regular","7th cross, GT street,Karur");
Employee p3=new Employee("Sri","lead",20000,"25th july 2023","5th cross,Nehru street,erode");

Set<ConstraintViolation<Person>> violations;

try (Session ses = HBUtil.getSesFactory().openSession()){


tr = ses.beginTransaction();
violations=validator.validate(p1);
if(violations.isEmpty()) {
System.out.println("--------------------------");
System.out.println("Valid data Found");
ses.persist(p1);
System.out.println("data persisted sucessfully");
System.out.println("--------------------------");

} else {
System.out.println("--------------------------");
System.out.println("Invalid user data found");
System.out.println("--------------------------");
for(ConstraintViolation<Person> violation:violations)
{
System.out.println(violation.getMessage());
}
System.out.println("--------------------------");
}

violations=validator.validate(p2);
if(violations.isEmpty()) {
System.out.println("--------------------------");
System.out.println("Valid data Found");
ses.persist(p2);
System.out.println("data persisted sucessfully");
System.out.println("--------------------------");
} else {
System.out.println("Invalid user data found");
System.out.println("--------------------------");
for(ConstraintViolation<Person> violation:violations)
{
System.out.println(violation.getMessage());
}
System.out.println("--------------------------");
}
violations=validator.validate(p3);
if(violations.isEmpty()) {
System.out.println("--------------------------");
System.out.println("Valid data Found");
ses.persist(p3);
System.out.println("data persisted sucessfully");
System.out.println("--------------------------");
} else {
System.out.println("Invalid user data found");
System.out.println("--------------------------");
for(ConstraintViolation<Person> violation:violations)
{
System.out.println(violation.getMessage());
}
System.out.println("--------------------------");
}
tr.commit();
ses.close();


}
catch(Exception e) {
e.printStackTrace();
}

}

}