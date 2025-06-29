// Função para mostrar ou esconder o modal
function toggleModal(modalId, show) {
    const modal = document.getElementById(modalId);
    if (modal) {
        if (show) {
            modal.classList.add('active');
        } else {
            modal.classList.remove('active');
        }
    }
}

function showPage(pageId) {
    // Esconde todas as páginas
    document.querySelectorAll('.page').forEach(page => {
        page.classList.remove('active');
        page.classList.add('hidden'); // Adiciona a classe hidden do Tailwind
        page.style.display = 'none'; // Garante que a página seja ocultada via CSS
    });

    // Mostra a página solicitada
    const page = document.getElementById(pageId);
    if (page) {
        page.classList.add('active');
        page.classList.remove('hidden');
        page.style.display = pageId === 'login-page' ? 'flex' : 'block'; // Usa flex para login-page e block para main-app
    }
}

// Função para exibir o modal de mensagem
function showMessageModal(type, title, message) {
    const messageModal = document.getElementById('message-modal');
    const messageIcon = document.getElementById('message-icon');
    const messageTitle = document.getElementById('message-title');
    const messageBody = document.getElementById('message-body');

    // Limpa classes de ícone anteriores
    messageIcon.classList.remove('fa-check-circle', 'fa-times-circle', 'icon-success', 'icon-error');

    if (type === 'success') {
        messageIcon.classList.add('fa-check-circle', 'icon-success');
    } else if (type === 'error') {
        messageIcon.classList.add('fa-times-circle', 'icon-error');
    }

    messageTitle.textContent = title;
    messageBody.textContent = message;

    toggleModal('message-modal', true);
}

// // Função para mostrar uma página e esconder as outras
// function showPage(pageId) {
//     // Esconde todas as páginas
//     document.querySelectorAll('.page').forEach(page => {
//         page.classList.remove('active');
//         page.classList.add('hidden'); // Garante que a página seja oculta com Tailwind
//     });
    
//     // Mostra a página solicitada
//     const page = document.getElementById(pageId);
//     if (page) {
//         page.classList.add('active');
//         page.classList.remove('hidden'); // Torna a página visível
//     }
// }

function handleAction(action) {
    const urlBase = "http://localhost:8080/admin";

    switch(action) {
        case 'reset-db':
            if (confirm("Tem certeza que deseja apagar todo o banco de dados?")) {
                fetch(`${urlBase}/reset`, {
                    method: 'DELETE'
                })
                .then(response => {
                    if (response.ok) {
                        alert("Banco de dados apagado com sucesso!");
                    } else {
                        alert("Erro ao apagar o banco de dados.");
                    }
                })
                .catch(error => alert("Erro na requisição: " + error));
            }
            break;

        case 'create-db':
            fetch(`${urlBase}/popular`, {
                method: 'POST'
            })
            .then(response => {
                if (response.ok) {
                    alert("Banco de dados criado e populado com sucesso!");
                } else {
                    alert("Erro ao criar o banco de dados.");
                }
            })
            .catch(error => alert("Erro na requisição: " + error));
            break;
        case 'add-specialty':
            abrirModalEspecialidade();
            break;

        case 'add-measurements':
            abrirModalMedidas();
            break;
        case 'add-material':
            abrirModalMateriaPrima();
            break;
        case 'add-service':
            abrirModalServicos();
            break;
        case 'add-payment':
            abrirModalFormaPagamento();
            break;
        case 'plotar-graficos':
            console.log("Plotar Gráficos...");
            abrirGraficos();
            break;
        default:
            console.warn("Ação desconhecida:", action);
    }
}

const apiFormaPagamento = "http://localhost:8080/formapagamento";

function abrirModalFormaPagamento() {
    document.getElementById("modalFormaPagamento").classList.remove("hidden");
    carregarFormaPagamentos();
}

function fecharModalFormaPagamento() {
    document.getElementById("modalFormaPagamento").classList.add("hidden");
    document.getElementById("formaPagamentoForm").reset();
    document.getElementById("formaPagamentoId").value = "";
}

async function carregarFormaPagamentos() {
    try {
        const response = await fetch(apiFormaPagamento);
        const data = await response.json();
        const formas = data.content || data;

        const lista = document.getElementById("listaFormaPagamento");
        lista.innerHTML = "";
        formas.forEach(fp => {
            const li = document.createElement("li");
            li.className = "flex justify-between items-center border p-2 rounded";
            li.innerHTML = `
                <span>${fp.nomeFormaPagamento}</span>
                <div class="flex gap-2">
                    <button onclick="editarFormaPagamento(${fp.idFormaPagamento}, '${fp.nomeFormaPagamento}')" class="text-blue-600">Editar</button>
                    <button onclick="excluirFormaPagamento(${fp.idFormaPagamento})" class="text-red-600">Excluir</button>
                </div>
            `;
            lista.appendChild(li);
        });
    } catch (error) {
        console.error("Erro ao carregar formas de pagamento:", error);
        showMessageModal('error', 'Erro', 'Não foi possível carregar as formas de pagamento.');
    }
}

function editarFormaPagamento(id, nome) {
    document.getElementById("formaPagamentoId").value = id;
    document.getElementById("nomeFormaPagamento").value = nome;
}

async function salvarFormaPagamento(event) {
    event.preventDefault();
    const id = document.getElementById("formaPagamentoId").value;
    const nomeFormaPagamento = document.getElementById("nomeFormaPagamento").value.trim();

    if (!nomeFormaPagamento) {
        showMessageModal('error', 'Erro', 'O nome da forma de pagamento é obrigatório.');
        return;
    }

    const metodo = id ? "PUT" : "POST";
    const payload = id 
        ? { idFormaPagamento: parseInt(id), nomeFormaPagamento } 
        : { nomeFormaPagamento };

    try {
        const response = await fetch(apiFormaPagamento, {
            method: metodo,
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload),
        });

        if (!response.ok) throw new Error("Erro ao salvar forma de pagamento");

        fecharModalFormaPagamento();
        showMessageModal('success', 'Sucesso', id ? 'Forma de pagamento atualizada!' : 'Forma de pagamento cadastrada!');
        carregarFormaPagamentos();
    } catch (error) {
        console.error("Erro ao salvar forma de pagamento:", error);
        showMessageModal('error', 'Erro', 'Não foi possível salvar a forma de pagamento.');
    }
}

async function excluirFormaPagamento(id) {
    if (confirm("Deseja realmente excluir esta forma de pagamento?")) {
        try {
            const response = await fetch(`${apiFormaPagamento}/${id}`, { method: "DELETE" });
            if (!response.ok) throw new Error("Erro ao excluir forma de pagamento.");

            showMessageModal('success', 'Sucesso', 'Forma de pagamento excluída com sucesso!');
            carregarFormaPagamentos();
        } catch (error) {
            console.error("Erro ao excluir forma de pagamento:", error);
            showMessageModal('error', 'Erro', 'Não foi possível excluir a forma de pagamento.');
        }
    }
}


const apiServicos = "http://localhost:8080/servico";

function abrirModalServicos() {
  document.getElementById("modalServicos").classList.remove("hidden");
  carregarServicos();
}

function fecharModalServicos() {
  document.getElementById("modalServicos").classList.add("hidden");
  document.getElementById("servicoForm").reset();
  document.getElementById("servicoId").value = "";
}

async function carregarServicos() {
  try {
    const response = await fetch(apiServicos + "/todas"); // ou apenas apiServicos se você não criou "/todas"
    const data = await response.json();
    const servicos = data.content || data;

    const lista = document.getElementById("listaServicos");
    lista.innerHTML = "";
    servicos.forEach(s => {
      const li = document.createElement("li");
      li.className = "flex justify-between items-center border p-2 rounded";
      li.innerHTML = `
        <span>${s.nome} - ${s.tempoMedio} min - R$ ${Number(s.valorServico).toFixed(2)}</span>
        <div class="flex gap-2">
          <button onclick="editarServico(${s.idServico}, '${s.nome}', ${s.tempoMedio}, ${s.valorServico})" class="text-blue-600">Editar</button>
          <button onclick="excluirServico(${s.idServico})" class="text-red-600">Excluir</button>
        </div>
      `;
      lista.appendChild(li);
    });

  } catch (error) {
    console.error("Erro ao carregar serviços:", error);
    showMessageModal('error', 'Erro', 'Não foi possível carregar os serviços.');
  }
}

