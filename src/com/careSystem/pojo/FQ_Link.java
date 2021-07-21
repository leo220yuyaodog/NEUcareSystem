package com.careSystem.pojo;

import java.util.Objects;

/**
 * Created by Enzo Cotter on 2021/7/9.
 * 模板于问题的关系
 */
public class FQ_Link {
    private Integer fid;
    private Integer qid;

    public FQ_Link() {
    }

    public FQ_Link(Integer fid, Integer qid) {
        this.fid = fid;
        this.qid = qid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FQ_Link fq_link = (FQ_Link) o;
        return Objects.equals(fid, fq_link.fid) && Objects.equals(qid, fq_link.qid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fid, qid);
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"fid\":")
                .append(fid);
        sb.append(",\"qid\":")
                .append(qid);
        sb.append('}');
        return sb.toString();
    }
}
