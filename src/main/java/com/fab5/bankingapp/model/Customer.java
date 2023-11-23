package com.fab5.bankingapp.model;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Customer {
    /**
     * Long customerID
     * String firstName
     * String lastName
     * Set of Addresses
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany( cascade = CascadeType.ALL)
    @JoinColumn(name ="customer_id")
    private Set<Address> addresses ;


    @OneToMany(mappedBy = "customer")
    private List<Account> account;

    public Customer() {
    }

    public Customer(Long customer_id, String firstName, String lastName, Set<Address> addresses, List<Account> account) {
        this.customer_id = customer_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addresses = addresses;
        this.account = account;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long id) {
        this.customer_id = id;
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

//    public List<Account> getAccount() {
//        return account;
//    }
//
//    public void setAccount(List<Account> account) {
//        this.account = account;
//    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + customer_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}

