package com.geolocalizacion.api.opencage;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.byteowls.jopencage.model.JOpenCageStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PorDomicilio {
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PorDomicilio.class);
	
	private static final String API_KEY = "AIzaSyBNvufnrlmqpenuoEYttAgqSTAFMhojaUs";
	private static final String PAIS = "mx";
	private static final double southWestLng = -117.12776;
	private static final double southWestLat = 14.5388286402;
	private static final double northEastLng = -86.811982388;
	private static final double northEastLat = 32.72083;
	
	public Map<String, String> ubicacionCoordenadas(Map<String, String> ubicacion) {
		Map<String, String> coordenadas = new HashMap<>();
		try {
			log.info("Valores que llegan: {}", ubicacion);
			StringBuilder sb = new StringBuilder();
			sb.append(ubicacion.get("calle"));
			sb.append(" ").append(ubicacion.get("numero"));
			sb.append(", ");
			sb.append(ubicacion.get("codigoPostal"));
			sb.append(", ");
			sb.append(ubicacion.get("localidad"));
			sb.append(", ").append(ubicacion.get("estado"));
			sb.append(", Mexico");
			JOpenCageGeocoder geo = new JOpenCageGeocoder(API_KEY);
			JOpenCageForwardRequest request = new JOpenCageForwardRequest(sb.toString());
			request.setRestrictToCountryCode(PAIS);
			//request.setBounds(southWestLng, southWestLat, northEastLng, northEastLat);
			JOpenCageResponse response = geo.forward(request);
			JOpenCageStatus estatus = response.getStatus();
			log.info("Estatus obtenido: {}",estatus.getCode());
			log.info("Mensaje: {}",estatus.getMessage());
			JOpenCageLatLng result = response.getFirstPosition();
			double latitud = result.getLat();
			double longitud = result.getLng();
			log.info("Coordenadas obtenidas: longitud: {}, latitud: {}",longitud,latitud);
		}catch(Exception ex) {
			
		}
		return null;
	}
	
}
