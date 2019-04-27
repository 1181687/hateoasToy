package pt.ipp.isep.dei.project.io.ui.logger;

import java.io.IOException;
import java.util.logging.*;

public class MyLogger {
    static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;

    static private FileHandler fileHTML;
    static private Formatter formatterHTML;

    static public void setup() throws IOException {

        // get the global logger to configure it
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        // suppress the logging output to the console
        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();

        for (Handler handler : handlers) {

            rootLogger.removeHandler(handler);
        }

        //logger.setLevel(Level.INFO);
        fileTxt = new FileHandler("C:\\Users\\Diana\\IdeaProjects\\project_g3\\log\\ProjectLog.txt");
        fileHTML = new FileHandler("C:\\Users\\Diana\\IdeaProjects\\project_g3\\log\\Logging.html");

        // create a TXT formatter
        MyFormatter formatter = new MyFormatter();
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        fileTxt.setFormatter(formatter);
        logger.addHandler(fileTxt);

        // create an HTML formatter
        formatterHTML = new MyHtmlFormatter();
        fileHTML.setFormatter(formatterHTML);
        logger.addHandler(fileHTML);
    }
}
