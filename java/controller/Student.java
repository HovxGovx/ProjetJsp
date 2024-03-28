package controller;

public class Student {
    private String numEt;  // Student ID
    private String nom;    // Student Name
    private double moyenne; // Average

    // Constructors
    public Student() {
        // Default constructor
    }

    public Student(String numEt, String nom, double moyenne) {
        this.numEt = numEt;
        this.nom = nom;
        this.moyenne = moyenne;
    }

    // Getters and Setters
    public String getNumEt() {
        return numEt;
    }

    public void setNumEt(String numEt) {
        this.numEt = numEt;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }

    // Additional methods or overrides if needed
}
