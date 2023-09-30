package ru.mataprime;

import ru.mataprime.service.ConvertService;
import ru.mataprime.service.DatabaseService;
import ru.mataprime.service.XmlService;

public class Main {

    public static void main(String[] args) {
        ConvertService convertService = new ConvertService(new XmlService(), new DatabaseService());
        convertService.convert();
    }
}