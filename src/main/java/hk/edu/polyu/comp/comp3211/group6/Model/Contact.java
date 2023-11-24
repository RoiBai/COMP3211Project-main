package hk.edu.polyu.comp.comp3211.group6.Model;

import java.io.Serializable;

public class Contact extends PIR implements Serializable {
    private String name;
    private String address;
    private String mobile;
    public Contact(String identifier, String name, String address, String mobile){
        super(identifier);
        this.address = address;
        this.name = name;
        this.mobile = mobile;
    }
    @Override
    public <T> T accept(PrintVisitor<T> visitor) {
        return visitor.visitContact(this);
    }

    @Override
    public <T> T accept(ModifyVisitor<T> visitor) {
        return visitor.visitContact(this);
    }


    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String getString() {
        return name + " " + address + " " + mobile;
    }

    @Override
    public String toString() {
        return super.getIdentifier() + "\n" + name + "\n" + address + "\n" + mobile;
    }
}
