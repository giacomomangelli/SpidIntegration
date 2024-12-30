package it.activadigital.SpidIntegration.service.impl;

import it.activadigital.SpidIntegration.controller.dto.request.AssertionRequestDto;
import it.activadigital.SpidIntegration.controller.dto.response.AssertionSpidResponse;
import it.activadigital.SpidIntegration.enumeration.Constant;
import it.activadigital.SpidIntegration.service.AssertionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Service
@Slf4j
public class AssertionServiceImpl implements AssertionService {

    @Override
    public void checkSpidAssertion(String xmlAuthResponse) {
        AssertionRequestDto request = new AssertionRequestDto("", xmlAuthResponse);
        ResponseEntity<AssertionSpidResponse> responseDto = RestClient
                .create()
                .post()
                .uri(Constant.SPID_BASE_URL.getDescription() + "/spid/check_assertion")
                .body(request)
                .retrieve()
                .toEntity(AssertionSpidResponse.class);
        if (responseDto.getStatusCode() != HttpStatus.OK) {
            log.error("checkAssertion SPID returned status code {}", responseDto.getStatusCode());
            throw new RestClientException("Error in response from SPID assertion request request: " + responseDto.getStatusCode());
        }
    }

    @Override
    public void checkCieAssertion(String xmlAuthResponse) {
        AssertionRequestDto request = new AssertionRequestDto("", xmlAuthResponse);
        ResponseEntity<AssertionSpidResponse> responseDto = RestClient
                .create()
                .post()
                .uri(Constant.SPID_BASE_URL.getDescription() + "/spid/check_assertion")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(request)
                .retrieve()
                .toEntity(AssertionSpidResponse.class);
        if (responseDto.getStatusCode() != HttpStatus.OK) {
            log.error("checkAssertion CIE returned status code {}", responseDto.getStatusCode());
            throw new RestClientException("Error in response from CIE assertion request request: " + responseDto.getStatusCode());
        }
    }
}
