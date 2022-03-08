var inicio = new Vue({
    el:"#inicio",
    data: {
        lista: []
    },
    created: function(){
        let vm =  this;
        vm.listarFuncionarios();
    },
    methods:{
        //Busca os itens para a lista da primeira página
        listarFuncionarios: function(){
            const vm = this;
            axios.get("/funcionarios/rs/funcionarios")
                .then(response => {vm.lista = response.data;
                }).catch(function (error) {
                vm.mostraAlertaErro("Erro interno", "Não foi possível listar natureza de serviços");
            }).finally(function() {
            });
        },
        editarFuncionario: function(id){
            localStorage.setItem("funcionario", id);
            window.location.href = 'pages/editar-funcionario.html';
        },
        deletarFuncionario: function(id){
            if(window.confirm("Deseja realmente apagar o funcionário?")){
                axios.delete("/funcionarios/rs/funcionarios/"+id)
                    .then(response => {
                        alert("Funcionário deletado com sucesso!");
                        this.listarFuncionarios();
                    }).catch(error => {
                    alert('Erro ao deletar o Funcionário!');
                })
            }
        },
    }
});