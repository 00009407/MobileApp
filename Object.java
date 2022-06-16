package com.example.dictionary.modules;

import java.io.Serializable;
//Serialization is the process of converting an object into a stream of bytes to store//
// the object or transmit it to memory, a database, or a file.
public class Object implements Serializable {

    private int id = 0;
    private String name;
    private String definition;
    private String Synonym;
    private String origin;
    private Type type;
    private String date;
    private String note;

    public Object(int id, String name, String definition, String Synonym, String origin, Type type, String date) {
        this.id = id;
        this.name = name;
        this.definition = definition;
        this.Synonym = Synonym;
        this.origin = origin;
        this.type = type;
        this.date = date;
    }

    public Object(String name, String definition, String Synonym, String origin, Type type) {
        this.name = name;
        this.definition = definition;
        this.Synonym = Synonym;
        this.origin = origin;
        this.type = type;
    }

    public Object(String name, String definition, String Synonym, String origin, Type type, String date) {
        this.name = name;
        this.definition = definition;
        this.Synonym = Synonym;
        this.origin = origin;
        this.type = type;
        this.date = date;
    }

    public Object(int id, String name, String definition, String Synonym, String origin, Type type, String date, String note) {
        this.id = id;
        this.name = name;
        this.definition = definition;
        this.Synonym = Synonym;
        this.origin = origin;
        this.type = type;
        this.date = date;
        this.note = note;
    }

    public Object(String name, String definition, String Synonym, String origin, Type type, String date, String note) {
        this.name = name;
        this.definition = definition;
        this.Synonym = Synonym;
        this.origin = origin;
        this.type = type;
        this.date = date;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getCount() { return Synonym; }

    public void setSynonym(String origin) {
        this.Synonym = origin;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSynonym()  {
        return Synonym;
    }
}
