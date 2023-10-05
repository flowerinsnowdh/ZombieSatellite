package online.flowerinsnow.zombiessatellite.config;

import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import online.flowerinsnow.zombiessatellite.ZombieSatellite;
import online.flowerinsnow.zombiessatellite.util.XMLDOMUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Config {
    private static File file;
    public static class TeammatesHealth {
        public static List<String> colourPatterns = new ArrayList<>(Arrays.asList(
                ">15:a",
                ">10:e",
                ">5:6",
                "default:c"
        ));
        public static String text = "%(colour)%(player_name) %(health)&7/%(max_health)&c❤ &a%(direction)";
    }

    public static class BossHealth {
        public static List<String> colourPatterns = new ArrayList<>(Arrays.asList(
                ">15:a",
                ">10:e",
                ">5:6",
                "default:c"
        ));
        public static String text = "%(colour)%(mob_name) %(health)&7/%(max_health)&c❤ &a%(direction)";
    }

    public static void init(File file) {
        Config.file = file;
    }

    public static void saveDefault() {
        Minecraft mc = Minecraft.getMinecraft();
        if (!file.exists()) {
            try (InputStream classpathConfig = ZombieSatellite.class.getResourceAsStream("/config.xml")) {
                //noinspection ResultOfMethodCallIgnored
                file.createNewFile();
                try (FileOutputStream fileConfig = new FileOutputStream(file)) {
                    byte[] bytes = new byte[1024];
                    int len;
                    //noinspection DataFlowIssue
                    while ((len = classpathConfig.read(bytes)) != -1) {
                        fileConfig.write(bytes, 0, len);
                    }
                }
            } catch (IOException e) {
                mc.crashed(CrashReport.makeCrashReport(e, "Failed to save default config."));
            }
        }
    }

    public static void load() {
        TeammatesHealth.colourPatterns.clear();
        BossHealth.colourPatterns.clear();

        Minecraft mc = Minecraft.getMinecraft();
        Document document;
        try {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            mc.crashed(CrashReport.makeCrashReport(e, "Failed to load config."));
            return;
        }
        Element root = document.getDocumentElement();
        loadRoot(root);
    }

    private static void loadRoot(Element root) {
        XMLDOMUtils.forEachChildNodes(root, Element.class, element -> {
            if ("teammatesHealth".equals(element.getTagName())) {
                loadTeammatesHealth(element);
            } else if ("bossHealth".equals(element.getTagName())) {
                loadBossHealth(element);
            }
        });
    }

    private static void loadTeammatesHealth(Element root) {
        XMLDOMUtils.forEachChildNodes(root, Element.class, element -> {
            switch (element.getTagName()) {
                case "colourPattern":
                    TeammatesHealth.colourPatterns.add(XMLDOMUtils.getString(element));
                    break;
                case "text":
                    TeammatesHealth.text = XMLDOMUtils.getString(element);
                    break;
            }
        });
    }

    private static void loadBossHealth(Element root) {
        XMLDOMUtils.forEachChildNodes(root, Element.class, element -> {
            switch (element.getTagName()) {
                case "colourPattern":
                    BossHealth.colourPatterns.add(XMLDOMUtils.getString(element));
                    break;
                case "text":
                    BossHealth.text = XMLDOMUtils.getString(element);
                    break;
            }
        });
    }
}
