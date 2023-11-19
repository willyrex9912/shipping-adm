package com.modela.shipping.adm.service;

import com.modela.shipping.adm.dto.ShippingPage;
import com.modela.shipping.adm.model.AdmParameter;
import com.modela.shipping.adm.repository.AdmParameterRepository;
import com.modela.shipping.adm.util.exception.ShippingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdmParameterService {

    private final AdmParameterRepository repository;

    public ShippingPage<List<AdmParameter>, Long> getAll() {
        var parameters = repository.findAll();
        return ShippingPage.of(parameters, (long) parameters.size());
    }

    public AdmParameter findById(Long id) {
        var oParameter = repository.findById(id);
        if(oParameter.isEmpty())
            throw new ShippingException("Parameter not found")
                    .withStatus(HttpStatus.NOT_FOUND);

        return oParameter.get();
    }

    public AdmParameter save(AdmParameter parameter) {
        return repository.save(parameter);
    }

    public AdmParameter update(Long id, AdmParameter parameter) {
        var oParameter = repository.findById(id);
        if(oParameter.isEmpty())
            throw new ShippingException("Parameter not found")
                    .withStatus(HttpStatus.NOT_FOUND);

        parameter.setParameterId(id);
        return repository.save(parameter);
    }
}
