package main;

import builder.Builder;
import builder.BuilderClient;
import builder.DirectorClient;

public class App {
    public static void main(String[] args) {
        Builder builder = new BuilderClient();
        DirectorClient directorClient = new DirectorClient(builder);
        directorClient.make();
    }
}