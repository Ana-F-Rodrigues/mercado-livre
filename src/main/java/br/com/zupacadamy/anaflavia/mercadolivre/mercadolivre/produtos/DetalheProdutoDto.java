package br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.produtos;

public class DetalheProdutoDto {

	private String nome;
	private String descricao;
	
	
	public DetalheProdutoDto () {

	}

	public DetalheProdutoDto(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public  DetalhesProduto converter(Produto produto) {
		return new DetalhesProduto(this.nome, this.descricao, produto);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "DetalheProdutoDto [nome=" + nome + ", descricao=" + descricao + "]";
	}



	

}


