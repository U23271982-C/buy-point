package Backend.Reporte;

import Backend.Utilidad.exportar.Exportador;
import org.apache.poi.ss.usermodel.Row;


import java.util.List;

public class ReporteXLSX extends ReportePlantilla {
    Row filaEncabezado;

    public ReporteXLSX(String tituloHoja, String tituloLibro, String direccionGuardado, List<Object[]> datos) {
        super(tituloHoja, "excel", datos);

        super.direccionGuardado = String.format("%s%s%s", direccionGuardado, tituloLibro, super.extensionDocumento);
        crearReporte();
    }

    private void formatearEncabezadoHojaCalculo() {
        String[] e = super.encabezado;

        this.filaEncabezado = super.hoja.createRow(0);

        // llenamos las celadas del encabezado
        for (int i = 0; i < e.length; i++) {
            this.filaEncabezado.createCell(i).setCellValue(e[i]);
        }
    }

    private void formatearCuerpoHojaCalculo() {
        // formatear el cuerpo del reporte
        int filaComienzoCuerpo = 1;
        for (Object[] filaCuerpo : super.contenido) {
            Row fila = super.hoja.createRow(filaComienzoCuerpo++);
            for (int i = 0; i < filaCuerpo.length; i++) {
                fila.createCell(i).setCellValue(filaCuerpo[i].toString());
            }
        }
    }

    private void crearReporte() {
        formatearEncabezadoHojaCalculo();
        formatearCuerpoHojaCalculo();
    }

    @Override
    public void exportarReporte(){
        Exportador exportador = super.exportadorDocumento;
        try {
            crearReporte();
            exportador.exportar( super.workbook , super.direccionGuardado);
        } catch (Exception e) {
            //NotificacionUtil.mostrarError(e);
        }

    }
}
