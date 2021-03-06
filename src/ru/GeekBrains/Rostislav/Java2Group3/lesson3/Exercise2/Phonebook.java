package ru.GeekBrains.Rostislav.Java2Group3.lesson3.Exercise2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Phonebook {

    private final HashMap<String, ArrayList<String>> catalog = new HashMap<>();

    public void add(String surname, String phone) {
        if(!catalog.containsKey(surname)){  // новый список создается только если такой фамилии еще не было
            ArrayList<String> phones = new ArrayList<>();
            phones.add(phone);
            catalog.put(surname, phones);
        } else {
            catalog.get(surname).add(phone);  // если фамилия уже была, то добавляется еще телефон (прежние сохраняются - видно при запуске AppRun)
        }
    }

    public ArrayList<String> get(String surname) {
        for (Map.Entry<String,ArrayList<String>> s : catalog.entrySet()) {
            if(s.getKey().equals(surname)) {
                return s.getValue();
            }
        }
        return null;
    }
}
