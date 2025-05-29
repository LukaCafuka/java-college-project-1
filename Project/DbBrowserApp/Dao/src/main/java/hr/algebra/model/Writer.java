/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

/**
 *
 * @author bubif
 */
public class Writer extends Person implements Comparable<Writer>{
    private int id;

    public Writer(int id, String firstName, String lastName) {
        super(firstName, lastName);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Writer other = (Writer) obj;
        return this.id == other.id;
    }
    
    
    
    
    public Writer(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public int compareTo(Writer other) {
        return Integer.compare(this.id, other.id);
    }

}
