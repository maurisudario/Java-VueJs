package com.hepta.funcionarios.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.hepta.funcionarios.entity.Setor;
import com.hepta.funcionarios.persistence.SetorDAO;


@Path("/setores")
public class SetorService {

    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;

    private  SetorDAO setdao;

    public void SetorService(){
        setdao = new SetorDAO();
    }

    protected void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * Adiciona novo Setor, retornando um erro caso não funcine.
     *
     * @param Setor: Novo Setor
     * @return response 200 (OK) - Conseguiu adicionar
     */
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response SetorCreate (Setor Setor) {
        try {
            setdao.save(Setor);
        }catch (Exception e)
        {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao inserio o Setor").build();
        }
        return Response.status(Status.OK).build();
    }

    /**
     * Retorna um Setor, retornando um erro caso não funcine.
     *
     * @param id: id do Setor
     * @return response 200 (OK) - Conseguiu buscar
     */
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response SetorFind(@PathParam("id") Integer id){
        Setor set = null;
        try {
            set = setdao.find(id);
        }catch (Exception e){
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar o Setor").build();
        }
        return Response.status(Status.OK).entity(set).build();
    }

    /**
     * Atualiza um Setor, retornando um erro caso não funcine.
     *
     * @param id:          id do Setor
     * @param Setor: Setor atualizado
     * @return response 200 (OK) - Conseguiu atualizar
     */
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PUT
    public Response SetorUpdate(@PathParam("id") Integer id, Setor Setor){
        Setor atualizado;
        try {
            atualizado = setdao.update(Setor);
        }catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar o Setor").build();
        }
        return Response.status(Status.OK).entity(atualizado).build();
    }

    /**
     * Remove um Setor, retornando um erro caso não funcine.
     * No momento, se usada para remover um Setor com Funcionários retornará um erro devido aos funcionários ligados ao setor no BD.
     *
     * @param id: id do Setor
     * @return response 200 (OK) - Conseguiu remover
     */
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @DELETE
    public Response SetorDelete(@PathParam("id") Integer id) {
        try {
            setdao.delete(id);
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao remover o Setor").build();
        }
        return Response.status(Status.OK).build();
    }

    /**
     * Retorna uma Lista com todos os Setores, retornando um erro caso não funcine.
     *
     * @param id: id do Setor
     * @return response 200 (OK) - Conseguiu remover
     */
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response SetorRead() {
        List<Setor> setores = new ArrayList<>();
        try {
            setores = setdao.getAllSetores();
        }catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar Setores").build();
        }
        GenericEntity<List<Setor>> entity = new GenericEntity<List<Setor>>(setores){
        };
        return Response.status(Status.OK).entity(entity).build();
    }
}
