package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.model.Conta;
import br.com.caelum.financas.model.Movimentacao;
import br.com.caelum.financas.model.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class QueryTestJPA {
	public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();

        Conta conta = new Conta();
        conta.setId(1);
        
        //1� option - choice bad
        
       /* Query query = manager
                .createQuery("select m from Movimentacao m where m.conta.id="
                        + conta.getId());*/
        
//		2� Other option. Positional Parameter Notation
        /*Query query = manager
                .createQuery("select m from Movimentacao m where m.conta=?1"
                        + " and m.tipoMovimentacao=?2");
        query.setParameter(1, conta);
        query.setParameter(2, TipoMovimentacao.ENTRADA);*/

//		3� option - Named Parameter Notation
        Query query = manager
                .createQuery("select m from Movimentacao m where m.conta = :pConta"
                        + " and m.tipoMovimentacao = :pTipo");

        query.setParameter("pConta", conta);
        query.setParameter("pTipo", TipoMovimentacao.SAIDA);
        
        List<Movimentacao> movimentacoes = query.getResultList();

        for (Movimentacao m : movimentacoes) {
            System.out.println("\nDescricao ..: " + m.getDescricao());
            System.out.println("Valor ......: R$ " + m.getValor());
        }

        manager.close();
	}
}
