package com.example.fragprac;

public class Pelanggan {
    private String name;
    private String address;
    private String phone;

    public Pelanggan() {
        // Default constructor required for calls to DataSnapshot.getValue(Pelanggan.class)
    }


    public Pelanggan(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
