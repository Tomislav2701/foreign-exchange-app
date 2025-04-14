package com.tomi.fexapp.service;

import com.tomi.fexapp.dto.ExchangeRateResponse;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;

@Service
public class ExchangeRateService {

    private final RestTemplate restTemplate;
    private final String apiKey;

    public ExchangeRateService(Dotenv dotenv) {
        this.restTemplate = new RestTemplate();
        this.apiKey = dotenv.get("EXCHANGE_API_KEY");
    }

    @Cacheable(value = "exchangeRates", key = "#fromCurrency + '-' + #toCurrency")
    public BigDecimal getExchangeRate(String fromCurrency, String toCurrency) {
        String url = UriComponentsBuilder.fromUriString("https://api.exchangerate.host/convert")
                .queryParam("from", fromCurrency)
                .queryParam("to", toCurrency)
                .queryParam("amount", "1")
                .queryParam("access_key", apiKey)
                .toUriString();

        ExchangeRateResponse response = restTemplate.getForObject(url, ExchangeRateResponse.class);
        if (response != null && response.isSuccess()) {
            return response.getResult();
        } else {
            throw new RuntimeException("Failed to fetch exchange rate");
        }
    }
}
