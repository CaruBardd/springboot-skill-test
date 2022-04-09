# SpringBoot Skill Test REST API v1.0

_REST API que permite el uso de una base de datos sencilla para relacionar usuarios con facturas_

## Ejecutar en Docker 🚀

Para ejecutar en docker, se debe descargar y descomprimir el proyecto.
Abrir CMD en carpeta raíz del proyecto ( /springboot-skill-test/ ).
Ejecutar
```
docker-compose up 
```

### Acceso a la API 📋

Para acceder a la API, primero se debe crear un usuario por medio de POSTMAN mientras se ejecuta el proyecto.
1. Desde el método POST, ingresar la URL:
_localhost:9898/auth/new_
Seleccionar Body > Raw > JSON y diligenciar formato:
```
{
    "name":"Nombre de Persona",
    "username":"Nombre de Usuario (Para el Login)",
    "email":"Correo Electrónico",
    "password":"Contraseña (Para el login)",
    "roles":["admin"]
}
```
_"roles":["admin"]_ es opcional, en caso de no diligenciar, se creará un usuario de rol "user", con acceso limitado.
Deberá devolver _Status Code 201 Created_ y mensaje _Usuario Guardado_

2. Iniciar sesión para extraer API Token. Escribir URL
_localhost:9898/auth/login_
Y diligenciar Body > Raw > JSON:
```
{
    "username":"Nombre de Usuario",
    "password":"Contraseña"
}
```
Devolverá un JSON con valores
```
{
    "token": "token",
    "bearer": "Bearer",
    "username": "Nombre de Usuario ( Logeado )",
    "authorities": [
        {
            "authority": "ROLE_USER"
        },
        {
            "authority": "ROLE_ADMIN"
        }
    ]
}
```
Importante **copiar el token**.

3. Configurar Token.
En el apartado Authorization, seleccionar llave de tipo _"Bearer Token"_ y en el valor de la llave, establecer
```
Bearar tokenKey
```

4. Realizar peticiones.

## API REST
El acceso a la api se brinda a partir de URL Base:
```
localhost:9898/api/
```

* Crear Usuario - ADMIN - POST - /create/user/id/nombre/edad/correo
```
/create/user/10/John Doe/30/johndoe@example.com
```

* Crear Factura - ADMIN - POST - /create/bill/id/valorTotal/descripcion/idDeUsuario
```
/create/bill/5/154.56/Paquete de mentas/10
```

* Mostrar Facturas de un Usuario - USER/ADMIN - GET - /show/idDeUsuario
```
/show/10
```

* Actualizar Factura - ADMIN - /update/bill/id/valorTotal/descripcion/idDeUsuario
```
/update/bill/5/1540.50/Paquete de mentas X 10/10
```

* Eliminar Factura - ADMIN - /delete/bill/idDeFactura
```
/delete/bill/5
```

## Construido con 🛠️

* [SpringBoot](https://spring.io/projects/spring-boot) - El framework web usado
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [ProgreSQL](https://www.postgresql.org/) - Usado para gestionar Base de Datos
* [Docker](https://www.docker.com/) - Usado para crear Dockerfile y Docker-compose


