# Proejto batch leitor de arquivos

Neste projeto o objeto é criar um software que rode com spring boot e otilize-se de batches. Para leitura de um ou mais arquivos 
e produza um arquivo com resultado do processamento do primeiro aquiquivo.

O sistema deve verificar todos os arquivos da asta de entrada que seguirem a especificação abaixo e não deve se limitar a 
ler apenas o primeiro arquivo encontrado.

Este sistema deve continuar executando até a solicitação de parada do mesmo e se encontrar arquivos passiveis de interpretação
dentro das palastas citadas no tópico *Estrutura do arquivo de entrada* o emsmo deve interpretar e gerar uma saída.

## Insatação do projeto:
  Para a instalação do projeto deve-se apenas importalo para a sua IDE preferia, se estiver utulizando o ECLIPSE ou alguma 
distribuição modificada como o STS, apenas basta importa-lo como projeto maven e as dependencias serão altomaticamente baixadas
e seu projeto já estará pronto para utilização. Caso contrariodeve-se utilizar comandos maven ou os similares na ide que vc escolheu
parq que as dependencias do maven sejam baixadas e instaladas.



## Estrutura do arquivo

Inicialmente devemos notar que arquivo deve estar na pasta "%HOMEPATH%/data/in" e o arquivo de saida deve estar na pasta "%HOMEPATH%/data/out".

*AS PASTAS MENCIONADAS DEVEM ESTAR PREVIAMENTE CRIADAS*

### Estrutura do arquivo de entrada

O arquivo de entrada contem informações sobre, CLIENTE, VENDEDOR e VENDA. Segue agora a estrutura cada linha representando
as informçãoes apresentadas. Estes arquivos também devem apresentar a extenção ".dat" 

1. Cliente 
  O cliente é identificado em linhas que iniciam com o código **002** e segue a estrutura abaixo:
  
  **002çCNPJçNameçBusiness Area**
  
2. Vendedor
  O vendedor é identificado em linhas que iniciam com o código **001** e segue a estrutura abaixo:
  
  **001çCPFçNameçSalary**
  
3. Venda 
  O venda é identificado em linhas que iniciam com o código **003** e segue a estrutura abaixo:
  
  **003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name**
  
3.1 Itens
  Os itens sãoe struturas internas da vendao e estão representados pela estrutura abaixo:
  
  **[Item ID-Item Quantity-Item Price]**
  
  
## Exemplo de dados:

O exemplo abaixo mostra a estrutua interna de um arquivo a ser processo: 

001ç1234567891234çPedroç50000<br/>
001ç3245678865434çPauloç40000.99<br/>
002ç2345675434544345çJose da SilvaçRural<br/>
002ç2345675433444345çEduardo PereiraçRural<br/>
003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro<br/>
003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo<br/>


### Estrutura do arquivo de saída

O arquivo de saída deve ser gravado na pasta "%HOMEPATH%/data/out", sendo esta pasta preexistente. O arquivo deve 
ser gravado com a nomenclatura a seguir:

**time_milesegundos.done.dat**

1. Estrutura do arquivo de saída:
  O arquivo de saída deve conter as informação sobre:
  
  **Quantidade de clientes no arquivo de entrada**<br/>
  **Quantidade de vendedor no arquivo de entrada**<br/>
  **ID da venda mais cara**<br/>
  **O pior vendedor**<br/>
  

