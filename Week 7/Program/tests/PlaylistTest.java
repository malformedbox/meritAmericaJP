import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistTest {

    @Test
    @DisplayName("Test Adding a Playlist")
    void addToPlaylist() throws Exception{
        Playlist testList = new Playlist("Master List");
        Playlist favList = new Playlist("Favorites");

        testList.add(favList);

        Assertions.assertFalse(testList.getName().isEmpty());
        Assertions.assertEquals(1, testList.playlist.size());
    }

    @Test
    @DisplayName("Test Removing a Playlist")
    void removeFromPlaylist() {
        Playlist testList = new Playlist("Master List");

    }

    @Test
    @DisplayName("Test Setting a Playback Speed")
    void setPlaybackSpeed() {
        Song testSongName = new Song("Haven", "Nell");
        float testSpeed = 1.5f;
        testSongName.setPlaybackSpeed(testSpeed);
        Assertions.assertFalse(testSpeed < 0);
    }

    @Test
    @DisplayName("Test Get Name of PlayList")
    void getNameOfPlaylist() {
        Playlist testList = new Playlist("Favorites");
        assertEquals("Favorites", testList.getName());
    }
}