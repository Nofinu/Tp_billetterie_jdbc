package service;

import dao.LieuDAO;
import model.Evenement;
import model.Lieu;
import org.example.util.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ServiceEvenement {
    private static Scanner scanner = new Scanner(System.in);

    public static Evenement menuEvenement (){
        System.out.println("nom :");
        String nom = scanner.nextLine();
        System.out.println("date format(dd-MM-yyyy) :");
        String dateString = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            date = new Date("01/01/2001");
        }

        System.out.println("heure :");
        String heure = scanner.nextLine();

        System.out.println("lieu (id) :");
        int idLieu = scanner.nextInt();
        scanner.nextLine();
        try{
            Connection connectionLieu = new DatabaseManager().getConnection();
            LieuDAO LieuDaoEvenement = new LieuDAO(connectionLieu);
            Lieu lieu = LieuDaoEvenement.findById(idLieu);
            if(lieu == null){
                System.out.println("aucun lieu trouver a cette id ");
                return null;
            }
            System.out.println("prix :");
            float prix = scanner.nextFloat();
            scanner.nextLine();
            Evenement evenement = new Evenement(nom,date,heure,lieu,prix);
            return evenement;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}
