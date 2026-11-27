
# Pétalo 🌷

## Descripción del proyecto

Pétalo es una aplicación móvil desarrollada para la venta y administración de flores. Permite consultar productos, registrar usuarios, administrar pedidos y gestionar un carrito de compras.

## Objetivo

Facilitar la administración de una florería mediante una aplicación móvil con una interfaz sencilla, almacenamiento local y una arquitectura organizada.

## Funcionalidades

- Inicio de sesión.
- Registro de usuarios.
- Catálogo de flores.
- Perfil de usuario.
- Administración de flores.
- Administración de pedidos.
- Administración de usuarios.
- Gestión del carrito de compras.
- Operaciones CRUD.
- Validación de formularios.
- Almacenamiento local de información.

## Tecnologías utilizadas

- Kotlin.
- Android Studio.
- Jetpack Compose.
- Material Design 3.
- Room.
- SQLite.
- Hilt.
- Navigation Compose.
- Kotlin Coroutines.
- StateFlow.
- Git y GitHub.

## Arquitectura

La aplicación utiliza la arquitectura MVVM y una organización por capas:

- Data: acceso y almacenamiento de datos.
- Domain: modelos y contratos del repositorio.
- UI: pantallas y ViewModel.

## Base de datos

Se utiliza Room para administrar cuatro tablas:

| Tabla | Función |
|---|---|
| Flor | Almacenar las flores disponibles. |
| Pedido | Registrar los pedidos de los clientes. |
| Usuario | Administrar los usuarios. |
| Carrito | Registrar los productos seleccionados. |

Cada tabla cuenta con operaciones para insertar, consultar, actualizar y eliminar registros.

## Patrones de diseño

- Singleton.
- Repository.
- Observer.
- Flujo unidireccional de datos (UDF).
- Inyección de dependencias.

## Inyección de dependencias

Se utiliza Hilt para proporcionar las dependencias del repositorio, la base de datos y el ViewModel.

## Navegación

Se utiliza NavHost para controlar la navegación entre las diferentes pantallas de la aplicación.

## Instalación

1. Clonar el repositorio.
2. Abrir el proyecto en Android Studio.
3. Sincronizar las dependencias de Gradle.
4. Seleccionar un emulador o dispositivo Android.
5. Ejecutar la aplicación.

## Estado del proyecto

Proyecto académico en desarrollo, con almacenamiento local, arquitectura MVVM y administración de datos mediante Room.
