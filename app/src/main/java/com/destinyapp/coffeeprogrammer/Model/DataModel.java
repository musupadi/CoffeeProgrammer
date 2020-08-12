package com.destinyapp.coffeeprogrammer.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataModel {
    @SerializedName("id_user")
    @Expose
    public String id_user;

    @SerializedName("email")
    @Expose
    public String email;

    @SerializedName("password")
    @Expose
    public String password;

    @SerializedName("foto")
    @Expose
    public String foto;

    @SerializedName("minat")
    @Expose
    public String minat;

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getMinat() {
        return minat;
    }

    public void setMinat(String minat) {
        this.minat = minat;
    }
}
