package dev.ramindu.nexamarket.utils;

import java.awt.event.MouseEvent;
import dev.ramindu.nexamarket.model.ModelDate;

/**
 *
 * @author Raven
 */
public interface CalendarSelectedListener {

    public void selected(MouseEvent evt, ModelDate date);
}
