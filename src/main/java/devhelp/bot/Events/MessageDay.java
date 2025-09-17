package devhelp.bot.Events.MemeListeners;

import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.time.*;
import java.util.concurrent.*;

public class MessageDay extends ListenerAdapter {

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    @Override
    public void onReady(ReadyEvent event) {
        long delay = calcularDelay(21, 0); // exemplo: 21:00 todo dia
        long intervalo = TimeUnit.DAYS.toSeconds(1); // 24h

        scheduler.scheduleAtFixedRate(() -> {
            TextChannel canal = event.getJDA().getTextChannelById("ID_DO_CANAL_AQUI");
            if (canal != null) {
                canal.sendMessage("Mensagem diária!").queue();
            }
        }, delay, intervalo, TimeUnit.SECONDS);
    }

    private long calcularDelay(int hora, int minuto) {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime proximo = agora.withHour(hora).withMinute(minuto).withSecond(0).withNano(0);

        if (agora.compareTo(proximo) >= 0) {
            proximo = proximo.plusDays(1); // já passou hoje, agenda pra amanhã
        }

        Duration duracao = Duration.between(agora, proximo);
        return duracao.getSeconds();
    }
}
