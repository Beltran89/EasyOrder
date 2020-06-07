# EasyOrder
Índice


1.	Módulos formativos aplicados en el trabajo	2

2.	Objetivos del proyecto	2

3.	Herramientas/Lenguajes utilizados.	2

4.	Fases del proyecto (punto más extenso con el grueso del trabajo)	3
Fase 1: Lector Código Bidi	3
Fase 2: Creación Layout Carta	4
Fase 3: Creación BBDD “Productos” y tablas Refrescos, Cervezas y Batidos.	5
Fase 4: Creación de la tabla pedido.	6
Fase 5: Creación del Activity Cerveza:	6
Fase 6: Creación del Activity Carrito y clase Controlador.	7
Fase 7: Pruebas	8
Fase 8: Implementación Activity batidos y refrescos.	9
Fase 9: Creación Activity Shishas.	10
Fase 10: Creación Activity Combinado.	11
Fase 11: Creación clase insert	12
Fase 12: Creación del Splash.	13

5.	Conclusiones/Resultado final del proyecto	13

6.	Biografía	13








1.	Módulos formativos aplicados en el trabajo


Para la realización del proyecto se han utilizados los módulos:
-Programación  ( Java).
-Acceso a Datos ( conexión JDBC) 
-Base de Datos (creación, consultas….), 
-Programación Multimedia (Android)



2.	Objetivos del proyecto

EL objetivo del proyecto era desarrollar una aplicación para solicitar en un negocio el pedido sin el uso de una carta física y la toma del pedido por una persona física (Camarer@).

Durante la reacción del proyecto se ha vivido una situación muy anómala en el mundo y el uso de esta aplicación ha quedado muy útil para la realización en el sector de la hostería, pudiendo evitar el contacto de los diferentes clientes de las cartas que se repartían en el negocio para la elección del producto, creando una barrera tecnológica para evitar contagios.





3.	Herramientas/Lenguajes utilizados.


Lenguaje Java: Para el grueso del proyecto y por medio de la FrameWork Android Studio se ha codificado todo en un lenguaje Java(creación de métodos, clases, objetos…).

SQL: Para la creación y utilización de las diferentes tablas de productos y pedidos, se ha realizado por medio de las consultas SQL y la visualización de las mismas por medio de la aplicación PHPMYADMIN instalada en mi equipo.

PHPMYADMIN/XAMPP: Gestión de la BBDD, tanto por medio de consultas SQL y como de forma visual por las diferentes opciones que ofrece.








4.	Fases del proyecto (punto más extenso con el grueso del trabajo)

Fase 1: Lector Código Bidi

		Para la lectura de los códigos Bidi que se encontraran en la mesa del negocio por medio de nuestro dispositivo, se ha realizado por medio de la librería ZXING que se ha implementado en el build.gradle de nuestra app. Posteriores se ha integrado el código de en una clase MainActivity de nuestro proyecto. En esta primera prueba de funcionamiento se recoge un texto de un código Bidi generado en la página https://www.codigos-qr.com/generador-de-codigos-qr/ y se muestra en un TOAST para comprobar que funciona a la perfección.


 


En el archivo AndroidManifest ha sido necesario dar permisos de uso de cámara que nos los pedirá la primera vez que hagamos uso de la App


Fase 2: Creación Layout Carta

		En esta parte por medio se crea un Activity con las imágenes de los diferentes productos y un texto en la parte superior correspondiente a la mesa leída en el código BIDI.
En el Activity correspondiente al lector de código Bidi se crea el código para que cuando lea el código Bidi pase a este nuevo Layout de la carta y nos pase el texto leído en el código BIDI



 








Fase 3: Creación BBDD “Productos” y tablas Refrescos, Cervezas y Batidos.
	
	Por medio de la aplicación PhpMyAdmin montada en mi máquina creo la BBDD Productos y las tablas Refrescos, Cervezas y Batidos.
Dichas tablas se componen de un código único (Clave Primaria) Nombre Producto y precio.


Tabla Refrescos

 

Tabla Cervezas

 

Tabla Batidos

 

Fase 4: Creación de la tabla pedido.

	En dicha tabla añadiremos los productos que el usuario añada a su pedido, se compone de las columnas: número de mesa, código de producto, nombre de producto, precio del producto, cantidad y total.
Numero mesa (el correspondiente al texto leído en el código Bidi)
Código Producto (Clave Foránea)
Precio (clave Foránea)
Cantidad (introducida por el usuario)
Total (Multiplicación automática por medio de la tabla de cantidad por precio)

Fase 5: Creación del Activity Cerveza:

	Creada la BBDD y las tablas se crea un Activity Cervezas el cual su Layout se compone de un LinearLayout en el cual en su interior se compone de un Listviev, para la creación por parte del código en Cerveza.class se le añade un item Layout  que contiene el Textview que por el que veremos las líneas de las diferentes cervezas. 

