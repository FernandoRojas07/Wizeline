package com.wizeline.singleton;

public class Client {

	public static void main(String[] args) {

		DatabaseConfig databaseConfig = DatabaseConfig.getInstancia("MySQL");
		databaseConfig.conectar();

		DatabaseConfig databaseConfig2 = DatabaseConfig.getInstancia("Oracle");
		databaseConfig2.conectar();
	}
}
