package main_package.builders;

import main_package.Database;

public class DatabaseInterfaceBuilder {
    private static final String path = "test/candidates_test.json";

    private final Database _dbi;
    
    public DatabaseInterfaceBuilder() {
        _dbi = new Database(path);
    }
    
    public DatabaseInterfaceBuilder withPath(String path){
        _dbi.setPath(path); 
        return this;
    }
    
    public Database build() {
        return _dbi;
    }
    
    
}
