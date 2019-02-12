import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;


/**
 * A demo applet that shows how to use JGraphX to visualize JGraphT graphs. Applet based on
 * JGraphAdapterDemo.
 */
public class JGraphXAdapterDemo extends JApplet {

   /**
    * An alternative starting point for this demo, to also allow running this applet as an
    * application.
    *
    * @param args
    *       command line arguments
    */
   public static void main(String[] args) throws Exception {
      JGraphXAdapterDemo applet = new JGraphXAdapterDemo();
      applet.init();

      JFrame frame = new JFrame();
      final Container contentPane = frame.getContentPane();
      contentPane.add(applet);

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      autoSize(frame);

      createImage(frame.getWidth(), frame.getHeight(), applet);
   }

   private static void autoSize(final JFrame frame) {
      frame.pack();
      final int height = (int) (frame.getHeight() * 1.2);
      final int width = (int) (frame.getWidth() * 1.2);
      frame.setSize(width, height);
   }

   private static void createImage(final int width, final int height, final Component frame) throws IOException {
      frame.setVisible(true);
      BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      Graphics2D graphics2D = image.createGraphics();
      frame.printAll(graphics2D);
      ImageIO.write(image, "jpeg", new File("/mnt/b72c5a51-4de0-4cd2-800b-a9e788068220/tempFolder/images/jmemPractice.jpeg"));
   }

   @Override
   public void init() {
      ListenableGraph<String, DefaultEdge> g =
            new DefaultListenableGraph<>(new DefaultDirectedGraph<>(DefaultEdge.class));

      JGraphXAdapter<String, DefaultEdge> jgxAdapter = new JGraphXAdapter<>(g);

      mxGraphComponent component = new mxGraphComponent(jgxAdapter);
      final Container contentPane = getContentPane();
      contentPane.add(component);
      disableEdgeLabels(component);

      configureGraph(g);

      // positioning via jgraphx layouts
      mxHierarchicalLayout layout = new mxHierarchicalLayout(jgxAdapter);

      layout.execute(jgxAdapter.getDefaultParent());
   }

   private void disableEdgeLabels(final mxGraphComponent component) {
      final mxGraph graph = component.getGraph();
      graph.getStylesheet().getDefaultEdgeStyle().put(mxConstants.STYLE_NOLABEL, "1");
   }

