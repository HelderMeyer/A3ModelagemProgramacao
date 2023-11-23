package com.helder.cantina;

import javax.swing.JOptionPane;

public class PedidosTela {

	public static void rodar(int usuario) {
		String meuUsuario = Database.sqlRead("SELECT * FROM student WHERE aluno_RA = " + usuario, "aluno_nome");
		System.out.println("Olá, " + meuUsuario);
		System.out.println("Você deseja: ");
		System.out.println("1 - Fazer novos pedidos.");
		System.out.println("2 - Consultar pedidos.");
		System.out.println("3 - Sair");

		String resultInitialOptionsString = JOptionPane.showInputDialog("Digite um número: ");
		int resultInitialOptionsInt = Integer.parseInt(resultInitialOptionsString);

		switch (resultInitialOptionsInt) {
		case 1:
			System.out.println("Opções de Lanches: ");
			System.out.println(
					"1 - " + Database.sqlRead("SELECT * FROM lanche WHERE lan_id = 1", "lan_nome") + " | R$" +
							Database.sqlRead("SELECT * FROM lanche WHERE lan_id = 1", "lan_valor") + " | "  +
							Database.sqlRead("SELECT * FROM lanche WHERE lan_id = 1", "lan_descricao"));
			System.out.println(
					"2 - " + Database.sqlRead("SELECT * FROM lanche WHERE lan_id = 2", "lan_nome") + " | R$" +
							Database.sqlRead("SELECT * FROM lanche WHERE lan_id = 2", "lan_valor") + " | "  +
							Database.sqlRead("SELECT * FROM lanche WHERE lan_id = 2", "lan_descricao"));
			System.out.println(
					"3 - " + Database.sqlRead("SELECT * FROM lanche WHERE lan_id = 3", "lan_nome") + " | R$" +
							Database.sqlRead("SELECT * FROM lanche WHERE lan_id = 3", "lan_valor") + " | "  +
							Database.sqlRead("SELECT * FROM lanche WHERE lan_id = 3", "lan_descricao"));
			System.out.println(
					"4 - " + Database.sqlRead("SELECT * FROM lanche WHERE lan_id = 4", "lan_nome") + " | R$" +
							Database.sqlRead("SELECT * FROM lanche WHERE lan_id = 4", "lan_valor") + " | "  +
							Database.sqlRead("SELECT * FROM lanche WHERE lan_id = 4", "lan_descricao"));
			System.out.println("Selecione os lanches e as quantidades: ");
			PedidosFrame.main(null);
			
			break;
		case 2:
			
			break;
		case 3:
			
			break;
		case 4:
			System.out.println("Você saiu!");
			LoginFrame.main(null);
			break;
		default:
			System.out.println("Opção Inválida!");
			break;
		}
	}

}