function editarServico(id, nome, tempo, valor) {
  document.getElementById("servicoId").value = id;
  document.getElementById("nomeServico").value = nome;
  document.getElementById("tempoMedio").value = tempo;
  document.getElementById("valorServico").value = valor;
}

async function salvarServico(event) {
  event.preventDefault();
  const id = document.getElementById("servicoId").value;
  const nomeServico = document.getElementById("nomeServico").value.trim();
  const tempoMedio = parseInt(document.getElementById("tempoMedio").value);
  const valorServico = parseFloat(document.getElementById("valorServico").value);

  if (!nomeServico || isNaN(tempoMedio) || isNaN(valorServico)) {
    showMessageModal('error', 'Erro', 'Preencha corretamente todos os campos.');
    return;
  }

  const metodo = id ? "PUT" : "POST";

  try {
    const response = await fetch(apiServicos, {
      method: metodo,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        idServico: id || null,
        nomeServico,
        tempoMedio,
        valorServico
      }),
    });

    if (!response.ok) throw new Error("Erro ao salvar serviço");

    fecharModalServicos();
    showMessageModal('success', 'Sucesso', id ? 'Serviço atualizado!' : 'Serviço cadastrado!');
    carregarServicos();
  } catch (error) {
    console.error("Erro ao salvar serviço:", error);
    showMessageModal('error', 'Erro', 'Não foi possível salvar o serviço.');
  }
}

async function excluirServico(id) {
  if (confirm("Deseja realmente excluir este serviço?")) {
    try {
      const response = await fetch(`${apiServicos}/${id}`, { method: "DELETE" });
      if (!response.ok) throw new Error("Erro ao excluir serviço.");

      showMessageModal('success', 'Sucesso', 'Serviço excluído com sucesso!');
      carregarServicos();
    } catch (error) {
      console.error("Erro ao excluir serviço:", error);
      showMessageModal('error', 'Erro', 'Não foi possível excluir o serviço.');
    }
  }
}


const apiMedidas = "http://localhost:8080/medida";

function abrirModalMedidas() {
  document.getElementById("modalMedidas").classList.remove("hidden");
  carregarMedidas();
}

function fecharModalMedidas() {
  document.getElementById("modalMedidas").classList.add("hidden");
  document.getElementById("medidasForm").reset();
  document.getElementById("medidaId").value = "";
}

async function carregarMedidas() {
    try {
        const response = await fetch(apiMedidas+ "/todas");
        const data = await response.json();
        const medidas = data.content || data;

        const lista = document.getElementById("listaMedidas");
        lista.innerHTML = "";
        medidas.forEach(m => {
            const li = document.createElement("li");
            li.className = "flex justify-between items-center border p-2 rounded";
            li.innerHTML = `
                <span>${m.nome} - ${Number(m.valor).toFixed(2)} cm</span>

                <div class="flex gap-2">
                    <button onclick="editarMedida(${m.idMedida}, '${m.nome}', ${m.valor})"
                    class="text-blue-600">Editar</button>
                    <button onclick="excluirMedida(${m.idMedida})" class="text-red-600">Excluir</button>
                </div>
            `;
            lista.appendChild(li);
        });

    } catch (error) {
        console.error("Erro ao carregar medidas:", error);
        showMessageModal('error', 'Erro ao Carregar', 'Não foi possível carregar as medidas. Tente novamente.');
    }
}

function editarMedida(id, parte, valor) {
  document.getElementById("medidaId").value = id;
  document.getElementById("categoria").value = parte;
  document.getElementById("valorMedida").value = valor;
}

async function salvarMedida(event) {
    event.preventDefault();
    const id = document.getElementById("medidaId").value;
    const categoria = document.getElementById("categoria").value.trim();
    const valorMedida = parseFloat(document.getElementById("valorMedida").value);

    if (!categoria || isNaN(valorMedida)) {
        showMessageModal('error', 'Erro', 'Preencha corretamente todos os campos.');
        return;
    }

    const metodo = id ? "PUT" : "POST";

    try {
        const response = await fetch(apiMedidas, {
            method: metodo,
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                idMedida: id || null,
                categoria,
                valorMedida
            }),
        });

        if (!response.ok) throw new Error('Erro ao salvar a medida.');

        fecharModalMedidas();
        showMessageModal('success', 'Sucesso', id ? 'Medida atualizada!' : 'Medida cadastrada!');
        carregarMedidas();

    } catch (error) {
        console.error("Erro ao salvar medida:", error);
        showMessageModal('error', 'Erro', 'Não foi possível salvar a medida.');
    }
}

async function excluirMedida(id) {
    if (confirm("Deseja realmente excluir esta medida?")) {
        try {
            const response = await fetch(`${apiMedidas}/${id}`, { method: "DELETE" });
            if (!response.ok) throw new Error('Erro ao excluir medida.');

            showMessageModal('success', 'Sucesso', 'Medida excluída com sucesso!');
            carregarMedidas();
        } catch (error) {
            console.error("Erro ao excluir medida:", error);
            showMessageModal('error', 'Erro', 'Não foi possível excluir a medida.');
        }
    }
}

const apiMateriaPrima = "http://localhost:8080/materiaprima";

function abrirModalMateriaPrima() {
  document.getElementById("modalMateriaPrima").classList.remove("hidden");
  carregarMateriaPrima();
}

function fecharModalMateriaPrima() {
  document.getElementById("modalMateriaPrima").classList.add("hidden");
  document.getElementById("materiaPrimaForm").reset();
  document.getElementById("materiaPrimaId").value = "";
}

async function carregarMateriaPrima() {
  try {
    const response = await fetch(apiMateriaPrima + "/todas");
    const data = await response.json();
    const lista = document.getElementById("listaMateriaPrima");
    lista.innerHTML = "";

    data.forEach(m => {
      const li = document.createElement("li");
      li.className = "flex justify-between items-center border p-2 rounded";
      li.innerHTML = `
        <span>${m.nomeMateriaPrima} - ${m.qtdeMateriaPrima.toFixed(2)} ${m.unidadeMateriaPrima}</span>
        <div class="flex gap-2">
          <button onclick="editarMateriaPrima(${m.idMateriaPrima}, '${m.nomeMateriaPrima}', ${m.qtdeMateriaPrima}, '${m.unidadeMateriaPrima}')" class="text-blue-600">Editar</button>
          <button onclick="excluirMateriaPrima(${m.idMateriaPrima})" class="text-red-600">Excluir</button>
        </div>
      `;
      lista.appendChild(li);
    });

  } catch (error) {
    console.error("Erro ao carregar matérias-primas:", error);
    showMessageModal('error', 'Erro ao Carregar', 'Não foi possível carregar as matérias-primas.');
  }
}

function editarMateriaPrima(id, nome, qtde, unidade) {
  document.getElementById("materiaPrimaId").value = id;
  document.getElementById("nomeMateriaPrima").value = nome;
  document.getElementById("qtdeMateriaPrima").value = qtde;
  document.getElementById("unidadeMateriaPrima").value = unidade;
}

async function salvarMateriaPrima(event) {
  event.preventDefault();

  const id = document.getElementById("materiaPrimaId").value;
  const nomeMateriaPrima = document.getElementById("nomeMateriaPrima").value.trim();
  const qtdeMateriaPrima = parseFloat(document.getElementById("qtdeMateriaPrima").value);
  const unidadeMateriaPrima = document.getElementById("unidadeMateriaPrima").value.trim();

  if (!nomeMateriaPrima || isNaN(qtdeMateriaPrima) || !unidadeMateriaPrima) {
    showMessageModal('error', 'Erro', 'Preencha todos os campos corretamente.');
    return;
  }

  const metodo = id ? "PUT" : "POST";

  try {
    const response = await fetch(apiMateriaPrima, {
      method: metodo,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        idMateriaPrima: id || null,
        nomeMateriaPrima,
        qtdeMateriaPrima,
        unidadeMateriaPrima
      }),
    });

    if (!response.ok) throw new Error('Erro ao salvar a matéria-prima.');

    fecharModalMateriaPrima();
    showMessageModal('success', 'Sucesso', id ? 'Matéria-prima atualizada!' : 'Matéria-prima cadastrada!');
    carregarMateriaPrima();

  } catch (error) {
    console.error("Erro ao salvar matéria-prima:", error);
    showMessageModal('error', 'Erro', 'Não foi possível salvar a matéria-prima.');
  }
}

