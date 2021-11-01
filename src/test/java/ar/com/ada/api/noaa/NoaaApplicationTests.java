package ar.com.ada.api.noaa;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ar.com.ada.api.noaa.services.BoyaService;
import ar.com.ada.api.noaa.entities.Boya;

@SpringBootTest
class NoaaApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	BoyaService boyaService;

	@Test
	void validarLatitudYLongitud() {

		Double latitud1 = -200.00;
		Double latitud2 = 10.00;
		Double longitud1 = -200.00;
		Double longitud2 = 10.00;

		Boya boya = new Boya();
		boya.setLatitudInstalacion(latitud2);
		boya.setLongitudInstalacion(longitud2);

		Boya boya2 = new Boya();
		boya2.setLatitudInstalacion(latitud1);
		boya2.setLongitudInstalacion(longitud1);

		assertTrue(boyaService.validarLatitudYLongitud(boya));
		assertFalse(boyaService.validarLatitudYLongitud(boya2));

	}
}
