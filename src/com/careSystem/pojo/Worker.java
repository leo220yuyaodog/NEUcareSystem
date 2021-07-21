package com.careSystem.pojo;

import java.util.Objects;

/**
 * Created by Enzo Cotter on 2021/7/8.
 */
public class Worker {
    private Integer id;//编码
    private String username;//用户名
    private String password;//密码
    private String name;//姓名
    private String profession;//职称
    private String birthday;//生日
    private String speciality;//专长
    private String phone;//电话

    private int deleted;//0 未删除   1 删除

    public Worker() {
    }

    public Worker(Integer id, String username, String password, String name, String profession, String birthday, String speciality, String phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.profession = profession;
        this.birthday = birthday;
        this.speciality = speciality;
        this.phone = phone;
    }


    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return deleted == worker.deleted && Objects.equals(id, worker.id) && Objects.equals(username, worker.username) && Objects.equals(password, worker.password) && Objects.equals(name, worker.name) && Objects.equals(profession, worker.profession) && Objects.equals(birthday, worker.birthday) && Objects.equals(speciality, worker.speciality) && Objects.equals(phone, worker.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, name, profession, birthday, speciality, phone, deleted);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"username\":\"")
                .append(username).append('\"');
        sb.append(",\"password\":\"")
                .append(password).append('\"');
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"profession\":\"")
                .append(profession).append('\"');
        sb.append(",\"birthday\":\"")
                .append(birthday).append('\"');
        sb.append(",\"speciality\":\"")
                .append(speciality).append('\"');
        sb.append(",\"phone\":\"")
                .append(phone).append('\"');
        sb.append(",\"deleted\":")
                .append(deleted);
        sb.append('}');
        return sb.toString();
    }


}
