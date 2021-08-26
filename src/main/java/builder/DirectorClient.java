package builder;

public class DirectorClient {

    private Builder builder;

    public DirectorClient(Builder builder) {
        this.builder = builder;
    }

    public Builder getBuilder() {
        return builder;
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }
    
    public void make() {
        this.getBuilder().buildClient();
        this.getBuilder().buildEvents();
        this.getBuilder().buildDisconnectEvent();
    }
    
}