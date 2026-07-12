# Pico Botella

Juego móvil desarrollado en **Kotlin para Android**, inspirado en el clásico juego de *"Pico Botella"*.  
Los jugadores forman un círculo, giran la botella virtual desde la aplicación y quien resulte señalado debe cumplir un reto aleatorio.

> **Proyecto académico**  
> Universidad del Valle  
> Escuela de Ingeniería de Sistemas y Computación  
> Curso: Desarrollo de Aplicaciones para Dispositivos Móviles  
> Miniproyecto 1 

---

# Descripción

La aplicación simula el tradicional juego de la botella:

- El jugador presiona un botón para girar una botella animada.
- La botella se detiene en una dirección aleatoria.
- La aplicación selecciona un reto de una lista local editable por el usuario.
- Se muestra un Pokémon aleatorio obtenido mediante una API pública junto con el reto seleccionado.


# Arquitectura y tecnologías

| Tecnología | Uso |
|------------|-----|
| **Kotlin** | Lenguaje principal |
| **MVVM** | Arquitectura de la aplicación |
| **Repository Pattern** | Separación de acceso a datos |
| **Room (SQLite)** | Persistencia local |
| **Retrofit** | Consumo de API REST |
| **Navigation Component** | Navegación entre pantallas |
| **Fragments** | Construcción de interfaces |
| **ViewBinding** | Acceso seguro a vistas |
| **Corrutinas Kotlin** | Manejo de tareas asíncronas |

---

# Estructura del proyecto

```
JUEGO-PICO-BOTELLA-DAPDM/
│
└── app/
    │
    └── src/
        │
        ├── androidTest/
        │
        └── main/
            │
            ├── java/
            │   └── ... 
            │       # Paquetes organizados por capas:
            │       # data, domain, ui, viewmodel
            │
            └── res/
                │
                ├── anim/
                │   # Animaciones (giro de botella, splash, etc.)
                │
                ├── drawable/
                │   # Imágenes y recursos gráficos
                │
                ├── layout/
                │   # Diseños XML de pantallas y diálogos
                │
                ├── navigation/
                │   # Grafo de navegación nav_graph.xml
                │
                └── raw/
                    # Recursos multimedia como audio
```

---

# Cómo ejecutar el proyecto

## 1. Clonar el repositorio

```bash
git clone <URL_DEL_REPOSITORIO>
```

## 2. Abrir el proyecto

Abrir la carpeta del proyecto desde:

```
Android Studio → File → Open
```

Se recomienda utilizar:

```
Android Studio Hedgehog o superior
```

## 3. Sincronizar Gradle

Ejecutar:

```
File → Sync Project with Gradle Files
```

Esperar a que finalice la configuración de dependencias.

## 4. Ejecutar la aplicación

Ejecutar el proyecto en:

- Emulador Android.
- Dispositivo físico conectado mediante USB.

Requisitos mínimos:

```
Android 8.0 (API 26) o superior
```

> Si el emulador presenta problemas de ejecución, verificar que la **virtualización esté activada desde la BIOS/UEFI** del equipo.

---

# API externa

Los datos de Pokémon utilizados en el diálogo del reto aleatorio son obtenidos mediante una API pública:

```
https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/pokedex.json
```

---

# Autores

Proyecto desarrollado por estudiantes de:

**Universidad del Valle**  
**Ingeniería de Sistemas y Computación**
