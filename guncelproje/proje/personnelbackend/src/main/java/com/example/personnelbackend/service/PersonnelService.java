package com.example.personnelbackend.service;

import com.example.personnelbackend.entity.Personnel;
import com.example.personnelbackend.repository.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class PersonnelService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private PersonnelRepository personnelRepository;

    public List<Personnel> getAllPersonnel() {
        return personnelRepository.findAll();
    }

    public Optional<Personnel> getPersonnelById(Long id) {
        return personnelRepository.findById(id);
    }

    public Personnel savePersonnel(Personnel personnel) {
        Personnel savedPersonnel = personnelRepository.save(personnel);
        String message = "New Personnel Added:\n" + savedPersonnel.toString();
        rabbitTemplate.convertAndSend("notificationQueue", message);
        return savedPersonnel;
    }

    public void deletePersonnel(Long id) {
        Optional<Personnel> optionalPersonnel = personnelRepository.findById(id);
        if (optionalPersonnel.isPresent()) {
            Personnel personnel = optionalPersonnel.get();
            String message = "Personnel Deleted:\n" + personnel.toString();
            rabbitTemplate.convertAndSend("notificationQueue", message);
            personnelRepository.deleteById(id);
        } else {
            throw new RuntimeException("Personnel not found");
        }
    }

    public Personnel updatePersonnel(Long id, Personnel newPersonnel) {
        Optional<Personnel> optionalPersonnel = personnelRepository.findById(id);
        if (optionalPersonnel.isPresent()) {
            Personnel oldPersonnel = optionalPersonnel.get();
            String message = "Personnel updated:\n\nOld Details:\n" + oldPersonnel.toString() + "\n\nNew Details:\n"
                    + newPersonnel.toString();
            rabbitTemplate.convertAndSend("notificationQueue", message);

            oldPersonnel.setName(newPersonnel.getName());
            oldPersonnel.setEmail(newPersonnel.getEmail());
            return personnelRepository.save(oldPersonnel);
        } else {
            throw new RuntimeException("Personnel not found");
        }
    }
}
