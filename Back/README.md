# **Vikings Barber Shop | Backend**
![N|Solid](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_pXv9zmbYh3z2Xv_esFbPXwRXk9xziap4Vw&s)

## **Indice**
1.Resumen
2.Detalle de la BDD y las tablas
2.Explicacion endpoints
3.Explicacion datos generado automaticos para esta version

## **Resumen**
El Backend se desarrolló con Java 11.
Para ejecutar el back simplemente hacer run del back en algún IDE por ejemplo :
 - IntellIJ
- Spring Tools 4
- Eclipse
- Visual Studio Code

Recordar que el backend por defecto se levanta en el puerto 8080.

Para esta version del proyecto simplemente se desarrollo el proceso de pedir Turnos y ABM Productos

## **Detalles BDD**
Por si se desea ver la base de datos simplemente levante el back e ir a la dirección: `localhost:8080/h2-console`

Saldrá un login y simplemente hay que darle Connect siempre que `UserName = sa` (Si desea tocar botón TestConnection para verificar conexión) 

Algunos detalles de las tablas:
- Roles
    - Id
    - Name *Varchar(20)*
- Persona_Roles
    - Persona_Id
    - Rol_Id
- Persona
    - Id
    - Email *Varchar(255)*
    - Password *Varchar(255)*
    - Username *Varchar(255)*
- Producto
    - Id
    - Nombre *Varchar(255)*
    - Descripcion *Varchar(255)*
    - Cantidad *Integer*
    - Coste *Float*
    - Precio *Float*
    - Proveedor *Varchar(255)*
    - Imagen *Varchar(255)*
    - Disponible *Boolean*
- Turno
    - Id
    - Dia *Integer*
    - Mes *Integer*
    - Hora *Varchar(255)*
    - Username *Varchar(255)*
    - Email *Varchar(255)*
    - Barbero *Varchar(255)*
    - Disponible *Boolean*

## **Endpoints**
Lista de Endpoint desarrollados junto a su metodo Http y que se supone que hace o retorna
1. **Auth**
Es la encargada de realizar los registros y login.
Su request raiz esta dado por: `/api/auth`
    - Post `/signin`: *Se encarga de realizar el login.*
        - *Para funcionar debe recibir los datos de username y passowrd.* 
    - Post `/signup`: *Se encarga de realizar registro de nuevo usuario*
        - *Necesito recibir los datos de registro* 

2. **Contacto**
Es el encargado de manejar los mensajes de 'Contactanos'.
Su request raiz es: `/api/contactos`
    - Post: *Se encarga de crear los mensajes de contacto*
    - Get: *Es el encargado de retornar todos los mensajes generados*
    - Delete: *Se encarga de eliminar un mensaje generado*

3. **Producto**
Esta se encarga de todo lo relacionado con productos
Su request raiz es: `/api/productos`
    - Post: *Se encarga de insertar un nuevo producto a la BDD*
    - Get: *Retorna toda la lista de productos*
    - Put: *Actualiza los datos de un producto*
    - Delete `/{id}`: *Es la encargada de borrar un producto segun su identificador*

4. **Test**
Se encarga de generar datos en la BDD.
Su request raiz es: `/api/test`
    - Get `/all-info`:*Si no existe datos en la bdd los inserta y retorna algunos de estos mensajes:*
        - "Ya hay Info en la BDD" 
        - "Se Agrego Informacion"

5. **Turno**
Se encarga de todo lo relacionado con los turnos
Su request raiz es: `/api/turnos`
    - Post: *Se encarga de crear nuevos turnos*
    - Get: *Retorna la lista de turnos*
    - Delete: *Borra un turno*


## **Datos automaticos**
En el caso de no existir datos en la BBD se genera estos datos:
1. Roles
Se inserta los 3 tipos de roles disponibles:
    - `ROLE_USER`: *Es el rol asignado a cualquier usuario que no sea barbero o administrador*
    - `ROLE_ADMIN`: *Como su nombre indica es el encargado de ser el administrador*
    - `ROLE_EMPLEADO`: *Rol para barberos tiene casi los mismos permisos que el Admin pero no tiene permitido borrar informacion*

2. Persona
Se genera 2 usuarios con `RoLE_ADMIN` y `ROLE_USER`:
    - Administrador: Usuario `admin` y Password: `admin1234`
    - User: Usuario:`user` y Password:`user1234`
3. Turno
Tomando el dia actual, genera turnos 15 dias antes y despues de la fecha actual
    > Lista Barberos: ["Alfredo","Ignacio","Miguel"]
    > Disponible: [true,false]
    > Horarios: Lunes a Viernes de 9 a 21 | Sabado de 8 a 13 | Domingo no se trabaja.

    - Al turno se le asigna por defecto al Administrador, para barbero y disponibilidad se asigna aleatoriamente un valor de su respectiva lista 

 4. Producto
Dada estas listas:
    > Lista de Proveedor: ["Proveedor Bruno","Proveedor Diego","Proveedor Luciano"]
    > Disponible: [true,false]

    - Se genera 36 productos asignandole aleatoriamente un proveedor y si esta disponible a la venta
5. Contacto
Genera 2 mensajes de contacto para que se pueda visualizar   