   private void configureGraph(final ListenableGraph<String, DefaultEdge> graph) {
      registerRelation(graph, "customerRelations", "b2bservices");
      registerRelation(graph, "mobileOffice", "b2bservices");
      registerRelation(graph, "memoryCards", "accessories");
      registerRelation(graph, "gpsTrackers", "equipment");
      registerRelation(graph, "telephony", "b2bservices");
      registerRelation(graph, "smart", "b2ctariffs");
      registerRelation(graph, "internetSolutions", "b2bservices");
      registerRelation(graph, "m2m", "servicesclass");
      registerRelation(graph, "m2m", "FacetSearchCharacteristics");
      registerRelation(graph, "m2m", "servicesclass-service-entitlement");
      registerRelation(graph, "m2m", "servicesclass-service-usage-charges");
      registerRelation(graph, "DEVICE_MEMORY", "DEVICE_COLOR");
      registerRelation(graph, "main_services", "b2cservices");
      registerRelation(graph, "voka", "b2cservices");
      registerRelation(graph, "DEVICE_MEMORY_128", "DEVICE_MEMORY");
      registerRelation(graph, "international_calls", "b2cservices");
      registerRelation(graph, "routers", "equipment");
      registerRelation(graph, "soft", "shop");
      registerRelation(graph, "DEVICE_MEMORY_256", "DEVICE_MEMORY");
      registerRelation(graph, "tablets", "TopCharacteristics");
      registerRelation(graph, "tablets", "FacetSearchCharacteristics");
      registerRelation(graph, "tablets", "shop");
      registerRelation(graph, "modems", "equipment");
      registerRelation(graph, "kasko", "KaskoCharacteristics");
      registerRelation(graph, "kasko", "FacetSearchCharacteristics");
      registerRelation(graph, "headphones", "accessories");
      registerRelation(graph, "telemetryDataProcessing", "m2m");
      registerRelation(graph, "equipmentMonitoring", "m2m");
      registerRelation(graph, "cases", "accessories");
      registerRelation(graph, "for_calls", "b2ctariffs");
      registerRelation(graph, "DEVICE_SIZE_43", "DEVICE_SIZE");
      registerRelation(graph, "b2btariffs", "tariffsclass");
      registerRelation(graph, "b2btariffs", "FacetSearchCharacteristics");
      registerRelation(graph, "b2btariffs", "tariffsclass-tariff-promo-entitlement");
      registerRelation(graph, "b2btariffs", "tariffsclass-tariff-entitlement");
      registerRelation(graph, "b2btariffs", "tariffsclass-tariff-additional-features");
      registerRelation(graph, "b2btariffs", "tariffsclass-tariff-usage-charges");
      registerRelation(graph, "devices_vip", "devices");
      registerRelation(graph, "roaming", "b2cservices");
      registerRelation(graph, "DEVICE_SIZE_49", "DEVICE_SIZE");
      registerRelation(graph, "phones", "TopCharacteristics");
      registerRelation(graph, "phones", "FacetSearchCharacteristics");
      registerRelation(graph, "phones", "shop");
      registerRelation(graph, "buttonphones", "phones");
      registerRelation(graph, "DEVICE_MEMORY_16", "DEVICE_MEMORY");
      registerRelation(graph, "DEVICE_MEMORY_32", "DEVICE_MEMORY");
      registerRelation(graph, "DEVICE_MEMORY_64", "DEVICE_MEMORY");
      registerRelation(graph, "accessories", "TopCharacteristics");
      registerRelation(graph, "accessories", "AccessoryCharacteristics");
      registerRelation(graph, "accessories", "FacetSearchCharacteristics");
      registerRelation(graph, "accessories", "shop");
      registerRelation(graph, "smartphones", "phones");
      registerRelation(graph, "DEVICE_MEMORY_8", "DEVICE_MEMORY");
      registerRelation(graph, "b2cservices", "servicesclass");
      registerRelation(graph, "b2cservices", "FacetSearchCharacteristics");
      registerRelation(graph, "b2cservices", "servicesclass-service-entitlement");
      registerRelation(graph, "b2cservices", "servicesclass-service-usage-charges");
      registerRelation(graph, "DEVICE_COLOR_BLACK", "DEVICE_COLOR");
      registerRelation(graph, "internet", "b2ctariffs");
      registerRelation(graph, "equipment", "TopCharacteristics");
      registerRelation(graph, "equipment", "FacetSearchCharacteristics");
      registerRelation(graph, "equipment", "shop");
      registerRelation(graph, "b2ctariffs", "tariffsclass");
      registerRelation(graph, "b2ctariffs", "FacetSearchCharacteristics");
      registerRelation(graph, "b2ctariffs", "tariffsclass-tariff-promo-entitlement");
      registerRelation(graph, "b2ctariffs", "tariffsclass-tariff-entitlement");
      registerRelation(graph, "b2ctariffs", "tariffsclass-tariff-additional-features");
      registerRelation(graph, "b2ctariffs", "tariffsclass-tariff-usage-charges");
      registerRelation(graph, "tv", "TopCharacteristics");
      registerRelation(graph, "tv", "FacetSearchCharacteristics");
      registerRelation(graph, "tv", "shop");
      registerRelation(graph, "DEVICE_COLOR_RED", "DEVICE_COLOR");
      registerRelation(graph, "b2bservices", "servicesclass");
      registerRelation(graph, "b2bservices", "FacetSearchCharacteristics");
      registerRelation(graph, "b2bservices", "servicesclass-service-entitlement");
      registerRelation(graph, "b2bservices", "servicesclass-service-usage-charges");
      registerRelation(graph, "devices", "device-cpu");
      registerRelation(graph, "devices", "device-os");
      registerRelation(graph, "devices", "device-display");
      registerRelation(graph, "devices", "DeviceCharacteristics");
      registerRelation(graph, "devices", "TopCharacteristics");
      registerRelation(graph, "devices", "shop");

   }

   private void registerRelation(final ListenableGraph<String, DefaultEdge> graph, final String first, final String second) {
      graph.addVertex(first);
      graph.addVertex(second);
      graph.addEdge(first, second);
   }
}