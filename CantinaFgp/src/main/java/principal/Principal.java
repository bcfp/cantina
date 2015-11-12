package principal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ui.PrincipalView;
import daoimpl.ConnectionFactory;

public class Principal {

	public static void main(String[] args) {
		
		/*
		try {
			
			new LoginView().abrirJanela();
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		*/
		
		new PrincipalView();
		
	}
	
}