Código Cerveza.class: Esta clase es la encargada de la funcionabilidad de la misma, para ello por medio de JDBC, creamos la conexión con nuestra base de datos. Con una consulta a la BBDD por medio de los métodos PreparedStament recogemos los datos en una HaspMap (la clave será el código de producto) para recoger los datos del producto como son el nombre del producto y el precio ( se incluirán en un String separados por “:”), y una ArrayList que nos permitirá visualizar los productos en un adaptador con nuestra ListView. En dicho visualizado solo guardaremos la tabla “nombre de producto” para que sea mas vistosa para el usuario. 
Cuando el usuario seleccione alguno de los productos de la lista, leeremos su posición y por medio de un Switch obtendremos los datos del producto seleccionado. En este caso el argumento del Switch será la posición que el usuario selección, dentro de cada posición se genera el código en concreto para cada producto (precio, imagen…) Cada posición esta recogida inicialmente con un código de producto, dicho código de producto lo utilizaremos para recoger de nuestro HaspMap el nombre de producto y el precio. 
Cuando el usuario seleccione uno de los productos abrirá un Alert Dialog que le mostrara el nombre del producto, una foto del producto y el precio. El usuario podrá seleccionar la cantidad del producto deseado, si pulsa el botón cancelar cerrará el Alert Dialog y volverá a la lista, Si por medio de un NumberPicker deslizando con su dedo podrá seleccionar la cantidad, si la cantidad es distinta a 0 darle a añadir nos la añadirá a una BBDD de datos interna de SQLLITE. (mas adelante se explica la creación y métodos de la misma), dicho insert lo realizará por el método addProducto, recogerá los datos para pasarlos como argumento del número de mesa, id_prodroducto, nombre producto, precio, cantidad
-	Id_mesa: String recibido por medio del Activity carta y que será de uso general para toda la clase Cerveza.class
-	Id_producto: Se recoge de una String que se encuentra distinto en los diferentes casos del Switch dependiendo de la posición seleccionada por el usuario
-	Nombre producto: Lo recogeremos en nuestro array de visualizarporducto.get (posición), Dicha posicion lo establecemos de forma manual en cada uno de los casos del Switch. 
-	Precio : Se genera un método llamado obtenerPrecio(), Por medio de una búsqueda en nuestros HaspMap dándole como argumentó el cod_producto, dicho HaspMap nos devolverá una String que los separemos por medio de un método Split y dándole como argumento ”:”. En la segunda parte recogeremos el precio, haremos un casting de String a Int.
-	Cantidad: la cantidad la recogeremos de la selección del usuario en NumberPicker del Alert Dialog.

                      

Fase 6: Creación del Activity Carrito y clase Controlador.

En esta fase se incluye una base interna para poder visualizar los productos que el usuario añada a su pedido y que pueda ser visualizados, modificados o borrados antes de proceder a enviar dicho pedido.
Para ello necesitamos crear una clase controladorDB que extiende de SQLiteOpenHelper. Dicho controlador  establecemos :

	----------		CONTROLADORDB ---------- 


-	Creación de una base interna llamada Carrito
o	ID: Integer Primary Key AutoIncrement
o	Mesa: Text not null
o	Id_producto: text not null
o	Nombre producto: text not null
o	Precio: INT NOT NULL
o	Cantidad: INT NOT NULL
o	MEZCLA: TEXT NOT NULL 

-	Métodos addProducto. Este método cuando sea llamado le pasaremos como argumento el número de mesa, el id_producto, nombre del producto, la cantidad, precio.
Lo primero que realizamos con este método es realizar una búsqueda en nuestra BBDD interna para ver si existen ya algún registro con el nombre de dicho producto. Si dicho producto ya se encuentra en nuestra BBDD recogeremos la cantidad que tenemos guardándola en una variable y borraremos por el registro por medio del método borrarProducto(), dicho método se explica más adelante.
Sumaremos la cantidad que hemos recogido y la sumaremos la cantidad que hemos pasado como argumento en nuestro método addProducto.
Realizaremos una inserción en nuestra BBDD interna con todos los argumentos por medio del método .put(“nombre columna”, datos a introducir) 

-	Método numeroReg(). Método que se encarga de devolver el número de registros por medio de una sentencia select.

-	Método obtenerProductos(). Dicho método se encargara de recorrer nuetra BBDD interna y nos devolverá un String[] con los el nombre del producto y la cantidad de la misma

-	Método borrarPorducto(). Se encargara de buscar un producto que le pasemos como argumento al método y eliminarlo de nuestra BBDD interna.


-	Método borrarTodo(). Se encarga de borrar todas las líneas de nuestra BBDD interna y dejarla limpia. 

