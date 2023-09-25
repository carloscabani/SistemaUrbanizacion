# SistemaUrbanizacion

# Proyecto: Sistema de ingreso a urbanizaciones

**VERSION:** 14.01.2023

**Autores**
- Geovanny Alexander Nieves Reyes
- Carlos Abel Cabanilla Tomalá

## Información General
**Cómo ejecutar el proyecto:** Se recomienda realizar un "Clean and Build", usar Java JDK19.0.1 y JavaFX19. Ejecutar el método `main()` de la clase `Sistema.java` para interactuar
con la interfaz. Es necesario contar con archivos .csv con la información de las personas, localizados en `storage/personas`. Además, los íconos
y las imágenes necesarias se ubican en `storage/images`.

**Descripción:** Este Sistema implementa una interfaz gráfica amigable para administrar un 
medio de ingreso y almacenamiento de datos de una urbanización. Además, permite manipular permisos de entrada 
y ejecutar procesos de revisión eficientes. Si el proyecto se ejecuta por primera vez, el `Sistema` instancia los datos iniciales de la 
urbanización y 6 permisos de ejemplo. Utiliza los archivos .csv para leer la información de las personas. Es posible modificar los datos de la urbanización 
y crear, consultar y eliminar permisos. Los permisos nuevos solo pueden crearlos visitantes con estado ACTIVO, al crearlo, 
obtendrás un código único que se entrega al visitante que lo podrá usar a la hora de visita correspondiente hasta que caduque el permiso. Para 
registrar la visita de un visitante, es necesario ubicarse en "Revisión de Entrada" del menú lateral para, mediante el código único, consultar los detalles 
de la visita y autorizar o negar el ingreso. Adicionalmente, se pueden crear reportes para analizar los permisos creados por los residentes.

**Objetivo:** Sistema que administre los permisos de entrada a una urbanización,
realizando validaciones de ingreso y generando reportes de control.

**Lenguaje Utilizado:** Java

**Interfaz Gráfica:** Sí

**Instrucciones Uso:** El proyecto está diseñado bajo la arquitectura MVC. Se ejecuta el método `main()` de la clase `Sistema.java`
para que el programa lance la interfaz gráfica del proyecto. Para más información, consulta la documentación.
