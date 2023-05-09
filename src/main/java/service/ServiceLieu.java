package service;

import model.Lieu;

import java.util.Scanner;

public class ServiceLieu {
    private static Scanner scanner = new Scanner(System.in);

    public static Lieu menuLieu (){
        System.out.println("nom :");
        String nom = scanner.nextLine();
        System.out.println("adresse :");
        String adresse = scanner.nextLine();
        System.out.println("capacité :");
        int capacite = scanner.nextInt();
        scanner.nextLine();

        Lieu lieu = new Lieu(nom,adresse,capacite);
        return lieu;
    }
}
