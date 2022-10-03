package Ejercicio1;

import com.sun.source.tree.WhileLoopTree;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        //leer fichero eliminar etc
        File partidas = new File("saved.dat");


        do {
            opcion = menu(sc);
            switch (opcion){
                case 1:
                    crearPartida(sc,partidas);

                    break;
                case 2:
                    mostrarPartidas(partidas);

                    break;
                case 3:
                    eliminarPartidas(partidas,sc);

                    break;
                case 4:
                    System.out.println("Bye Bye");
                    break;


            }
        }while (opcion!= 4);




    }

    private static void eliminarPartidas(File partidas , Scanner sc) {
        System.out.println("Seguro, seguroooo?");
        String respuesta= sc.nextLine().toLowerCase().trim();
        if (respuesta.equals("si")){
            if (partidas.delete()){
                System.out.println("a tomar por culo");

            }else {

                System.out.println("no se deja borrar");
            }

        }


    }

    private static void mostrarPartidas(File partidas) {
        if (partidas.exists()){
            try {
                FileInputStream inputStream = new FileInputStream(partidas);

                ObjectInputStream ois= new ObjectInputStream(inputStream);
                while (true){
                    Partida p = (Partida) ois.readObject();
                    System.out.println(p.toString());

                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }


        }

    }

    private static void crearPartida(Scanner sc, File partidas) {
        try{
            Partida partida = datosPartida(sc);
            ObjectOutputStream ous;

            if (partidas.exists()){
                ous = new ObjectOutputStream(new FileOutputStream(partidas,true));


            }else {
                ous = new ObjectOutputStream(new FileOutputStream(partidas));


            }

            ous.writeObject(partida);
            ous.close();


        }catch (InputMismatchException ex) {
            System.out.println("error con los datos de la partida");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static Partida datosPartida(Scanner sc) throws InputMismatchException {
        System.out.println("Dime la vida");
        int vida = sc.nextInt();
        System.out.println("dime la pantalla");
        int pantalla = sc.nextInt();

        return new Partida(vida, pantalla);
    }

    private static int menu(Scanner sc){

        int opcion;

        do {
            System.out.println("1.- Guardar partida");
            System.out.println("2.- Mostrar partida");
            System.out.println("3.- Eliminar partida");
            System.out.println("4.- salir");
            try{

                opcion = sc.nextInt();
            }catch (InputMismatchException e){
                opcion = 0;
                System.out.println(" Eres bobo??");
                sc.nextLine();

            }
        }while (opcion < 1 || opcion > 4);
        sc.nextLine();
         return opcion;

     }
}
