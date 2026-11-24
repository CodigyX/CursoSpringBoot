package es.uv.school.springboot.reuniones.async;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.uv.school.springboot.reuniones.models.Persona;
import es.uv.school.springboot.reuniones.models.Reunion;
import es.uv.school.springboot.reuniones.services.PersonaService;
import es.uv.school.springboot.reuniones.services.ReunionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BuscaListener {

    private static final Logger log = LoggerFactory.getLogger(BuscaListener.class);

    private final ObjectMapper mapper;
    private final PersonaService personaService;
    private final ReunionService reunionService;

    public BuscaListener(ObjectMapper mapper, PersonaService personaService,
                         ReunionService reunionService) {
        this.mapper = mapper;
        this.personaService = personaService;
        this.reunionService = reunionService;
    }

    public void recibirMensaje(String mensaje) {
        try {
            InfoBusca info = mapper.readValue(mensaje, InfoBusca.class);
            Optional<Persona> persona = personaService.getById(info.getIdAsistente());
            if (persona.isEmpty()) {
                log.warn("Mensaje recibido, pero la persona {} no existe",
                        info.getIdAsistente());
            }
            Optional<Reunion> reunion = reunionService.getById(info.getIdReunion());
            if (reunion.isEmpty()) {
                log.warn("Mensaje recibido, pero la reunión {} no existe",
                        info.getIdReunion());
            }
            if (persona.isPresent() && reunion.isPresent()) {
                log.info("{} {} tiene una reunión a las {}",
                        persona.get().getNombre(),
                        persona.get().getApellidos(),
                        reunion.get().getFecha());
            }
        } catch (JsonProcessingException e) {
            log.warn("Mensaje incorrecto", e);

        }
    }

}
