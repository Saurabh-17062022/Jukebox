/*
package com.jukebox.main;

import com.jukebox.data.Song;
import com.jukebox.util.Db_Connection;

import java.sql.*;
import java.util.Scanner;

public class Jukebox {
    Connection connection;
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException, ClassNotFoundException ,NullPointerException{
        Connection connection = Db_Connection.getConnection();
       // Song song = new Song();
        Jukebox jukebox = new Jukebox();
        jukebox.menu();
    }

    public void menu() throws SQLException, ClassNotFoundException {
        System.out.println("Menu");
        System.out.println("1. Show all Songs");
        System.out.println("2. Create new Playlist");
        System.out.println("3. View all Playlists");
        System.out.println("4. Search Songs");
        System.out.println("5. Return back to main menu");
        System.out.println("6. Exit Application");
        System.out.println("Enter choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                showAllSongs(); ;
                break;
           */
/* case 2:
                String userID = null;
                createPlaylist(userID);
                break;*//*

        }
    }
    public void showAllSongs(){
        String q1 = "Select s.songid,s.title, s.genre, ar.ArtistName, a.albumname, s.duration\n" +
                "FROM songlist s inner join album a on\n" +
                "a.albumid = s.albumid inner join artist ar on\n";
        try{
            Statement st1 = connection.createStatement();
            ResultSet rs1 = st1.executeQuery(q1);
            System.out.format("%-10s %-30s %-15s %-30s %-9s %35s", "Song ID", "Title of the song","Genre", "Artist Name", "Album", "Duration\n");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");

            while (rs1.next()){
                System.out.format("%-10s %-30s %-15s %-30s %-20s %20s",rs1.getInt(1),rs1.getString(2),
                        rs1.getString(3),rs1.getString(4),rs1.getString(5), rs1.getTime(6)+ "\n");
            }
        }
        catch (SQLException se){
            System.out.println(se);
        }
    }
    public void createPlaylist(String userId){
        System.out.println("Enter playlist Id");
        int pId = scanner.nextInt();
            String q = "INSERT INTO Playlist VALUES(?,?)";
            try {
                PreparedStatement prepareStatement = connection.prepareStatement(q);
                prepareStatement.setInt(1,pId);
                prepareStatement.setString(2,userId);
                int res = prepareStatement.executeUpdate();
                if(res == 0)
                    System.out.println("Insertion failed");
                else
                    System.out.println("Playlist Created");
            }
            catch (SQLException se){
                System.out.println(se);
            }
        }
         {
            System.out.println("Playlist Id already Exists.\nRetuning Back");
        try {
            menu();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    }*/
