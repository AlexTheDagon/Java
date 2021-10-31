package foodDelivery.dataLayer;

import java.io.IOException;

/**
 * The type File writer.
 */
public class FileWriter {

    /**
     * Write file.
     *
     * @param reportName    the report name
     * @param reportContent the report content
     */
    public void writeFile(String reportName, String reportContent) {
        try {
            java.io.FileWriter billGatesDivorce = new java.io.FileWriter(reportName);
            billGatesDivorce.write(reportContent);
            billGatesDivorce.flush();
            billGatesDivorce.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
