
package com.tienda.service.api;

import com.tienda.model.ReportePersonasDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author David
 */
public interface ReportePersonasServiceAPI {
    
    ReportePersonasDTO obtenerReportePersonas(Map<String, Object> params) throws JRException, IOException, SQLException;
}
