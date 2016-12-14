package com.jmrodrigg.model.CJT;

/**
 * Author: jrodriguezg
 * Date: 8/4/16
 */
public class CloudJobTicket {
    public String version;
    public PrintTicket print;

    private CloudJobTicket(CloudJobTicketBuilder builder) {
        this.version = builder.version;
        this.print = builder.print;
    }

    public static class CloudJobTicketBuilder {
        private String version;
        private PrintTicket print;

        public CloudJobTicketBuilder version(String version) {
            this.version = version;
            return this;
        }

        public CloudJobTicketBuilder printTicket(PrintTicket print) {
            this.print = print;
            return this;
        }

        public CloudJobTicket build() {
            return new CloudJobTicket(this);
        }
    }

}
