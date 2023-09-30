package ru.mataprime.service;

import ru.mataprime.model.Book;

import java.util.Set;

public class ConvertService {
    private final XmlService xmlService;
    private final DatabaseService databaseService;

    public ConvertService(XmlService xmlService, DatabaseService databaseService) {
        this.xmlService = xmlService;
        this.databaseService = databaseService;
    }

    public void convert() {
        // перевод данных из xml-файла в java-множество моделей
        Set<Book> books = xmlService.parseToModel(xmlService.getXmlFile());
        books.forEach(book -> System.out.println(book));

        // добавление данных в базу
        databaseService.createTableIfNotExists();
        databaseService.insert(books);
    }
}
