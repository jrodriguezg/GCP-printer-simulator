package com.jmrodrigg.model.CJT;

import java.util.List;

/**
 * Author: jrodriguezg
 * Date: 8/4/16
 */
public class PrintTicket {
    public final List<Vendor> vendor_ticket_item;
    public final Color color;
    public final Duplex duplex;
    public final PageOrientation page_orientation;
    public final Copies copies;
    public final Margins margins;
    public final Dpi dpi;
    public final FitToPage fit_to_page;
    public final PageRange page_range;
    public final MediaSize media_size;
    public final Collate collate;
    public final ReverseOrder reverse_order;


    // TODO: Implement Builder pattern.
}
