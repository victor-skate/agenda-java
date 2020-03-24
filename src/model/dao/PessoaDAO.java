package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.Pessoa;

public class PessoaDAO {
	public static final String URL = "jdbc:postgresql://localhost:5432/agenda";
	public static final String USUARIO = "postgres";
	public static final String SENHA = "postgresql";

	private Connection con;
	private PreparedStatement selecionarPessoas;
	private PreparedStatement selecionarPessoasPorSobrenome;
	private PreparedStatement inserirPessoa;
	private PreparedStatement deletarPessoa;

	public PessoaDAO() {
		try{
			con = DriverManager.getConnection(URL,USUARIO,SENHA);
			selecionarPessoas = con.prepareStatement("SELECT* FROM pessoa");
			selecionarPessoasPorSobrenome = con.prepareStatement("SELECT*FROM pessoa WHERE sobrenome=?");
			inserirPessoa = con.prepareStatement("INSERT INTO pessoa"+"(nome,sobrenome,email,telefone)"+
			"values(?,?,?,?)");
			deletarPessoa = con.prepareStatement("DELETE FROM pessoa WHERE id=?");		
		}catch(SQLException sql){
			sql.printStackTrace();
			System.exit(1);//informa que o sistema não foi executado corretamente;
			
		}
	}
	
	public List<Pessoa> getTodasPessoas(){
		ResultSet rs=null;
		List<Pessoa> resultado= null;
		
		try{
		rs = selecionarPessoas.executeQuery();
		resultado = new ArrayList<Pessoa>();
		while(rs.next()){
			resultado.add(new Pessoa(
				rs.getInt("id"),
				rs.getString("nome"),
				rs.getString("sobrenome"),
				rs.getString("email"),
				rs.getString("telefone")
				)
			);
			
			}
		}catch(SQLException sql){
			sql.printStackTrace();
			
		}finally{
			try{
				rs.close();
			}catch(SQLException sql){
				sql.printStackTrace();
				close();
			}
		}
	return resultado;
	}
	
	public List<Pessoa> consultarPessoasPorSobrenome(String sobrenome){
		ResultSet rs = null;
		List<Pessoa> resultado = null;
		
		try{
			selecionarPessoasPorSobrenome.setString(1,sobrenome);
			rs = selecionarPessoasPorSobrenome.executeQuery();
			resultado = new ArrayList<Pessoa>();
			
			selecionarPessoasPorSobrenome.setString(1,sobrenome);
			
			while(rs.next()){
				resultado.add (new Pessoa(
					rs.getInt("id"),
					rs.getString("nome"),
					rs.getString("sobrenome"),
					rs.getString("email"),
					rs.getString("telefone")
						
					)
				);
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException sql) {
				sql.printStackTrace();
				close();
			}
		}
		return resultado;
	}
	
		public int adicionarPessoa(Pessoa p){
			int resultado= 0;
			
			try{
				inserirPessoa.setString(1,p.getNome());
				inserirPessoa.setString(2, p.getSobrenome());
				inserirPessoa.setString(3,p.getEmail());
				inserirPessoa.setString(4,p.getTelefone());
			resultado=inserirPessoa.executeUpdate();
			
			}catch(SQLException sql){
				sql.printStackTrace();
			}
			return resultado;
		}
	
		public int deletar(int id){
			int resultado = 0;
			
			try{
				deletarPessoa.setInt(1,id);
				deletarPessoa.executeUpdate();
			}catch(SQLException sql){
				sql.printStackTrace();
			}
		return resultado;
		}

		private void close() {
			try{
				con.close();
				
			}catch(SQLException sql){
				sql.printStackTrace();
				close();
			}
		}
	}