package notas

class Teoria {

    /*
                                VIEWS OCULTOS

        No todos lo view que existen se muestran en el menu del layout, sin embargo es posible
        acceder a ellos o crearlos en el codigo xml del layout, si se coloca un < el propio
        android muestra una ayuda en donde muestra todos los view que se pueden crear
        Algunos de ellos son lo siguientes



                                IMPLEMENTACION DE TERCEROS

        No solo los view que hay en el palette o los que se pueden crear en el propio codigo son todos los
        que podemos implementar en nuestro codigo
        Nosotros mismos podemos crear mas views asi como implementar view creados por la comunidad

        Para esto se puede buscar estos elementos en google, se puede probar suerte en github
        Se tiene que ser muy critico al implementar estos elementos ya que se tiene que ver que sea confiable,
        que este bien documentada la herramienta
        Ventajas
            Te ahorras mucho codigo por hacer
        Desventajas
            No siempre va a ser exactamente lo que quieres
            Para implementarlo se tiene que hacer referencia a una libreria la cual el auto puede borrar o modificar
                y si esto pasa tu proyecto deja de funcionar
            Para solucionar esto podemos copiar el codigo y agregarlo a nuestro propio gitHub, sin embargo se puede tener
            problemas con el autor ya que esto es robar codigo, depende de cada caso pero mejor no hacerlo

        Para implementar estas herramientas, se necesita colocar en el gradle (Module) la referencia a la libreria
        Se copia el elemento del la carpeta de layout



                                VIEW BINDING

        El view binding es otra forma de vincular el layout y sus elementos con el Activity
        Esta opcion es un poco mas moderna y puede acabar siendo la mas utilizada
        Con esta opcion ya no es necesario guardar en variables los objetos del layout a los que quremos hacer referencia
        Solamente con un binding se tiene acceso a las referencias del layout

        Para esto se tiene que:
        Habilitar el viewBinding en el grade(Module)
        Se configuar el Activity para utilizar el View Binding


     */
}