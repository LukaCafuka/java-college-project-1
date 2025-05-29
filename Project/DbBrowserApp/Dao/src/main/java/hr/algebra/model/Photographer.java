/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

/**
 *
 * @author bubif
 */
public class Photographer extends Person implements Comparable<Photographer>{
    private int id;

    public Photographer(int id, String firstName, String lastName) {
        super(firstName, lastName);
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        return hash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        final Photographer other = (Photographer) obj;
        return this.id == other.id;
    }
    
    

    public Photographer(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public int compareTo(Photographer other) {
        return Integer.compare(this.id, other.id);
    }
    
    
}
