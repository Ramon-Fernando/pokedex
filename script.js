document.addEventListener("DOMContentLoaded", () => {
    const tbody = document.querySelector("#pokemonTable tbody");
    const filtroTipo = document.getElementById("filtroTipo");
    const filtroNome = document.getElementById("filtroNome");

    const btnAdicionar = document.getElementById("btnAdicionar");
    const formAdicionar = document.getElementById("formularioAdicionar");
    const btnSalvarNovo = document.getElementById("btnSalvarNovo");
    const inputNovoNome = document.getElementById("novoNome");
    const selectNovoTipo = document.getElementById("novoTipo");
    const selectNovoTipoSecundario = document.getElementById("novoTipoSecundario");

    let todosPokemons = [];
    let todosTipos = [];

    // Mostrar ou ocultar o formulário
    btnAdicionar.addEventListener("click", () => {
        formAdicionar.style.display = formAdicionar.style.display === "none" ? "block" : "none";
    });

    // Adicionar novo pokémon
    btnSalvarNovo.addEventListener("click", () => {
        const nome = inputNovoNome.value.trim();
        const tipoId = selectNovoTipo.value;
        const tipoSecId = selectNovoTipoSecundario.value;

        if (!nome || !tipoId) {
            alert("Nome e tipo primário são obrigatórios.");
            return;
        }

        const payload = {
            nome: nome,
            tipo: { id: parseInt(tipoId) },
            tipoSecundario: tipoSecId ? { id: parseInt(tipoSecId) } : null
        };

        fetch("http://localhost:8080/pokemons", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload)
        })
        .then(res => {
            if (!res.ok) throw new Error("Erro ao adicionar Pokémon");
            return res.json();
        })
        .then(() => {
            formAdicionar.style.display = "none";
            inputNovoNome.value = "";
            selectNovoTipo.value = "";
            selectNovoTipoSecundario.value = "";
            carregarPokemons();
        })
        .catch(err => alert("Erro ao adicionar Pokémon: " + err.message));
    });

    function preencherSelectsTipos(tipos) {
        selectNovoTipo.innerHTML = `<option value="">Selecione o tipo</option>`;
        selectNovoTipoSecundario.innerHTML = `<option value="">Nenhum</option>`;
        tipos.forEach(tipo => {
            const option1 = document.createElement("option");
            option1.value = tipo.id;
            option1.textContent = tipo.nome;
            selectNovoTipo.appendChild(option1);

            const option2 = document.createElement("option");
            option2.value = tipo.id;
            option2.textContent = tipo.nome;
            selectNovoTipoSecundario.appendChild(option2);
        });
    }

    function renderTabela(pokemons) {
        tbody.innerHTML = "";

        pokemons.forEach(pokemon => {
            const row = document.createElement("tr");

            row.innerHTML = `
                <td>${pokemon.id}</td>
                <td>${pokemon.nome}</td>
                <td>${pokemon.tipo.nome}</td>
                <td>${pokemon.tipoSecundario ? pokemon.tipoSecundario.nome : "-"}</td>
                <td>
                    <button class="edit-btn">Editar</button>
                    <button class="delete-btn">Deletar</button>
                </td>
            `;

            row.querySelector(".edit-btn").addEventListener("click", () => {
                editarPokemon(row, pokemon);
            });

            row.querySelector(".delete-btn").addEventListener("click", () => {
                if (confirm(`Tem certeza que deseja deletar ${pokemon.nome}?`)) {
                    fetch(`http://localhost:8080/pokemons/${pokemon.id}`, {
                        method: "DELETE"
                    })
                    .then(res => {
                        if (!res.ok) throw new Error("Erro ao deletar Pokémon");
                        carregarPokemons();
                    })
                    .catch(err => alert("Erro ao deletar: " + err.message));
                }
            });

            tbody.appendChild(row);
        });
    }

    function editarPokemon(row, pokemon) {
        row.innerHTML = `
            <td>${pokemon.id}</td>
            <td><input type="text" value="${pokemon.nome}" id="edit-nome-${pokemon.id}" /></td>
            <td>
                <select id="edit-tipo-${pokemon.id}">
                    ${todosTipos.map(tipo =>
                        `<option value="${tipo.id}" ${tipo.id === pokemon.tipo.id ? 'selected' : ''}>${tipo.nome}</option>`
                    ).join("")}
                </select>
            </td>
            <td>
                <select id="edit-tipo-secundario-${pokemon.id}">
                    <option value="">Nenhum</option>
                    ${todosTipos.map(tipo =>
                        `<option value="${tipo.id}" ${pokemon.tipoSecundario && tipo.id === pokemon.tipoSecundario.id ? 'selected' : ''}>${tipo.nome}</option>`
                    ).join("")}
                </select>
            </td>
            <td>
                <button class="save-btn">Salvar</button>
            </td>
        `;

        row.querySelector(".save-btn").addEventListener("click", () => {
            const nome = document.getElementById(`edit-nome-${pokemon.id}`).value;
            const tipoId = parseInt(document.getElementById(`edit-tipo-${pokemon.id}`).value);
            const tipoSecId = document.getElementById(`edit-tipo-secundario-${pokemon.id}`).value;

            const payload = {
                nome: nome,
                tipo: { id: tipoId },
                tipoSecundario: tipoSecId ? { id: parseInt(tipoSecId) } : null
            };

            fetch(`http://localhost:8080/pokemons/${pokemon.id}`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(payload)
            })
            .then(res => {
                if (!res.ok) throw new Error("Erro ao salvar Pokémon");
                return res.json();
            })
            .then(() => carregarPokemons())
            .catch(err => alert("Erro ao salvar Pokémon: " + err.message));
        });
    }

    function carregarPokemons() {
        fetch("http://localhost:8080/pokemons")
            .then(res => res.json())
            .then(data => {
                todosPokemons = data;
                renderTabela(todosPokemons);
                popularTiposUnicos(todosPokemons);
            })
            .catch(err => console.error("Erro ao buscar pokémons:", err));
    }

    function carregarTipos() {
        return fetch("http://localhost:8080/tipos")
            .then(res => res.json())
            .then(data => {
                todosTipos = data;
                preencherSelectsTipos(data);
                return data;
            })
            .catch(err => {
                console.error("Erro ao buscar tipos:", err);
                alert("Erro ao buscar tipos. Verifique se o servidor está rodando com CORS habilitado.");
            });
    }

    function popularTiposUnicos(pokemons) {
        const tiposUnicos = new Map();

        pokemons.forEach(p => {
            if (p.tipo) tiposUnicos.set(p.tipo.id, p.tipo.nome);
            if (p.tipoSecundario) tiposUnicos.set(p.tipoSecundario.id, p.tipoSecundario.nome);
        });

        filtroTipo.innerHTML = '<option value="">Todos os tipos</option>';

        tiposUnicos.forEach((nome, id) => {
            const option = document.createElement("option");
            option.value = id;
            option.textContent = nome;
            filtroTipo.appendChild(option);
        });
    }

    function aplicarFiltros() {
        const tipoId = filtroTipo.value;
        const nomeFiltro = filtroNome.value.toLowerCase();

        let filtrados = [...todosPokemons];

        if (tipoId) {
            filtrados = filtrados.filter(p =>
                (p.tipo && p.tipo.id == tipoId) ||
                (p.tipoSecundario && p.tipoSecundario.id == tipoId)
            );
        }

        if (nomeFiltro) {
            filtrados = filtrados.filter(p =>
                p.nome.toLowerCase().includes(nomeFiltro)
            );
        }

        renderTabela(filtrados);
    }

    filtroTipo.addEventListener("change", aplicarFiltros);
    filtroNome.addEventListener("input", aplicarFiltros);

    // Início
    carregarTipos().then(() => carregarPokemons());
});
