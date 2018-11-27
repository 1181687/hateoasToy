package Sprint_0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalizacaoTest {

    @Test
    public void testarDistanciaLinearDuasAreas() {
        // arrange

        Localizacao local1 = new Localizacao(41.1496, -8.6109, 0);
        Localizacao local2 = new Localizacao(32.6333, -16.9, 0);
        double expectedResult = 11.8843;

        // act
        double resultado = local1.distanciaDuasLocalizacoes (local1, local2);

                //assert
        assertEquals (expectedResult,resultado, 0.0001);
    }
}
