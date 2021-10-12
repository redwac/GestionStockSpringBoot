package com.reda.GestionStock.services.implementation;

import com.reda.GestionStock.dto.ClientDto;
import com.reda.GestionStock.exception.EntityNotFoundException;
import com.reda.GestionStock.exception.ErrorCodes;
import com.reda.GestionStock.exception.InvalidEntityException;
import com.reda.GestionStock.repository.ClientRepository;
import com.reda.GestionStock.services.ClientService;
import com.reda.GestionStock.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {


    private ClientRepository clientRepository;


    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDto save(ClientDto dto) {
        List<String> errors = ClientValidator.validate(dto);
        if (!errors.isEmpty()){
            log.error("la validation Client n'est pas correct");
            throw new InvalidEntityException("la validation client n'est pas valid 2 ", ErrorCodes.CLIENT_NOT_FOUND, errors);
        }
        return ClientDto.fromEntity(
                clientRepository.save(ClientDto.toEntity(dto))
        );
    }

    @Override
    public ClientDto findByID(Integer id) {
        if (id == null){
            log.error("id Client is null");
            return null;
        }
        return clientRepository.findById(id)
                .map(ClientDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException(
                        "la validation Client n'est pas correct", ErrorCodes.CLIENT_NOT_FOUND
                ));
    }

    @Override
    public List<ClientDto> finAll() {

        return clientRepository.findAll().stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("Id client is null !! ");
            return;
        }
        clientRepository.deleteById(id);

    }
}
