package online.flowerinsnow.zombiessatellite.util;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import java.util.function.Consumer;

public abstract class XMLDOMUtils {
    private XMLDOMUtils() {
    }

    public static <T> void forEachChildNodes(Element element, Class<T> typeRequired, Consumer<T> action) {
        NodeList nodeList = element.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (typeRequired.isInstance(node)) {
                action.accept(typeRequired.cast(node));
            }
        }
    }

    public static String getElementText(Element element) {
        Text text = (Text) element.getChildNodes().item(0);
        return text.getNodeValue();
    }
}
