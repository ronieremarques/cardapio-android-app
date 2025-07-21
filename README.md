# MyApplication2 – Cardápio Digital para Restaurante

## Descrição

Este aplicativo Android demonstra um **Cardápio Digital** para restaurantes, desenvolvido em Kotlin com Jetpack Compose. O app permite exibir pratos com imagens, vídeos, categorias e informações detalhadas, além de navegação entre diferentes telas.

## Demostração GIF

![20250721_105150](https://github.com/user-attachments/assets/e14859c4-d54b-48bf-a490-105bb90c6aef)

## Funcionalidades

- Tela inicial (Home) com dois botões:
  - **Restaurante:** Acessa o cardápio digital completo.
  - **Pesquisa:** (desabilitado por padrão)
- Tela de **Restaurante**:
  - Exibe categorias de pratos (Principal, Entrada, Sobremesa, Bebida)
  - Cada prato mostra imagem, nome, preço, tempo de preparo, ingredientes, disponibilidade
  - Imagem real do prato
  - Vídeo do ambiente do restaurante no topo
  - Botão "Assista" para vídeo do chef (em pratos selecionados)
  - Navegação de volta para a Home
- Tela de **Pesquisa**:
  - Exemplo de tela para expansão futura
  - Navegação de volta para a Home

## Estrutura de Navegação

- **MainActivity:** Tela inicial (Home)
- **RestauranteActivity:** Tela do cardápio digital
- **PesquisaActivity:** Tela de pesquisa (exemplo)

## Como rodar o projeto

1. **Clone o repositório** e abra no Android Studio.
2. **Adicione as imagens dos pratos** na pasta `app/src/main/res/drawable/`:
   - `risoto.png`, `parmegiana.png`, `caesar.png`, `sopa.png`, `brownie.png`, `suco.png`
3. **Crie a pasta `raw`** em `app/src/main/res/` e adicione os vídeos:
   - `ambiente.mp4` (vídeo do ambiente do restaurante)
   - `chef.mp4` (vídeo do chef)
4. **Compile e execute** o app em um emulador ou dispositivo Android.

## Dependências

- Kotlin
- Jetpack Compose
- Material3 (experimental)

## Observações

- O botão "Pesquisa" está desabilitado por padrão, mas pode ser habilitado facilmente no código.
- Para personalizar os pratos, basta editar a lista `pratos` em `RestauranteActivity.kt`.
- Para trocar imagens ou vídeos, basta substituir os arquivos nas pastas `drawable` e `raw`.

---

Desenvolvido para fins didáticos e demonstração de boas práticas com Compose e navegação em apps Android. 
