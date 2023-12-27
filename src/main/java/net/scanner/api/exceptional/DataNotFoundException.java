package net.scanner.api.exceptional;

public class DataNotFoundException  extends RuntimeException{

    public DataNotFoundException(String msg){
        super(msg);
    }
}
