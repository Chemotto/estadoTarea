# Proyecto EstadoTarea

## Descripción General

`EstadoTarea` es una sencilla aplicación de Android diseñada para realizar un seguimiento del progreso de una tarea. Permite al usuario definir un número total de pasos y avanzar uno por uno hasta completar la tarea.

## Características

- **Selector de Pasos**: Un selector numérico (`NumberPicker`) permite elegir el número total de pasos para completar la tarea (de 1 a 50).
- **Contador de Progreso**: Muestra el progreso actual en dos formatos:
    - Un contador de pasos dados sobre el total (ej: `5 / 10`).
    - Un porcentaje del progreso completado (ej: `(50%)`).
- **Control de Avance**:
    - **`AVANZAR UN PASO`**: Botón para incrementar en uno los pasos completados. Se deshabilita automáticamente al alcanzar el 100%.
    - **`REINICIAR TAREA`**: Botón para reiniciar el contador de pasos a cero, manteniendo el total seleccionado.

## Personalización Visual

La interfaz ha sido personalizada con los siguientes estilos:

- **Fondo General**: Un sutil degradado de gris claro.
- **Botones**:
    - `AVANZAR UN PASO`: Fondo verde oscuro.
    - `REINICIAR TAREA`: Fondo gris oscuro.
- **Selector de Pasos (`NumberPicker`)**:
    - **Fondo**: Un degradado naranja personalizado para que sea más llamativo.
    - **Estilo de Números**: El número seleccionado se muestra en **rojo**, enmarcado por dos líneas separadoras de color **negro**.
    - **Tamaño**: El componente ha sido escalado a un 125% de su tamaño original para mejorar la visibilidad.
- **Textos**:
    - El título principal "ESTADO DE LA TAREA" ha sido aumentado de tamaño para mayor jerarquía.
    - El texto del progreso ha sido reducido para equilibrar el diseño.

## Lógica de la Aplicación (`MainActivity.kt`)

- La lógica principal gestiona el estado de los pasos (`pasosActuales` y `pasosTotales`).
- Una función `actualizarProgreso()` centraliza la actualización de la UI (textos y estado del botón) cada vez que hay un cambio.
- El `NumberPicker` permite un desplazamiento circular (`wrapSelectorWheel = true`) para una selección más cómoda.
