package com.jmrodrigg.model.CJT;

/**
 * Author: jrodriguezg
 * Date: 8/4/16
 */
public class PrintTicket {
    public final List<VendorTicketItem> vendor_ticket_item;
    public final ColorTicketItem color;
    public final DuplexTicketItem duplex;
    public final PageOrientationTicketItem page_orientation;
    public final CopiesTicketItem copies;
    public final MarginsTicketItem margins;
    public final DpiTicketItem dpi;
    public final FitToPageTicketItem fit_to_page;
    public final PageRangeTicketItem page_range;
    public final MediaSizeTicketItem media_size;
    public final CollateTicketItem collate;
    public final ReverseOrderTicketItem reverse_order;


    // TODO: Implement Builder pattern.
}
