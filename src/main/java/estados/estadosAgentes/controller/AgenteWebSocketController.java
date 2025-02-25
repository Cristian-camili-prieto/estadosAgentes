package estados.estadosAgentes.controller;

import estados.estadosAgentes.model.EstadoAgente;
import estados.estadosAgentes.service.AgenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class AgenteWebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private AgenteService agenteService;

    @MessageMapping("/cambiarEstado")
    public void cambiarEstado(@Payload EstadoAgente agente) {
        if (agente == null || agente.getId() == null) {
            System.out.println("❌ El agente recibido es null o inválido");
            return;
        }

        // ✅ Cambiar el estado del agente
        agenteService.cambiarEstado(agente);
        notificarActualizacion(agente);
    }

    private void notificarActualizacion(EstadoAgente agente) {  // ✅ Cambiar Agente por EstadoAgente
        if (agente != null) {
            messagingTemplate.convertAndSend("/topic/agentes", agente);
        } else {
            System.out.println("❌ No se puede enviar un agente null");
        }
    }
}
