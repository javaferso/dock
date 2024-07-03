package servlet;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.DefaultValueDataset;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import logica.Controladora;
import org.jfree.chart.plot.dial.DialCap;
import org.jfree.chart.plot.dial.DialPlot;
import org.jfree.chart.plot.dial.DialPointer;
import org.jfree.chart.plot.dial.DialTextAnnotation;
import org.jfree.chart.plot.dial.DialValueIndicator;
import org.jfree.chart.plot.dial.StandardDialFrame;
import org.jfree.chart.plot.dial.StandardDialScale;

public class SvGraficos extends HttpServlet {
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipo = request.getParameter("tipo");
        response.setContentType("image/png");
        OutputStream out = response.getOutputStream();
        Controladora controladora = new Controladora();

        if ("tortaIp".equals(tipo)) {
            JFreeChart chart = crearGraficoTorta("Servidores de Tiendas Online", controladora.contarPorEstadoIp("online"), controladora.contarPorEstadoIp("offline"));
            ChartUtilities.writeChartAsPNG(out, chart, 400, 300);
        } else if ("tortaEnlace".equals(tipo)) {
            JFreeChart chart = crearGraficoTorta("Enlaces Online", controladora.contarPorEstadoEnlace("online"), controladora.contarPorEstadoEnlace("offline"));
            ChartUtilities.writeChartAsPNG(out, chart, 400, 300);
        } else if ("relojIp".equals(tipo)) {
            JFreeChart chart = crearGraficoReloj("Porcentaje Servidores de Tienda Online", calcularPorcentaje(controladora.contarPorEstadoIp("online"), controladora.contarPorEstadoIp("online") + controladora.contarPorEstadoIp("offline")));
            ChartUtilities.writeChartAsPNG(out, chart, 400, 300);
        } else if ("relojEnlace".equals(tipo)) {
            JFreeChart chart = crearGraficoReloj("Porcentaje Enlaces Online", calcularPorcentaje(controladora.contarPorEstadoEnlace("online"), controladora.contarPorEstadoEnlace("online") + controladora.contarPorEstadoEnlace("offline")));
            ChartUtilities.writeChartAsPNG(out, chart, 400, 300);
        }

        out.close();
    }

    private JFreeChart crearGraficoTorta(String titulo, long online, long offline) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Online", online);
        dataset.setValue("Offline", offline);

        JFreeChart chart = ChartFactory.createPieChart(titulo, dataset, true, true, false);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint((Comparable) "Online", Color.GREEN);
        plot.setSectionPaint((Comparable) "Offline", Color.RED);
        return chart;
    }

    private JFreeChart crearGraficoReloj(String titulo, double valor) {
        DefaultValueDataset dataset = new DefaultValueDataset(valor);
        DialPlot plot = new DialPlot(dataset);

        StandardDialFrame dialFrame = new StandardDialFrame();
        dialFrame.setForegroundPaint(Color.DARK_GRAY);
        dialFrame.setStroke(new BasicStroke(3.0f));
        plot.setDialFrame(dialFrame);

        plot.setBackgroundPaint(Color.WHITE);

        DialTextAnnotation annotation = new DialTextAnnotation(titulo);
        annotation.setFont(new Font("Dialog", Font.BOLD, 14));
        annotation.setRadius(0.7);
        plot.addLayer(annotation);

        DialValueIndicator dvi = new DialValueIndicator(0);
        dvi.setFont(new Font("Dialog", Font.PLAIN, 10));
        dvi.setRadius(0.6);
        plot.addLayer(dvi);

        StandardDialScale scale = new StandardDialScale(0, 100, -120, -300, 10, 4);
        scale.setMajorTickPaint(Color.BLACK);
        scale.setMinorTickPaint(Color.BLACK);
        plot.addScale(0, scale);
        plot.addPointer(new DialPointer.Pointer());

        DialCap cap = new DialCap();
        cap.setRadius(0.10);
        plot.setCap(cap);

        return new JFreeChart(plot);
    }

    private double calcularPorcentaje(long parte, long total) {
        return (total == 0) ? 0 : (double) parte / total * 100;
    }
}
