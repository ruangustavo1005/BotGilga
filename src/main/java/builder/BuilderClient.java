package builder;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import factory.FactoryProcesso;
import model.Comando;
import processos.Processo;

public class BuilderClient implements Builder {

    static public final String TOKEN = "ODY2NzY5OTU4Mjc5OTcwODQ2.YPXYbQ.4OezEYROquu1h6uSSs_9RP0v2Eg";
    
    private GatewayDiscordClient client;
    
    @Override
    public void reset() {
        this.client = DiscordClientBuilder.create(TOKEN).build().login().block();
    }

    @Override
    public void buildClient() {
        this.reset();
    }

    @Override
    public void buildEvents() {
        this.client.getEventDispatcher().on(ReadyEvent.class).subscribe((ReadyEvent readyEvent) -> {
            onReady(readyEvent);
        });
        
        this.client.getEventDispatcher().on(MessageCreateEvent.class).subscribe((MessageCreateEvent messageCreateEvent) -> {
            onMessageCreate(messageCreateEvent);
        });
    }

    @Override
    public void buildDisconnectEvent() {
        this.client.onDisconnect().block();
    }
    
    private static void onReady(ReadyEvent readyEvent) {
        User user = readyEvent.getSelf();
        
        System.out.println("Login efetuado com sucesso como " + user.getUsername() + "#" + user.getDiscriminator() + "!");
    }
    
    private static void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        Message message = messageCreateEvent.getMessage();
        String content = message.getContent();
        User user = message.getAuthor().get();
        Guild guild = message.getGuild().block();
        
        System.out.println("(" + guild.getName() + ") " + user.getTag() + "@" + guild.getChannelById(message.getChannelId()).block().getName() + ": \"" + content + "\"");
        
        if (!user.isBot()) {
            if (content.startsWith(Comando.CHAVE + " ")) {
                content = content.substring(3);
            
                String comando;
                String[] argumentos = null;
                
                if (content.contains(" ")) {
                    comando = content.substring(0, content.indexOf(" "));
                    argumentos = content.substring(content.indexOf(" ") + 1).split(" ");
                }
                else {
                    comando = content;
                }
                
                try {
                    Processo processo = FactoryProcesso.factoryProcesso(message, comando, argumentos);
                    
                    if (processo != null && processo.podeExecutar()) {
                        processo.execute();
                    }
                }
                catch (ClassNotFoundException ex) {
                    System.out.println("O comando \"" + comando + "\" não foi reconhecido!");
                }
                
            }
        }
    }

}