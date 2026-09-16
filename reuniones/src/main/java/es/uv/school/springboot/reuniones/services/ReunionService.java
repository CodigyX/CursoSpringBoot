package es.uv.school.springboot.reuniones.services;

import es.uv.school.springboot.reuniones.data.ReunionRepository;
import es.uv.school.springboot.reuniones.models.Persona;
import es.uv.school.springboot.reuniones.models.Reunion;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReunionService {

    private ReunionRepository reunionRepository;

    public ReunionService(ReunionRepository reunionRepository) {
        this.reunionRepository = reunionRepository;
    }

    public List<Reunion> getAllReuniones() {
        return reunionRepository.findAll();
    }

    public Optional<Reunion> getById(long id) {

        return reunionRepository.findById(id);
    }
}
