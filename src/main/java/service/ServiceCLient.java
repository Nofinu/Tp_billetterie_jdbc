package service;

import model.Client;

import java.util.Scanner;

public class ServiceCLient {
    private static Scanner scanner = new Scanner(System.in);

    public static Client menuClient (){
        System.out.println("nom :");
        String nom = scanner.nextLine();
        System.out.println("prenom :");
        String prenom = scanner.nextLine();
        System.out.println("email :");
        String email = scanner.nextLine();

        Client client = new Client(nom,prenom,email);
        return client;
    }

}
