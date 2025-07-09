package dev.ramindu.nexamarket.swing;

import java.awt.event.MouseEvent;

/**
 *
 * @author RAVEN
 */
public interface DynamicCellListener {

    public void scrollChanged(boolean scrollNext);

    public void mouseSelected(MouseEvent mouse);
}
