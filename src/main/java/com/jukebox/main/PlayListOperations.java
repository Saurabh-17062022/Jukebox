package com.jukebox.main;

import com.jukebox.util.Db_Connection;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class PlayListOperations {

    Scanner scanner = new Scanner(System.in);
    JukeboxIMPL jukeboxIMPL = new JukeboxIMPL();
    int choice;
    // Create Playlists By Category:
    public void createPlaylistByCategory(Connection connection) throws SQLException, ClassNotFoundException, NullPointerException {
        Connection connection1 = Db_Connection.getConnection();
        jukeboxIMPL.displayAllSong();
        Scanner scanner1=new Scanner(System.in);
        System.out.println("Type 'Song Id ' to Create Playlist Of Songs");
        int choice = scanner1.nextInt();
        System.out.println("Give Playlist Id");
        int choice1 = scanner1.nextInt();
        String query = "insert into playlist(playlist_id,song_id) values(?,?)";

        PreparedStatement preparedStatement = connection1.prepareStatement(query);

        preparedStatement.setInt(1,choice1);
        preparedStatement.setInt(2,choice);
        int count = preparedStatement.executeUpdate();
        if (count == 0) {
            System.out.println("Playlist Creation Failed...!");
        } else {
            System.out.println("Playlist Created Successfully");

            //calling the method which gives users to add songs to created playlist
        }
    }
   /* public void insertingSongToPlaylist(Connection con, int playlistId, int songId) throws SQLException {
        String query1 = "insert into playlistsongs (playlist_id,song_id) values (?,?)";
        PreparedStatement preparedStatement = con.prepareStatement(query1);
        preparedStatement.setInt(1, playlistId);
        preparedStatement.setInt(2, songId);
        int count = preparedStatement.executeUpdate();
        if (count == 0) {
            System.out.println("Adding song to playlist is Failed...!");
        } else {
            System.out.println("Song has been added to playlist Successfully");
        }
    }*/
    public void viewYourPlaylists(Connection con) throws SQLException, UnsupportedAudioFileException, LineUnavailableException, IOException, ClassNotFoundException {
        System.out.println("Playlists are :");
        System.out.format("Song_id\t\t\tPlaylist_Id\n");
        String query = "select song_id,playlist_id from playlist";
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next())
        {
            System.out.format("%1s",resultSet.getInt("song_id"));
            System.out.format("%20s",resultSet.getInt("playlist_id"));
            System.out.println("\n");
        }
        System.out.println("Enter the playlist id");
        int id=scanner.nextInt();
        jukeboxIMPL.playSong(con);
    }

}