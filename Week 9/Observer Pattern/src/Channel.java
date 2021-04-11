import java.util.ArrayList;

public class Channel implements Subject{
    private ArrayList<Observer> observerList = new ArrayList<>();
    private String channelName;
    private String status;

    public Channel(String channelName, String status){
        this.channelName = channelName;
        this.status = status;
    }

    public String getChannelName(){
        return this.channelName;
    }
    public void setChannelName(String channelName){
        this.channelName = channelName;
    }

    public String getStatus(){
        return this.status;
    }
    public void setStatus(String status){
        this.status = status;
    }

    public void registerObserver(Observer observer){
        this.observerList.add(observer);
    }
    public void removeObserver(Observer observer){
        this.observerList.remove(observer);
    }
    public void notifyObservers(){
        for(Observer obs : observerList){
            obs.update(this.status);
        }
    }
}
