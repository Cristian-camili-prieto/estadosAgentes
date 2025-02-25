package estados.estadosAgentes.service;

import estados.estadosAgentes.model.EstadoAgente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class AgenteService {

    private final ConcurrentHashMap<Long, EstadoAgente> agentes = new ConcurrentHashMap<>();

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // Incrementar el tiempo de espera cada segundo para agentes en llamada
    @Scheduled(fixedRate = 1000)
    public void actualizarTiemposDeEspera() {
        agentes.forEach((id, agente) -> {
            if ("En llamada".equalsIgnoreCase(agente.getEstado())) {
                agente.setTiempoEspera(agente.getTiempoEspera() + 1);
                // Enviar actualización por WebSocket
                messagingTemplate.convertAndSend("/topic/agentes", agente);
            }
        });
    }

    // Método para obtener el agente actualizado
    public EstadoAgente obtenerAgente(Long id) {
        return agentes.get(id);
    }

    // Método para cambiar el estado del agente y notificar en tiempo real
    public void cambiarEstado(EstadoAgente agente) {
        if (agente != null && agente.getId() != null) {
            // Lógica para cambiar el estado usando el ID correctamente
            System.out.println("Actualizando estado del agente con ID: " + agente.getId());
        } else {
            System.out.println("❌ El agente es nulo o no tiene ID válido");
        }
    }


    // Método para agregar un nuevo agente
    public void agregarAgente(EstadoAgente agente) {
        agentes.put(agente.getId(), agente);
        messagingTemplate.convertAndSend("/topic/agentes", agente); // Notificar nuevo agente
    }
}
