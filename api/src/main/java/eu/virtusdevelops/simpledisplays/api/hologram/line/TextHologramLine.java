/**
 *
 * Copyright (C) NeutralPlasma
 *
 */
package eu.virtusdevelops.simpledisplays.api.hologram.line;
import javax.annotation.Nullable;


/**
 *
 * @since 1
 */
public interface TextHologramLine extends HologramLine {

    /**
     * Returns the currently displayed text.
     *
     * @return the current text
     * @since 1
     */
    @Nullable
    String getText();

    /**
     * Sets the displayed text.
     *
     * @param text the new text
     * @since 1
     */
    void setText(@Nullable String text);


}
