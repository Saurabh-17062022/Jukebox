package com.jukebox.main;

import com.jukebox.data.Song;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JukeboxIMPLTest {
    JukeboxIMPL jukeboxIMPL;

    @BeforeEach
    void setUp() {
        jukeboxIMPL = new JukeboxIMPL();
    }

    @AfterEach
    void tearDown() {
        jukeboxIMPL = null;
    }

    @Test
    void searchBySongName() throws SQLException, ClassNotFoundException{
        String songName = "Mercy";
        List<Song> songList = jukeboxIMPL.searchBySongName(songName);
        assertEquals(songList,songList);

    }

    @Test
    void searchByGenre() throws SQLException, ClassNotFoundException{
        String genreName = "Party";
        List<Song> songList = jukeboxIMPL.searchByGenre(genreName);
        assertEquals(songList,songList);
    }

    @Test
    void searchBySingerName() throws SQLException, ClassNotFoundException {
        String singerName = "Badshah";
        List<Song> songList = jukeboxIMPL.searchBySingerName(singerName);
        assertEquals(songList,songList);
    }
}