-	Método modificarCantidad(). Método que se encarga de recibir le nombre del producto y la cantidad recogida en el Alert Dialog y actualizar la base de datos interna con el nuevo valor


Fase 7: Pruebas

	Actualmente con el Activity de Cervezas, el Activity Carrito y el controlador, se realizan las primeras pruebas de funcionamiento y la corrección de errores.

	-Conecta con la BBDD externas y lista los productos 
	-Se puede clicar un producto y aparece el Alert Dialog con el nombre, el precio, la foto y la cantidad que deseamos. Si clicamos el botón añadir aparece en carrito los productos. 
	-En el carrito se pueden modificar las cantidades, eliminando si seleccionamos 0 unidades
	  










Fase 8: Implementación Activity batidos y refrescos.

	Se implementan los Activities batidos y refrescos, con una copia adaptada del Activity cerveza que está en funcionamiento.
	Se realizan las pruebas que en Fase 7

                     

















Fase 9: Creación Activity Shishas.
	
	Este Activity tiene la peculiaridad de que tiene dos opciones, y no es válida la forma de planteamiento de los Activities anteriores, en este caso es necesario un dos ExpandibleList, las cual necesitan su correspondientes .xml de grupo e hijos. Se realiza la adaptación de la misma con el código adaptado tanto a la nueva lectura de la BBDD externa como la presentación de la misma, esta vez ha sido necesaria un método creando() con la funcionabilidad de la misma. No era necesario en los casos anteriores con adaptador. Se realizan pruebas de funcionamiento hasta que todo está operativo
          






Fase 10: Creación Activity Combinado.

	La creación es similar a la Fase 9, con la necesidad de crear un nuevo Alert Dialog que nos permita la selección de la mezcla que deseamos nuestro combinado. 

	Tras las pruebas es necesario crear dos nuevos métodos en el controladorDB. 
-	Método modificarCantidadCombinado(). Funciona igual que el método modificaCantidad pero además tiene que recibir también la mezcla con la que esta el combinado para realizar la modificación, Si lo hiciéramos como el método anterior al modificar “barcelo” nos modifica la cantidad de todos los combinados Barcelo indistintamente de la mezcla
-	Método borrarProductoCombinado(). Se encargara de eliminar el producto que le pasaremos por argumento con el nombre de producto y la mezcla de nuestra BBDD interna.


Tanto en el caso del Activity correspondiente a la clase Shishas y Combinados el Switch recoge la información de la selección del usuario en vez de la posición como se realiza en el resto de Activities se recoge el nombre del producto elegido. Se realiza de las dos maneras para probar los dos funcionamientos de la misma

                  
                                                                                    





Fase 11: Creación clase insert

	Se crea una clase específica para la comunicación con la BBDD externa y el insert de los datos de nuestra BBDD interna del teléfono con el pedido
 Se hace el llamamiento de dicho objeto por medio del método enviarPedido() en la clase controladorDB

Se observa la inserción en la base de datos del pedido

 



                              

      Activity Carro con los produictos añadidos	Una vez enviamos el pedido nos salta un un mensaje con el total de dicho pedido y el total de la mesa (en la imagen solo se aprecia el total del dicha mesa





 Fase 12: Creación del Splash.

	Se crea un Splash de entrada a la aplicación

5.	Conclusiones/Resultado final del proyecto

La creación de dicha aplicación para el negocio que se esta desarrollando es muy útil como se expone al principio para evitar el contacto de cartas entre clientes. La versión manager, que permitirá al personal de barra recibir cada uno de los pedidos en tiempo real esta aun en desarrollo.
Dicha aplicación puede ser útil para ver el funcionamiento e la misma y generara una aplicación genérica para todos los negocios que deseen incluirse en el futuro proyecto. Con una app genérica leeríamos el código Bidi de la mesa y nos daría acceso a la carta del negocio en concreto para realizar el pedido.

Actualmente las pruebas realizadas han sido con 2 / 3 dipositivos móviles, aun asi el acceso a la base de datos desde el exterior a veces es demasiado lenta.


6.	Biografía

•	Conectar con la BBDD externa

https://www.youtube.com/watch?v=lM2vDPPx4Xg

•	Mostrar en ListView los datos recogidos en la consulta

Para esta parte me base en la parte de Acceso a Datos de realizar consultas y recoger los datos.
Implementarla en una ListView en el apartado del Programación Multimedia que creamos ListViev en la aplicación de “Mis Tareas”

•	Crear la Base de Datos con SQLITE para el carrito

Se sigue los pasos de la Actividad “Mis Tareas” de Programación Multimedia

•	Lector Bidi

https://www.codigos-qr.com/generador-de-codigos-qr/

•	ExpandableListview

https://www.journaldev.com/9942/android-expandablelistview-example-tutorial
