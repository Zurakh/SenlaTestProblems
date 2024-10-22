package org.example.javazero_2.commands;

public class Help implements Command{
    @Override
    public void execute() {
        System.out.println("help -- print this info");
        System.out.println("convert <value> <from> <to> -- converts value of one currency to another");
        System.out.println("all <amount> <current currency> -- converts to every currency");
        System.out.println("available -- prints all available currencies");
    }
}
