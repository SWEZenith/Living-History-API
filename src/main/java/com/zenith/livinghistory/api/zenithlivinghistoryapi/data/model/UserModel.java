package com.zenith.livinghistory.api.zenithlivinghistoryapi.data.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"user\"")
public class UserModel extends BaseModel {

    // ?
    @Column(name = "jsonld_id")
    private String jsonldId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "creator")
    private List<ContentModel> histories = new ArrayList<>();

    private String username;

    private String email;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
