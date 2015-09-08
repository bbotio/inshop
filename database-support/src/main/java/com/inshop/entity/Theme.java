package com.inshop.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Base entity for UI theme.
 * Contains user's name and path to the UI theme of shop.
 *
 * Created by savetisyan on 05/09/15.
 */
@Entity
public class Theme implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Theme{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
