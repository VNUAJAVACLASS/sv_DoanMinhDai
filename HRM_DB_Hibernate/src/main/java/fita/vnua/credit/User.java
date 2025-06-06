package fita.vnua.credit;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "User")
public class User {
 
    @Id
    @Column(name = "user_id")
    private int userid;
 
    @Column(name = "user_name")
    private String username;
 
    @Column(name = "created_by")
    private String createdBy;
 
    @Column(name = "created_date")
    private Date createdDate;
 
    public int getUserid() {
        return userid;
    }
 
    public void setUserid(int userid) {
        this.userid = userid;
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getCreatedBy() {
        return createdBy;
    }
 
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
 
    public Date getCreatedDate() {
        return createdDate;
    }
 
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
