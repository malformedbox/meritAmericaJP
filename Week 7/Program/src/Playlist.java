import java.util.ArrayList;

public class Playlist implements IComponent {

    public String playlistName;
    public ArrayList<IComponent> playlist = new ArrayList();

    public Playlist(String playlistName) {
        this.playlistName = playlistName;
    }

    public void add(IComponent addPlaylist) {
        playlist.add(addPlaylist);
    }

    public void remove(IComponent removePlaylist) {
        playlist.remove(removePlaylist);
    }

    public void play(){
        for(IComponent component : playlist) {
            component.play();
        }
    }

    public void setPlaybackSpeed(float speed) {
        for(IComponent component : this.playlist){
            component.setPlaybackSpeed(speed);
        }
    }

    public String getName() {
        return this.playlistName;
    }

}