async function excluirMateriaPrima(id) {
  if (confirm("Deseja realmente excluir esta matéria-prima?")) {
    try {
      const response = await fetch(`${apiMateriaPrima}/${id}`, { method: "DELETE" });
      if (!response.ok) throw new Error('Erro ao excluir matéria-prima.');

      showMessageModal('success', 'Sucesso', 'Matéria-prima excluída com sucesso!');
      carregarMateriaPrima();
    } catch (error) {
      console.error("Erro ao excluir matéria-prima:", error);
      showMessageModal('error', 'Erro', 'Não foi possível excluir a matéria-prima.');
    }
  }
}

const apiEspecialidade = "http://localhost:8080/especialidade";

function abrirModalEspecialidade() {
  document.getElementById("modalEspecialidade").classList.remove("hidden");
  carregarEspecialidades();
}

function fecharModalEspecialidade() {
  document.getElementById("modalEspecialidade").classList.add("hidden");
  document.getElementById("especialidadeForm").reset();
  document.getElementById("especialidadeId").value = "";
}

async function carregarEspecialidades() {
    try {
        const response = await fetch(apiEspecialidade);
        const data = await response.json();
        const especialidades = data.content || data; // Acessa 'content' se for paginado, ou a resposta direta se não for

        const lista = document.getElementById("listaEspecialidades");
        lista.innerHTML = "";
        especialidades.forEach(e => {
            const li = document.createElement("li");
            li.className = "flex justify-between items-center border p-2 rounded";
            li.innerHTML = `
                <span>${e.nome}</span>
                <div class="flex gap-2">
                    <button onclick="editarEspecialidade(${e.idEspecialidade}, '${e.nome}')" class="text-blue-600">Editar</button>
                    <button onclick="excluirEspecialidade(${e.idEspecialidade})" class="text-red-600">Excluir</button>
                </div>
            `;
            lista.appendChild(li);
        });

    } catch (error) {
        console.error("Erro ao carregar especialidades:", error);
        showMessageModal('error', 'Erro ao Carregar', 'Não foi possível carregar as especialidades. Tente novamente.');
    }
}

function editarEspecialidade(id, descricao) {
  document.getElementById("especialidadeId").value = id;
  document.getElementById("descricaoEspecialidade").value = descricao;
}

async function salvarEspecialidade(event) {
    event.preventDefault();
    const id = document.getElementById("especialidadeId").value;
    const descricao = document.getElementById("descricaoEspecialidade").value;

    if (!descricao) {
        showMessageModal('error', 'Erro', 'Por favor, preencha a descrição da especialidade.');
        return;
    }

    const metodo = id ? "PUT" : "POST";
    const url = apiEspecialidade;

    try {
        const response = await fetch(url, {
            method: metodo,
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({idEspecialidade: id || null,descricaoEspecialidade: descricao}),
        });

        if (!response.ok) {
            throw new Error('Erro ao salvar a especialidade.');
        }

        fecharModalEspecialidade();
        showMessageModal('success', 'Sucesso', id ? 'Especialidade atualizada com sucesso!' : 'Especialidade cadastrada com sucesso!');
        carregarEspecialidades(); // Recarrega a lista
    } catch (error) {
        console.error("Erro ao salvar especialidade:", error);
        showMessageModal('error', 'Erro', 'Não foi possível salvar a especialidade. Tente novamente.');
    }
}

async function excluirEspecialidade(id) {
    if (confirm("Tem certeza que deseja excluir esta especialidade?")) {
        try {
            const response = await fetch(`${apiEspecialidade}/${id}`, { method: "DELETE" });
            if (!response.ok) {
                throw new Error('Erro ao excluir a especialidade.');
            }
            showMessageModal('success', 'Sucesso', 'Especialidade excluída com sucesso!');
            carregarEspecialidades();
        } catch (error) {
            console.error("Erro ao excluir especialidade:", error);
            showMessageModal('error', 'Erro', 'Não foi possível excluir a especialidade. Tente novamente.');
        }
    }
}


async function abrirGraficos() {
    toggleModal('modal-graficos', true);

    try {
        const response = await fetch('http://localhost:8080/admin/graficos');
        const data = await response.json();
        console.log('teste')
        // Exemplo de estrutura esperada da API
        // data = {
        //   grafico1: { labels: [...], valores: [...] },
        //   grafico2: { labels: [...], valores: [...] },
        //   grafico3: { labels: [...], valores: [...] }
        // }

        // Destrói os gráficos antigos se existirem
        if (window.chart1) window.chart1.destroy();
        if (window.chart2) window.chart2.destroy();
        if (window.chart3) window.chart3.destroy();

        const ctx1 = document.getElementById('grafico1').getContext('2d');
        window.chart1 = new Chart(ctx1, {
            type: 'bar',
            data: {
                labels: data.grafico1.labels,
                datasets: [{
                    label: 'Gráfico 1',
                    data: data.grafico1.valores,
                    backgroundColor: 'rgba(54, 162, 235, 0.6)'
                }]
            }
        });

        const ctx2 = document.getElementById('grafico2').getContext('2d');
        window.chart2 = new Chart(ctx2, {
            type: 'line',
            data: {
                labels: data.grafico2.labels,
                datasets: [{
                    label: 'Gráfico 2',
                    data: data.grafico2.valores,
                    borderColor: 'rgba(255, 99, 132, 1)',
                    fill: false
                }]
            }
        });

        const ctx3 = document.getElementById('grafico3').getContext('2d');
        window.chart3 = new Chart(ctx3, {
            type: 'pie',
            data: {
                labels: data.grafico3.labels,
                datasets: [{
                    label: 'Gráfico 3',
                    data: data.grafico3.valores,
                    backgroundColor: [
                        'rgba(255, 205, 86, 0.6)',
                        'rgba(75, 192, 192, 0.6)',
                        'rgba(255, 99, 132, 0.6)'
                    ]
                }]
            }
        });

    } catch (error) {
        console.error('Erro ao buscar dados da API:', error);
        alert('Erro ao carregar gráficos.');
    }
}

