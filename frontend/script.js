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

                // Esconde a página de login e mostra a tela principal
                showPage('main-app'); 
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
            uf: document.getElementById('modal-uf').value,
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

    document.getElementById('btn-novo-pedido').addEventListener('click', async function() {
        
        const lista = document.getElementById('modal-atelie');
        lista.innerHTML = '<div>Carregando...</div>';
        // Troque esta URL pela da sua API!
        const resp = await fetch('http://localhost:8080/atelie');
        const especialidades = await resp.json();
        lista.innerHTML = `
            <label for="select-atelie" class="block mb-2 font-medium text-gray-700">Escolha um Ateliê:</label>
            <select id="select-atelie" name="atelie" class="w-full p-2 border rounded">
                ${especialidades.content.map(e => `
                    <option value="${e.idAtelie}">${e.nome}</option>
                `).join('')}
            </select>
            `;
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
            
            
            dados.idsMedida = JSON.parse(dados.idsMedida).map(id => parseInt(id, 10));
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

// Salvar forma de pagamento escolhida
    
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
});
