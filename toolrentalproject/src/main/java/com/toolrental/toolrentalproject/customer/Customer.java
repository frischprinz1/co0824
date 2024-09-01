// package com.toolrental.toolrentalproject.customer;

// import java.io.Serializable;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;

// @Entity
// public class Customer implements Serializable {
//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private Integer id;
//     private String firstName;
//     private String lastName;
//     private String email;

//     public void setFirstName(String firstName) {
//         this.firstName = firstName;
//     }

//     public void setLastName(String lastName) {
//         this.lastName = lastName;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     public Integer getId() {
//         return id;
//     }

//     public String getFirstName() {
//         return firstName;
//     }

//     public String getLastName() {
//         return lastName;
//     }

//     public String getEmail() {
//         return email;
//     }
// }
