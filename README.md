# auction
基于区块链的拍卖平台

### geth dev
```bash
geth --datadir ./devdata/ --dev --http --http.addr 0.0.0.0 --http.api web3,eth,debug,personal,net --rpc.allow-unprotected-txs console
```

### solidity compile
+ solidity into the abi and bin 
  + idea config
    ```bash
    --abi --bin $FileName$ -o ./
    ```
+ abi and bin Into the java
  + web3j-cli download
    + windows
    ```bash
    Set-ExecutionPolicy Bypass -Scope Process -Force; iex ((New-Object System.Net.WebClient).DownloadString('https://raw.githubusercontent.com/web3j/web3j-installer/master/installer.ps1'))
    ``` 
    + linux
    ```bash
    curl -L get.web3j.io | sh
    source $HOME/.web3j/source.sh
    ```
  + Into the java
  ```bash
  web3j generate solidity --abiFile Dapp_sol_Dapp.abi --binFile Dapp_sol_Dapp.bin -o .\src\main\java -p xpit.top.action.contract
  ```
  ```bash
  web3j generate solidity --abiFile EcommerceStore.abi --binFile EcommerceStore.bin -o .\src\main\java -p xpit.top.action.contract
  ```
