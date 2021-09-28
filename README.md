# 86 No More, A Kitchen Intelligence Solution

![Build Status](https://github.com/CSC510-Group-25/CSC510_Group25_Project1/workflows/maven_build/badge.svg)
[![DOI](https://zenodo.org/badge/402155508.svg)](https://zenodo.org/badge/latestdoi/402155508)
[![Made with](https://img.shields.io/badge/Made%20with-Java-blue)](https://www.java.com/en/)
[![License: MIT](https://img.shields.io/badge/license-MIT-green)](https://github.com/elric97/CalBot/blob/master/LICENSE)
[![GitHub issues open](https://img.shields.io/github/issues/elric97/CalBot)](https://github.com/elric97/CalBot/issues)


## About:
As customers decide on what to order at a restaurant, there is no worse feeling than hearing your 
waiter tell them that their item of choice is no longer available. To prevent this issue,
we have created an inventory tracker that will be able to track all the quantities of ingredients but 
also have features that are beneficial to a restaurant. Our software will have is 
the ability to send notifications to the manager when quantities for certain items are running low.
In addition, our product will have an analytics page that will be able to display total sales, orders,
and amount of waste per month. This product will allow restaurants to become more profitable while also
being beneficial to the planet as there will be less waste generated in the restaurant industry.

### Watch this video to know more about our product:
https://user-images.githubusercontent.com/42051115/135004434-50d95b77-87a3-4c18-8d35-b680c63239b0.mp4

## Steps for Running Backend
You can directly use Eclipse or Intellj to open up the project and run SpringSocialApplication.java file. You can also the start code from terminal. You need to update sql username and password in resources/application.yml file.

Go to spring-social folder and run 
```bash
mvn spring-boot:run
```
You also need to setup mysql. Make sure that mysql is installed in your local machine. After that run the following command inside spring-social folder

```bash
mysql -u root -p < create_table.sql
```

This command will create the database inventory_tracker and inside the database, users table will be formed. 

For plotting the line graphs use below versions:
```bash
npm i react-chartjs-2@2.11.1
npm i chart.js@2.9.4

```


