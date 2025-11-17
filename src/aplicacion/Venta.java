
package aplicacion;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import persistencia.VentaDAO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.awt.print.PrinterJob;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.Loader;


public class Venta {
    
    private int id;
    private float total;
    private LocalDate fecha;
    private Cliente cliente;
    private List<DetalleVenta> detalle;

    public Venta(float total, List<DetalleVenta> detalles) {
        this.total = total;
        this.detalle = detalles;
    }

    public Venta(int id, float total, LocalDate fecha, Cliente cliente, List<DetalleVenta> detalle) {
        this.id = id;
        this.total = total;
        this.fecha = fecha;
        this.cliente = cliente;
        this.detalle = detalle;
    }

    public Venta() {
        
    }
    
    
    public List<Venta> CargarVentasPeriodo(LocalDate fechaInicio,LocalDate fechaFin) throws ClassNotFoundException, SQLException
    {
        VentaDAO vent = new VentaDAO();
        return vent.CargarVentasPeriodo(fechaInicio,fechaFin);
    }
    
    public byte[] GenerarPDF()
    {
        ByteArrayOutputStream pdf = new ByteArrayOutputStream();
        Document document = new Document();
    try {
        
        PdfWriter.getInstance(document, pdf);
        document.open();

        // ----- TITLE -----
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Paragraph title = new Paragraph("Comprobante de Venta", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        document.add(new Paragraph(" "));
        document.add(new LineSeparator());
        document.add(new Paragraph(" "));

        // ----- CLIENT -----
        document.add(new Paragraph("Cliente: " + cliente.getNombre()+" "+cliente.getApellido()));
         

        document.add(new Paragraph("Fecha: " + fecha.toString()));
        document.add(new Paragraph(" "));
        
        // ----- DETAIL TABLE -----
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1f, 3f, 2f, 2f});

        addHeaderCell(table, "Cant.");
        addHeaderCell(table, "Producto");
        addHeaderCell(table, "Precio");
        addHeaderCell(table, "Subtotal");

        for (DetalleVenta det : detalle) {
            table.addCell(String.valueOf(det.getCantidad()));
            table.addCell(det.getProducto().getNombre());
            table.addCell(String.format("$ %.2f", det.getPrecio()));
            table.addCell(String.format("$ %.2f", det.getSubtotal()));
        }

        document.add(table);

        document.add(new Paragraph(" "));
        document.add(new LineSeparator());
        document.add(new Paragraph(" "));

        // ----- TOTAL -----
        Font totalFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
        Paragraph totalParagraph = new Paragraph("TOTAL: $ " + total, totalFont);
        totalParagraph.setAlignment(Element.ALIGN_RIGHT);
        document.add(totalParagraph);

        document.close();
        

    } catch (Exception e) {
        e.printStackTrace();
    }
        return pdf.toByteArray();
    
        
    }
    
    private void addHeaderCell(PdfPTable table, String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetalleVenta> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetalleVenta> detalle) {
        this.detalle = detalle;
    }


    
    
    public void agregarVenta () throws ClassNotFoundException, SQLException{
        fecha = LocalDate.now();
        VentaDAO ven = new VentaDAO();
        ven.agregarVenta(this);
    }

    public String guardarPDF() throws FileNotFoundException, IOException {
        byte [] pdf = GenerarPDF();
        File file = new File( "venta_" + id + ".pdf");
         try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(pdf);
        }
        return file.getAbsolutePath();
    }

    public void imprimirPDF() {
           try {
                byte[] pdf = GenerarPDF();
                PDDocument doc = Loader.loadPDF(pdf);
                PrinterJob job = PrinterJob.getPrinterJob();
                job.setPageable(new PDFPageable(doc));
                if (job.printDialog()) {
                    job.print();
                }
                doc.close();
            } 
           catch (Exception e) {
                e.printStackTrace();  
            }   
    }
    
    
}
