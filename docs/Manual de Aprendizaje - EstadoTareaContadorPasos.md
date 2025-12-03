# Manual de Aprendizaje - EstadoTareaContadorPasos

## 1. Introducción

Este documento sirve como un manual de aprendizaje para desarrolladores de Android. Detalla la construcción de la aplicación `EstadoTarea`, un contador de pasos simple pero completamente funcional y personalizable. A través de este proyecto, se exploran conceptos clave del desarrollo de interfaces de usuario (UI), la gestión de estado y la personalización de componentes.

El propósito de la aplicación es permitir a un usuario:
1.  Definir una meta numérica (total de pasos).
2.  Avanzar paso a paso hacia esa meta.
3.  Visualizar el progreso tanto en formato de contador como en porcentaje.
4.  Reiniciar el conteo para empezar de nuevo.

## 2. Componentes de la Interfaz (UI)

El diseño de la aplicación se encuentra en `app/src/main/res/layout/activity_main.xml`. Está construido con un `ConstraintLayout` como contenedor principal, lo que permite un diseño plano y flexible.

- **Fondo General**: Se aplica un `drawable` con un degradado gris (`@drawable/gradient_gray`) al `ConstraintLayout` principal para dar un fondo sutil a toda la aplicación.

- **Título (`tvEstado`)**: Un `TextView` que muestra el título principal "ESTADO DE LA TAREA:". Su tamaño de texto ha sido aumentado para darle mayor jerarquía visual.

- **Visualización del Progreso**:
    - `tvLabelProgreso`: Etiqueta de texto "Progreso: ".
    - `tvProgreso`: `TextView` que muestra el contador numérico (ej: `5 / 10`).
    - `tvPorcentaje`: `TextView` que muestra el porcentaje completado (ej: `(50%)`).

- **Botones de Acción**:
    - `btnAvanzar`: Botón con el texto "AVANZAR UN PASO". Tiene un fondo verde oscuro (`#006400`).
    - `btnReiniciar`: Botón con el texto "REINICIAR TAREA". Tiene un fondo gris oscuro (`#505050`).

- **Selector de Pasos**:
    - `tvSelectorPasos`: Etiqueta de texto "Selector de pasos".
    - `npPasos`: Un `NumberPicker` que permite al usuario seleccionar el número total de pasos (de 1 a 50).

## 3. Lógica de la Aplicación (`MainActivity.kt`)

El comportamiento de la aplicación está definido en `app/src/main/java/com/chema/estadotarea/MainActivity.kt`.

### Variables Principales

- `pasosTotales`: Almacena el número total de pasos a completar, seleccionado por el usuario en el `NumberPicker`.
- `pasosActuales`: Contador de los pasos que se han completado.
- Referencias a las vistas (Views) para poder manipularlas (ej: `tvProgreso`, `btnAvanzar`).

### Flujo de Ejecución

1.  **`onCreate()`**: Es el punto de entrada. Aquí se inicializan las vistas usando `findViewById()` y se configuran los listeners para los botones y el `NumberPicker`.

2.  **`setOnValueChangedListener`**: Asociado al `NumberPicker`. Se dispara cada vez que el usuario cambia el valor:
    - Actualiza la variable `pasosTotales`.
    - Ajusta `pasosActuales` si el nuevo total es menor que los pasos ya dados (para evitar estados como `10 / 5`).
    - Llama a `actualizarProgreso()` para reflejar los cambios en la UI.

3.  **`setOnClickListener` para `btnAvanzar`**: Se ejecuta al pulsar el botón de avanzar:
    - Incrementa `pasosActuales` solo si no se ha alcanzado el total.
    - Llama a `actualizarProgreso()`.

4.  **`setOnClickListener` para `btnReiniciar`**: Se ejecuta al pulsar el botón de reiniciar:
    - Resetea `pasosActuales` a `0`.
    - Llama a `actualizarProgreso()`.

### Función Central: `actualizarProgreso()`

Esta función privada es clave, ya que centraliza toda la lógica de actualización de la interfaz:
- Calcula el porcentaje de progreso.
- Actualiza los `TextView` del contador y del porcentaje.
- **Habilita o deshabilita** el botón `btnAvanzar` comprobando si `pasosActuales < pasosTotales`. Esta es la lógica que impide seguir avanzando una vez completada la tarea.

## 4. Personalización Visual y Estilos

La personalización avanzada se logra mediante `drawables` y `styles`.

- **Drawables (`res/drawable/`)**:
    - `gradient_gray.xml`: Define el degradado gris para el fondo de la pantalla.
    - `gradient_orange.xml`: Define un degradado naranja más contrastado para el fondo del `NumberPicker`.

- **Estilos (`res/values/themes.xml`)**:
    - Se define un estilo personalizado `NumberPickerStyle` que hereda de `android:Widget.Material.NumberPicker`.
    - `android:textColorPrimary`: Se establece en rojo (`#FF0000`) para cambiar el color del número seleccionado.
    - `android:colorControlNormal`: Se establece en negro (`#000000`) para colorear las líneas separadoras, creando un efecto de "fondo" para el número seleccionado.

- **Escalado de Componentes**:
    - En `activity_main.xml`, se usan las propiedades `android:scaleX="1.25"` y `android:scaleY="1.25"` en el `NumberPicker` para aumentar su tamaño visual en un 25% sin alterar el layout de forma compleja.
