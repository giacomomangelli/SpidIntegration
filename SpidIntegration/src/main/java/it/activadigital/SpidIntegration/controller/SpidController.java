package it.activadigital.SpidIntegration.controller;

import it.activadigital.SpidIntegration.service.UserService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@Builder
public class SpidController {

    private final UserService userService;

}
