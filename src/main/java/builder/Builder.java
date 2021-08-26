package builder;

public interface Builder {
    
    public void reset();
    
    public void buildClient();
    
    public void buildEvents();
    
    public void buildDisconnectEvent();
    
}
