package main_package.builders;

import main_package.DatabaseInterface;

public class DatabaseInterfaceBuilder {
    private static final String path = "test/candidates_test.json";

    private final DatabaseInterface _dbi;
    
    public DatabaseInterfaceBuilder() {
        _dbi = new DatabaseInterface(path);
    }
    
    public DatabaseInterfaceBuilder withPath(String path){
        _dbi.setPath(path); 
        return this;
    }
    
    public DatabaseInterface build() {
        return _dbi;
    }
    
    
}
