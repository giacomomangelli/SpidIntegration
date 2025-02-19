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

    @CrossOrigin(origins = {"http://localhost:4200, http://localhost:4201"})
    @GetMapping("/users")
    public ResponseEntity<List<ZoonUserResponseDto>> getUsers() {
        return ResponseEntity.ok(zoonService.listZoonUser());
    }

    @CrossOrigin(origins = {"http://localhost:4200, http://localhost:4201"})
    @GetMapping("/users/{username}")
    public ResponseEntity<ZoonUserResponseDto> getUser(@PathVariable String username) {
        ZoonUserResponseDto userResponseDto = zoonService.getZoonUser(username);
        if (userResponseDto != null) {
            return ResponseEntity.ok(userResponseDto);
        }
        return ResponseEntity.notFound().build();
    }

    @CrossOrigin(origins = {"http://localhost:4200, http://localhost:4201"})
    @PostMapping("/users")
    public ResponseEntity<Void> addZoonUser(@RequestBody ZoonUserRequestDto zoonUserRequestDto) {
        zoonService.addZoonUser(zoonUserRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @CrossOrigin(origins = {"http://localhost:4200, http://localhost:4201"})
    @PutMapping("/users")
    public ResponseEntity<Void> updateZoonUserPassword(@RequestBody ZoonUserRequestDto zoonUserRequestDto) {
        zoonService.updateZoonUserPwd(zoonUserRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
