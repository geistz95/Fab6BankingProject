package com.fab5.bankingapp.model;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Customer implements Model {
    /**
     * Long customerID
     * String firstName
     * String lastName
     * Set of Addresses
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany( cascade = CascadeType.ALL)
    @JoinColumn(name ="customer_id")
    private Set<Address> addresses ;
    @OneToMany(mappedBy = "customer")
    private List<Account> account;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}

