# APP-NEWS

App Noticias 
By Shiomara Huaracallo 

En la presente aplicacion se hizo uso de Clean Architecture de la mano con la arquitectura MVVM.
Con la finalidad de una arquitectura limpia, se dividio el proyecto en 3 capas 
 - Domain
    Como capa mas interna, para los modelos de datos, logica del negocio y al ser un proyecto pequeño se incluye casos de usos en esta capa
 - Data
    Capa para la definicion abstracta de diferentes fuentes de datos. Se incluye acceso al Repositorio y persistencia de datos con Room
 - App 
    Capa que interactua con el usuario. Se incluye activitys, adapters, views, viewmodels 

El proyecto cuenta con corrutinas e inyeccion de dependencias(Koin) 

 