// Configuração dos eventos quando o DOM estiver carregado
document.addEventListener('DOMContentLoaded', function() {
    // Configurar botões para abrir os modais de cadastro
    document.getElementById('btn-cadastro-cliente').addEventListener('click', function(e) {
        e.preventDefault();
        toggleModal('modal-cadastro-cliente', true);
    });
    
    document.getElementById('btn-cadastro-atelie').addEventListener('click', function(e) {
        e.preventDefault();
        toggleModal('modal-cadastro-atelie', true);
    });
    
    // Configurar botões de voltar nos modais de cadastro para fechar o modal
    document.getElementById('btn-voltar-modal-cliente').addEventListener('click', function() {
        toggleModal('modal-cadastro-cliente', false);
    });
    
    document.getElementById('btn-voltar-modal-atelie').addEventListener('click', function() {
        toggleModal('modal-cadastro-atelie', false);
    });

    // Configurar botão de fechar no modal de mensagem
    document.getElementById('btn-close-message-modal').addEventListener('click', function() {
        toggleModal('message-modal', false);
    });

    let contextoEndereco = null; // 'cliente' ou 'atelie'

    document.getElementById('btn-cadastrar-endereco-cliente').addEventListener('click', function() {
        contextoEndereco = 'cliente';
        toggleModal('modal-endereco', true);
    });
    document.getElementById('btn-cadastrar-endereco-atelie').addEventListener('click', function() {
        contextoEndereco = 'atelie';
        toggleModal('modal-endereco', true);
    });
    document.getElementById('btn-cancelar-endereco').addEventListener('click', function() {
        toggleModal('modal-endereco', false);
        contextoEndereco = null;
    });
    

    document.getElementById('btn-novo-pedido').addEventListener('click', function() {
        toggleModal('modal-pedido', true);
    });
    
    // Configurar formulários
    const loginForm = document.getElementById('login-form');
    if (loginForm) {
        loginForm.addEventListener('submit', function(e) {
            e.preventDefault();
            
            const formData = new FormData(loginForm);
            const dados = Object.fromEntries(formData.entries());
            
            const json = JSON.stringify(dados);
            
            fetch ('http://localhost:8080/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: json
            })
            .then(resp => resp.json())
            .then(dadosResposta => {
                showMessageModal('success', 'Login Concluído!');
                loginForm.reset(); // Limpar formulário
                // Fecha quaisquer modais abertos
                toggleModal('modal-cadastro-cliente', false);
                toggleModal('modal-cadastro-atelie', false);
                toggleModal('message-modal', false); 
                console.log(dadosResposta)
                localStorage.setItem('idPessoa', dadosResposta.idPessoa)
                localStorage.setItem('userName', dadosResposta.nomePessoa);

                // Exemplo: Atualizar um elemento na tela principal com o nome do usuário
                

                if (dadosResposta.inicio01) {
                    const userNameElement = document.querySelector('.nome-atelie');
                    if (userNameElement) {
                        userNameElement.textContent = `${dadosResposta.nomePessoa}!`;
                    }

                    const userName = localStorage.getItem('userName');
                    
                    if (userName) {
                        const userNameElement = document.querySelector('.atelie-nome');
                        if (userNameElement) {
                            userNameElement.textContent = userName;
                        }
                    }
                    showPage('app-atelie')
                } else {
                    const userNameElement = document.querySelector('.user-name');
                    if (userNameElement) {
                        userNameElement.textContent = `${dadosResposta.nomePessoa}!`;
                    }

                    const userName = localStorage.getItem('userName');
                    
                    if (userName) {
                        const userNameElement = document.querySelector('.name-user');
                        if (userNameElement) {
                            userNameElement.textContent = userName;
                        }
                    }
                    showPage('main-app'); 
                }

                
            }) 
            .catch(erro => {
                showMessageModal('error', 'Erro no Login', 'Usuário ou senha inválidos. Tente novamente.');
            })
        });
    }
    
    const formCadastroCliente = document.getElementById('form-cadastro-cliente');
    if (formCadastroCliente) {
        formCadastroCliente.addEventListener('submit', function(e) {
            e.preventDefault();

            const formData = new FormData(formCadastroCliente);
            const dados = Object.fromEntries(formData.entries());
            delete dados.confirmarSenha;
            const json = JSON.stringify(dados);
            console.log(json);

            fetch ('http://localhost:8080/cliente', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: json
            })
            .then(resp => resp.json())
            .then(dadosResposta => {
                toggleModal('modal-cadastro-cliente', false); // Fechar o modal de cadastro
                showMessageModal('success', 'Cadastro de Cliente Concluído!', 'Sua conta de cliente foi criada com sucesso. Bem-vindo(a)!');
                formCadastroCliente.reset(); // Limpar formulário
            })
            .catch(erro => {
                showMessageModal('error', 'Erro no Cadastro', 'Não foi possível cadastrar o cliente. Verifique os dados e tente novamente.');
            })
            console.log(json)
            const success = true; // Troque para false para testar a mensagem de erro
            
            if (success) {
                toggleModal('modal-cadastro-cliente', false); // Fechar o modal de cadastro
                showMessageModal('success', 'Cadastro de Cliente Concluído!', 'Sua conta de cliente foi criada com sucesso. Bem-vindo(a)!');
                formCadastroCliente.reset(); // Limpar formulário
            } else {
                // Exemplo de erro (você receberia a mensagem do backend)
                showMessageModal('error', 'Erro no Cadastro', 'Não foi possível cadastrar o cliente. Verifique os dados e tente novamente.');
            }
        });
    }
    
    const formCadastroAtelie = document.getElementById('form-cadastro-atelie');
    if (formCadastroAtelie) {
        formCadastroAtelie.addEventListener('submit', function(e) {
            e.preventDefault();

            const formData = new FormData(formCadastroAtelie);
            const dados = Object.fromEntries(formData.entries());
            delete dados.confirmarSenha;
            
            dados.idsServico = JSON.parse(dados.idsServico).map(id => parseInt(id, 10));
            console.log(dados.idsServico)
            const json = JSON.stringify(dados);

            console.log(json);

            fetch ('http://localhost:8080/atelie', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: json
            })
            .then(resp => resp.json())
            .then(dadosResposta => {
                toggleModal('modal-cadastro-atelie', false); // Fechar o modal de cadastro
                showMessageModal('success', 'Cadastro de Ateliê Concluído!', 'Sua conta de ateliê foi criada com sucesso. Bem-vindo(a)!');
                formCadastroAtelie.reset(); // Limpar formulário
            })
            .catch(erro => {
                showMessageModal('error', 'Erro no Cadastro', 'Não foi possível cadastrar o ateliê. Verifique os dados e tente novamente.');
            })


            const success = true; // Troque para false para testar a mensagem de erro

            if (success) {
                toggleModal('modal-cadastro-atelie', false); // Fechar o modal de cadastro
                showMessageModal('success', 'Cadastro de Ateliê Concluído!', 'Seu ateliê foi cadastrado com sucesso. Comece a receber pedidos!');
                formCadastroAtelie.reset(); // Limpar formulário
            } else {
                // Exemplo de erro (você receberia a mensagem do backend)
                showMessageModal('error', 'Erro no Cadastro', 'Não foi possível cadastrar o ateliê. Tente novamente mais tarde.');
            }
        });
    }


    document.getElementById('form-endereco').addEventListener('submit', async function(e) {
        e.preventDefault();
        // Pega valores
        const endereco = {
            estado: document.getElementById('modal-estado').value,
            cidade: document.getElementById('modal-cidade').value,
            bairro: document.getElementById('modal-bairro').value,
            rua: document.getElementById('modal-rua').value,
            cep: document.getElementById('modal-cep').value,
            numero: document.getElementById('modal-numero').value,
            complemento: document.getElementById('modal-complemento').value,
        };
        // Enviar para a API
    try {
        const resp = await fetch('http://localhost:8080/endereco', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(endereco)
        });

        if (!resp.ok) throw new Error('Erro ao salvar endereço!');
        const resposta = await resp.json();

        document.getElementById("endereco-id").value = resposta.idEndereco;
        const enderecoInput = document.querySelector('#form-cadastro-atelie #endereco-id');
        if(enderecoInput) {
            enderecoInput.value = resposta.idEndereco;
        } else {
            console.error('Campo hidden #endereco-id não encontrado no formulário do ateliê.');
        }
        // (Opcional) mostrar resumo para usuário
        // document.getElementById('endereco-resumo').innerText =
        //     `Rua: ${endereco.rua}, Nº ${endereco.numero}, Bairro: ${endereco.bairro}, Cidade: ${endereco.cidade}, Estado: ${endereco.estado}`;

        toggleModal('modal-endereco', false);

        } catch (err) {
            alert('Erro ao cadastrar endereço: ' + err.message);
        }
    });

    // Abrir o modal e buscar especialidades
    document.getElementById('btn-escolher-especialidade').addEventListener('click', async function() {
        toggleModal('modal-especialidade', true);
        const lista = document.getElementById('lista-especialidade');
        lista.innerHTML = '<div>Carregando...</div>';
        // Troque esta URL pela da sua API!
        const resp = await fetch('http://localhost:8080/especialidade');
        const especialidades = await resp.json();
        lista.innerHTML = `
            <label for="select-especialidade" class="block mb-2 font-medium text-gray-700">Escolha uma especialidade:</label>
            <select id="select-especialidade" name="especialidade" class="w-full p-2 border rounded">
                ${especialidades.content.map(e => `
                    <option value="${e.idEspecialidade}">${e.nome}</option>
                `).join('')}
            </select>
            `;
    });

    // Fechar modal no cancelar
    document.getElementById('btn-cancelar-especialidade').addEventListener('click', function() {
        toggleModal('modal-especialidade', false);
    });

    // Salvar especialidade escolhida
    
    document.getElementById('form-especialidade').addEventListener('submit', function(e) {
        
        e.preventDefault();
        
        const select = document.getElementById('select-especialidade');
        const selecionada = select.value;

        if (selecionada) {
            document.getElementById('especialidade-id').value = selecionada;
            toggleModal('modal-especialidade', false);
            console.log(selecionada);
        } else {
            alert("Escolha uma especialidade.");
        }
    });


    document.getElementById('btn-escolher-servico').addEventListener('click', async function() {
        toggleModal('modal-servico', true);
        const lista = document.getElementById('lista-servico');
        lista.innerHTML = '<div>Carregando...</div>';
        // Troque esta URL pela da sua API!
        const resp = await fetch('http://localhost:8080/servico');
        const servicos = await resp.json();
        lista.innerHTML = `
        <label class="block mb-2 font-medium text-gray-700">Escolha os serviços:</label>
        <div class="space-y-2">
            ${servicos.content.map(e => `
                <label class="flex items-center space-x-2">
                    <input type="checkbox" name="servico" value="${e.idServico}" class="form-checkbox">
                    <span>${e.nome}</span>
                </label>
            `).join('')}
        </div>
    `;
    });

    // Fechar modal no cancelar
    document.getElementById('btn-cancelar-servico').addEventListener('click', function() {
        toggleModal('modal-servico', false);
    });

    // Salvar serviços escolhidos
    
    document.getElementById('form-servico').addEventListener('submit', function(e) {
        
        e.preventDefault();
        
        const checkboxes = document.querySelectorAll('input[name="servico"]:checked');
        const idsSelecionados = Array.from(checkboxes).map(checkbox => checkbox.value);

        if (idsSelecionados.length > 0) {
            console.log(idsSelecionados);
            document.getElementById('servico-ids').value = JSON.stringify(idsSelecionados);
            console.log(document.getElementById('servico-ids').value)
            toggleModal('modal-servico', false);
            
        } else {
            alert("Escolha pelo menos um serviço.");
        }
    });

    document.getElementById('btn-novo-pedido').addEventListener('click', async function () {
        const select = document.getElementById('modal-atelie');
        
        const cliente = document.getElementById('cliente-id')
        cliente.value = localStorage.getItem('idPessoa')
        // Limpa o select e mostra "carregando"
        select.innerHTML = '<option>Carregando...</option>';
    
        try {
            const resp = await fetch('http://localhost:8080/atelie');
            const data = await resp.json();
    
            // Substitui pelas opções reais
            select.innerHTML = '<option value="">Selecione um ateliê</option>' +
                data.content.map(e => `
                    <option value="${e.idAtelie}">${e.nome}</option>
                `).join('');
        } catch (error) {
            select.innerHTML = '<option>Erro ao carregar</option>';
            console.error(error);
        }
    });

    // Fechar modal no cancelar
    document.getElementById('btn-cancelar-pedido').addEventListener('click', function() {
        toggleModal('modal-pedido', false);
    });

    const formPedido = document.getElementById('form-pedido');
    if (formPedido) {
        formPedido.addEventListener('submit', function(e) {
            e.preventDefault();
            
            const formData = new FormData(formPedido);
            
            const dados = Object.fromEntries(formData.entries());
            console.log(dados.idAtelie)
            dados.status = "AGUARDANDO";
            dados.servicos = JSON.parse(dados.servicos);
            dados.idsMedida = JSON.parse(dados.idsMedida).map(id => parseInt(id, 10));
            console.log('teste')
            console.log(dados.idsMedida)
            const json = JSON.stringify(dados);

            console.log(json);

            fetch ('http://localhost:8080/pedido', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: json
            })
            .then(resp => resp.json())
            .then(dadosResposta => {
                toggleModal('modal-pedido', false); // Fechar o modal de cadastro
                showMessageModal('success', 'Cadastro de Pedido!', 'Seu pedido foi criado com sucesso.');
                formPedido.reset(); // Limpar formulário
            })
            .catch(erro => {
                showMessageModal('error', 'Erro no Pedido', 'Não foi possível cadastrar o Pedido.');
            })
        })
    }

    // Salvar especialidade escolhida
    
    document.getElementById('form-especialidade').addEventListener('submit', function(e) {
        
        e.preventDefault();
        
        const select = document.getElementById('select-especialidade');
        const selecionada = select.value;

        if (selecionada) {
            document.getElementById('especialidade-id').value = selecionada;
            toggleModal('modal-especialidade', false);
            console.log(selecionada);
        } else {
            alert("Escolha uma especialidade.");
        }
    });

    // Abrir o modal e buscar formas de pagamento
    document.getElementById('btn-escolher-formaPagamento').addEventListener('click', async function() {
        toggleModal('modal-formaPagamento', true);
        const lista = document.getElementById('lista-formaPagamento');
        lista.innerHTML = '<div>Carregando...</div>';
        // Troque esta URL pela da sua API!
        const resp = await fetch('http://localhost:8080/formapagamento');
        const formaPagamento = await resp.json();
        lista.innerHTML = `
            <label for="select-formaPagamento" class="block mb-2 font-medium text-gray-700">Escolha uma Forma de Pagamento:</label>
            <select id="select-formaPagamento" name="formaPagamento" class="w-full p-2 border rounded">
                ${formaPagamento.content.map(e => `
                    <option value="${e.idFormaPagamento}">${e.nomeFormaPagamento}</option>
                `).join('')}
            </select>
            `;
    });

    // Fechar modal no cancelar
    document.getElementById('btn-cancelar-formaPagamento').addEventListener('click', function() {
        toggleModal('modal-formaPagamento', false);
    });

    // Salvar forma de pagamento escolhida
    
    document.getElementById('form-formaPagamento').addEventListener('submit', function(e) {
        
        e.preventDefault();
        
        const select = document.getElementById('select-formaPagamento');
        const selecionada = select.value;

        if (selecionada) {
            document.getElementById('formaPagamento-id').value = selecionada;
            toggleModal('modal-formaPagamento', false);
            console.log(selecionada);
        } else {
            alert("Escolha uma Forma de Pagamento.");
        }
    });

    // Abrir o modal e buscar medidas
    document.getElementById('btn-escolher-medida').addEventListener('click', async function() {
        toggleModal('modal-medida', true);
        const lista = document.getElementById('lista-medida');
        lista.innerHTML = '<div>Carregando...</div>';
        // Troque esta URL pela da sua API!
        const resp = await fetch('http://localhost:8080/medida');
        const medida = await resp.json();
        lista.innerHTML = `
        <label class="block mb-2 font-medium text-gray-700">Escolha as medidas:</label>
        <div class="space-y-2">
            ${medida.content.map(e => `
                <label class="flex items-center space-x-2">
                    <input type="checkbox" name="medida" value="${e.idMedida}" class="form-checkbox">
                    <span>${e.nome} ${e.valor}</span>
                </label>
            `).join('')}
        </div>`
    });

    // Fechar modal no cancelar
    document.getElementById('btn-cancelar-medida').addEventListener('click', function() {
        toggleModal('modal-medida', false);
    });

    // Salvar medidas escolhidas
    
    document.getElementById('form-medida').addEventListener('submit', function(e) {
        
        e.preventDefault();
        
        const checkboxes = document.querySelectorAll('input[name="medida"]:checked');
        const idsSelecionados = Array.from(checkboxes).map(checkbox => checkbox.value);

        if (idsSelecionados.length > 0) {
            console.log(idsSelecionados);
            document.getElementById('medida-ids').value = JSON.stringify(idsSelecionados);
            console.log(document.getElementById('medida-ids').value)
            toggleModal('modal-medida', false);
        } else {
            alert("Escolha uma Medida.");
        }
    });


     // Abrir o modal e buscar serviços e materiaprima
     document.getElementById('btn-adicionar-servico').addEventListener('click', async function() {
        const formData = new FormData(formPedido);
        const dados = Object.fromEntries(formData.entries());
        dados.idAtelie
        toggleModal('modal-servico-pedido', true);
        const lista = document.getElementById('lista-servico-pedido');
        lista.innerHTML = '<div>Carregando...</div>';
        // Troque esta URL pela da sua API!
        const resp = await fetch('http://localhost:8080/servico/atelie/' + dados.idAtelie);
        const servico = await resp.json();
        console.log(servico)
        lista.innerHTML = `
            <label for="select-servico-pedido" class="block mb-2 font-medium text-gray-700">Escolha um serviço:</label>
            <select id="select-servico-pedido" name="servicoPedido" class="w-full p-2 border rounded">
                ${servico.map(e => `
                    <option value="${e.idServico}" data-tempo="${e.tempoMedio}" data-valor="${e.valorServico}">${e.nome}</option>
                `).join('')}
            </select>
            `;
        
        const listaMP = document.getElementById('lista-materia-pedido');    
        const respMP = await fetch('http://localhost:8080/materiaprima');
        const materiaPrima = await respMP.json();

        listaMP.innerHTML = `
        <label class="block mb-2 font-medium text-gray-700">Escolha as matérias prima:</label>
        <div class="space-y-2">
            ${materiaPrima.content.map(e => `
                <label class="flex items-center space-x-2">
                    <input type="checkbox" name="materiaPrima" value="${e.idMateriaPrima}" class="form-checkbox">
                    <span>${e.nome}</span>
                </label>
            `).join('')}
        </div>`
    });

    // Fechar modal no cancelar
    document.getElementById('btn-cancelar-servico-pedido').addEventListener('click', function() {
        toggleModal('modal-servico-pedido', false);
    });

    // Salvar serviço e as matérias prima escohlida
    
    document.getElementById('form-servico-pedido').addEventListener('submit', function(e) {
        e.preventDefault();
    
        // Pega o serviço selecionado
        const selectServico = document.getElementById('select-servico-pedido');
        const idServico = parseInt(selectServico.value);
        const selected = selectServico.options[selectServico.selectedIndex];

        const tempoMedio = parseInt(selected.getAttribute('data-tempo'));
        const valorServico = parseFloat(selected.getAttribute('data-valor'));

        const inputValor = document.getElementById('modal-valor');
        inputValor.value = valorServico.toFixed(2);

        const dataAtual = new Date();
        dataAtual.setDate(dataAtual.getDate() + tempoMedio)
        const dataFormatada = dataAtual.toISOString().split('T')[0];

        const inputData = document.getElementById('modal-dataPrevisao');
        inputData.value = dataFormatada;
        // Pega os checkboxes de matérias-primas selecionadas
        const checkboxes = document.querySelectorAll('input[name="materiaPrima"]:checked');
        const materiasPrima = Array.from(checkboxes).map(cb => ({
            idMateriaPrima: parseInt(cb.value)
        }));
    
        if (!idServico || materiasPrima.length === 0) {
            alert("Selecione um serviço e ao menos uma matéria-prima.");
            return;
        }
    
        // Monta o objeto final
        const servicoPedido = 
                [{
                    idServico: idServico,
                    materiasPrima: materiasPrima
                }];
            
    
        
        console.log(JSON.stringify(servicoPedido));
    
        // Se quiser esconder o modal
        toggleModal('modal-servico-pedido', false);
    
        document.getElementById('servico-pedido-ids').value = JSON.stringify(servicoPedido);
        console.log(document.getElementById('servico-pedido-ids').value);
    });

    const linkPedidos = document.getElementById('nav-pedidos');
    
    linkPedidos.addEventListener('click', function (e) {
        e.preventDefault(); 
        
        fetch('http://localhost:8080/pedido/cliente/' + localStorage.getItem("idPessoa")) 
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro na requisição');
                }
                return response.json(); 
            })
            .then(data => {
                console.log('Pedidos recebidos:', data);
            
                
                const lista = document.getElementById('lista-pedidos-feitos');
                if (Array.isArray(data.content) && data.content.length) {
                    lista.innerHTML = data.content.map(pedido => `
                        <div class="py-2 border-b">
                            <span class="text-gray-800">
                            ${pedido.descricao} |
                            ${pedido.nomeAtelie} |
                            ${pedido.status}
                            </span>
                        </div>
                    `).join('');
                } else {
                    lista.innerHTML = '<div class="py-4 text-gray-500">Nenhum pedido encontrado.</div>';
                }
            
                toggleModal('modal-pedidos', true);
            })
            .catch(error => {
                console.error('Erro ao buscar pedidos:', error);
                alert('Erro ao buscar pedidos!');
            });
        });
        
        document.getElementById('btn-fechar-pedidos').addEventListener('click', () => 
            toggleModal('modal-pedidos', false));

    const linkAtelies = document.getElementById('nav-atelies');
    
    linkAtelies.addEventListener('click', function (e) {
        e.preventDefault(); 
        
        fetch('http://localhost:8080/atelie') 
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro na requisição');
                }
                return response.json(); 
            })
            .then(data => {
                console.log('Atelies recebidos:', data);
            
                
                const lista = document.getElementById('lista-atelies-cadastrados');
                if (Array.isArray(data.content) && data.content.length) {
                    lista.innerHTML = data.content.map(a => `
                        <div class="py-2 border-b">
                            <span class="text-gray-800">
                            ${a.nome}
                            </span>
                        </div>
                    `).join('');
                } else {
                    lista.innerHTML = '<div class="py-4 text-gray-500">Nenhum Ateliê encontrado.</div>';
                }
            
                toggleModal('modal-atelies', true);
            })
            .catch(error => {
                console.error('Erro ao buscar ateliês:', error);
                alert('Erro ao buscar ateliês!');
            });
    });
        
        document.getElementById('btn-fechar-atelies').addEventListener('click', () => 
            toggleModal('modal-atelies', false));


    const searchInput = document.getElementById('search-field');
    searchInput.addEventListener('keydown', function(event) {
        if (event.key === 'Enter') {
            event.preventDefault(); // Opcional: evita o submit padrão em forms
            const termoBusca = searchInput.value.trim();
            console.log(JSON.stringify(termoBusca))
            if (termoBusca) {
                fetch('http://localhost:8080/atelie/ia', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({termoBusca})
                })
                .then(response => response.json())
                .then(data => {
                    console.log('Resultado da busca:', data);
                    const lista = document.getElementById('lista-atelies-cadastrados');
                    if (Array.isArray(data.content) && data.content.length) {
                        lista.innerHTML = data.content.map(a => `
                            <div class="py-2 border-b">
                                <span class="text-gray-800">
                                ${a.nome}
                                </span>
                            </div>
                        `).join('');
                    searchInput.value = ''
                    } else {
                        lista.innerHTML = '<div class="py-4 text-gray-500">Nenhum Ateliê encontrado.</div>';
                    }
                    toggleModal('modal-atelies', true);
                })
                .catch(error => {
                    console.error('Erro na busca:', error);
                });
            }
                
                

        }
    });

    

    const linkPerfil = document.getElementById('link-ver-perfil');
    
    linkPerfil.addEventListener('click', function (e) {
        e.preventDefault(); 
        
        fetch('http://localhost:8080/cliente/' + localStorage.getItem("idPessoa")) 
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro na requisição');
                }
                return response.json(); 
            })
            .then(data => {
                console.log('Cliente recebido:', data);
                document.getElementById('cliente-id-atualizar').value = data.idPessoa;
                document.getElementById('cliente-nome-atualizacao').value = data.nomePessoa;
                document.getElementById('cliente-cpf-atualizacao').value = data.cpf;
                document.getElementById('cliente-email-atualizacao').value = data.email;
                document.getElementById('cliente-telefone-atualizacao').value = data.telefone;
                document.getElementById('cliente-usuario-atualizacao').value = data.usuario;
                document.getElementById('cliente-nascimento-atualizacao').value = data.data;
                document.getElementById('cliente-senha-atualizacao').value = data.senha;
                
                toggleModal('modal-atualizacao-cliente', true);
                document.getElementById('btn-atualizar-endereco-cliente').addEventListener('click', function() {
                    toggleModal('modal-atualizar-endereco', true);
                    
                    fetch('http://localhost:8080/endereco/' + data.idEndereco) 
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('Erro na requisição');
                        }
                        return response.json(); 
                    })
                    .then(dataEndereco => {
                        console.log('Endereco recebido:', dataEndereco);
                        document.getElementById('endereco-id-atualizacao').value = dataEndereco.idEndereco
                        document.getElementById('modal-estado-atualizacao').value = dataEndereco.estado;
                        document.getElementById('modal-cidade-atualizacao').value = dataEndereco.cidade;
                        document.getElementById('modal-bairro-atualizacao').value = dataEndereco.bairro;
                        document.getElementById('modal-rua-atualizacao').value = dataEndereco.rua;
                        document.getElementById('modal-cep-atualizacao').value = dataEndereco.cep;
                        document.getElementById('modal-numero-atualizacao').value = dataEndereco.numero;
                        document.getElementById('modal-complemento-atualizacao').value = dataEndereco.complemento;
                        
                    })
                    .catch(error => {
                        console.error('Erro ao buscar perfil:', error);
                        alert('Erro ao buscar perfil!');
                    });
                })
            });     
        });

        
    const formEndereco = document.getElementById('form-atualizar-endereco');

    formEndereco.addEventListener('submit', function(e) {
        
        e.preventDefault();

        
        const formData = new FormData(formEndereco);
        const dadosDoFormulario = Object.fromEntries(formData.entries());
        
        
        console.log(dadosDoFormulario)
        
        fetch(`http://localhost:8080/endereco`, { 
            method: 'PUT', 
            headers: {
                'Content-Type': 'application/json',
        
            },
            body: JSON.stringify(dadosDoFormulario)
        })
        .then(response => {
            if (!response.ok) {
                
                throw new Error('Falha ao atualizar o endereço. Status: ' + response.status);
            }
            return response.json();
        })
        .then(data => {
            console.log('Endereço atualizado com sucesso:', data);
            document.getElementById('endereco-id-atualizar').value = data.idEndereco
            console.log(document.getElementById('endereco-id-atualizar').value)
            toggleModal('modal-atualizar-endereco', false)
            
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Ocorreu um erro ao tentar atualizar o endereço.');
        });
    });

    const formAtualizarCliente = document.getElementById('form-atualizacao-cliente');
    formAtualizarCliente.addEventListener('submit', function(e) {
        
        e.preventDefault();

        
        const formData = new FormData(formAtualizarCliente);
        const dadosDoFormulario = Object.fromEntries(formData.entries());
        
        
        console.log(dadosDoFormulario)
        
        fetch(`http://localhost:8080/cliente`, { 
            method: 'PUT', 
            headers: {
                'Content-Type': 'application/json',
        
            },
            body: JSON.stringify(dadosDoFormulario)
        })
        .then(response => {
            if (!response.ok) {
                
                throw new Error('Falha ao atualizar o perfil. Status: ' + response.status);
            }
            return response.json();
        })
        .then(data => {
            console.log('Perfil atualizado com sucesso:', data);
            
            toggleModal('modal-atualizacao-cliente', false)
            
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Ocorreu um erro ao tentar atualizar o perfil.');
        });
    });

    document.getElementById('btn-voltar-modal-cliente-atualizacao').addEventListener('click', () => 
        toggleModal('modal-atualizacao-cliente', false));

    document.getElementById('btn-cancelar-endereco-atualizacao').addEventListener('click', () => 
        toggleModal('modal-atualizar-endereco', false));

    const linkSair = document.getElementById('nav-sair');

    linkSair.addEventListener('click', function(event) {
        event.preventDefault();

        localStorage.clear();
        location.reload();
        
    });

    const linkPerfilAtelie = document.getElementById('link-ver-perfil-atelie');
    
    linkPerfilAtelie.addEventListener('click', function (e) {
        e.preventDefault(); 
        
        fetch('http://localhost:8080/atelie/' + localStorage.getItem("idPessoa")) 
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro na requisição');
                }
                return response.json(); 
            })
            .then(data => {
                console.log('Atelie recebido:', data);
                document.getElementById('atelie-id-atualizar').value = data.idPessoa;
                document.getElementById('atelie-nome-atualizacao').value = data.nomePessoa;
                document.getElementById('atelie-cnpj-atualizacao').value = data.cnpj;
                document.getElementById('atelie-email-atualizacao').value = data.email;
                document.getElementById('atelie-telefone-atualizacao').value = data.telefone;
                document.getElementById('atelie-usuario-atualizacao').value = data.usuario;
                document.getElementById('atelie-senha-atualizacao').value = data.senha;
                document.getElementById('atelie-inicio-atualizacao').value = data.inicio01;
                document.getElementById('atelie-fim-atualizacao').value = data.fim01;
                
                toggleModal('modal-atualizacao-atelie', true);
                document.getElementById('btn-atualizar-endereco-atelie').addEventListener('click', function() {
                    toggleModal('modal-atualizar-endereco-atelie', true);
                    
                    fetch('http://localhost:8080/endereco/' + data.idEndereco) 
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('Erro na requisição');
                        }
                        return response.json(); 
                    })
                    .then(dataEndereco => {
                        console.log('Endereco recebido:', dataEndereco);
                        document.getElementById('endereco-id-atualizacao-atelie').value = dataEndereco.idEndereco
                        document.getElementById('modal-estado-atualizacao-atelie').value = dataEndereco.estado;
                        document.getElementById('modal-cidade-atualizacao-atelie').value = dataEndereco.cidade;
                        document.getElementById('modal-bairro-atualizacao-atelie').value = dataEndereco.bairro;
                        document.getElementById('modal-rua-atualizacao-atelie').value = dataEndereco.rua;
                        document.getElementById('modal-cep-atualizacao-atelie').value = dataEndereco.cep;
                        document.getElementById('modal-numero-atualizacao-atelie').value = dataEndereco.numero;
                        document.getElementById('modal-complemento-atualizacao-atelie').value = dataEndereco.complemento;
                        
                    })
                    .catch(error => {
                        console.error('Erro ao buscar perfil:', error);
                        alert('Erro ao buscar perfil!');
                    });
                })
            });     
        });

        document.getElementById('btn-cancelar-endereco-atualizacao-atelie').addEventListener('click', () => 
            toggleModal('modal-atualizar-endereco-atelie', false));

    const formEnderecoAtelie = document.getElementById('form-atualizar-endereco-atelie');

    formEnderecoAtelie.addEventListener('submit', function(e) {
        
        e.preventDefault();

        
        const formData = new FormData(formEnderecoAtelie);
        const dadosDoFormulario = Object.fromEntries(formData.entries());
        
        
        console.log(dadosDoFormulario)
        
        fetch(`http://localhost:8080/endereco`, { 
            method: 'PUT', 
            headers: {
                'Content-Type': 'application/json',
        
            },
            body: JSON.stringify(dadosDoFormulario)
        })
        .then(response => {
            if (!response.ok) {
                
                throw new Error('Falha ao atualizar o endereço. Status: ' + response.status);
            }
            return response.json();
        })
        .then(data => {
            console.log('Endereço atualizado com sucesso:', data);
            document.getElementById('endereco-id-atualizar-atelie').value = data.idEndereco
            console.log(document.getElementById('endereco-id-atualizar-atelie').value)
            toggleModal('modal-atualizar-endereco-atelie', false)
            
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Ocorreu um erro ao tentar atualizar o endereço.');
        });
    });

    const formAtualizarAtelie = document.getElementById('form-atualizacao-atelie');
    formAtualizarAtelie.addEventListener('submit', function(e) {
        
        e.preventDefault();

        
        const formData = new FormData(formAtualizarAtelie);
        const dadosDoFormulario = Object.fromEntries(formData.entries());
        
        
        console.log(dadosDoFormulario)
        
        fetch(`http://localhost:8080/atelie`, { 
            method: 'PUT', 
            headers: {
                'Content-Type': 'application/json',
        
            },
            body: JSON.stringify(dadosDoFormulario)
        })
        .then(response => {
            if (!response.ok) {
                
                throw new Error('Falha ao atualizar o perfil. Status: ' + response.status);
            }
            return response.json();
        })
        .then(data => {
            console.log('Perfil atualizado com sucesso:', data);
            
            toggleModal('modal-atualizacao-atelie', false)
            
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Ocorreu um erro ao tentar atualizar o perfil.');
        });
    });

    document.getElementById('btn-voltar-modal-atelie-atualizacao').addEventListener('click', () => 
        toggleModal('modal-atualizacao-atelie', false));


    const linkSairAtelie = document.getElementById('nav-sair-atelie');

    linkSairAtelie.addEventListener('click', function(event) {
        event.preventDefault();

        localStorage.clear();
        location.reload();
        
    });


    const linkPedidosAtelie = document.getElementById('nav-pedidos-atelie');
    
    const listaPedidosContainer = document.getElementById('lista-pedidos-feitos-atelie');
    



    linkPedidosAtelie.addEventListener('click', function(e) {
        e.preventDefault(); 

        toggleModal('modal-pedidos-atelie', true)
        
        listaPedidosContainer.innerHTML = '<p class="text-gray-500">Carregando pedidos...</p>';
        
        // Busca os dados da sua API
        // Assumindo que a API retorna um objeto com uma propriedade `content` que é um array de pedidos
        fetch(`http://localhost:8080/pedido/atelie/${localStorage.getItem("idPessoa")}`) // <-- Adapte a URL se necessário
            .then(response => {
                if (!response.ok) {
                    throw new Error('Falha ao buscar os pedidos.');
                }
                return response.json();
            })
            .then(data => {
                listaPedidosContainer.innerHTML = '';

                if (data.content && data.content.length > 0) {
                    
                    data.content.forEach(pedido => {
                        const pedidoElement = document.createElement('div');
                        pedidoElement.className = 'flex justify-between items-center py-3 border-b';
                        
                        
                        pedidoElement.innerHTML = `
                            <span class="text-gray-800">${pedido.descricao}</span>
                            <button 
                                class="btn-ver-detalhes py-1 px-3 text-sm bg-primary text-white rounded hover:bg-primary-dark"
                                data-pedido-id="${pedido.idPedido}">
                                Atualizar Pedido
                            </button>
                        `;
                        
                        listaPedidosContainer.appendChild(pedidoElement);
                    });
                } else {
                    listaPedidosContainer.innerHTML = '<p class="text-gray-500">Nenhum pedido encontrado.</p>';
                }
            })
            .catch(error => {
                console.error('Erro:', error);
                listaPedidosContainer.innerHTML = `<p class="text-red-500">Erro ao carregar os pedidos.</p>`;
            });
    });

    
    listaPedidosContainer.addEventListener('click', function(e) {
        // Verifica se o elemento clicado é um dos nossos botões
        
        if (e.target && e.target.classList.contains('btn-ver-detalhes')) {
            const pedidoId = e.target.dataset.pedidoId;
            console.log(`Botão clicado para o pedido com ID: ${pedidoId}`);
            fetch(`http://localhost:8080/pedido/${pedidoId}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Falha ao buscar o pedidos.');
                    }
                    return response.json();
                })
                .then(data => {
                    console.log(data)
                    document.getElementById('id-pedido').value = data.idPedido;
                    document.getElementById('modal-descricao-alterar').value = data.descricaoPedido;
                    document.getElementById('modal-status-atualizacao-pedido').value = data.status;
                    document.getElementById('modal-dataPrevisao-alterar').value = data.dataPrevisaoEntrega;
                    document.getElementById('modal-valor-alterar').value = data.valorTotal;
                    
                    toggleModal('modal-pedido-atelie-alterar', true)
                    
                })
                .catch(error => {
                    
                });
            
        }
    });

    document.getElementById('btn-fechar-pedidos-atelie').addEventListener('click', () => 
        toggleModal('modal-pedidos-atelie', false));


    const formAtualizarPedido = document.getElementById('form-pedido-atelie-atualizar');
    formAtualizarPedido.addEventListener('submit', function(e) {
        
        e.preventDefault();

        
        const formData = new FormData(formAtualizarPedido);
        const dadosDoFormulario = Object.fromEntries(formData.entries());
        
        
        console.log(dadosDoFormulario)
        
        fetch(`http://localhost:8080/pedido`, { 
            method: 'PUT', 
            headers: {
                'Content-Type': 'application/json',
        
            },
            body: JSON.stringify(dadosDoFormulario)
        })
        .then(response => {
            if (!response.ok) {
                
                throw new Error('Falha ao atualizar o pedido. Status: ' + response.status);
            }
            return response.json();
        })
        .then(data => {
            console.log('Pedido atualizado com sucesso:', data);
            
            toggleModal('modal-pedido-atelie-alterar', false)
            
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Ocorreu um erro ao tentar atualizar o pedido.');
        });
    });    

    document.getElementById('btn-cancelar-pedido-atelie').addEventListener('click', () => 
        toggleModal('modal-pedido-atelie-alterar', false));

    document.getElementById('btn-deletar-conta-cliente').addEventListener('click', () => {
        
        fetch(`http://localhost:8080/cliente/${localStorage.getItem("idPessoa")}`, { 
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            }
        })
        .then(response => {
            if (!response.ok) {
                alert("Você tem pedidos registrados, não é possível deletar a conta")
                throw new Error('Falha ao deletar o perfil. Status: ' + response.status);
            }
            return response.json();
        })
        .then(() => {
            localStorage.clear();
            location.reload();
        })
        .catch(err => {
            console.error("Erro ao deletar cliente:", err);
        });
    });


    document.getElementById('btn-deletar-conta-atelie').addEventListener('click', () => {
        
        fetch(`http://localhost:8080/atelie/${localStorage.getItem("idPessoa")}`, { 
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            }
        })
        .then(response => {
            if (!response.ok) {
                alert("Você tem pedidos registrados, não é possível deletar a conta")
                throw new Error('Falha ao deletar o perfil. Status: ' + response.status);
            }
            return response.json();
        })
        .then(() => {
            localStorage.clear();
            location.reload();
        })
        .catch(err => {
            console.error("Erro ao deletar atelie:", err);
        });
    });


    // Código para a navegação das abas da tela principal
    const sidebarItems = document.querySelectorAll('.sidebar-item');
    const tabContents = document.querySelectorAll('.tab-content');
    const mobileNavItems = document.querySelectorAll('[id^="mobile-nav-"]');

    function activateTab(tabId) {
        // Remove 'active' de todos os itens da sidebar e mobile nav, adiciona 'active' ao clicado
        sidebarItems.forEach(item => item.classList.remove('active'));
        mobileNavItems.forEach(item => item.classList.remove('text-primary', 'text-gray-500'));
        mobileNavItems.forEach(item => item.classList.add('text-gray-500')); // Resetar para cinza

        const desktopNavItem = document.getElementById(`nav-${tabId}`);
        if (desktopNavItem) desktopNavItem.classList.add('active');

        const mobileNavItem = document.getElementById(`mobile-nav-${tabId}`);
        if (mobileNavItem) {
            mobileNavItem.classList.remove('text-gray-500');
            mobileNavItem.classList.add('text-primary');
        }

        // Esconde todo o conteúdo das abas
        tabContents.forEach(content => content.classList.remove('active'));
        
        // Mostra o conteúdo da aba selecionada
        const activeContent = document.getElementById(`content-${tabId}`);
        if (activeContent) {
            activeContent.classList.add('active');
        }
    }

    // Adiciona event listeners para os itens da sidebar desktop
    sidebarItems.forEach(item => {
        const tabId = item.id.replace('nav-', '');
        item.addEventListener('click', function(e) {
            e.preventDefault();
            activateTab(tabId);
        });
    });

    // Adiciona event listeners para os itens da navegação mobile
    mobileNavItems.forEach(item => {
        const tabId = item.id.replace('mobile-nav-', '');
        // Handle mobile-nav-perfil separately if it's just a link, not a tab
        if (tabId === 'perfil') {
            // Se "Perfil" deve ir para uma página real ou outro modal, trate aqui
            return; 
        }
        item.addEventListener('click', function(e) {
            e.preventDefault();
            activateTab(tabId);
        });
    });

    // Lógica para o dropdown de usuário (já existente)
    const userMenuButton = document.getElementById('user-menu-button');
    const userMenu = document.getElementById('user-menu');

    if (userMenuButton && userMenu) {
        userMenuButton.addEventListener('click', function() {
            userMenu.classList.toggle('hidden');
        });

        document.addEventListener('click', function(event) {
            if (!userMenuButton.contains(event.target) && !userMenu.contains(event.target)) {
                userMenu.classList.add('hidden');
            }
        });
    }

    // Lógica para o botão de menu mobile (já existente)
    const mobileMenuButton = document.getElementById('mobile-menu-button');
    const sidebar = document.querySelector('.md\\:flex-shrink-0 > div'); // Seleciona a div da sidebar completa

    if (mobileMenuButton && sidebar) {
        mobileMenuButton.addEventListener('click', function() {
            sidebar.classList.toggle('hidden');
            sidebar.classList.toggle('flex'); // Alterna entre hidden e flex
        });
    }

    const cornerButton = document.getElementById('corner-button');
    const newScreen = document.getElementById('new-screen');
    const closeScreen = document.getElementById('close-screen');
    const overlay = document.getElementById('overlay');

    // Abrir a tela lateral
    cornerButton.addEventListener('click', function() {
        newScreen.classList.remove('translate-x-full');
        newScreen.classList.add('translate-x-0');
        overlay.classList.remove('hidden');
    });

    // Fechar a tela lateral
    function closeSidebar() {
        newScreen.classList.remove('translate-x-0');
        newScreen.classList.add('translate-x-full');
        overlay.classList.add('hidden');
    }

    closeScreen.addEventListener('click', closeSidebar);
    overlay.addEventListener('click', closeSidebar);

    

});
