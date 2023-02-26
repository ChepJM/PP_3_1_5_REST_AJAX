package ru.kata.spring.boot_security.demo.entity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    @Size(min = 3, message = "Имя пользователя должно содержать не менее 3 знаков")
    private String username;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "password")
    @Size(min = 4, message = "Пароль должен содержать не менее 4 символов")
    private String password;

    @Column(name = "age")
//    @Min(value = 1, message = "Возраст пользователя не должен быть менее 1 года")
    private int age;

    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles"
    , joinColumns = @JoinColumn(name = "user_id")
    , inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roleList;

    public User() {
    }

    public User(String username, String secondName, String password, int age, String email, List<Role> roleList) {
        this.username = username;
        this.secondName = secondName;
        this.password = password;
        this.age = age;
        this.email = email;
        this.roleList = roleList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public String getRolesString() {
        List<String> roleNames = new ArrayList<>();
        for (Role role : roleList) {
            roleNames.add(role.getRoleName().substring(5));
        }
        return String.join(" ", roleNames);
    }
    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", secondName='" + secondName + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", roleList=" + roleList +
                '}';
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoleList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
