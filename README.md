# CSC510_Group25_Project1

## Steps for Running Backend
You can directly use Eclipse or Intellj to open up the project and run SpringSocialApplication.java file. You can also the start code from terminal.
Go to spring-social folder and run 
```bash
	mvn spring-boot:run
```
You also need to setup mysql. Make sure that mysql is installed in your local machine. After that run the following command 

```bash
mysql -u root -p < create_table.sql
```

This command will create the database inventory_tracker and inside the database, users table will be formed. 

