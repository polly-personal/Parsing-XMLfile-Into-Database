package ru.mataprime.service;

import ru.mataprime.model.Book;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class XmlService {
    private long generatedId;

    public Set<Book> parseToModel(String filePath) {
        Set<Book> books = new HashSet<>();
        Book book = null;

        // XMLInputFactory создает "сканер" xml-файла
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            // XMLEventReader "сканирует" переданный xml-файл
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(filePath));

            while (reader.hasNext()) {
                // получение данных из текущего тега
                XMLEvent xmlEvent = reader.nextEvent();

                // определение открывающегося/закрывающегося тега

                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();

                    if (startElement.getName().getLocalPart().equals("book")) {
                        book = new Book();
                    } else if (startElement.getName().getLocalPart().equals("name")) {
                        xmlEvent = reader.nextEvent();
                        book.setName(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("author")) {
                        xmlEvent = reader.nextEvent();
                        book.setAuthor(xmlEvent.asCharacters().getData());
                    }
                }

                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();

                    // сохранение модели в множество
                    if (endElement.getName().getLocalPart().equals("book")) {
                        books.add(book);
                    }
                }
            }
            books.forEach(book1 -> book1.setId(generateId()));
        } catch (FileNotFoundException | XMLStreamException exc) {
            exc.printStackTrace();
        }
        return books;
    }

    public String getXmlFile() {
        return "src/main/resources/books.xml";
    }

    private long generateId() {
        if (generatedId == 0L) {
            return ++generatedId;
        }
        return ++generatedId;
    }
}
