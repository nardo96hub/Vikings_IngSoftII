# **Vikings Barber Shop | Ejecutables**
![N|Solid](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_pXv9zmbYh3z2Xv_esFbPXwRXk9xziap4Vw&s)
 
## **Indice**
- **Requisitos**
- **Comandos para ejecutar**
- **Problemas con puertos**

### Requisitos
- **Backend**
Tener instalado Java 8/11 *Se recomienda Jdk11*
- **Frontend**
Tener instalado Node
Versiones:
    - Node: 18.20.0
    - Angular CLI: 17.3.8
Instalar *globalmente* **http-server** ejecutando:
        ```
        npm install -g http-server
        ```
### Comandos para ejecutar 
Es importante que se ejecute primero el backend
- Backend
En la carpeta Back ver que este el archivo `Vikings Barberia.jar`
    1. Abrir consola de comandos
    2. Ejecutar `java -jar "Vikings Barberia.jar"`
    3. Para detener la ejecuciones usar : `Control + C`. 
    Se dara cuenta que se detuvo si le deja escribir comandos

- Frontend
En la carpeta `Front/proyecto-angular` 
    1. Abrir consola de comandos
    2. Ejecutar `http-server -p 4200`
    3. Le aparecera 3 posibles url copiar cualquiera y pegar en el navegador
    4. Para detener la ejecuciones usar : `Control + C`. 
Se dara cuenta que se detuvo si le deja escribir comandos

## Problemas con puertos
Si existe algun conflico al ejecutar el front o back por problemas con puerto en uso. Siga estas instrucciones:

- Para ver si esta en uso los puertos puede ejecutar:  
	 ```netstat -ano| findstr "PUERTO"```
	 EN CASO DE NO FUNCIONAR buscar alguna combinacion de `netstat -ano`

	 La informacion que muestra corresponde a:
	- Protocolo 
	- Direccion local 
	- Direccion remota
	- Estado 
	- Pid	

- Kill puerto:
      Al ejecutar `netstat` conseguira un codigo **Pid** y tendra que ejecutar:
	```
    taskkill /F /PID (NºPid sin parentesis)
    ```

