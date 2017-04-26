package com.learnrecord.file;

/**
 * Created by Code4Android on 2017/4/26.
 */

public class StaffJ<T> {
    private String name;
    private String position;
    private T age;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    public T getAge() {
        return age;
    }
    public void setAge(T age) {
        this.age = age;
    }
}
