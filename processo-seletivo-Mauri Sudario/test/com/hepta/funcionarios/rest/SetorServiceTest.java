package com.hepta.funcionarios.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.hepta.funcionarios.entity.Setor;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SetorServiceTest {
    private static Setor setor;

    /**
     * Definição da classe a ser testada e do funcionario utilizado no teste
     */

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        setor = new setor();
        setor.setNome("Setor teste");
    }

    /**
     * Testa a crioação de setores
     */

    @Test
    @Order(1)
    void testSetorCreate(){
        SetorService service = new SetorService();
        response response = null;
        try {
            response = service.SetorCreate(setor);
        }catch (Exception e){
            e.printStackTrace();
            fail(e.toString());
        }
        assertEquals(200, response.getStatus(), "Erro interno ao criar setor")
    }

    /**
     * Teste para conferir a criação do setor no BD,
     * e um segundo para conferir se Setor foi atualizado
     * O aviso "Unchecked" foi suprimido pois se o tipo estiver icorreto o teste falhará
     */

    @Test
    @Order(2)
    @SuppressWarnings("unchecked")
    void testSetorUpdate(){
        SetorService service = new SetorService();
        List<Setor> lista = null;

        try{
            lista = (List<Setor>) service.SetorRead().getEntity();
        }catch (Exception e){
            e.printStackTrace();
            fail("Erro ao resgatar a lista de setores");
        }

        Setor aux = lista.get(lista.size()-1);
        Response response = null;

        aux.setNome("Financeiro");
        try {
            response = service.SetorUpdate(aux.getId(), aux);
        }catch (Exception e){
            e.printStackTrace();
            fail("Erro interno ao atualizar setor");
        }
        setor = (Setor) response.getEntity();
        assertEquals("Financeiro", setor.getNome(), "Falha ao atualizar setor");
    }


    /**
     * Tete para conferir se o setor atualizado esta no BD
     * O aviso "Unchecked" foi suprimido pois se o tipo estiver icorreto o teste falhará
     */
    @Test
    @Order(3)
    @SuppressWarnings("unchecked")
    void testSetorRead(){
        SetorService service = new SetorService();
        Response response = null;
        List<Setor> lista = null;
        try {
            response = service.SetorRead();
        }catch (Exception e){
            e.printStackTrace();
            fail(e.toString());
        }
        lista = (List<Setor>) response.getEntity();
        assertEquals(setor.getNome(), lista.get(lista.size()-1).getNome(), "Lista retornada não contem funcionario teste");
    }

    /**
     * Teste para deleção do setor teste,
     * A função não esta preparada para deletar setores con funcionarios, então esse teste noa é realizado
     */

    @Test
    @Order(4)
    void testSetorDelete(){
        SetorService service = new SetorService();
        Response response = null;
        try {
            response.SetorDelete(setor.getId());
        }catch (Exception e){
            e.printStackTrace();
            fail(e.toString());
        }
        assertEquals(200, response.getStatus(), "Erro interno na exclusão do setor");
    }
}