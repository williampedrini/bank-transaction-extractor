# Bank Transaction Extractor

This project is a library responsible performing extractions of bank transactions for users, based on their credentials.

## RUNNING TESTS

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### PREREQUISITES

What things you need to install the application and how to install them:

```
Java 11
Maven 3.6.3
```
#### INSTALLING

Install the tool for managing parallel versions of multiple Software Development Kits [SDK MAN](https://sdkman.io/install).

##### JAVA

Execute the following command:

```
sdk install java 11.0.8-amzn
```

##### MAVEN

Execute the following command:

```
sdk install maven 3.6.3
```

### RUNNING

Currently, there are only tests related to a portuguese bank named 'Caixa Geral de Depósitos'. 

Run the application with the following command inside of the [root folder](.):

```
 clean install -Dusername=<cgd_bank_username> -Dpassword=<cgd_bank_password> -DloginPageUrl=<cgd_bank_login>
```