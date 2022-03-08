var cadastro = new Vue({
    el:"#cadastro",
    data: {
        setor: [],
        dnome: '',
        demail: '',
        dsalario: '',
        didade: '',
        dsetor: '',
    },
    created: function(){
        let vm =  this;
        vm.getSetores();
    },
    methods:{
        getSetores: function () {
            axios.get('/funcionarios/rs/setores')
                .then(response => {this.setor = response.data;
                }).catch(function(error) {
                alert('Ocorreu um erro: ' + error);
            });
        },

        cadastrarFuncionario: function(dnome, demail, dsalario, didade, dsetor) {

            let func = {nome: dnome,
                email: demail,
                salario: dsalario,
                idade: didade,
                setor: dsetor}

            axios.post('/funcionarios/rs/funcionarios', func)
                .then(response => {
                        alert("Funcionario cadastrado com sucesso")
                        window.location.href = '/funcionarios/';},
                    error => {alert('Ocorreu um erro.')});

        },
    }
});