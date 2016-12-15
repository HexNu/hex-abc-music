package nu.hex.abc.music.service.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;

/**
 * Created 2016-dec-15
 *
 * @author hl
 */
class PdfPrinter implements Printer<Void> {

    private static boolean jobRunning = false;
    private final File pdfFile;

    public PdfPrinter(File pdfFile) {
        this.pdfFile = pdfFile;
    }

    @Override
    public Void print() {
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(pdfFile));
            DocFlavor flavor = DocFlavor.INPUT_STREAM.PDF;
            PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
            PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
            PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, attributes);
            PrintService service = ServiceUI.printDialog(null, 200, 200, printService, defaultService, flavor, attributes);
            if (service != null) {
                DocPrintJob printJob = service.createPrintJob();
                printJob.addPrintJobListener(new JobCompleteMonitor());
                Doc doc = new SimpleDoc(inputStream, flavor, null);
                jobRunning = true;
                printJob.print(doc, null);
            }
        } catch (FileNotFoundException | PrintException ex) {
            Logger.getLogger(PdfPrinter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(PdfPrinter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    private static class JobCompleteMonitor extends PrintJobAdapter {

        @Override
        public void printJobCompleted(PrintJobEvent pje) {
            Logger.getLogger(PdfPrinter.class.getName()).log(Level.INFO, "Printing complete");
            jobRunning = false;
        }
    }

}
