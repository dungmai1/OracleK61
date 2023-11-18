package com.example.Ecommerce_SellPhone.service.Provider;

import com.example.Ecommerce_SellPhone.DTO.ProductDTO;
import com.example.Ecommerce_SellPhone.DTO.ProviderDTO;
import com.example.Ecommerce_SellPhone.models.Provider;
import com.example.Ecommerce_SellPhone.repository.ProviderRepository;
import com.example.Ecommerce_SellPhone.service.Provider.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProviderImpl implements ProviderService {
    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public void createProvider(ProviderDTO providerDTO) {
        Provider provider = new Provider();
        provider.setProvider_address(providerDTO.getProvider_address());
        provider.setProvider_name(providerDTO.getProvider_name());
        provider.setProvider_email(providerDTO.getProvider_email());
        provider.setProvider_phone(providerDTO.getProvider_phone());
        provider.setProvider_website(providerDTO.getProvider_website());
        providerRepository.save(provider);
    }
    @Override
    public List<Provider> getAllProvider() {
        List<Provider> allProvider = providerRepository.findAll();
        return allProvider;
    }

    @Override
    public Provider findById(Integer id) {
        return providerRepository.findById(id).get();
    }

    @Override
    public void updateProvider(Provider provider) {
        providerRepository.updateProvider(
                provider.getId(),
                provider.getProvider_name(),
                provider.getProvider_address(),
                provider.getProvider_phone(),
                provider.getProvider_email(),
                provider.getProvider_website()
        );
    }

    @Override
    public Boolean deleteProvider(int provider_id) {
        try{
            providerRepository.delete(findById(provider_id));
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Provider getProviderById(int provider_id) {
        Provider provider = providerRepository.findById(provider_id).get();
        return provider;
    }
}
