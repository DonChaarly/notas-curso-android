package notas

class Teoria {

    /*

                            LINEAR LAYOUT

    Este tipo de layout es de los mas basicos, tiene como principal caracteristica los elementos que
    contenga iran ordenados uno despues del otro
    Si la orientacion es horizonatal: El siguiente elemetno ira siempre al lado derecho del siguiente
    Si la orientacion es vertical: El siguiente elemento siemmpre ira debajo del anterior aunque
                                      tenga espacio al lado

            Responsive Desing en Linera Layout
    Debido a que la aplicacion puede abrirse ya sea en una tablet o cualquier celular es importante
    hacer una aplicacion con diseño responsivo el cual significa que los elementos del layout deben ajustarse
    a la pantalla del dispositivo
    Esto se hace mejor en un constrain layout pero tambien se puede hacer en un linear Layout de la siguiente forma:
        WeightSum: Se establece un peso en la etiqueta padre, esto es un valor el cual va a corresponder a la
                   suma del espacio que ocuparan los elementos dentro
        Layout_weight: Si se ocupa el weightSum, cada valor tendra un valor dependiendo de que espacio del total disponible en el WeihtSum tendra este elemento
                       Si no se ocupa un weightSum, Se puede especificar el porcentaje de espacio que se ocupara, esto puede ser en punto decimal o entero


                            RELATIVE LAYOUT

    El relative layout es un viewgroup en donde al colocar elementos dentro de el, por defecto, estos aparecen en la esquina superior
    izqueirda un arriba del otro
    Sin embargo, para poder posicionarlos en donde queramos se necesitan especificar dos cosas
        Una referencia: Se tiene que especificar como referencia otro elemnto
        Una Posicionalidad: Se especifica en que posicion con refencia al otro elemento que estamos especificando, queremos que se coloque

    En este view gruop no hay un responsive desing


                            CONSTRAIN LAYOUT

    Los constrain layout es un view group en donde es un poco mas libre como se colocal los elemntos, se puede manejar un poco mas el acomodo
    desde la interfaz del layout
    En los constrain layout se tiene que especificar dos referencias siempre, una con respecto a la vertical y otra a la horizontal
    Las referencias tiene que ser siempre en el mismo plano, osea, si se quiere referenciar un lado vertical de un elemento, se tiene que
    vincular con algun lado vertical de otro elemento.

            Distancias en el CosntrainLayout
    Se tienen dos tipos de distanciamiento en el constrain layout:
    Absoluto: Esta distancia con respecto ya sea al padre o otro elemento siempre sera la misma,
              aunque el layout se cambie a orizontal o a una pantalla mas grande, se mantendra esta cantidad de distancia siempre
              Se mostrata una flecha tipo continua en esta distancia
    Relativo: Esta distancia nos sirve para establecer un porcentaje de distancia con respecto a los dos espacios vacios que queden
              ya sea arriba y abajo o a la derecha e izquierda.
              Se mostrata una flecha tipo sierra en esta distancia
    Combinado: Una distancia absoluta y una relativa pueden ser combinadas

            Guias y Barreras en Cosntarin layout
    Las guias y barreras son lineas imaginarias a las cuales podemos referencias elementos, con lo cual podemos modificar la posicion
    de los elementos al mover estas lineas
    Guias: Las guias estan referenciadas con respecto al padre y no cambian son fijas
            Se tiene guias horizontales y verticales
            se tiene tres formas de especificar la posicion de la guia
                una distancia con respecto arriba o a la derecha
                una distancia con respecto a abajo o la izqueirda
                Un porcentaje de la distancia, con lo cual la guia se adaptara al tamaño de la pantalla, respetando este porcentaje
    Barreras: Estan referenciadas con respecto a un elemento con lo cual pueden modificarse

            Chains o cadenas
    Las cadenas son conjuntos de elemntos los cuales estan unidos los unos a los otros al ser referenciada su posicion con respecto al siguiente
    Se pueden hacer cadenas horizontales y verticales, en las horizotales el end de un elemento apuntara al start del siguiente
    en las verticales, el bottom de un elemento apuntara al top del otro
    hay tres tipos de estilos en las cadenas:
        spreed: Este estilo hace un reparto equitativo de los espacios entre elementos
        spreed_inside: Los elementos de los bordes se pegan al padre en sus bordes y el espacio restante se reparte entre los elementos que resten
        packed: Este estilo junta todos los elementos y los espacios restates se reparte en los lados

            End y Start
    El end y start Son equivalentes al right y Left, sin embargo es mas utilizado el end y Start Debido a que estos hacen mas versatil nuestra aplicacion
    ya que dependiendo del idioma que el usuario tenga configurado, puede que el inicio no siempre se corresponda con la derecha y el final con la izquierda
    como el caso del ebreo.
    Por ello con el end y start nuestra aplicacion cambiara en la pocision del inicio y fin dependiendo del idioma que el usuario tenga configurado


            Convertir layout a ConstraintLayuout
    Android da la facilidad de migrar cualquier tipo de layout a constraintLayout dando click derecho en la interfaz y seleccionado la opcion de Convert X to CosntraintLayout
    Nos aparece una ventana con dos opciones para selecionar:
        Flatten Layout Hierachy: Los layouts anidados tambien sera reconvertidos por lo que solo se tendra un solo constraintLayout sin layouts internos
        Dons flatten layouts referenced by id from other files: Si hay algun layout cuyo id este en el archivo de codigo este no se cambia


            Crear o Quitar Cosntarins
    Se pueden crear elemenentos view y no indicarles ninguna constrain y despues con el boton infer constraint Crear sus consatraint
    Si por algun motivo queremos quitar todas las constrains de todos los elementos podemos hacer esto con el boton Clear All Constraints
    Sin embargo no se recomienda crear las constrains a menos que el layout sea una estructura muy muy basica


                            TABLE LAYOUT

    Los table layout se utiliza para dar un formato de tabla en donde todos los hijos directos al padre seran filas las cuales ocuparan toodo el ancho
    Tambien se pueden configurar conlumnas y darle un orden asignandole el numero de columna


                            GRID LAYOUT

    Este layout utiliza una especie de cuadricula de filas y columnas en las que se puede trabajar de una forma muy ordenada


                            MODIFICAR EL LAYOUT AL ROTAR

    Dentro de un mismo layout se puede configurar la pantalla para que al momento de que el usuario rote la misma, esta tenga una diferente disposicion
    de sus elementos, que no simplemente se coloque los elementos abajo del otro
    Se puede configurar que algunos elementos esten a la derecha del otro como por ejemplo en spotify

    Para esto En el layout En la ventana de Desing, en el boton de Orientation for preview
    Se puede seleccionara Create Landscape Variation
    Tambien se tiene la posibilidad de crear una variacion para una tablet.






     */
}