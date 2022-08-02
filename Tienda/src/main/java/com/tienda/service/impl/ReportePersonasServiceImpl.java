package com.tienda.service.impl;

import com.tienda.commons.JasperReportManager;
import com.tienda.enums.TipoReporteEnum;
import com.tienda.model.ReportePersonasDTO;
import com.tienda.service.api.ReportePersonasServiceAPI;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author David
 */
@Service
public class ReportePersonasServiceImpl implements ReportePersonasServiceAPI {

    @Autowired
    private JasperReportManager reportManager;

    @Autowired
    private DataSource dataSource;

    @Override
    public ReportePersonasDTO obtenerReportePersonas(Map<String, Object> params) throws JRException, IOException, SQLException {
        // Este es el nombre del archivo 
        String fileName = "reporte_de_personas";
        ReportePersonasDTO dto = new ReportePersonasDTO();
        String extension = params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name()) ? ".xlsx"
                : ".pdf";
        dto.setFileName(fileName + extension);

        // Aqui retornamos el metodo que esta en report manager
        ByteArrayOutputStream stream = reportManager.export(fileName, params.get("tipo").toString(), params,
                dataSource.getConnection());

        // Aqui creamos un array de bytes que convertimos nuestro output string 
        // en un input String.
        byte[] bs = stream.toByteArray();
        dto.setStream(new ByteArrayInputStream(bs));

        // Aqui le pasamos el lenght de los bytes
        dto.setLength(bs.length);

        return dto;
    }

}
