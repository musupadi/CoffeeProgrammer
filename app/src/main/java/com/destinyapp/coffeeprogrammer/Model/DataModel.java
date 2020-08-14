package com.destinyapp.coffeeprogrammer.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataModel {
    //Users
    @SerializedName("id_users")
    @Expose
    public String id_users;

    @SerializedName("email_users")
    @Expose
    public String email_users;

    @SerializedName("nama_users")
    @Expose
    public String nama_users;

    @SerializedName("password_users")
    @Expose
    public String password_users;

    @SerializedName("image_users")
    @Expose
    public String image_users;

    @SerializedName("email_confirmation_status")
    @Expose
    public String email_confirmation_status;

    @SerializedName("level_users")
    @Expose
    public String level_users;

    @SerializedName("status_users")
    @Expose
    public String status_users;

    //Modul
    @SerializedName("id_modul")
    @Expose
    public String id_modul;

    @SerializedName("nama_modul")
    @Expose
    public String nama_modul;

    @SerializedName("slug_modul")
    @Expose
    public String slug_modul;

    //Sub Modul
    @SerializedName("id_sub_modul")
    @Expose
    public String id_sub_modul;

    @SerializedName("nama_sub_modul")
    @Expose
    public String nama_sub_modul;

    @SerializedName("slug_sub_modul")
    @Expose
    public String slug_sub_modul;

    @SerializedName("description_sub_modul")
    @Expose
    public String description_sub_modul;

    @SerializedName("cover_sub_modul")
    @Expose
    public String cover_sub_modul;

    //Bab Pelajaran
    @SerializedName("id_bab")
    @Expose
    public String id_bab;

    @SerializedName("isi_bab")
    @Expose
    public String isi_bab;

    @SerializedName("video_bab")
    @Expose
    public String video_bab;

    @SerializedName("tugas_bab")
    @Expose
    public String tugas_bab;

    @SerializedName("urutan_bab")
    @Expose
    public String urutan_bab;

    //Sub Bab
    @SerializedName("id_sub_bab")
    @Expose
    public String id_sub_bab;

    @SerializedName("nama_sub_bab")
    @Expose
    public String nama_sub_bab;

    @SerializedName("isi_sub_bab")
    @Expose
    public String isi_sub_bab;

    @SerializedName("urutan_sub_bab")
    @Expose
    public String urutan_sub_bab;

    public String getId_users() {
        return id_users;
    }

    public void setId_users(String id_users) {
        this.id_users = id_users;
    }

    public String getEmail_users() {
        return email_users;
    }

    public void setEmail_users(String email_users) {
        this.email_users = email_users;
    }

    public String getPassword_users() {
        return password_users;
    }

    public void setPassword_users(String password_users) {
        this.password_users = password_users;
    }

    public String getImage_users() {
        return image_users;
    }

    public void setImage_users(String image_users) {
        this.image_users = image_users;
    }

    public String getEmail_confirmation_status() {
        return email_confirmation_status;
    }

    public void setEmail_confirmation_status(String email_confirmation_status) {
        this.email_confirmation_status = email_confirmation_status;
    }

    public String getLevel_users() {
        return level_users;
    }

    public void setLevel_users(String level_users) {
        this.level_users = level_users;
    }

    public String getStatus_users() {
        return status_users;
    }

    public void setStatus_users(String status_users) {
        this.status_users = status_users;
    }

    public String getNama_users() {
        return nama_users;
    }

    public void setNama_users(String nama_users) {
        this.nama_users = nama_users;
    }

    public String getId_modul() {
        return id_modul;
    }

    public void setId_modul(String id_modul) {
        this.id_modul = id_modul;
    }

    public String getNama_modul() {
        return nama_modul;
    }

    public void setNama_modul(String nama_modul) {
        this.nama_modul = nama_modul;
    }

    public String getSlug_modul() {
        return slug_modul;
    }

    public void setSlug_modul(String slug_modul) {
        this.slug_modul = slug_modul;
    }

    public String getId_sub_modul() {
        return id_sub_modul;
    }

    public void setId_sub_modul(String id_sub_modul) {
        this.id_sub_modul = id_sub_modul;
    }

    public String getNama_sub_modul() {
        return nama_sub_modul;
    }

    public void setNama_sub_modul(String nama_sub_modul) {
        this.nama_sub_modul = nama_sub_modul;
    }

    public String getSlug_sub_modul() {
        return slug_sub_modul;
    }

    public void setSlug_sub_modul(String slug_sub_modul) {
        this.slug_sub_modul = slug_sub_modul;
    }

    public String getDescription_sub_modul() {
        return description_sub_modul;
    }

    public void setDescription_sub_modul(String description_sub_modul) {
        this.description_sub_modul = description_sub_modul;
    }

    public String getCover_sub_modul() {
        return cover_sub_modul;
    }

    public void setCover_sub_modul(String cover_sub_modul) {
        this.cover_sub_modul = cover_sub_modul;
    }

    public String getId_bab() {
        return id_bab;
    }

    public void setId_bab(String id_bab) {
        this.id_bab = id_bab;
    }

    public String getIsi_bab() {
        return isi_bab;
    }

    public void setIsi_bab(String isi_bab) {
        this.isi_bab = isi_bab;
    }

    public String getVideo_bab() {
        return video_bab;
    }

    public void setVideo_bab(String video_bab) {
        this.video_bab = video_bab;
    }

    public String getTugas_bab() {
        return tugas_bab;
    }

    public void setTugas_bab(String tugas_bab) {
        this.tugas_bab = tugas_bab;
    }

    public String getUrutan_bab() {
        return urutan_bab;
    }

    public void setUrutan_bab(String urutan_bab) {
        this.urutan_bab = urutan_bab;
    }

    public String getId_sub_bab() {
        return id_sub_bab;
    }

    public void setId_sub_bab(String id_sub_bab) {
        this.id_sub_bab = id_sub_bab;
    }

    public String getNama_sub_bab() {
        return nama_sub_bab;
    }

    public void setNama_sub_bab(String nama_sub_bab) {
        this.nama_sub_bab = nama_sub_bab;
    }

    public String getIsi_sub_bab() {
        return isi_sub_bab;
    }

    public void setIsi_sub_bab(String isi_sub_bab) {
        this.isi_sub_bab = isi_sub_bab;
    }

    public String getUrutan_sub_bab() {
        return urutan_sub_bab;
    }

    public void setUrutan_sub_bab(String urutan_sub_bab) {
        this.urutan_sub_bab = urutan_sub_bab;
    }
}
