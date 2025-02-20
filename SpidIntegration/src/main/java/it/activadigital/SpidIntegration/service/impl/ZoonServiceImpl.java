package it.activadigital.SpidIntegration.service.impl;

import it.activadigital.SpidIntegration.controller.dto.request.ZoonUserRequestDto;
import it.activadigital.SpidIntegration.controller.dto.response.ZoonUserResponseDto;
import it.activadigital.SpidIntegration.model.ZoonUser;
import it.activadigital.SpidIntegration.model.mapper.ZoonUserMapper;
import it.activadigital.SpidIntegration.repository.ZoonUserRepository;
import it.activadigital.SpidIntegration.service.ZoonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ZoonServiceImpl implements ZoonService {

    @Autowired
    private ZoonUserRepository zoonUserRepository;

    @Override
    public ZoonUserResponseDto getZoonUser(String username) {
        return ZoonUserMapper.mapToDto(zoonUserRepository.findByUsername(username).orElse(null));
    }

    @Override
    public List<ZoonUserResponseDto> listZoonUser() {
        return zoonUserRepository.findAll().stream().map(ZoonUserMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public void updateZoonUserPwd(ZoonUserRequestDto zoonDto) {
        ZoonUser zoonUser = zoonUserRepository.findByUsername(zoonDto.username()).orElse(null);
        if (zoonUser == null) {
            return;
        }
        zoonUser.setPassword(zoonDto.password());
        zoonUserRepository.save(zoonUser);
    }

    @Override
    public void addZoonUser(ZoonUserRequestDto zoonDto) {
        zoonUserRepository.save(ZoonUserMapper.mapToEntity(zoonDto));
    }

    @Override
    public void deleteZoonUser(String username) {
        zoonUserRepository.deleteByUsername(username);
    }


}
