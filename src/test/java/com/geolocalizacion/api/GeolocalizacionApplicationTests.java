package com.geolocalizacion.api;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.geolocalizacion.api.google.DomicilioCoordenadas;
import com.geolocalizacion.api.opencage.PorDomicilio;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class GeolocalizacionApplicationTests {
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(GeolocalizacionApplicationTests.class);
	
	@Autowired
	PorDomicilio domicilio;
	
	@Autowired
	DomicilioCoordenadas coor;
	
	@Test
	public void contextLoads() {
		localizacion();
	}
	
	public void localizacion() {
		Map<String, String> ubicacion = new HashMap<>();
		ubicacion.put("calle", "Insurgentes sur");
		ubicacion.put("numero", "3579");
		ubicacion.put("codigoPostal", "14000");
		ubicacion.put("localidad", "Tlalpan");
		ubicacion.put("estado", "Ciudad de Mexico");
		
//		Map<String, String> respuesta = domicilio.ubicacionCoordenadas(ubicacion);
		Map<String, Double> respuesta = coor.getCoordenadas(ubicacion);
		log.info("Valores obtenidos: {}",respuesta);
	}

}
