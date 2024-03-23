package logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class LoggerUtility {

    //loger care vegheaza asupra tuturor executiilor mele si va sti sa manageuiasca logurile in directaia in care am  nevoie
    private static final Logger logger = LogManager.getLogger();

    //ce tre sa fac pt aceste loguri:
    //vreau sa fac o metoda care ma asigura ca porneste un test
    public static  void startTestCase(String testName) {
        logger.info("======== Execution started: " + testName + " ========");
    }

    //metoda care se asigura ca a terminat un test
    public static  void endTestCase(String testName) {
        logger.info("======== Execution finished: " + testName + " ========");
    }

    //metoda care se asigura ca imi adauga un entry ca log
    public static  void infoLog(String message) {
        logger.info(message);

    }

    //metoda care se asigura ca logheaza o eroare
    public static  void errorLog(String message) {
        logger.error(message);

    }






}
