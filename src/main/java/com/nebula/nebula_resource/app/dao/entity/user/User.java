package com.nebula.nebula_resource.app.dao.entity.user;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "TBL_USER", schema = "users")
@SequenceGenerator(
        name = "SEQ_USER_ID_GENERATOR",
        sequenceName = "SEQ_USER_ID",
        initialValue = 1,
        allocationSize = 1
)
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_USER_ID_GENERATOR"
    )
    private int id;
    @Column(name = "USERNAME", unique = true, nullable = false)
    private String username;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;
    @Column(name = "JOIN_DATE", updatable = false, nullable = false)
    private Date joinDate;
    @Column(name = "IS_DELETED", nullable = false)
    private String isDeleted;
    @Column(name = "DELETED_DATE")
    private Date deletedDate;

    public User() {
    }

    public User(int id, String username, String password, String email, Date joinDate, String isDeleted, Date deletedDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.joinDate = joinDate;
        this.isDeleted = isDeleted;
        this.deletedDate = deletedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", joinDate=" + joinDate +
                ", isDeleted='" + isDeleted + '\'' +
                ", deletedDate=" + deletedDate +
                '}';
    }
}
