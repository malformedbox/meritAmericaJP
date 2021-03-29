import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SongTest {

    @Test
    void setPlaybackSpeed() {
        Song testSongName = new Song("Haven", "Nell");
        float testSpeed = 1.5f;
        testSongName.setPlaybackSpeed(testSpeed);
        Assertions.assertFalse(testSpeed < 0);
    }

    @Test
    @DisplayName("Test to Get Name")
    void getName() {
        Song testSongName = new Song("Haven", "Nell");
        Assertions.assertFalse(testSongName.getName().isEmpty());
        Assertions.assertEquals("Haven", testSongName.getName());
    }

    @Test
    @DisplayName("Test to Get Artist")
    void getArtist() {
        Song testSongName = new Song("Haven", "Nell");
        Assertions.assertFalse(testSongName.getArtist().isEmpty());
        Assertions.assertEquals("Nell", testSongName.getArtist());
    }
}