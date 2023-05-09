package Entities;

public class Etudiant
{
    // Membres privés
    private int numero;
    private String nom;
    private int age;


    public Etudiant()
    {

    }
    public Etudiant(int unNum, String unNom,int unAge)
    {
        numero = unNum;
        nom = unNom;
        age = unAge;
    }
    public Etudiant(int unNum, String unNom)
    {
        numero = unNum;
        nom = unNom;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
