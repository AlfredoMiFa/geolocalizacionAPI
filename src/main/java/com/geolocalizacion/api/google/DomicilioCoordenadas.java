package com.geolocalizacion.api.google;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.geolocalizacion.api.google.beans.Container;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DomicilioCoordenadas {
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DomicilioCoordenadas.class);
	private static final String API_KEY = "AIzaSyBNvufnrlmqpenuoEYttAgqSTAFMhojaUs";
	private static final String PAIS = "mx";
	private static final String URL = "https://maps.googleapis.com/maps/api/geocode/json?address=";
	
	public Map<String, Double> getCoordenadas(Map<String, String> ubicacion) {
		Map<String, Double> retorno = new HashMap<>();
		try {
			log.info("Datos que llegan: {}",ubicacion);
			StringBuilder sb = new StringBuilder();
			sb.append(URL);
			sb.append(ubicacion.get("numero")).append(" ");
			sb.append(ubicacion.get("calle"));
			sb.append(", ");
			sb.append(ubicacion.get("codigoPostal"));
			sb.append(", ");
			sb.append(ubicacion.get("localidad"));
			sb.append(", ").append(ubicacion.get("estado"));
			sb.append(", Mexico").append(", ");
			sb.append("CA&key=").append(API_KEY);
			String informacion = sb.toString().replaceAll(" ", "\\+");
			log.info(informacion);
			RestTemplate restTemplate = new RestTemplate();
			Container result = restTemplate.getForObject(sb.toString(), Container.class);
//			ResponseEntity<String> response = restTemplate.getForEntity(sb.toString(), String.class);
//			log.info("Respuesta: {}",response.getBody());
			if(result != null) {
				if(compruebaLista(result.getResults()) && result.getResults().stream().anyMatch(t->t.getFormatted_address().contains("Mexico"))) {
					log.info("Respuesta: {}",result.toString());
					retorno.put("longitud", result.getResults().stream().filter(t-> t.getGeometry()!=null).findFirst().get().getGeometry().getLocation().getLng());
					retorno.put("latitud", result.getResults().stream().filter(t-> t.getGeometry()!=null).findFirst().get().getGeometry().getLocation().getLat());
				}
			}
		}catch(Exception ex) {
			log.info("Exception obtenida: {}",ex);
		}
		return retorno;
	}
	
	public boolean compruebaLista(List lista) {
		if(lista == null)
			return false;
		if(lista.isEmpty())
			return false;
		return true;
	}
	
}
