package main_package;

public interface DbInterface {
    
    public DbInterface loadDatabase(JsonDatabase db);
    public void dumpDatabase();
}
