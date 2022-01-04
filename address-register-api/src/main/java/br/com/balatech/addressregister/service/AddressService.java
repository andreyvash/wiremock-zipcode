package br.com.balatech.addressregister.service;

import br.com.balatech.addressregister.bean.ZipCodeResponse;
import br.com.balatech.addressregister.config.CEPConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AddressService {

    private static final Logger LOG = Logger.getLogger(AddressService.class.getName());

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CEPConfig cepConfig;

    public ZipCodeResponse getAddressByZipCode(String zipCode) {

        LOG.info(String.format("Searching zipcode [%s]...", zipCode));

        try {
            LOG.info(String.format("EndPoint [%s]...", cepConfig.getEndpoint()));
            ResponseEntity<ZipCodeResponse> forEntity = restTemplate.getForEntity(String.format("%s/%s/json", cepConfig.getEndpoint(), zipCode), ZipCodeResponse.class);

            return forEntity.getBody();
        } catch (Exception ex) {

            LOG.log(Level.WARNING, "Error searching Zipcode", ex);

            return new ZipCodeResponse();
        }
    }

}
