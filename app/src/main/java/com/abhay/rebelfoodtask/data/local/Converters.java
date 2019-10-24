package com.abhay.rebelfoodtask.data.local;

import androidx.room.TypeConverter;

import com.abhay.rebelfoodtask.data.model.Address;
import com.abhay.rebelfoodtask.data.model.Company;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Converters {

    Gson gson = new Gson();

    public Converters(){

    }

    @TypeConverter
    public Address StringToAddress(String string) {
        Type type = new TypeToken<Address>() {
        }.getType();
        return gson.fromJson(string, type);
    }

    @TypeConverter
    public String AddressToString(Address address) {
        return gson.toJson(address);
    }

    @TypeConverter
    public Company StringToCompany(String string) {
        Type type = new TypeToken<Company>() {
        }.getType();
        return gson.fromJson(string, type);
    }

    @TypeConverter
    public String CompanyToString(Company company) {
        return gson.toJson(company);
    }

}
