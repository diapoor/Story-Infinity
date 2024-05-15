package com.example.TestProject.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;

    @Column(name = "username", nullable = false)
    @Size(min = 5,max=10, message = "username must be 5-10 characters ")
    @NotBlank(message = "Please input your Username")
    @Pattern(regexp = "^[a-z]*$", message = "username  only contain lowercase  letters")
    private String username;

    @NotBlank(message = "Please input your password")
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank(message = "Invalid email format")
    @Email(message = "Invalid email format")
    @Column(name = "email",nullable = true)
    private String email;

    @Column(name = "birthday",nullable = false)
    @Past(message = "Date must be in the past")
    @NotNull(message = "Date of birth is required")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date birthday;

    @NotBlank(message = "Please input your nickname")
    @Size(min = 5,max = 10,message = "nick name must be 5-10 characters")
    @Column(name = "nick_name", nullable = false)
    private String nickname;

    //Relationship Many-One employee-user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    @JsonIgnore
    private Employee employee;
    //Relationship Many-One role-user
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonIgnore
    private Set<Role> roles;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Story> stories;
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.stream().forEach(i->authorities.add(new SimpleGrantedAuthority(i.getName())));
        return List.of(new SimpleGrantedAuthority(authorities.toString()));
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
