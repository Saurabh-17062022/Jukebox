
package com.jukebox.main;

import com.jukebox.data.Song;
import com.jukebox.util.Db_Connection;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JukeboxIMPL {
    // Connection connection = Db_Connection.getConnection();
    static PlayListOperations playListOperations = new PlayListOperations();
    public static void main(String[] args) throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException, FileNotFoundException {
        JukeboxIMPL jukeboxIMPL = new JukeboxIMPL();
        Scanner scanner = new Scanner(System.in);
        jukeboxMenu();
        //SongOperations songOperations = new SongOperations();
        //PlayListOperations playListOperations = new PlayListOperations();
    }
        public static void jukeboxMenu() throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        int res = 0;
        while (res == 0) {
            Scanner scanner = new Scanner(System.in);
            JukeboxIMPL jukeboxIMPL = new JukeboxIMPL();
            System.out.println("1. Show all Songs");
            System.out.println("2. Create new Playlist");
            System.out.println("3. View all Playlists");
            System.out.println("4. Search Songs");
            System.out.println("5. Exit");
            System.out.println("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    jukeboxIMPL.displayAllSong();
                    jukeboxIMPL.playSong(Db_Connection.getConnection());
                    break;
                case 2:
                    playListOperations.createPlaylistByCategory(Db_Connection.getConnection());
                    break;
                case 3:
                    playListOperations.viewYourPlaylists(Db_Connection.getConnection());
                    break;
                case 4:
                    jukeboxIMPL.searchSongByCategory(Db_Connection.getConnection());
                    break;
                case 5:
                    System.exit(0);
                default:
            }
        }
    }

    public static List<Song> displayAllSong() {
        List<Song> songList1 = new ArrayList<>();
        try {
            Connection connection = Db_Connection.getConnection();
            Statement statement = connection.createStatement();
            String sql = "Select * From song ";
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("song_id=============song_name==========Duration=================Genre==================Singer_name=================SongPath");
            System.out.println("------------------------------------------------------------------------------------------------------------------");
            while (resultSet.next()) {
                System.out.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", resultSet.getInt("song_id"), resultSet.getString("song_name"),
                        resultSet.getString("song_duration"), resultSet.getString("genres"), resultSet.getString("singer_name"), resultSet.getString("song_path"), resultSet.getString("album_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return songList1;
    }

    public void searchSongByCategory(Connection connection) throws
            SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");     //used delimiter to get space included while taking input from user
        System.out.println("How do you want to search");
        System.out.println("1.Search by Song Name");
        System.out.println("2.Search by Genres");
        System.out.println("3.Search by Singer Name");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Enter the Song Name");
                String songName = scanner.next();
                searchBySongName(songName);
                playSong(connection);
                break;
            case 2:
                System.out.println("Enter the Genres Name");
                String genresName = scanner.next();
                searchByGenre(genresName);
                playSong(connection);
                break;
            case 3:
                System.out.println("Enter the Singer Name");
                String singerName = scanner.next();
                searchBySingerName(singerName);
                playSong(connection);
                break;
        }
    }

    public List<Song> searchBySongName(String songTitle) throws SQLException, ClassNotFoundException {
        List<Song> songList = new ArrayList<>();
        try {
            Connection connection = Db_Connection.getConnection();
            String sql = "Select * from song Where song_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, songTitle);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("SongId==============SongTitle=========SingerName=========Genre===========Duration");
            System.out.println("------------------------------------------------------------------------");
            while (resultSet.next()) {
                System.out.format("%-20s%-20s%-20s%-20s%-20s", resultSet.getInt("song_id"), resultSet.getString("song_name"), resultSet.getString("singer_name"), resultSet.getString("genres"), resultSet.getString("song_duration"), resultSet.getString("song_path"));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songList;
    }

    public List<Song> searchByGenre(String genre) throws SQLException, ClassNotFoundException {
        List<Song> songList = new ArrayList<>();
        try {
            Connection connection = Db_Connection.getConnection();
            String sql = "Select * from song Where genres = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, genre);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("SongId==============SongTitle=========SingerName=========Genre===========Duration");
            System.out.println("------------------------------------------------------------------------");
            while (resultSet.next()) {
                System.out.format("%-20s%-20s%-20s%-20s%-20s", resultSet.getInt("song_id"), resultSet.getString("song_name"), resultSet.getString("singer_name"), resultSet.getString("genres"), resultSet.getString("song_duration"), resultSet.getString("song_path"));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songList;
    }

    public List<Song> searchBySingerName(String artist) throws SQLException, ClassNotFoundException {
        List<Song> songList = new ArrayList<>();
        try {
            Connection connection = Db_Connection.getConnection();
            String sql = "Select * from song Where singer_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, artist);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("SongTitle=========SingerName=========Genre===========Duration");
            System.out.println("------------------------------------------------------------------------");
            while (resultSet.next()) {
                System.out.format("%-20s%-20s%-20s%-20s%-20s", resultSet.getInt("song_id"), resultSet.getString("song_name"), resultSet.getString("singer_name"), resultSet.getString("genres"), resultSet.getString("song_duration"), resultSet.getString("song_path"));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songList;
    }

    static int repeat = 0, id = 0;
    Clip clip;


    public void playSong(Connection con) throws
            UnsupportedAudioFileException, IOException, LineUnavailableException, SQLException, ClassNotFoundException {

        Scanner scan = new Scanner(System.in);
        if (repeat == 0) {
            System.out.println("enter the song_id to play the song");
            id = scan.nextInt();
        }

        String query = "select song_path from song where song_id=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        String song_path = "";

        while (rs.next()) {
            song_path = rs.getString(1);

        }


        Scanner sc = new Scanner(System.in);
        File file = new File(song_path);
        Clip clip = AudioSystem.getClip();
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip.open(audioStream);
            /*long duration = clip.getMicrosecondLength();
            duration = clip.getMicrosecondLength();
            long finalDuration = (duration / 1000000)*1000;      //converting into microsecond
            final Timer t = new Timer(1000, new ActionListener()  {
                private long time = finalDuration; //10 seconds, for example

                public void actionPerformed(ActionEvent e) {
                    if (time >= 0) {
                        long s = ((time / 1000) % 60);   //converting into sec
                        long m = (((time / 1000) / 60) % 60); //converting into min
                        long h = ((((time / 1000) / 60) / 60) % 60);   //converting into hour
                        System.out.print("\r" + h + " hours, " + m + " minutes " + s + " seconds");//it moves the cursor to the beginning of the line
                        time -= 1000;
                    }
                }
                );
            }
            */

        System.out.println();
        int input;
        int flag = 0;
        long clipps = 0;

        while (flag != 1) {
            System.out.println("\nEnter Your Choice\n---------------\n 1. Play\n 2. Pause\n 3. Resume\n 4. Restart\n 5. Forward\n 6.Backwards\n 7. Next \n 8. Exit");
            input = sc.nextInt();
            //  switch (input){
            if (input == 1) {
                // case 1:
                clip.start();
                System.out.println("+------------");
                System.out.println("|Playing Song|");
                System.out.println("+------------+");
                       /* break;
                    case 2:*/
            }
            if (input == 2) {
                clipps = clip.getMicrosecondPosition();
                clip.stop();
                System.out.println("+-----------+");
                System.out.println("|Song Paused|");
                System.out.println("+-----------+");
                        /*break;
                        case 3:*/
            }
            if (input == 3) {
                clip.setMicrosecondPosition(clipps);
                clip.start();
                System.out.println("+------------+");
                System.out.println("|Song Resumed|");
                System.out.println("+------------+");
                     /*break;
                    case 4:*/
            }
            if (input == 4) {
                clip.setMicrosecondPosition(0);
                clip.start();
                System.out.println("+--------------+");
                System.out.println("|Song Restarted|");
                System.out.println("+--------------+");
                   /* break;
                    case 5:*/
            }
            if (input == 5) {
                System.out.println("+-----------------+");
                System.out.println("|Forwarding by 10s|");
                System.out.println("+-----------------+");
                clip.setMicrosecondPosition(clip.getMicrosecondPosition() + 1000000);
                     /*break;
                    case 6:*/
            }
            if (input == 6) {
                System.out.println("+-----------------+");
                System.out.println("|Backward by 10s|");
                System.out.println("+-----------------+");
                clip.setMicrosecondPosition(clip.getMicrosecondPosition() - 1000000);
                    /* break;
                    case 7:*/
            }
            if (input == 7) {
                System.out.println("+------------------+");
                System.out.println("|Next|");
                System.out.println("+------------------+");
                clip.close();
                repeat = repeat + 1;
                id = id + 1;
                //playSong(con);
                // System.out.println("1:play");
                //clip.start();
                // break;
            }
            if (input == 8) {
                JukeboxIMPL jukeboxIMPL = new JukeboxIMPL();
                clip.close();
                jukeboxMenu();
                //case 8:
                // clip.close();
                //flag = 1;
            }

            //  break;
            // default:
               /* } else {
                    System.out.println("Not a valid Input");
                }*/
        }
    }
}

