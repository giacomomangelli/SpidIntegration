package it.activadigital.SpidIntegration.controller;

import it.activadigital.SpidIntegration.controller.dto.request.ZoonUserRequestDto;
import it.activadigital.SpidIntegration.controller.dto.response.ZoonUserResponseDto;
import it.activadigital.SpidIntegration.service.ZoonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/zoon")
@RestController
public class ZoonController {

    @Autowired
    private ZoonService zoonService;

    @GetMapping("/users")
    public ResponseEntity<List<ZoonUserResponseDto>> getUsers() {
        return ResponseEntity.ok(zoonService.listZoonUser());
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<ZoonUserResponseDto> getUser(@PathVariable String username) {
        return ResponseEntity.ok(zoonService.getZoonUser(username));
    }

    @PostMapping("/users")
    public ResponseEntity<Void> addZoonUser(@RequestBody ZoonUserRequestDto zoonUserRequestDto) {
        zoonService.addZoonUser(zoonUserRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/users")
    public ResponseEntity<Void> updateZoonUserPassword(@RequestBody ZoonUserRequestDto zoonUserRequestDto) {
        zoonService.updateZoonUserPwd(zoonUserRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }




}
