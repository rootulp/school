package com.rootulp.rootuljsona;

import java.io.Serializable;

/**
 * Created by Rootul Patel on 5/1/15.
 */


public class PersonInfo implements Serializable {
    String person;
    String occupation;
    String imglink;
    Boolean isGenderFemale;
    Integer age;
    public String getPerson() {

        return person;
    }

    public void setPerson(String person) {

        this.person = person;
    }

    public String getOccupation() {

        return occupation;
    }

    public void setOccupation(String occupation) {

        this.occupation = occupation;
    }

    public String getlink() {

        return imglink;
    }

    public void setlink(String imglink) {

        this.imglink = imglink;
    }

    public Boolean getIsGenderFemale() {
        return isGenderFemale;
    }

    public void setIsGenderFemale(Boolean isGenderFemale) {
        this.isGenderFemale = isGenderFemale;
    }

    public Integer getAge() {

        return age;
    }

    public void setAge(Integer age) {

        this.age = age;
    }
}