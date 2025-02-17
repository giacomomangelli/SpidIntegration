package it.activadigital.SpidIntegration.service.impl;

import it.activadigital.SpidIntegration.controller.dto.request.AssertionRequestDto;
import it.activadigital.SpidIntegration.controller.dto.response.AssertionCieResponse;
import it.activadigital.SpidIntegration.controller.dto.response.AssertionSpidResponse;
import it.activadigital.SpidIntegration.enumeration.Constant;
import it.activadigital.SpidIntegration.service.AssertionService;
import it.activadigital.SpidIntegration.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;


@Slf4j
@Service
public class AssertionServiceImpl implements AssertionService {

    @Autowired
    private RequestUtil util;
    @Value("${deda-client.client_id}")
    private String clientId;

    @Override
    public AssertionSpidResponse checkSpidAssertion(AssertionRequestDto assertionRequestDto) {
        log.info("Check SpidAssertion xmlAuthResponse: {}", assertionRequestDto);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", clientId.trim());
        map.add("assertion", assertionRequestDto.samlResponse());
        ResponseEntity<AssertionSpidResponse> response = RestClient
                .create()
                .post()
                .uri(Constant.SPID_BASE_URL.getDescription() + "/check_assertion")
                .headers(httpHeaders -> {
                    httpHeaders.addAll(util.setHeaders());
                })
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(map)
                .retrieve()
                .toEntity(AssertionSpidResponse.class);
        if (response.getStatusCode() != HttpStatus.OK ||
                (response.getBody() != null && response.getBody().getEsito().equals("ko"))) {
            log.error("checkAssertion SPID returned status code {} and body {}", response.getStatusCode(), response.getBody());
            throw new RestClientException("Error in response from SPID assertion request " + response.getStatusCode() + " with body " + response.getBody());
        }
        log.info("Check SpidAssertion returned: {}", response.getBody());
        return response.getBody();
    }

    @Override
    public AssertionCieResponse checkCieAssertion(AssertionRequestDto assertionRequestDto) {
        log.info("Check CieAssertion xmlAuthResponse: {}", assertionRequestDto);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", clientId);
        map.add("assertion", assertionRequestDto.samlResponse());
        ResponseEntity<AssertionCieResponse> responseDto = RestClient
                .create()
                .post()
                .uri(Constant.CIE_BASE_URL.getDescription() + "/check_assertion")
                .headers(httpHeaders -> {
                    httpHeaders.addAll(util.setHeaders());
                })
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(map)
                .retrieve()
                .toEntity(AssertionCieResponse.class);
        if (responseDto.getStatusCode() != HttpStatus.OK ||
                (responseDto.getBody() != null && responseDto.getBody().getEsito().equals("ko"))) {
            log.error("checkAssertion CIE returned status code {} and body {}", responseDto.getStatusCode(), responseDto.getBody());
            throw new RestClientException("Error in response from CIE assertion request: " + responseDto.getStatusCode() + " with body " + responseDto.getBody());
        }
        return responseDto.getBody();
    }
}
