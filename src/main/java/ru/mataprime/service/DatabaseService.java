package ru.mataprime.service;

import ru.mataprime.config.DatabaseConfig;
import ru.mataprime.model.Book;
import ru.mataprime.repository.DatabaseDao;

import java.util.Set;

public class DatabaseService {
    private final DatabaseDao databaseDao = new DatabaseDao(new DatabaseConfig());

    public void createTableIfNotExists() {
       databaseDao.createTableIfNotExists();
    }

    public void insert(Set<Book> books) {
      databaseDao.batchInsert(books);
    }
}
