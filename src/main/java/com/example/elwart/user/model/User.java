package com.example.elwart.user.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @NotNull
    private String companyName;
    @NotNull
    private String companyAddress;
    @NotNull
    private String companyNip;
    @NotNull
    private String name;
    @NotNull
    @Column(name="full_name")
    private String fullName;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private boolean status = true;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime registrationDate = LocalDateTime.now();
    @ManyToMany (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Delegation> delegations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyNip() {
        return companyNip;
    }

    public void setCompanyNip(String companyNip) {
        this.companyNip = companyNip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Delegation> getDelegations() {
        return delegations;
    }

    public void setDelegations(List<Delegation> delegations) {
        this.delegations = delegations;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public static final class UserBuilder {
        private Long id;
        private String companyName;
        private String companyAddress;
        private String companyNip;
        private String name;
        private String fullName;
        private String email;
        private String password;
        private boolean status = true;
        private LocalDateTime registrationDate = LocalDateTime.now();
        private List<Role> roles;
        private List<Delegation> delegations;

        private UserBuilder() {
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder withCompanyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public UserBuilder withCompanyAddress(String companyAddress) {
            this.companyAddress = companyAddress;
            return this;
        }

        public UserBuilder withCompanyNip(String companyNip) {
            this.companyNip = companyNip;
            return this;
        }

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder withStatus(boolean status) {
            this.status = status;
            return this;
        }

        public UserBuilder withRegistrationDate(LocalDateTime registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public UserBuilder withRoles(List<Role> roles) {
            this.roles = roles;
            return this;
        }

        public UserBuilder withDelegations(List<Delegation> delegations) {
            this.delegations = delegations;
            return this;
        }

        public User build() {
            User user = new User();
            user.setId(id);
            user.setCompanyName(companyName);
            user.setCompanyAddress(companyAddress);
            user.setCompanyNip(companyNip);
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setStatus(status);
            user.setRegistrationDate(registrationDate);
            user.setRoles(roles);
            user.setDelegations(delegations);
            user.fullName = this.fullName;
            return user;
        }
    }
}
