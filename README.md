# 🏫 Sistema de Contratação de Docentes

Este é um sistema de gestão desenvolvido em **Java** para automatizar o processo de contratação de professores e organização de processos seletivos académicos. O foco principal do projeto foi a aplicação prática de **Estruturas de Dados** para manipulação e persistência de informações em ficheiros.

---

### 🚀 Funcionalidades Principais

* **Gestão Completa (CRUD):** Registro, consulta, atualização e remoção de Cursos, Disciplinas, Professores e Inscrições.
* **Classificação Dinâmica:** Ordenação de candidatos por pontuação para cada disciplina, facilitando a tomada de decisão no processo seletivo.
* **Monitorização de Processos:** Listagem de processos ativos organizados por curso através de uma **Tabela Hash**.
* **Persistência de Dados:** Armazenamento estruturado em ficheiros `.csv`, garantindo que os dados sejam mantidos sem a necessidade de um servidor de base de dados externo.

---

### 🛠️ Tecnologias e Conceitos Aplicados

* **Linguagem:** Java.
* **Interface Gráfica (GUI):** Java Swing para uma experiência de utilizador desktop intuitiva.
* **Estruturas de Dados Customizadas:** Implementação e uso de **Listas Encadeadas, Filas e Pilhas** para gestão de memória e performance.
* **Algoritmos de Pesquisa e Ordenação:**
    * **Tabela Hash:** Utilizada para a indexação rápida de disciplinas baseada no código do curso.
    * **Bubble Sort:** Implementado para a classificação dos inscritos por pontuação.
* **Arquitetura:** Organização baseada no padrão **MVC (Model-View-Controller)** para separação de responsabilidades.

---

### 📂 Estrutura do Projeto

* **`src/model`**: Classes de domínio (Curso, Disciplina, Professor, Inscrições).
* **`src/controller`**: Lógica de negócio e manipulação de ficheiros CSV.
* **`src/view`**: Interfaces gráficas (Telas de Menu, Cadastro, Processos e Manual).
* **`libs`**: Bibliotecas internas (.jar) contendo as implementações de Lista, Fila e Pilha desenvolvidas academicamente.

---

### ⚙️ Como Executar

1.  Certifique-se de ter o **JDK 11** ou superior instalado.
2.  Importe o projeto numa IDE (Eclipse/IntelliJ).
3.  Configure as bibliotecas em `libs/` no seu *classpath*.
4.  Execute a classe `src/view/TelaMenu.java` para iniciar a aplicação.

---

> **Nota:** Este é um projeto académico desenvolvido para consolidar conhecimentos em algoritmos e estruturas de dados na **Fatec ZL**. Ele demonstra a capacidade de construir sistemas funcionais "do zero", incluindo a manipulação direta de ficheiros e lógica de estruturas lineares e não lineares.
