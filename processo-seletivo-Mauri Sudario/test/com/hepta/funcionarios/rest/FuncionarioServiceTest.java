package com.hepta.funcionarios.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.hepta.funcionarios.entity.Funcionario;
import com.hepta.funcionarios.entity.Setor;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FuncionarioServiceTest {

	private static Setor setor;
	private static Funcionario funcionario;

	/**
	 * Definição da classe a ser testada e do funcionário que será utilizado como teste.
	 * @throws Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		setor = new Setor();
		setor.setId(1);
		setor.setNome("Desenvolvimento");

		funcionario = new Funcionario();
		funcionario.setNome("Teste");
		funcionario.setEmail("teste@teste.com");
		funcionario.setIdade(26);
		funcionario.setSalario(7000.00);
		funcionario.setSetor(setor)
	}

	/**
	 * Testa se o funcionario teste será criado no BD
	 */
	
	
	@Test
	@Order(1)
	void testFuncionarioCreate() {
		FuncionarioService service = new FuncionarioService();
		Response response = null;
		try{
			response = service.FuncionarioCreate(funcionario);
		}catch (Exception e){
			e.printStackTrace();
			fail (e.toString());
		}
		assertEquals(200, response.getStatus(), "Erro interno na criação do funcionario");
	}

	/**
	 * Teste para conferir se a cririação do BD foi um sucesso,
	 * e um segundo para conferir se Funcionario foi atualizado.
	 * O aviso "Unchecked" foi suprimido pois se o tipo estiver icorreto o teste falhará
	 */

	@Test
	@Order(2)
	@SuppressWarnings("unchecked")
	void testFuncionarioUpdate() {
		FuncionarioService service = new FuncionarioService();
		List<Funcionario> lista = null;

		try {
			lista = (List<Funcionario>) service.FuncionarioRead().getEntity();
		}catch (Exception e) {
			e.printStackTrace();
			fail("Erro interno ao resgatar a lista de Funcionarios");
		}

		Funcionario aux = lista.get(lista.size()-1);
		Response response = null;

		aux.setName("Mauri Sudario");
		try {
			response = service.FuncionarioUpdate(aux.getId(), aux);
		}catch (Exception e){
			e.printStackTrace();
			fail("Erro interno na atualização do funcionario");
		}
		funcionario = (Funcionario)  response.getEntity();
		assertEquals("Mauri Sudario", funcionario.getNome(), "Falha na atualização do funcionario");
	}

	/**
	 * Testa se o funcionario atualizado esta no BD
	 * O aviso "Unchecked" foi suprimido pois se o tipo estiver icorreto o teste falhará
	 */

	@Test
	@Order(3)
	@SuppressWarnings("unchecked")
	void testFuncionarioRead() {
		FuncionarioService service = new FuncionarioService();
		Response response = null;
		List<Funcionario> lista = null;

		try {
			response = service.FuncionarioRead();
		}catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
		lista = (List<Funcionario>) response.getEntity();
		assertEquals(funcionario.getNome(), lista.get(lista.size()-1).getNome(), "Lista retorneda não contem funcionario");
	}

	/**
	 * Teste para deleção do ususario teste
	 */


	@Test
	@Order(4)
	void testFuncionarioDelete() {
		FuncionarioService service = new FuncionarioService();
		Response response = null;
		try {
			response = service.FuncionarioDelete(funcionario.getId());
		}catch (Exception e) {
			e.printStackTrace();
			fali(e.toString());
		}
		assertEquals(200, response.getStatus(), "Erro interno na exclusão do funcionario")
	}

}
