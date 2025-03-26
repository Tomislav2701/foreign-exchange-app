package com.tomi.fexapp.service;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class ExchangeRateService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final Dotenv dotenv;

    public ExchangeRateService(Dotenv dotenv) {
        this.dotenv = dotenv;
    }

    public BigDecimal getExchangeRate(String fromCurrency, String toCurrency) {
        String apiKey = dotenv.get("EXCHANGE_API_KEY");
        String url = String.format(
            "https://api.exchangerate.host/convert?from=%s&to=%s&amount=1&access_key=%s",
            fromCurrency, toCurrency, apiKey
        );
        ExchangeRateResponse response = restTemplate.getForObject(url, ExchangeRateResponse.class);
        if (response != null && response.isSuccess()) {
            return response.getResult();
        } else {
            throw new RuntimeException("Failed to fetch exchange rate");
        }
    }

    public static class ExchangeRateResponse {
        private boolean success;
        private BigDecimal result;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public BigDecimal getResult() {
            return result;
        }

        public void setResult(BigDecimal result) {
            this.result = result;
        }
    }
}
