package com.careSystem.pojo;


import java.util.Objects;

/**
 * Created by Enzo Cotter on 2021/7/8.
 * 病人实体类
 */
public class Patient {

    private Integer id;
    private String name;// 姓名
    private String age;// 年龄
    private String sex;// 性别
    private String phone;// 联系电话
    private String emergency_number;// 紧急联系人
    private String emergency_contact;// 紧急联系电话
    private String id_number;//身份证号
    private int deleted;//0 未删除   1 删除

    public Patient() {
    }

    public Patient(Integer id, String name, String age, String sex, String phone, String emergency_number, String emergency_contact, String id_number) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.phone = phone;
        this.emergency_number = emergency_number;
        this.emergency_contact = emergency_contact;
        this.id_number = id_number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return deleted == patient.deleted && Objects.equals(id, patient.id) && Objects.equals(name, patient.name) && Objects.equals(age, patient.age) && Objects.equals(sex, patient.sex) && Objects.equals(phone, patient.phone) && Objects.equals(emergency_number, patient.emergency_number) && Objects.equals(emergency_contact, patient.emergency_contact) && Objects.equals(id_number, patient.id_number);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"age\":\"")
                .append(age).append('\"');
        sb.append(",\"sex\":\"")
                .append(sex).append('\"');
        sb.append(",\"phone\":\"")
                .append(phone).append('\"');
        sb.append(",\"emergency_number\":\"")
                .append(emergency_number).append('\"');
        sb.append(",\"emergency_contact\":\"")
                .append(emergency_contact).append('\"');
        sb.append(",\"id_number\":\"")
                .append(id_number).append('\"');
        sb.append(",\"deleted\":")
                .append(deleted);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, sex, phone, emergency_number, emergency_contact, id_number, deleted);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmergency_number() {
        return emergency_number;
    }

    public void setEmergency_number(String emergency_number) {
        this.emergency_number = emergency_number;
    }

    public String getEmergency_contact() {
        return emergency_contact;
    }

    public void setEmergency_contact(String emergency_contact) {
        this.emergency_contact = emergency_contact;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

}
