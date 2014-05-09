/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.util;

import javafx.scene.Node;
import javafx.scene.control.Tooltip;


/**
 *
 * @author Clicia
 */
public class Tooltips {
    private static final String TOOLTIP = "tooltips-tooltip";
    
    public static Tooltip setTooltip(Node node) {
        return (Tooltip)node.getProperties().get(TOOLTIP);
    }
    
    public static void setTooltip(Node node, Tooltip t) {
        Tooltip.install(node, t);
        node.getProperties().put(TOOLTIP,t);
    }
}
