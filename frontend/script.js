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
    
    // Configurar formulários
    const loginForm = document.getElementById('login-form');
    if (loginForm) {
        loginForm.addEventListener('submit', function(e) {
            e.preventDefault();
            // Simulando uma resposta do backend
            const success = true; // Troque para false para testar a mensagem de erro
            if (success) {
                // Fecha quaisquer modais abertos
                toggleModal('modal-cadastro-cliente', false);
                toggleModal('modal-cadastro-atelie', false);
                toggleModal('message-modal', false); 

                // Esconde a página de login e mostra a tela principal
                showPage('main-app'); 
                
                // Opcional: Se quiser mostrar uma mensagem de "Login bem-sucedido" antes de ir
                // showMessageModal('success', 'Login Realizado!', 'Você foi logado com sucesso no AteliMatch. Redirecionando...');
                // setTimeout(() => {
                //     showPage('main-app');
                //     document.getElementById('login-page').classList.remove('active');
                // }, 1500); // Redireciona após 1.5 segundos

            } else {
                showMessageModal('error', 'Erro no Login', 'Usuário ou senha inválidos. Tente novamente.');
            }
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
            // Aqui você enviaria os dados para o backend
            // Simulando uma resposta do backend
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

        
        document.getElementById('endereco-id').value = resposta.idEndereco; // campo hidden do formulário do cliente

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
    toggleModal('modal-especialidades', true);
    const lista = document.getElementById('lista-especialidade');
    lista.innerHTML = '<div>Carregando...</div>';
    // Troque esta URL pela da sua API!
    const resp = await fetch('http://localhost:8080/especialidade');
    const especialidades = await resp.json();
    console.log(especialidades);
    lista.innerHTML = especialidades.content.map(e => `
      <div>
        <label class="inline-flex items-center">
          <input type="radio" name="radio-especialidade" value="${e.nome}" class="especialidade-radio mr-2">
          <span>${e.nome}</span>
        </label>
      </div>
    `).join('');
});

// Fechar modal no cancelar
document.getElementById('btn-cancelar-especialidades').addEventListener('click', function() {
    toggleModal('modal-especialidades', false);
});

// Salvar especialidade escolhida
document.getElementById('form-especialidades').addEventListener('submit', function(e) {
    e.preventDefault();
    const selecionada = document.querySelector('.especialidade-radio:checked');
    if (selecionada) {
        document.getElementById('especialidade-id').value = selecionada.value;
        document.getElementById('especialidade-resumo').innerText = selecionada.nextElementSibling.innerText;
        toggleModal('modal-especialidades', false);
    } else {
        alert("Escolha uma especialidade.");
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
