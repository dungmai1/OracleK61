package com.example.Ecommerce_SellPhone.service.Provider;

import com.example.Ecommerce_SellPhone.DTO.ProviderDTO;
import com.example.Ecommerce_SellPhone.models.Provider;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProviderService {
    public void createProvider(ProviderDTO providerDTO);
    public List<Provider> getAllProvider();
    Provider findById(Integer id);
    void updateProvider(Provider provider);
    Boolean deleteProvider(int provider_id);
    Provider getProviderById(int provider_id);
}
