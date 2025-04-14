package com.tomi.fexapp.service;

import com.tomi.fexapp.dto.ExchangeRateResponse;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ExchangeRateServiceTest {

    @Mock
    private Dotenv dotenv;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ExchangeRateService exchangeRateService;

    @BeforeEach
    public void setUp() {
        when(dotenv.get("EXCHANGE_API_KEY")).thenReturn("dummy-api-key");
        ReflectionTestUtils.setField(exchangeRateService, "restTemplate", restTemplate);
    }

    @Test
    public void testGetExchangeRate_success() {
        String fromCurrency = "USD";
        String toCurrency = "EUR";
        BigDecimal expectedRate = BigDecimal.valueOf(0.85);

        ExchangeRateResponse fakeResponse = new ExchangeRateResponse();
        fakeResponse.setSuccess(true);
        fakeResponse.setResult(expectedRate);

        when(restTemplate.getForObject(anyString(), Mockito.eq(ExchangeRateResponse.class)))
                .thenReturn(fakeResponse);

        BigDecimal actualRate = exchangeRateService.getExchangeRate(fromCurrency, toCurrency);
        assertEquals(expectedRate, actualRate, "The exchange rate should match the expected value.");
    }

    @Test
    public void testGetExchangeRate_failure() {
        String fromCurrency = "USD";
        String toCurrency = "EUR";

        when(restTemplate.getForObject(anyString(), Mockito.eq(ExchangeRateResponse.class)))
                .thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () ->
            exchangeRateService.getExchangeRate(fromCurrency, toCurrency));

        assertTrue(exception.getMessage().contains("Failed to fetch exchange rate"));
    }
}
