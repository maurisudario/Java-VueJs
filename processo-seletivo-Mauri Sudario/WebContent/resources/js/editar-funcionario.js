var editar = new Vue({
    el:"#editar",

    data: {
        setor: [],
        funcionario : []
    },

    mounted: function(){

        let vm =  this;
        vm.getSetores();
        vm.getFuncionario();

    },

    methods: {

        getSetores: function () {
            axios.get('/funcionarios/rs/setores')
                .then(response => {this.setor = response.data;
                }).catch(function(error) {
                alert('Ocorreu um erro ao resgatar os setores: ' + error);
            });
        },

        editarFuncionario: function() {
            set = { id: document.querySelector("select[name=fsetor]").selectedIndex, nome: document.querySelector("select[name=fsetor]").options[document.querySelector("select[name=fsetor]").selectedIndex].text }
            let id = localStorage.getItem("funcionario");
            axios.put('/funcionarios/rs/funcionarios/'+id, {
                id: id,
                nome: document.querySelector("input[name=fnome]").value,
                setor: set,
                salario: document.querySelector("input[name=fsalario]").value,
                email: document.querySelector("input[name=femail]").value,
                idade: document.querySelector("input[name=fidade]").value
            })
                .then((response) => {
                    alert('Usuário editado com sucesso!')
                    window.location.href = '/funcionarios/';
                }, (error) => {
                    alert('Erro ao editar funcionário.')
                });
        },

        getFuncionario: function()
        {
            let id = localStorage.getItem("funcionario");

            axios.get("/funcionarios/rs/funcionarios/"+id).then(response => {
                funcionario = response.data;
                document.querySelector("input[name=fnome]").value = funcionario.nome;
                document.querySelector("input[name=fsalario]").value = funcionario.salario;
                document.querySelector("input[name=femail]").value = funcionario.email;
                document.querySelector("input[name=fidade]").value = funcionario.idade;
                document.querySelector("select[name=fsetor]").selectedIndex = funcionario.setor.id;


            }).catch(function (error) {
                vm.mostraAlertaErro("Erro interno", "Não foi possível acessar o funcionario");
            }).finally(function() {
            });
        }
    }
});