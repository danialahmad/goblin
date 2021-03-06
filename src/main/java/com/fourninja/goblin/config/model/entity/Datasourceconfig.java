package com.fourninja.goblin.config.model.entity;


import javax.persistence.Column;

/**
 * Datasourceconfig generated by hbm2java
 */

public class Datasourceconfig  implements java.io.Serializable {


     private Integer id;
     private String username;
     private String password;
     private String name;
     private String url;
     private String driverclassname;

    public Datasourceconfig() {
    }

    public Datasourceconfig(String username, String password, String name, String url, String driverclassname) {
       this.username = username;
       this.password = password;
       this.name = name;
       this.url = url;
       this.driverclassname = driverclassname;
    }
   

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    
    @Column(name="password")
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    

    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    

    public String getDriverclassname() {
        return this.driverclassname;
    }
    
    public void setDriverclassname(String driverclassname) {
        this.driverclassname = driverclassname;
    }




}


