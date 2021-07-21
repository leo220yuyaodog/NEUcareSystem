package com.careSystem.pojo;

import java.util.Objects;

/**
 * Created by Enzo Cotter on 2021/7/9.
 * 模板实体类
 */
public class FormWork {
    private Integer id;
    private String name;
    private String type;

    public FormWork() {
    }

    public FormWork(Integer id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"type\":\"")
                .append(type).append('\"');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormWork formWork = (FormWork) o;
        return Objects.equals(id, formWork.id) && Objects.equals(name, formWork.name) && Objects.equals(type, formWork.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type);
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
