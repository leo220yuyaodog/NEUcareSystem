package com.careSystem.pojo;

import java.util.Objects;

/**
 * Created by Enzo Cotter on 2021/7/13.
 * 评估结果实体类
 */
public class Estimate {
    private Integer id;
    private String name;
    private String sex;
    private String FormWorkName;
    private String FormWorkType;
    private String date;
    private String estimator;
    private String advice;

    public Estimate() {
    }

    public Estimate(String name, String sex, String formWorkName, String formWorkType, String date, String estimator, String advice) {
        this.name = name;
        this.sex = sex;
        FormWorkName = formWorkName;
        FormWorkType = formWorkType;
        this.date = date;
        this.estimator = estimator;
        this.advice = advice;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"sex\":\"")
                .append(sex).append('\"');
        sb.append(",\"FormWorkName\":\"")
                .append(FormWorkName).append('\"');
        sb.append(",\"FormWorkType\":\"")
                .append(FormWorkType).append('\"');
        sb.append(",\"date\":\"")
                .append(date).append('\"');
        sb.append(",\"estimator\":\"")
                .append(estimator).append('\"');
        sb.append(",\"advice\":\"")
                .append(advice).append('\"');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estimate estimate = (Estimate) o;
        return Objects.equals(id, estimate.id) && Objects.equals(name, estimate.name) && Objects.equals(sex, estimate.sex) && Objects.equals(FormWorkName, estimate.FormWorkName) && Objects.equals(FormWorkType, estimate.FormWorkType) && Objects.equals(date, estimate.date) && Objects.equals(estimator, estimate.estimator) && Objects.equals(advice, estimate.advice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sex, FormWorkName, FormWorkType, date, estimator, advice);
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFormWorkName() {
        return FormWorkName;
    }

    public void setFormWorkName(String formWorkName) {
        FormWorkName = formWorkName;
    }

    public String getFormWorkType() {
        return FormWorkType;
    }

    public void setFormWorkType(String formWorkType) {
        FormWorkType = formWorkType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEstimator() {
        return estimator;
    }

    public void setEstimator(String estimator) {
        this.estimator = estimator;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}
