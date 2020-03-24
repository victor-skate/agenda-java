import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.dao.PessoaDAO;
import model.vo.Pessoa;

public class AgendaSys {
	
	static Scanner teclado = new Scanner(System.in);
		
	public static void adicionar(){

		PessoaDAO pessoaDAO = new PessoaDAO();
		
		Pessoa p = new Pessoa();
		System.out.println("digite o nome:");
		p.setNome(teclado.next());
		System.out.println("digite o sobrenome:");
		p.setSobrenome(teclado.next());
		System.out.println("digite o email:");
		p.setEmail(teclado.next());
		System.out.println("digite o numero de telefone:");
		p.setTelefone(teclado.next());
		
		pessoaDAO.adicionarPessoa(p);
		
		menu();
	}
		
		public static void consultarTodasPessoas(){
			PessoaDAO pessoaDAO = new PessoaDAO();
			List<Pessoa> listaPessoas = new ArrayList<Pessoa>();
			listaPessoas = pessoaDAO.getTodasPessoas();
			
			for(int i = 0;i < listaPessoas.size();i++){
			System.out.println("ID:"+ listaPessoas.get(i).getId());
			System.out.println("nome:"+listaPessoas.get(i).getNome());
			System.out.println("sobrenome:"+listaPessoas.get(i).getSobrenome());
			System.out.println("email:"+listaPessoas.get(i).getEmail());
			System.out.println("telefone:"+listaPessoas.get(i).getTelefone());
			System.out.println("==============================================");
			}
		}
		
		public static void consultarPessoasPorSobrenome(){

			PessoaDAO pessoaDAO = new PessoaDAO();
			List<Pessoa> listaPessoas = new ArrayList<Pessoa>();
			
			System.out.println("digite o sobrenome");
			String sobrenome=(teclado.next());
			listaPessoas = pessoaDAO.consultarPessoasPorSobrenome(sobrenome);
			
			for (int i = 0;i < listaPessoas.size();i++){
				System.out.println("id:"+listaPessoas.get(i).getId());
				System.out.println("nome:"+listaPessoas.get(i).getNome());
				System.out.println("sobrenome:"+listaPessoas.get(i).getSobrenome());
				System.out.println("email:"+listaPessoas.get(i).getEmail());
				System.out.println("telefone:"+listaPessoas.get(i).getTelefone());
				System.out.println("=============================================");
			}
		}
			
		public static void excluirPessoa(){
			PessoaDAO pessoaDAO = new PessoaDAO();
			System.out.println("digite o id do contato que deseja deletar:");
			int id =(teclado.nextInt());
			pessoaDAO.deletar(id);
		}
		
		
	private static void menu() {

		System.out.println("(1)adicionar contato (2)consultar todos contatos (3)consultar contatos por sobrenome (4)excluir contato");
		int op = (teclado.nextInt());

		switch (op) {
		case 1:
			adicionar();
			break;

		case 2:
			consultarTodasPessoas();
			menu();
			break;

		case 3:
			consultarPessoasPorSobrenome();
			menu();
			break;

		case 4:
			excluirPessoa();
			menu();
			break;
		
		case 5:
			System.exit(0);
			menu();
			break;
		}

	}

	public static void main (String[]args){
		menu();
	}
}