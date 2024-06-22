package ir.ac.kntu;

import ir.ac.kntu.faribank.FariBank;
import ir.ac.kntu.util.ScannerWrapper;

import java.io.IOException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws ParseException, IOException {
        FariBank fariBank = new FariBank();
        fariBank.start();
        ScannerWrapper.getInstance().close();
    }

}
