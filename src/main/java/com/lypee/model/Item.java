package com.lypee.model;

public class Item {
    private Integer id;
    private int userid ;
    private String name ;
    private String inOrEx ;
    private String remark ;
    private int dele ;
    private int sort ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInOrEx() {
        return inOrEx;
    }

    public void setInOrEx(String inOrEx) {
        this.inOrEx = inOrEx;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getDele() {
        return dele;
    }

    public void setDele(int dele) {
        this.dele = dele;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
