package com.careSystem.pojo;

import java.util.List;
import java.util.Objects;

/**
 * Created by Enzo Cotter on 2021/7/9.
 * 问题实体类
 */
public class Question {
    private Integer id;
    private String question;
    private List<String> answers;
    private String type;


    public Question() {
    }

    public Question(Integer id, String question, List<String> answers, String type) {
        this.id = id;
        this.question = question;
        this.answers = answers;
        this.type = type;
    }

    public Question(String question, List<String> answers, String type) {
        this.question = question;
        this.answers = answers;
        this.type = type;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return Objects.equals(id, question1.id) && Objects.equals(question, question1.question) && Objects.equals(answers, question1.answers) && Objects.equals(type, question1.type);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"question\":\"")
                .append(question).append('\"');
        sb.append(",\"answers\":")
                .append(answers);
        sb.append(",\"type\":\"")
                .append(type).append('\"');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, answers, type);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
