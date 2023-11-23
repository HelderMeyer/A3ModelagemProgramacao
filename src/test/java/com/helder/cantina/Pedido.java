package com.helder.cantina;

public class Pedido {
	private int pedidoId;
	private int pedidoPedidoLancheId;
	private int pedidoAlunoRA;
	private int pedidoCantinaCnpj;
	private int raAluno;
	private int cnpjCantina;
	private double quantidade;
	private double pedidoValor;

	public Pedido(int pedidoId, int pedidoPedidoLancheId, int pedidoAlunoRA, int pedidoCantinaCnpj, int raAluno,
			int cnpjCantina, double quantidade, double pedidoValor) {
		this.pedidoId = pedidoId;
		this.pedidoPedidoLancheId = pedidoPedidoLancheId;
		this.pedidoAlunoRA = pedidoAlunoRA;
		this.pedidoCantinaCnpj = pedidoCantinaCnpj;
		this.raAluno = raAluno;
		this.cnpjCantina = cnpjCantina;
		this.quantidade = quantidade;
		this.pedidoValor = pedidoValor;
	}

	public int getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(int pedidoId) {
		this.pedidoId = pedidoId;
	}

	public int getPedidoPedidoLancheId() {
		return pedidoPedidoLancheId;
	}

	public void setPedidoPedidoLancheId(int pedidoPedidoLancheId) {
		this.pedidoPedidoLancheId = pedidoPedidoLancheId;
	}

	public int getPedidoAlunoRA() {
		return pedidoAlunoRA;
	}

	public void setPedidoAlunoRA(int pedidoAlunoRA) {
		this.pedidoAlunoRA = pedidoAlunoRA;
	}

	public int getPedidoCantinaCnpj() {
		return pedidoCantinaCnpj;
	}

	public void setPedidoCantinaCnpj(int pedidoCantinaCnpj) {
		this.pedidoCantinaCnpj = pedidoCantinaCnpj;
	}

	public int getRaAluno() {
		return raAluno;
	}

	public void setRaAluno(int raAluno) {
		this.raAluno = raAluno;
	}

	public int getCnpjCantina() {
		return cnpjCantina;
	}

	public void setCnpjCantina(int cnpjCantina) {
		this.cnpjCantina = cnpjCantina;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public double getPedidoValor() {
		return pedidoValor;
	}

	public void setPedidoValor(double pedidoValor) {
		this.pedidoValor = pedidoValor;
	}

	public void createPedido() {
		Database.sqlCreate("INSERT INTO pedido VALUES (" + this.getPedidoId() + "," + this.getPedidoPedidoLancheId()
				+ "," + this.getPedidoAlunoRA() + "," + this.getPedidoCantinaCnpj() + ")");
	}
}
