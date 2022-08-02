
package com.tienda.controller;

import com.tienda.enums.TipoReporteEnum;
import com.tienda.model.ReportePersonasDTO;
import com.tienda.service.api.ReportePersonasServiceAPI;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author David
 */

@RestController
@RequestMapping("/report")
public class ReportePersonasController {
    
    // Se implementa nuestro API de Personas
    @Autowired
	private ReportePersonasServiceAPI reportePersonasServiceAPI;
         
    // Ruta
        @GetMapping(path = "/personas/download")
        
        // Este metodo retorna un response entity de resource
	public ResponseEntity<Resource> download(@RequestParam Map<String, Object> params)
			throws JRException, IOException, SQLException {
		ReportePersonasDTO dto = reportePersonasServiceAPI.obtenerReportePersonas(params);

		InputStreamResource streamResource = new InputStreamResource(dto.getStream());
		MediaType mediaType = null;
                // Si es un Excel se hace esto:
		if (params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name())) {
			mediaType = MediaType.APPLICATION_OCTET_STREAM;
		} else {
			mediaType = MediaType.APPLICATION_PDF;
		} 
                
                // Aqui se retorna la respuesta 
                // Se asigna el content disposition
		return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + dto.getFileName() + "\"")
				.contentLength(dto.getLength()).contentType(mediaType).body(streamResource);
	}
}
