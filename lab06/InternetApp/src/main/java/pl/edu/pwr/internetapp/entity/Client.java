package pl.edu.pwr.internetapp.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Installation> installations = new HashSet<>();

    public Client() {
    }

    public Client(Long id, String firstName, String lastName,Set<Installation> installations) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.installations = installations;
    }

    public Client(String firstName, String lastName, Set<Installation> installations) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.installations = installations;
    }

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

    public Set<Installation> getInstallations() {
        return installations;
    }

    public void setInstallations(Set<Installation> installations) {
        this.installations = installations;
    }
}
