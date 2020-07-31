package ru.job4j.multithreading.non_blocking_algoritm;

import java.util.Objects;

public class Base {
    private String name;
    private final int id;
    private String description;
    private int version;

    public Base(String name, int id, String description, int version) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Base base = (Base) o;
        return id == base.id &&
                Objects.equals(name, base.name) &&
                Objects.equals(description, base.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, description);
    }

    @Override
    public String toString() {
        return name + ", id = " + id + ", version = " + version;
    }
}
