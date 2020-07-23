package ru.job4j.multithreading.resource_synchronization;

import java.io.*;

public class ParseFile {
    public static void main(String[] args) {
        ParseFile parseFile = new ParseFile();
        File file = new File("C:\\Users\\cns\\IdeaProjects\\job4j_middle\\Readme.md");
        parseFile.setFile(file);
        Thread thread1 = new Thread(() -> {
            try {
                parseFile.saveContent(parseFile.getContent());
                System.out.println(parseFile.getFile().length());
                System.out.println("--------------------------------");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                parseFile.saveContent(parseFile.getContent());
                System.out.println(parseFile.getFile().length());
                System.out.println("--------------------------------");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Thread thread3 = new Thread(() -> {
            try {
                parseFile.saveContent(parseFile.getContent());
                System.out.println(parseFile.getFile().length());
                System.out.println("--------------------------------");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Thread thread4 = new Thread(() -> {
            try {
                parseFile.saveContent(parseFile.getContent());
                System.out.println(parseFile.getFile().length());
                System.out.println("--------------------------------");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Thread thread5 = new Thread(() -> {
            try {
                parseFile.saveContent(parseFile.getContent());
                System.out.println(parseFile.getFile().length());
                System.out.println("--------------------------------");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }
    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized String getContent() throws IOException {
        InputStream i = new FileInputStream(file);
        StringBuilder output = new StringBuilder();
        int data;
        while ((data = i.read()) > 0) {
            output.append((char) data);
        }
        return output.toString();
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        InputStream i = new FileInputStream(file);
        StringBuilder output = new StringBuilder();
        int data;
        while ((data = i.read()) > 0) {
            if (data < 0x80) {
                output.append((char) data);
            }
        }
        return output.toString();
    }

    public synchronized void saveContent(String content) throws IOException {
        OutputStream o = new FileOutputStream(file);
        for (int i = 0; i < content.length(); i += 1) {
            o.write(content.charAt(i));
        }
    }
}
/*
1. Добавил ключевое слово synchronized для методов, что бы нити корректно, по одной, считывали или записывали данные

2. Обернул потоки чтения и записи в конструкцию try-with-resources что бы закрыть потоки

3. Переменная для возврата результата чтения файла заменена на переменную класса StringBuilder. вместо конкатенации
метод StringBuilder.append();
 */