package com.xuanke1.bean;

public class Application {
    private int id;
    private int rid;
    private String out_time;
    private String in_time;
    private String actual_out_time;
    private String actual_in_time;
    private String reason;
    private String temperature;
    private int statu;
    private String username;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getOut_time() {
        return out_time;
    }

    public void setOut_time(String out_time) {
        this.out_time = out_time;
    }

    public String getIn_time() {
        return in_time;
    }

    public void setIn_time(String in_time) {
        this.in_time = in_time;
    }

    public String getActual_out_time() {
        return actual_out_time;
    }

    public void setActual_out_time(String actual_out_time) {
        this.actual_out_time = actual_out_time;
    }

    public String getActual_in_time() {
        return actual_in_time;
    }

    public void setActual_in_time(String actual_in_time) {
        this.actual_in_time = actual_in_time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public int getStatu() {
        return statu;
    }

    public void setStatu(int statu) {
        this.statu = statu;
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

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", rid=" + rid +
                ", out_time='" + out_time + '\'' +
                ", in_time='" + in_time + '\'' +
                ", actual_out_time='" + actual_out_time + '\'' +
                ", actual_in_time='" + actual_in_time + '\'' +
                ", reason='" + reason + '\'' +
                ", temperature='" + temperature + '\'' +
                ", statu=" + statu +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
