# Challenge ML - Pronóstico de Clima de Sitema Solar

 Solucion de la prueba de ingreso a Mercado Libre.

## Resumen del enunciado

Existe un sistema solar conformado por 3 planetas: Ferrengi, Betasoide y Vulcano

- El planeta Ferengi se desplaza con una velocidad angular de 1 grados/día en sentido horario. Su distancia con respecto al sol es de 500Km.
- El planeta Betasoide se desplaza con una velocidad angular de 3 grados/día en sentido horario. Su distancia con respecto al sol es de 2000Km.
- El planeta Vulcano se desplaza con una velocidad angular de 5 grados/día en sentido antihorario, su distancia con respecto al sol es de 1000Km.

- Cuando los tres planetas están alineados entre sí y con sol, se experimenta un período de sequía.
![image](https://user-images.githubusercontent.com/43072051/126923524-c6a90749-926a-4127-a346-fe4e1c6d1ad5.png)
- Cuando los tres planetas no están alineados, forman entre sí un triángulo, si el sol se encuentra dentro del triángulo, el sistema solar experimenta un período de lluvia, siendo la intensidad de este proporcial al perímetro del triángulo.
![image](https://user-images.githubusercontent.com/43072051/126923564-861cf3ad-d930-498b-9111-19942c5ae8f8.png)
- Las condiciones óptimas de presión y temperatura se dan cuando los tres planetas están alineados entre sí pero no están alineados con el sol.
![image](https://user-images.githubusercontent.com/43072051/126923579-639779bd-be71-4948-a4bf-bf3e75f61786.png)

Realizar un programa informático para poder predecir en los próximos 10 años:
1. ¿Cuántos períodos de sequía habrá?
2. ¿Cuántos períodos de lluvia habrá y qué día será el pico máximo de lluvia?
3. ¿Cuántos períodos de condiciones óptimas de presión y temperatura habrá?
 
## Stack tecnológico utilizado

##### BackEnd
- Java 11
- Spring Boot 2.5.2
- Gradle 
- Lombok
##### Testing
- Mockito
- JUnit
- Swagger
##### Despliegue
- Heroku
- Docker
##### Base de datos
- PostgreSQL 13 (despliegue)
- H2 (local)

## Solución

La solucion se encuentra desplegada en Heroku, el siguiente es el enlace para acceder a swagger

https://ml-challenge-sswf.herokuapp.com/swagger-ui.html

#### Servicios:

##### Obtener pronóstico por día, este servicio muestra tambien la posicion de los planetas para la comprobacion

https://ml-challenge-sswf.herokuapp.com/api/sswf/weather?day=123

Para comprobar la solucion utilizar la siguiente grafica y reemplazar los puntos de P1, P2 y P3

https://www.geogebra.org/classic/yjwppygq
 
##### Obtener resumen del pronóstico a 10 años, con este servicio se solucionan los 3 puntos

https://ml-challenge-sswf.herokuapp.com/api/sswf/summary

## Instalación Local

### Requerimientos

Docker

- Desccargar repositorio y en la carpeta raíz ejecutar el comando:
  ```docker-compose up -d```
- Acceder a http://localhost/swagger-ui

 ## Particularidades de la solución
 
- Debido a que el periodo que se encuentran los planetas alineados es menor a un dia, para realizar el pronóstico se simplifican los numeros de la ecuacion de la linea recta.
- Los planetas inician alineados a 90 grados.
- Un periodo son uno o más dias consecutivos con el mismo pronóstico.

## Formulas Utilizadas
##### Formula de vectores en R2 para determinar si 3 puntos están alineados
![image](https://user-images.githubusercontent.com/43072051/126928637-1f6cc19c-31d3-4c94-95b4-ea75a71fbf40.png)
##### Formulas para determinar cordenadas en la órbita
![image](https://user-images.githubusercontent.com/43072051/126928681-146216d5-0f4b-4e7b-8625-866ac5f0d919.png)
##### Formula para determinar el area
si el sol esta dentro del triangulo, se suman las areas de los 3 triangulos que se forman y si el resultado es igual al area del triangulo original, entonces el sol está dentro
![image](https://user-images.githubusercontent.com/43072051/126928882-14c8cea3-a658-483c-b864-af185ea865d9.png)
##### Formula para determinar el perímetro
![image](https://user-images.githubusercontent.com/43072051/126928912-fb02cbf9-705c-48b6-922f-78e0ca144dfc.png)





