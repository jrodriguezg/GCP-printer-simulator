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


    private PrintTicket(PrintTicketBuilder builder) {
        this.vendor_ticket_item = builder.vendor_ticket_item;
        this.color = builder.color;
        this.duplex = builder.duplex;
        this.page_orientation = builder.page_orientation;
        this.copies = builder.copies;
        this.margins = builder.margins;
        this.dpi = builder.dpi;
        this.fit_to_page = builder.fit_to_page;
        this.page_range = builder.page_range;
        this.media_size = builder.media_size;
        this.collate = builder.collate;
        this.reverse_order = builder.reverse_order;
    }

    public static class PrintTicketBuilder {
        private List<Vendor> vendor_ticket_item;
        private Color color;
        private Duplex duplex;
        private PageOrientation page_orientation;
        private Copies copies;
        private Margins margins;
        private Dpi dpi;
        private FitToPage fit_to_page;
        private PageRange page_range;
        private MediaSize media_size;
        private Collate collate;
        private ReverseOrder reverse_order;

        public PrintTicketBuilder vendors(List<Vendor> vendor_ticket_item) {
            this.vendor_ticket_item = vendor_ticket_item;
            return this;
        }

        public PrintTicketBuilder color(Color color) {
            this.color = color;
            return this;
        }

        public PrintTicketBuilder duplex(Duplex duplex) {
            this.duplex = duplex;
            return this;
        }

        public PrintTicketBuilder pageOrientation(PageOrientation page_orientation) {
            this.page_orientation = page_orientation;
            return this;
        }

        public PrintTicketBuilder copies(Copies copies) {
            this.copies = copies;
            return this;
        }

        public PrintTicketBuilder margins(Margins margins) {
            this.margins = margins;
            return this;
        }

        public PrintTicketBuilder dpi(Dpi dpi) {
            this.dpi = dpi;
            return this;
        }

        public PrintTicketBuilder fitToPage(FitToPage fit_to_page) {
            this.fit_to_page = fit_to_page;
            return this;
        }

        public PrintTicketBuilder pageRange(PageRange page_range) {
            this.page_range = page_range;
            return this;
        }

        public PrintTicketBuilder mediaSize(MediaSize media_size) {
            this.media_size = media_size;
            return this;
        }

        public PrintTicketBuilder collate(Collate collate) {
            this.collate = collate;
            return this;
        }

        public PrintTicketBuilder reverseOrder(ReverseOrder reverse_order) {
            this.reverse_order = reverse_order;
            return this;
        }

        public PrintTicket build() {
            return new PrintTicket(this);
        }
    }
}
