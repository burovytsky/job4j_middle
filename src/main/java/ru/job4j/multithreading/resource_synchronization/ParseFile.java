package ru.job4j.multithreading.resource_synchronization;

import java.io.*;

public class ParseFile {

    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized String getContent() throws IOException {
        StringBuilder output;
        try (InputStream i = new FileInputStream(file)) {
            output = new StringBuilder();
            int data;
            while ((data = i.read()) > 0) {
                output.append((char) data);
            }
        }
        return output.toString();
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        StringBuilder output;
        try (InputStream i = new FileInputStream(file)) {
            output = new StringBuilder();
            int data;
            while ((data = i.read()) > 0) {
                if (data < 0x80) {
                    output.append((char) data);
                }
            }
        }
        return output.toString();

    }

    public synchronized void saveContent(String content) throws IOException {
        try (OutputStream o = new FileOutputStream(file)) {
            for (int i = 0; i < content.length(); i += 1) {
                o.write(content.charAt(i));
            }
        }
    }
}
/*
1. Добавил ключевое слово synchronized для методов, что бы нити корректно, по одной, считывали или записывали данные

2. Обернул потоки чтения и записи в конструкцию try-with-resources что бы закрыть потоки

3. Переменная для возврата результата чтения файла заменена на переменную класса StringBuilder. вместо конкатенации
метод StringBuilder.append();
 */