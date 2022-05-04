# Programacion Funcional
### Stream :  
Metodo para trabajar de una forma mas amena con las colecciones.

### Lambdas :
funcion anonima  -  Funcion que va a permitir generar un comportamiento sin la necesidad de declarar dicha funcion para que sea usado de forma global.


1. Filter (param : Predicate)
   ```
   Filtrar los datos y te devuelve otro stream().
   Predicate: Expresion que retorna una expresion Boleana : true o false
   
   ```
2. Map  (param: Function)
   ```
   Se usa para poder transformar de una tipo A  a un tipo B .
   Function: Espera retornar un valor
   ```
3. Sorted (param: Comparator)
   ```
   Se usa para ordenar una coleccion
   Comparator: Indicar un criterio o comparador de ordenamiento
   ```
4. Match (param: Predicate)
   ```
   Variantes para devolver expresion boleana de algun criterio que se  esta especificando en ese punto.
   
   - anyMatch: No evalua todo el String
   - allMatch: Evalua todo el stream bajo la condicion
   - noneMatch: Evalua todo el stream bajo la condicion
   ```
5. Limit/Skip
   ```
   Se utiliza en paginación
   
   - skip: Indica el inicio , de que posicion querras q te muestre
   - limit: Indica la cantidad que querras que te muestre (los primeros)
   ```
6. Collectors
   ```
   
   - groupingBy: Devuelve un map el tipo del que se especifica y el criterio del mismo .
   - counting: Contar la cantidad de elementos de acuerdo a un criterio que yo estoy agrupando. 
   
   ```